import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMainwindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		EntryPoint.InitializeTestData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainwindow = new JFrame();
		frmMainwindow.setTitle("Management Window");
		frmMainwindow.setBounds(100, 100, 450, 300);
		frmMainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainwindow.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		memberPanel = new JPanel();
		frmMainwindow.getContentPane().add(memberPanel);
		memberPanel.setLayout(new BorderLayout(0, 0));
		
		memberScroll = new JScrollPane();
		memberPanel.add(memberScroll, BorderLayout.CENTER);
		memberScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		memberTable = new JTable();
		memberTable.setModel(new MemberTableModel());
		memberTable.setEnabled(false);
		memberScroll.setViewportView(memberTable);
		
		memberControlPanel = new JPanel();
		memberPanel.add(memberControlPanel, BorderLayout.EAST);
		memberControlPanel.setLayout(new BoxLayout(memberControlPanel, BoxLayout.Y_AXIS));
		
		addMemberButton = new JButton("Add member");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(addMemberButton);
		
		editMemberButton = new JButton("Edit member");
		editMemberButton.setEnabled(false);
		editMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(editMemberButton);
		
		deleteMemberButton = new JButton("Delete member");
		deleteMemberButton.setEnabled(false);
		deleteMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		memberControlPanel.add(deleteMemberButton);
		
		concertPanel = new JPanel();
		frmMainwindow.getContentPane().add(concertPanel);
		concertPanel.setLayout(new BorderLayout(0, 0));
		
		concertScroll = new JScrollPane();
		concertPanel.add(concertScroll, BorderLayout.CENTER);
		
		concertTable = new JTable();
		concertTable.setModel(new ConcertTableModel());
		concertScroll.setViewportView(concertTable);
		
		concertControlPanel = new JPanel();
		concertPanel.add(concertControlPanel, BorderLayout.EAST);
		concertControlPanel.setLayout(new BoxLayout(concertControlPanel, BoxLayout.Y_AXIS));
		
		addConcertButton = new JButton("Add concert");
		addConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(addConcertButton);
		
		editConcertButton = new JButton("Edit concert");
		editConcertButton.setEnabled(false);
		editConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(editConcertButton);
		
		deleteConcertButton = new JButton("Delete concert");
		deleteConcertButton.setEnabled(false);
		deleteConcertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		concertControlPanel.add(deleteConcertButton);
	}

}
