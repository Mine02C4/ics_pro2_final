import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ReserveTicketDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable concertTable;
	private ConcertTableModel concertTableModel;
	private JButton okButton;
	private Member member;

	/**
	 * Create the dialog.
	 */
	public ReserveTicketDialog(Member user) {
		this.member = user;
		setModal(true);
		setTitle("Reserve concert");
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblSelectConcert = new JLabel("Select a concert");
			contentPanel.add(lblSelectConcert, BorderLayout.NORTH);
			lblSelectConcert.setFont(lblSelectConcert.getFont().deriveFont(lblSelectConcert.getFont().getStyle() | Font.BOLD, 15f));
		}
		{
			JScrollPane concertScroll = new JScrollPane();
			contentPanel.add(concertScroll, BorderLayout.CENTER);
			{
				concertTable = new JTable();
				concertTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				concertTableModel = new ConcertTableModel(user);
				concertTable.setModel(concertTableModel);
				concertTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						if (concertTable.getSelectedRowCount() == 1) {
							okButton.setEnabled(true);
						} else {
							okButton.setEnabled(false);
						}
					}

				});
				concertScroll.setViewportView(concertTable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowIndex = concertTable.getSelectedRow();
						if (rowIndex >= 0) {
							int concertId = concertTableModel.getIdFromRowIndex(rowIndex);
							DataHost.Single().tickets.AddTicket(member.getId(), concertId);
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
