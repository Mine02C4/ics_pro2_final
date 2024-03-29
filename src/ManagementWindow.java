import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ManagementWindow {

	private JFrame frmMainwindow;
	private JScrollPane memberScroll;
	private JTable memberTable;
	private JScrollPane concertScroll;
	private JTable concertTable;
	private JPanel memberControlPanel;
	private JButton addMemberButton;
	private JButton editMemberButton;
	private JButton deleteMemberButton;
	private JPanel memberPanel;
	private JPanel concertPanel;
	private JPanel concertControlPanel;
	private JButton addConcertButton;
	private JButton editConcertButton;
	private JButton deleteConcertButton;
	private MemberTableModel memberTableModel;
	private ConcertTableModel concertTableModel;
	private JLabel memberLabel;
	private JLabel concertLabel;
	private JButton detailButton;

	/**
	 * Create the application.
	 */
	public ManagementWindow() {
		initialize();
		frmMainwindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainwindow = new JFrame();
		frmMainwindow.setTitle("Management Window");
		frmMainwindow.setBounds(100, 100, 720, 400);
		frmMainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainwindow.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		memberPanel = new JPanel();
		frmMainwindow.getContentPane().add(memberPanel);
		memberPanel.setLayout(new BorderLayout(0, 0));

		memberScroll = new JScrollPane();
		memberScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		memberPanel.add(memberScroll, BorderLayout.CENTER);

		memberTable = new JTable();
		memberTableModel = new MemberTableModel();
		memberTable.setModel(memberTableModel);
		memberTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				int count = memberTable.getSelectedRowCount();
				if (count == 1) {
					editMemberButton.setEnabled(true);
				} else {
					editMemberButton.setEnabled(false);
				}
				if (count > 0) {
					deleteMemberButton.setEnabled(true);
				} else {
					deleteMemberButton.setEnabled(false);
				}
			}
		});
		memberScroll.setViewportView(memberTable);

		memberControlPanel = new JPanel();
		memberPanel.add(memberControlPanel, BorderLayout.EAST);
		memberControlPanel.setLayout(new BoxLayout(memberControlPanel, BoxLayout.Y_AXIS));

		addMemberButton = new JButton("Add member");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMemberDialog dialog = new AddMemberDialog();
				dialog.setVisible(true);
			}
		});
		addMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(addMemberButton);

		editMemberButton = new JButton("Edit member");
		editMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = memberTable.getSelectedRow();
				if (rowIndex >= 0) {
					int memberId = memberTableModel.getIdFromRowIndex(rowIndex);
					Member member = DataHost.Single().members.Get(memberId);
					EditMemberDialog dialog = new EditMemberDialog(member);
					dialog.setVisible(true);
				}
			}
		});
		editMemberButton.setEnabled(false);
		editMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(editMemberButton);

		deleteMemberButton = new JButton("Delete member");
		deleteMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (memberTable.getSelectedColumnCount() > 0) {
					ArrayList<Integer> deleteMenberIdList = new ArrayList<Integer>();
					ListSelectionModel lsm = memberTable.getSelectionModel();
					for (int i = lsm.getMinSelectionIndex(); i <= lsm.getMaxSelectionIndex(); i++) {
						if (lsm.isSelectedIndex(i)) {
							deleteMenberIdList.add(memberTableModel.getIdFromRowIndex(i));
						}
					}
					DataHost.Single().members.Delete(deleteMenberIdList);
				}
			}
		});
		deleteMemberButton.setEnabled(false);
		deleteMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(deleteMemberButton);

		memberLabel = new JLabel("Member management");
		memberLabel.setFont(memberLabel.getFont().deriveFont(memberLabel.getFont().getStyle() | Font.BOLD, 15f));
		memberPanel.add(memberLabel, BorderLayout.NORTH);

		concertPanel = new JPanel();
		frmMainwindow.getContentPane().add(concertPanel);
		concertPanel.setLayout(new BorderLayout(0, 0));

		concertLabel = new JLabel("Concert management");
		concertLabel.setFont(concertLabel.getFont().deriveFont(concertLabel.getFont().getStyle() | Font.BOLD, 15f));
		concertPanel.add(concertLabel, BorderLayout.NORTH);

		concertScroll = new JScrollPane();
		concertScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		concertPanel.add(concertScroll, BorderLayout.CENTER);

		concertTable = new JTable();
		concertTableModel = new ConcertTableModel();
		concertTable.setModel(concertTableModel);
		concertTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				int count = concertTable.getSelectedRowCount();
				if (count == 1) {
					editConcertButton.setEnabled(true);
					detailButton.setEnabled(true);
				} else {
					editConcertButton.setEnabled(false);
					detailButton.setEnabled(false);
				}
				if (count > 0) {
					deleteConcertButton.setEnabled(true);
				} else {
					deleteConcertButton.setEnabled(false);
				}
			}
		});
		concertScroll.setViewportView(concertTable);

		concertControlPanel = new JPanel();
		concertPanel.add(concertControlPanel, BorderLayout.EAST);
		concertControlPanel.setLayout(new BoxLayout(concertControlPanel, BoxLayout.Y_AXIS));

		addConcertButton = new JButton("Add concert");
		addConcertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddConcertDialog dialog = new AddConcertDialog();
				dialog.setVisible(true);
			}
		});
		addConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(addConcertButton);

		editConcertButton = new JButton("Edit concert");
		editConcertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = concertTable.getSelectedRow();
				if (rowIndex >= 0) {
					int concertId = concertTableModel.getIdFromRowIndex(rowIndex);
					Concert concert = DataHost.Single().concerts.Get(concertId);
					EditConcertDialog dialog = new EditConcertDialog(concert);
					dialog.setVisible(true);
				}
			}
		});
		editConcertButton.setEnabled(false);
		editConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(editConcertButton);

		deleteConcertButton = new JButton("Delete concert");
		deleteConcertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concertTable.getSelectedColumnCount() > 0) {
					ArrayList<Integer> deleteConcertIdList = new ArrayList<Integer>();
					ListSelectionModel lsm = concertTable.getSelectionModel();
					for (int i = lsm.getMinSelectionIndex(); i <= lsm.getMaxSelectionIndex(); i++) {
						if (lsm.isSelectedIndex(i)) {
							deleteConcertIdList.add(concertTableModel.getIdFromRowIndex(i));
						}
					}
					DataHost.Single().concerts.Delete(deleteConcertIdList);
				}
			}
		});

		detailButton = new JButton("Show detail");
		detailButton.setEnabled(false);
		detailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = concertTable.getSelectedRow();
				if (rowIndex >= 0) {
					int concertId = concertTableModel.getIdFromRowIndex(rowIndex);
					Concert concert = DataHost.Single().concerts.Get(concertId);
					ShowDetailDialog dialog = new ShowDetailDialog(concert);
					dialog.setVisible(true);
				}
			}
		});
		detailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(detailButton);
		deleteConcertButton.setEnabled(false);
		deleteConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(deleteConcertButton);
	}

}
