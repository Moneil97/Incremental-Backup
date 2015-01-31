package GUI;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class HelpMenu extends JDialog{

	private static final long serialVersionUID = 1L;

	public HelpMenu() {
		setTitle("Help Menu");
		setSize(200,200);
		setModal(true);
		setLocationRelativeTo(null);
		
		add(new JLabel("Help Yourself"));
		
		setVisible(true);
		
		
	}

}
