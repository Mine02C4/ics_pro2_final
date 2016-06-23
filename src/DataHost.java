import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataHost implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String dbFileName = "db.txt";
	private static DataHost single = new DataHost();

	public static DataHost Single() {
		return single;
	}

	public Members members = new Members();
	public Concerts concerts = new Concerts();
	public Tickets tickets = new Tickets();

	public DataHost() {
		// TODO: Load data
	}

	public void Save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFileName));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
}

class Concerts extends Collection<Concert> {
	private static final long serialVersionUID = 1L;
	private int nextId = 0;

	public void AddConcert(String name, int genreId, Date startTime, int siteId, int price) {
		Concert concert = new Concert(nextId++, name, genreId, startTime, siteId, price);
		try {
			Add(concert);
		} catch (IdConflictException e) {
			assert false;
		}
	}
}

class Tickets implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public void AddTicket(int memberId, int concertId) {
		tickets.add(new Ticket(memberId, concertId));
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
}

abstract class Collection<T extends HasId> implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, T> elements = new HashMap<Integer, T>();

	protected void Add(T elem) throws IdConflictException {
		if (elements.containsKey(elem.getId()))
			throw new IdConflictException();
		elements.put(elem.getId(), elem);
	}

	public ArrayList<T> getsequentialCollection() {
		ArrayList<T> result = new ArrayList<T>(elements.size());
		for (T t : elements.values()) {
			result.add(t);
		}
		return result;
	}
}

interface HasId {
	int getId();
}
