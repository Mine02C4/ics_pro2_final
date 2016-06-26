import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] columns = { "ID", "Name" };
	private ArrayList<Member> sequentialMembers = null;
	private Concert concert = null;

	public MemberTableModel() {
		this(null);
	}

	public MemberTableModel(Concert con) {
		concert = con;
		update();
		DataHost.Single().members.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				update();
				fireTableDataChanged();
			}
		});
	}

	private void update() {
		if (concert == null) {
			sequentialMembers = DataHost.Single().members.getsequentialCollection();
		} else {
			ArrayList<Integer> members = DataHost.Single().tickets.GetMembersFromConcert(concert);
			sequentialMembers = new ArrayList<Member>();
			for (Integer i : members) {
				sequentialMembers.add(DataHost.Single().members.Get(i));
			}
		}
	}

	public int getIdFromRowIndex(int rowIndex) {
		return sequentialMembers.get(rowIndex).getId();
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
		return sequentialMembers.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || getRowCount() < rowIndex - 1) {
			return null;
		}
		Member member = sequentialMembers.get(rowIndex);

		if (member == null)
			return null;
		switch (columnIndex) {
		case 0:
			return member.getId();
		case 1:
			return member.getName();
		}
		return null;
	}

}
