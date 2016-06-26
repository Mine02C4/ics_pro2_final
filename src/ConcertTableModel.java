import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;

public class ConcertTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] columns = { "ID", "Name", "GenreID",
			"StartTime", "SiteID", "Price", "Capacity" };
	private ArrayList<Concert> sequentialConcerts = null;
	private Member user = null;

	public ConcertTableModel() {
		this(null);
	}

	public ConcertTableModel(Member member) {
		user = member;
		update();
		DataHost.Single().concerts.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				update();
				fireTableDataChanged();
			}
		});
	}

	public void update() {
		sequentialConcerts = DataHost.Single().concerts.getsequentialCollection();
		if (user != null) {
			ArrayList<Integer> concerts = DataHost.Single().tickets.GetConcertsFromMember(user);
			Iterator<Concert> itr = sequentialConcerts.iterator();
			while (itr.hasNext()) {
				Concert concert = itr.next();
				for (int id : concerts) {
					if (id == concert.getId()) {
						itr.remove();
						break;
					}
				}
			}
		}
	}

	public int getIdFromRowIndex(int rowIndex) {
		return sequentialConcerts.get(rowIndex).getId();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public int getRowCount() {
		return sequentialConcerts.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || getRowCount() < rowIndex - 1) {
			return null;
		}
		Concert concert = sequentialConcerts.get(rowIndex);

		if (concert == null)
			return null;
		switch (columnIndex) {
		case 0:
			return concert.getId();
		case 1:
			return concert.getName();
		case 2:
			return concert.getGenre();
		case 3:
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			return sdf.format(concert.getStartTime());
		case 4:
			return concert.getSite();
		case 5:
			return concert.getPrice();
		case 6:
			return concert.getCapacity();
		}
		return null;
	}

}
