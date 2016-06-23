import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ConcertTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] columns = { "ID", "Name", "GenreID",
			"StartTime", "SiteID", "Price" };
	private ArrayList<Concert> sequentialConcerts = null;

	public ConcertTableModel() {
		update();
	}

	public void update() {
		sequentialConcerts = DataHost.Single().concerts.getsequentialCollection();
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
			return concert.getGenreId();
		case 3:
			return concert.getStartTime().toString();
		case 4:
			return concert.getSiteId();
		case 5:
			return concert.getPrice();
		}
		return null;
	}

}
