package GUI;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class FilesSplitDisplayPanel extends JSplitPane implements ComponentListener{

	private static final long serialVersionUID = 1L;

	public FilesSplitDisplayPanel() {
		
		FileDisplayScrollPane source = new FileDisplayScrollPane("Source").giveName("Src");
		setLeftComponent(source);
		
		FileDisplayScrollPane dest = new FileDisplayScrollPane("Destination").giveName("Dest");
		setRightComponent(dest);
		
		setResizeWeight(0.5);
		
		addComponentListener(this);
	}
	
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Title");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new FilesSplitDisplayPanel());
		frame.setVisible(true);
	}
	
	@Override
	public void componentResized(ComponentEvent ce) {
		setDividerLocation(getSize().width /2);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}
}
