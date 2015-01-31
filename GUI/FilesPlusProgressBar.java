package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FilesPlusProgressBar extends JPanel{

	private static final long serialVersionUID = 1L;

	public FilesPlusProgressBar() {
		
		setName("filesAndProgressBar");
		
		setLayout(new BorderLayout(0,2));
		add(new  MyProgressBar().paintString(true), "North");
		add(new FilesSplitDisplayPanel(), "Center");
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bar and Files");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new FilesPlusProgressBar());
		frame.setVisible(true);
	}
	
	



}
