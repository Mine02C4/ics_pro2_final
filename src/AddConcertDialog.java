import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AddConcertDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField genreField;
	private JLabel nameLabel;
	private JLabel genreLabel;
	private JLabel startTimeLabel;
	private JTextField startTimeField;
	private JLabel siteLabel;
	private JTextField siteField;
	private JTextField priceField;
	private JLabel priceLabel;

	/**
	 * Create the dialog.
	 */
	public AddConcertDialog() {
		setTitle("Add concert");
		setModal(true);
		setBounds(100, 100, 300, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{137, 137, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 30, 30, 30, 30, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			nameField = new JTextField();
			nameField.setColumns(10);
		}
		{
			nameLabel = new JLabel("Name");
		}
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.BOTH;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		contentPanel.add(nameLabel, gbc_nameLabel);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.BOTH;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		contentPanel.add(nameField, gbc_nameField);
		{
			genreField = new JTextField();
			genreField.setColumns(10);
		}
		{
			genreLabel = new JLabel("GenreID");
		}
		GridBagConstraints gbc_genreLabel = new GridBagConstraints();
		gbc_genreLabel.fill = GridBagConstraints.BOTH;
		gbc_genreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_genreLabel.gridx = 0;
		gbc_genreLabel.gridy = 1;
		contentPanel.add(genreLabel, gbc_genreLabel);
		GridBagConstraints gbc_genreField = new GridBagConstraints();
		gbc_genreField.fill = GridBagConstraints.BOTH;
		gbc_genreField.insets = new Insets(0, 0, 5, 0);
		gbc_genreField.gridx = 1;
		gbc_genreField.gridy = 1;
		contentPanel.add(genreField, gbc_genreField);
		{
			startTimeLabel = new JLabel("Start time");
			GridBagConstraints gbc_startTimeLabel = new GridBagConstraints();
			gbc_startTimeLabel.fill = GridBagConstraints.BOTH;
			gbc_startTimeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_startTimeLabel.gridx = 0;
			gbc_startTimeLabel.gridy = 2;
			contentPanel.add(startTimeLabel, gbc_startTimeLabel);
		}
		{
			startTimeField = new JTextField();
			GridBagConstraints gbc_startTimeField = new GridBagConstraints();
			gbc_startTimeField.fill = GridBagConstraints.BOTH;
			gbc_startTimeField.insets = new Insets(0, 0, 5, 0);
			gbc_startTimeField.gridx = 1;
			gbc_startTimeField.gridy = 2;
			contentPanel.add(startTimeField, gbc_startTimeField);
			startTimeField.setColumns(10);
		}
		{
			siteLabel = new JLabel("SiteID");
			GridBagConstraints gbc_siteLabel = new GridBagConstraints();
			gbc_siteLabel.fill = GridBagConstraints.BOTH;
			gbc_siteLabel.insets = new Insets(0, 0, 5, 5);
			gbc_siteLabel.gridx = 0;
			gbc_siteLabel.gridy = 3;
			contentPanel.add(siteLabel, gbc_siteLabel);
		}
		{
			siteField = new JTextField();
			GridBagConstraints gbc_siteField = new GridBagConstraints();
			gbc_siteField.fill = GridBagConstraints.BOTH;
			gbc_siteField.insets = new Insets(0, 0, 5, 0);
			gbc_siteField.gridx = 1;
			gbc_siteField.gridy = 3;
			contentPanel.add(siteField, gbc_siteField);
			siteField.setColumns(10);
		}
		{
			priceLabel = new JLabel("Price");
			GridBagConstraints gbc_priceLabel = new GridBagConstraints();
			gbc_priceLabel.fill = GridBagConstraints.BOTH;
			gbc_priceLabel.insets = new Insets(0, 0, 0, 5);
			gbc_priceLabel.gridx = 0;
			gbc_priceLabel.gridy = 4;
			contentPanel.add(priceLabel, gbc_priceLabel);
		}
		{
			priceField = new JTextField();
			GridBagConstraints gbc_priceField = new GridBagConstraints();
			gbc_priceField.fill = GridBagConstraints.BOTH;
			gbc_priceField.gridx = 1;
			gbc_priceField.gridy = 4;
			contentPanel.add(priceField, gbc_priceField);
			priceField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
