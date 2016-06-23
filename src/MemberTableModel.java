import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MemberTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] columns = { "ID", "Name" };
	private ArrayList<Member> sequentialMembers = null;

	public MemberTableModel() {
		update();
		DataHost.Single().members
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						// TODO Auto-generated method stub
						update();
						fireTableDataChanged();
					}

				});
	}

	public void update() {
		sequentialMembers = DataHost.Single().members.getsequentialCollection();
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
