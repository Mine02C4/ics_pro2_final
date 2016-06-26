import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class DataHost implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String dbFileName = "db.bin";
	private static DataHost single = new DataHost();
	private static boolean isInitialized = false;

	public static DataHost Single() {
		if (!isInitialized) {
			// TODO: Load data
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFileName));
				single = (DataHost) ois.readObject();
				ois.close();
				single.Initialize();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			isInitialized = true;
		}
		return single;
	}

	private transient PropertyChangeListener saveListener;

	public Members members = new Members();
	public Concerts concerts = new Concerts();
	public Tickets tickets = new Tickets();

	public DataHost() {
		Initialize();
	}

	public void Initialize() {
		saveListener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Save();
			}
		};
		members.addPropertyChangeListener(saveListener);
		concerts.addPropertyChangeListener(saveListener);
		tickets.addPropertyChangeListener(saveListener);
	}

	public void Save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFileName));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Members extends Collection<Member> {
	private static final long serialVersionUID = 1L;
	private int nextId = 0;

	public void AddMember(String name) {
		Member member = new Member(nextId++, name);
		try {
			Add(member);
		} catch (IdConflictException e) {
			assert false;
		}
	}

	public void Delete(ArrayList<Integer> ids) {
		for (Integer i : ids) {
			DataHost.Single().tickets.DeleteFromMemberId(i);
		}
		super.Delete(ids);
	}
}

class Concerts extends Collection<Concert> {
	private static final long serialVersionUID = 1L;
	private int nextId = 0;

	public void AddConcert(String name, String genre, Date startTime, String site, int price, int capacity) {
		Concert concert = new Concert(nextId++, name, genre, startTime, site, price, capacity);
		try {
			Add(concert);
		} catch (IdConflictException e) {
			assert false;
		}
	}

	public void Delete(ArrayList<Integer> ids) {
		for (Integer i : ids) {
			DataHost.Single().tickets.DeleteFromConcertId(i);
		}
		super.Delete(ids);
	}
}

class Tickets implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public void AddTicket(int memberId, int concertId) {
		tickets.add(new Ticket(memberId, concertId));
		changes.firePropertyChange("tickets", null, null);
	}

	public void Delete(int memberId, int concertId) {
		Iterator<Ticket> itr = tickets.iterator();
		while (itr.hasNext()) {
			Ticket ticket = itr.next();
			if (ticket.getMemberId() == memberId && ticket.getConcertId() == concertId) {
				itr.remove();
			}
		}
		changes.firePropertyChange("tickets", null, null);
	}

	public void DeleteFromMemberId(int memberId) {
		Iterator<Ticket> itr = tickets.iterator();
		while (itr.hasNext()) {
			Ticket ticket = itr.next();
			if (ticket.getMemberId() == memberId) {
				itr.remove();
			}
		}
		changes.firePropertyChange("tickets", null, null);
	}

	public void DeleteFromConcertId(int concertId) {
		Iterator<Ticket> itr = tickets.iterator();
		while (itr.hasNext()) {
			Ticket ticket = itr.next();
			if (ticket.getConcertId() == concertId) {
				itr.remove();
			}
		}
		changes.firePropertyChange("tickets", null, null);
	}

	public ArrayList<Integer> GetConcertsFromMember(Member member) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Ticket ticket : tickets) {
			if (ticket.getMemberId() == member.getId()) {
				result.add(ticket.getConcertId());
			}
		}
		return result;
	}

	public ArrayList<Integer> GetMembersFromConcert(Concert concert) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Ticket ticket : tickets) {
			if (ticket.getConcertId() == concert.getId()) {
				result.add(ticket.getMemberId());
			}
		}
		return result;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		changes = new PropertyChangeSupport(this);
	}
}

abstract class Collection<T extends HasId> implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, T> elements = new HashMap<Integer, T>();
	private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);

	protected void Add(T elem) throws IdConflictException {
		if (elements.containsKey(elem.getId()))
			throw new IdConflictException();
		elements.put(elem.getId(), elem);
		changes.firePropertyChange("elements", null, null);
	}

	public T Get(int id) {
		return elements.get(id);
	}

	protected void Delete(ArrayList<Integer> ids) {
		for (Integer i : ids) {
			elements.remove(i);
		}
		changes.firePropertyChange("elements", null, null);
	}

	public void Update(T elem) {
		if (elements.containsKey(elem.getId())) {
			elements.put(elem.getId(), elem);
		}
		changes.firePropertyChange("elements", null, null);
	}

	public ArrayList<T> getsequentialCollection() {
		ArrayList<T> result = new ArrayList<T>(elements.size());
		for (T t : elements.values()) {
			result.add(t);
		}
		return result;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		changes = new PropertyChangeSupport(this);
	}
}

interface HasId {
	int getId();
}
