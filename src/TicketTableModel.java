import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TicketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] columns = { "ID", "Name", "GenreID",
			"StartTime", "SiteID", "Price", "Capacity" };
	private Member member;
	ArrayList<Concert> concertList = new ArrayList<Concert>();

	public TicketTableModel(Member member) {
		this.member = member;
		update();
		DataHost.Single().tickets.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				update();
				fireTableDataChanged();
			}
		});
	}

	public void update() {
		concertList.clear();
		ArrayList<Integer> concerts = DataHost.Single().tickets.GetConcertsFromMember(member);
		for (Integer id : concerts) {
			concertList.add(DataHost.Single().concerts.Get(id));
		}
	}
	
	public int getIdFromRowIndex(int rowIndex) {
		return concertList.get(rowIndex).getId();
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
		return concertList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || getRowCount() < rowIndex - 1) {
			return null;
		}
		Concert concert = concertList.get(rowIndex);

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
			return concert.getStartTime().toString();
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
