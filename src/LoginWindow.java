import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class LoginWindow {

	private JFrame frmLogin;
	private DefaultComboBoxModel<Member> model;
	private JComboBox<Member> userDropdown;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 135);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout(0, 20));

		JLabel captionLabel = new JLabel("Select login mode");
		captionLabel.setFont(captionLabel.getFont().deriveFont(18f));
		frmLogin.getContentPane().add(captionLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frmLogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel userModePanel = new JPanel();
		panel.add(userModePanel);
		userModePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		model = new DefaultComboBoxModel<Member>(DataHost.Single().members.getsequentialCollection().toArray(new Member[0]));
		userDropdown = new JComboBox<Member>(model);
		userModePanel.add(userDropdown);
		
				JButton loginButton = new JButton("Login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						new UserWindow(model.getElementAt(userDropdown.getSelectedIndex()));
						frmLogin.dispose();
					}
				});
				userModePanel.add(loginButton);
				loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton manageButton = new JButton("Manager");
		panel.add(manageButton);
		manageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagementWindow();
				frmLogin.dispose();
			}
		});
		manageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

}
