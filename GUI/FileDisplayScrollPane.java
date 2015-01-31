package GUI;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FileDisplayScrollPane extends JScrollPane{

	private static final long serialVersionUID = 1L;
	JPanel labelContainer = new JPanel();

	public FileDisplayScrollPane(String name) {
		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		labelContainer.setLayout(new GridLayout(0,1));
		setViewportView(labelContainer);
		
		setColumnHeaderView(new JLabel(name, SwingConstants.CENTER));
	}
	
	public FileDisplayScrollPane addNewPathLabelToContainer(PathLabel pathLabel){
		
		labelContainer.add(pathLabel);
		//Scroll To the Bottom
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
		revalidate();
		repaint();
		return this;
	}
	
	public FileDisplayScrollPane setHeaderText(String name){
		setColumnHeaderView(new JLabel(name, SwingConstants.CENTER));
		return this;
	}

	public FileDisplayScrollPane giveName(String string) {
		setName(string);
		return this;
	}
	
	public void clearLabels(){
		labelContainer.removeAll(); 
		labelContainer.updateUI();
	}
}
