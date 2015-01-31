package GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

public class SidePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public SidePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		add(panel_6, "Center");
		panel_6.setLayout(new GridLayout(0,2));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_6.add(panel_5);
		panel_5.setLayout(new GridLayout(7,0));
		
		JLabel lblTimeLeft = new JLabel("Time Left: ");
		panel_5.add(lblTimeLeft);
		
		JLabel lblBackupInterval = new JLabel("Backup Interval: ");
		panel_5.add(lblBackupInterval);
		
		JLabel lblBackupsLeft = new JLabel("Backups Left:");
		panel_5.add(lblBackupsLeft);
		
		JLabel lblMaxCopies = new JLabel("Max Copies");
		panel_5.add(lblMaxCopies);
		
		JLabel lblAskBeforeOverwrite = new JLabel("Ask Before Overwrite:");
		panel_5.add(lblAskBeforeOverwrite);
		
		JLabel lblSuffix = new JLabel("Suffix: ");
		panel_5.add(lblSuffix);
		
		JLabel lblTotalTime = new JLabel("Elapsed Time:");
		panel_5.add(lblTotalTime);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(7,0));
		
		JLabel lbldisplayTimeLeft = new JLabel("[Display Time Left]");
		lbldisplayTimeLeft.setName("timeLeft");
		panel_7.add(lbldisplayTimeLeft);
		
		JLabel lblbackupInterval = new JLabel("[Backup Interval]");
		lblbackupInterval.setName("backupInterval");
		panel_7.add(lblbackupInterval);
		
		JLabel lblbackupsLeft = new JLabel("[Backups Left]");
		lblbackupsLeft.setName("backupsLeft");
		panel_7.add(lblbackupsLeft);
		
		JLabel lblmaxCopies = new JLabel("[Max Copies]");
		lblmaxCopies.setName("maxCopies");
		panel_7.add(lblmaxCopies);
		
		JLabel lblAsk = new JLabel("[Ask]");
		lblAsk.setName("ask");
		panel_7.add(lblAsk);
		
		JLabel lblsuffix = new JLabel("[Suffix]");
		lblsuffix.setName("suffix");
		panel_7.add(lblsuffix);
		
		JLabel lbltotalTime = new JLabel("[Elapsed Time]");
		lbltotalTime.setName("elapsedTime");
		panel_7.add(lbltotalTime);
		
		JPanel panel = new JPanel();
		add(panel, "South");
		
		JButton btnStart = new JButton("Start");
		
		panel.add(btnStart);
		btnStart.setName("start");
		JButton btnChangeSettings = new JButton("Change Settings");
		btnChangeSettings.setName("settings");
		panel.add(btnChangeSettings);
		
	}
	

}
