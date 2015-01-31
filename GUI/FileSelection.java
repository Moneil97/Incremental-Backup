package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class FileSelection extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField destTextField;
	private JTextField srcTextField;

	public FileSelection(){
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblMyLabel = new JLabel("        Source:");
		panel.add(lblMyLabel);
		
		destTextField = new JTextField();
		destTextField.setName("srcInput");
		panel.add(destTextField);
		destTextField.setText("C://");
		
		JButton btnBrowse = new JButton("Browse");
		panel.add(btnBrowse);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblDestination = new JLabel("Destination: ");
		panel_1.add(lblDestination);
		
		srcTextField = new JTextField();
		srcTextField.setName("destInput");
		srcTextField.setText("F://");
		panel_1.add(srcTextField);
		
		JButton btnBrowse_1 = new JButton("Browse");
		panel_1.add(btnBrowse_1);
		
	}
	
}
