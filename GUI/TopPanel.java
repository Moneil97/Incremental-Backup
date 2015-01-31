package GUI;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class TopPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public TopPanel(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 64);
		add(panel, "North");
		panel.setLayout(new GridLayout(2,0));
		
		JLabel lblTimedBackup = new JLabel("Timed Backup");
		panel.add(lblTimedBackup);
		lblTimedBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimedBackup.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblCameronOneil = new JLabel("Cameron O'Neil");
		panel.add(lblCameronOneil);
		lblCameronOneil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCameronOneil.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 240, 450, 60);
		add(panel_1, "South");
		panel_1.setLayout(new GridLayout(2,0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 5));
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSource = new JLabel("       Source:");
		panel_2.add(lblSource, "West");
		lblSource.setBounds(5, 185, 61, 14);
		
		JTextField txtC = new JTextField();
		panel_2.add(txtC, "Center");
		txtC.setName("srcPath");
		txtC.setEditable(false);
		txtC.setText("C:\\");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 10, 5, 5));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDestination = new JLabel("Destination:");
		panel_3.add(lblDestination, "West");
		lblDestination.setBounds(8, 207, 58, 14);
		lblDestination.setHorizontalAlignment(SwingConstants.LEFT);
		
		JTextField txtE = new JTextField();
		panel_3.add(txtE, "Center");
		txtE.setName("destPath");
		txtE.setEditable(false);
		txtE.setText("E:\\");
	}
}
