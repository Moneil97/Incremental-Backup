package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public CenterPanel(){
		setLayout(new BorderLayout(0,0));
		
		//add(new FileSelection(), "North");
		add(new FilesPlusProgressBar(), "Center");
	}
	
}
