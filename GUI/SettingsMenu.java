package GUI;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import code.SaveSettings;
import code.Configs;
import code.SuffixHandler;
import code.Validate;

public class SettingsMenu extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField srcInput, destInput, backupInterval, txtSuffix;
	private JButton srcBrowse, destBrowse, btnApply, btnSaveConfiguration,
			btnCancel, btnHelp, btnLoad;
	private JToggleButton backupsAmountToggle, oldestBackupToggle;
	private JSpinner backupsAmount, deleteOldestSpinner;
	private String newConfigName;
	private JComboBox<String> configDropDown, askDropDown;
	private Configs configs = new Configs();
	private MainGUI parent;
	private JTextField txtFilePreview;
	private UpdatePreview previewUpdate;
	private JLabel suffixValidDisplay;
	private JLabel srcValidDisplay;
	private JLabel destValidDisplay;
	private JLabel intervalValidDisplay;
	private JButton btnDelete;

	public SettingsMenu(MainGUI parent) {
		this.setTitle("Settings Menu");
		this.setSize(360, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(10, 0));

		this.parent = parent;

		previewUpdate = new UpdatePreview(this);
		previewUpdate.execute();

		configs.addDefault();

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);

		JLabel lblSavedConfigurations = new JLabel("Saved Configurations:");
		panel_3.add(lblSavedConfigurations);

		configDropDown = new JComboBox<String>(configs.getNames());
		configDropDown.setSelectedItem("Default");
		panel_3.add(configDropDown);

		btnLoad = new JButton("Load");
		panel_3.add(btnLoad);
		
		btnDelete = new JButton("Delete");
		panel_3.add(btnDelete);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JLabel lblSource = new JLabel("Source:");
		panel.add(lblSource);

		srcInput = new JTextField();
		srcInput.setText("C:\\");
		srcInput.setColumns(10);
		panel.add(srcInput);

		srcBrowse = new JButton("Browse");
		panel.add(srcBrowse);
		
		srcValidDisplay = new JLabel("Not Checked");
		panel.add(srcValidDisplay);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JLabel lblDestination = new JLabel("Destination:");
		panel_1.add(lblDestination);

		destInput = new JTextField();
		destInput.setText("D:\\");
		panel_1.add(destInput);
		destInput.setColumns(10);

		destBrowse = new JButton("Browse");
		panel_1.add(destBrowse);
		
		destValidDisplay = new JLabel("Not Checked");
		panel_1.add(destValidDisplay);

		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);

		JLabel IntervalLabel = new JLabel("Backup Every: [hh:mm:ss]");
		panel_4.add(IntervalLabel);

		backupInterval = new JTextField();
		backupInterval.setText("00:20:00");
		backupInterval.setColumns(10);
		panel_4.add(backupInterval);
		
		intervalValidDisplay = new JLabel("Not Checked");
		panel_4.add(intervalValidDisplay);

		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);

		JLabel lblAmountOfBackups = new JLabel("Amount of Backups:");
		panel_5.add(lblAmountOfBackups);

		backupsAmount = new JSpinner();
		backupsAmount.setEnabled(false);
		panel_5.add(backupsAmount);

		backupsAmountToggle = new JToggleButton("Disabled");
		panel_5.add(backupsAmountToggle);

		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6);

		JLabel lblDeleteOldestBackup = new JLabel("Delete Oldest Backup After:");
		panel_6.add(lblDeleteOldestBackup);

		deleteOldestSpinner = new JSpinner();
		deleteOldestSpinner.setEnabled(false);
		panel_6.add(deleteOldestSpinner);

		oldestBackupToggle = new JToggleButton("Disabled");
		panel_6.add(oldestBackupToggle);

		JPanel panel_7 = new JPanel();
		getContentPane().add(panel_7);

		JLabel lblAskBeforeOverwrite = new JLabel("Ask Before Overwrite:");
		panel_7.add(lblAskBeforeOverwrite);

		askDropDown = new JComboBox<String>();
		askDropDown.addItem("Yes");
		askDropDown.addItem("No");
		panel_7.add(askDropDown);

		JPanel panel_8 = new JPanel();
		getContentPane().add(panel_8);

		JLabel lblSuffix = new JLabel("Suffix:");
		panel_8.add(lblSuffix);

		txtSuffix = new JTextField();
		txtSuffix.setText("<HH-mm-ss>");
		txtSuffix.setColumns(10);
		panel_8.add(txtSuffix);
		
		suffixValidDisplay = new JLabel("Not Checked");
		panel_8.add(suffixValidDisplay);
		

		JPanel panel_9 = new JPanel();
		getContentPane().add(panel_9);

		JLabel lblPreview = new JLabel("Preview:");
		panel_9.add(lblPreview);

		txtFilePreview = new JTextField();
		txtFilePreview.setEditable(false);
		txtFilePreview.setText("[Preview]");
		panel_9.add(txtFilePreview);
		txtFilePreview.setColumns(30);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);

		btnApply = new JButton("Apply");
		panel_2.add(btnApply);

		btnSaveConfiguration = new JButton("Save Configuration");
		panel_2.add(btnSaveConfiguration);

		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);

		btnHelp = new JButton("Help");
		panel_2.add(btnHelp);

		AddActList(srcBrowse, destBrowse, oldestBackupToggle, btnApply,
				btnSaveConfiguration, btnCancel, btnHelp, backupsAmountToggle,
				btnLoad, btnDelete);
		
		
		srcInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateSourceValid();
				checkIfAllValid();
			}

			public void removeUpdate(DocumentEvent e) {
				updateSourceValid();
				checkIfAllValid();
			}

			public void insertUpdate(DocumentEvent e) {
				updateSourceValid();
				checkIfAllValid();
			}

			private void updateSourceValid() {
				
				if (new Validate().checkSourceInput(srcInput.getText().trim())){
					srcValidDisplay.setText("Valid");
					srcValidDisplay.setForeground(new Color(34,139,34));
				}
				else{
					srcValidDisplay.setText("Invalid");
					srcValidDisplay.setForeground(Color.red);
				}
			}
		});
		
		
		
		destInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateDestValid();
				checkIfAllValid();
			}

			public void removeUpdate(DocumentEvent e) {
				updateDestValid();
				checkIfAllValid();
			}

			public void insertUpdate(DocumentEvent e) {
				updateDestValid();
				checkIfAllValid();
			}

			private void updateDestValid() {
				
				if (new Validate().checkSourceInput(destInput.getText().trim())){
					destValidDisplay.setText("Valid");
					destValidDisplay.setForeground(new Color(34,139,34));
				}
				else{
					destValidDisplay.setText("Invalid");
					destValidDisplay.setForeground(Color.red);
				}
			}
		});
		
		backupInterval.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateIntervalValid();
				checkIfAllValid();
			}

			public void removeUpdate(DocumentEvent e) {
				updateIntervalValid();
				checkIfAllValid();
			}

			public void insertUpdate(DocumentEvent e) {
				updateIntervalValid();
				checkIfAllValid();
			}

			private void updateIntervalValid() {
				
				if (new Validate().checkTimeInput(backupInterval.getText().trim())){
					intervalValidDisplay.setText("Valid");
					intervalValidDisplay.setForeground(new Color(34,139,34));
				}
				else{
					intervalValidDisplay.setText("Invalid");
					intervalValidDisplay.setForeground(Color.red);
				}
			}
		});

		txtSuffix.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updatePreview();
				updateSuffixValid();
				checkIfAllValid();
			}

			public void removeUpdate(DocumentEvent e) {
				updatePreview();
				updateSuffixValid();
				checkIfAllValid();
			}

			public void insertUpdate(DocumentEvent e) {
				updatePreview();
				updateSuffixValid();
				checkIfAllValid();
			}
			
			private void updateSuffixValid() {
				if (new Validate().checkSuffixInput(txtSuffix.getText().trim())){
					suffixValidDisplay.setText("Valid");
					suffixValidDisplay.setForeground(new Color(34,139,34));
				}
				else{
					suffixValidDisplay.setText("Invalid");
					suffixValidDisplay.setForeground(Color.red);
				}
			}
		});
		
		//Forces DocumentListener to run once
		srcInput.setText(srcInput.getText());
		destInput.setText(destInput.getText());
		backupInterval.setText(backupInterval.getText());
		txtSuffix.setText(txtSuffix.getText());

		setVisible(true);
	}
	
	private void checkIfAllValid(){
		
		boolean valid = true;
		
		for (JLabel label: new JLabel[] {srcValidDisplay, destValidDisplay, intervalValidDisplay, suffixValidDisplay})
			if (!label.getText().equals("Valid"))
				valid = false;
		
		if (valid){
			btnApply.setEnabled(true);
			btnSaveConfiguration.setEnabled(true);
		}
		else{
			btnApply.setEnabled(false);
			btnSaveConfiguration.setEnabled(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source = ae.getSource();

		if (source == btnLoad) {

			String[] settings = configs.getConfigSettings(configDropDown
					.getSelectedItem().toString().trim());
			say(Arrays.toString(settings));

			srcInput.setText(settings[0]);
			destInput.setText(settings[1]);
			backupInterval.setText(settings[2]);
			txtSuffix.setText(settings[3]);

			if (settings[4].equals("Enabled")) {
				backupsAmountToggle.setSelected(true);
				backupsAmountToggle.setText("Enabled");
				backupsAmount.setEnabled(true);
			} else {
				backupsAmountToggle.setSelected(false);
				backupsAmountToggle.setText("Disabled");
				backupsAmount.setEnabled(false);
			}

			if (settings[5].equals("Enabled")) {
				oldestBackupToggle.setSelected(true);
				oldestBackupToggle.setText("Enabled");
				deleteOldestSpinner.setEnabled(true);
			} else {
				oldestBackupToggle.setSelected(false);
				oldestBackupToggle.setText("Disabled");
				deleteOldestSpinner.setEnabled(false);
			}
			backupsAmount.setValue(Integer.parseInt(settings[6]));
			deleteOldestSpinner.setValue(Integer.parseInt(settings[7]));
			askDropDown.setSelectedIndex(Integer.parseInt(settings[8]));

		} 
		else if (source == btnDelete) {
			new ConfigDeleteMenu();
		}
		else if (source == srcBrowse) {
			JFileChooser srcChooser = new JFileChooser();
			srcChooser.setDialogTitle("Choose a Source File/Folder");
			srcChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			srcChooser.showOpenDialog(null);
			srcInput.setText(srcChooser.getSelectedFile().toString());

		} else if (source == destBrowse) {
			JFileChooser destChooser = new JFileChooser();
			destChooser.setDialogTitle("Choose a Destination Folder");
			destChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			destChooser.showOpenDialog(null);
			destInput.setText(destChooser.getSelectedFile().toString());
		} else if (source == oldestBackupToggle) {

			if (oldestBackupToggle.isSelected()) {
				oldestBackupToggle.setText("Enabled");
				deleteOldestSpinner.setEnabled(true);
			} else {
				oldestBackupToggle.setText("Disabled");
				deleteOldestSpinner.setEnabled(false);
			}
		} else if (source == backupsAmountToggle) {
			if (backupsAmountToggle.isSelected()) {
				backupsAmountToggle.setText("Enabled");
				backupsAmount.setEnabled(true);
			} else {
				backupsAmountToggle.setText("Disabled");
				backupsAmount.setEnabled(false);
			}
		} else if (source == btnApply) {

			parent.getSrcField().setText(srcInput.getText().trim());
			parent.getDestField().setText(destInput.getText().trim());
			parent.getBackupInterval().setText(backupInterval.getText().trim());
			parent.getTimeLeft().setText(backupInterval.getText().trim());
			parent.getBackupsLeft().setText(
					(backupsAmountToggle.isSelected() ? backupsAmount
							.getValue().toString() : "Disabled"));
			parent.getElapsedTime().setText("00:00:00");
			parent.getMaxCopies().setText(
					(oldestBackupToggle.isSelected() ? deleteOldestSpinner
							.getValue().toString() : "Disabled"));
			parent.getAsk().setText(askDropDown.getSelectedItem().toString());
			parent.getSuffix().setText(txtSuffix.getText().trim());

			previewUpdate.cancel(true);
			this.dispose();
		} else if (source == btnSaveConfiguration) {
			
			getConfigName();

			if (newConfigName != null) {
				SaveSettings save = new SaveSettings(newConfigName.trim());
				save.addJTextFields(srcInput, destInput, backupInterval,
						txtSuffix);
				save.addToggles(backupsAmountToggle, oldestBackupToggle);
				save.addSpinners(backupsAmount, deleteOldestSpinner);
				save.addDropDowns(askDropDown);
				save.close();

				// Update Config dropdown
				configDropDown.removeAllItems();
				for (String names : configs.getNames())
					configDropDown.addItem(names);
				configDropDown.setSelectedItem(newConfigName);
			}
		} else if (source == btnCancel) {
			previewUpdate.cancel(true);
			this.dispose();
		} else if (source == btnHelp) {
			new HelpMenu().setVisible(true);
		}
	}

	private void getConfigName() {
		newConfigName = JOptionPane
				.showInputDialog("What would you like to name this Configuration?");
		
		for (String name : new Configs().getNames())
		{
			if (name.equals(newConfigName)){
				if (JOptionPane.showConfirmDialog(null, newConfigName + " Already exists. Do you want to overwrite it ?") == JOptionPane.YES_OPTION){
					new Configs().deleteConfig(newConfigName.trim());
				}
				else
					getConfigName(); //More Recursion!
			}
					
		}
	}

	public void AddActList(AbstractButton... butts) {

		for (AbstractButton butt : butts)
			butt.addActionListener(this);
	}

	public static void say(Object s) {
		System.out.println(s);
	}

	void updatePreview() {
		txtFilePreview.setText(destInput.getText().trim() + "\\" + new File(srcInput.getText()).getName().trim() + new SuffixHandler().getSuffix(txtSuffix
				.getText().trim()) + "\\");
	}

	public JTextField getPreviewTextField() {
		return txtFilePreview;
	}

}
