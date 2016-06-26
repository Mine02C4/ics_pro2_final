import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserWindow {

	private JFrame frmUserWindow;
	private Member user;
	private JTable table;
	private TicketTableModel ticketTableModel;
	private JButton cancelButton;

	/**
	 * Create the application.
	 */
	public UserWindow(Member user) {
		this.user = user;
		initialize();
		frmUserWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserWindow = new JFrame();
		frmUserWindow.setTitle("User window");
		frmUserWindow.setBounds(100, 100, 600, 300);
		frmUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmUserWindow.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel usernameLabel = new JLabel("Your name :");
		usernameLabel.setFont(usernameLabel.getFont().deriveFont(25f));
		panel.add(usernameLabel);

		JLabel nameLabel = new JLabel(user.getName());
		nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | Font.BOLD, 25f));
		panel.add(nameLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frmUserWindow.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		ticketTableModel = new TicketTableModel(user);
		table.setModel(ticketTableModel);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				if (table.getSelectedRowCount() > 0) {
					cancelButton.setEnabled(true);
				} else {
					cancelButton.setEnabled(false);
				}
			}

		});
		scrollPane.setViewportView(table);

		JPanel buttonPanel = new JPanel();
		frmUserWindow.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton reserveButton = new JButton("Reserve concert");
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveTicketDialog dialog = new ReserveTicketDialog(user);
				dialog.setVisible(true);
			}
		});
		buttonPanel.add(reserveButton);

		cancelButton = new JButton("Cancel concert");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedColumnCount() > 0) {
					ListSelectionModel lsm = table.getSelectionModel();
					for (int i = lsm.getMinSelectionIndex(); i <= lsm.getMaxSelectionIndex(); i++) {
						if (lsm.isSelectedIndex(i)) {
							DataHost.Single().tickets.Delete(user.getId(), ticketTableModel.getIdFromRowIndex(i));
						}
					}
				}
			}
		});
		cancelButton.setEnabled(false);
		buttonPanel.add(cancelButton);
	}

}
