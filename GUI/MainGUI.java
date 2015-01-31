package GUI;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

import code.TimerManager;

public class MainGUI extends JFrame implements ComponentListener{

	private static final long serialVersionUID = 1L;
	private List<Component> allComps;
	private FileDisplayScrollPane sourcePanel, destPanel;
	private JTextField srcField, destField;
	private JProgressBar bar;
	private JButton start, settings;
	private MainGUI tempThis;
	private JLabel timeLeft, backupsLeft, elapsedTime, backupInterval, maxCopies, suffix;
	private TimerManager timeManager;
	private JLabel ask;
	
	public MainGUI() {
		super("Timed Backup");
		setSize(800, 600);
		setMinimumSize(new Dimension(235, 340));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(new CenterPanel(), "Center");
		add(new TopPanel(), "North");
		add(new SidePanel() ,"East");
		
		setVisible(true);
		tempThis = this;
		
		allComps = getAllComponents(this);
		
		sourcePanel = (FileDisplayScrollPane) findCompWithName("Src");
		destPanel = (FileDisplayScrollPane) findCompWithName("Dest");
		srcField = (JTextField) findCompWithName("srcPath");
		destField = (JTextField) findCompWithName("destPath");
		bar = (JProgressBar) findCompWithName("Bar");
		start = (JButton)  findCompWithName("start");
		settings = (JButton)  findCompWithName("settings");
		
		timeLeft  =  (JLabel) findCompWithName("timeLeft");
		backupsLeft = (JLabel) findCompWithName("backupsLeft");
		elapsedTime  = (JLabel) findCompWithName("elapsedTime");
		backupInterval = (JLabel) findCompWithName("backupInterval");
		maxCopies = (JLabel) findCompWithName("maxCopies");
		ask = (JLabel) findCompWithName("ask");
		suffix = (JLabel) findCompWithName("suffix");
		
		start.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	say("start pressed");
		    	
				Thread runCopy = new Thread(new Runnable() {
					public void run() {
						
						if (start.getText().equals("Start")){
							
							start.setText("Stop");
							settings.setText("Backup Now");
							timeManager = new TimerManager(tempThis);
							timeManager.execute();
						}
						else{ //stop
							start.setText("Start");
							settings.setText("Change Settings");
							timeManager.cancel(true);
							timeManager = null;
						}
					}
				});
				runCopy.run();
		    }
		});
		
		settings.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if (settings.getText().equals("Change Settings")){
		    		say("settings pressed");
		    		new SettingsMenu(tempThis);
		    	}
		    	else{ //backup now
		    		
		    		int choice = JOptionPane.showConfirmDialog(null, "Would you like to reset the timer");
		    		
		    		if (choice == JOptionPane.YES_OPTION){
		    			timeManager.backupNowAndResetTimer();
		    		}
		    		else if (choice == JOptionPane.NO_OPTION){
		    			timeManager.backupNow();
		    		}
		    		
		    	}
		    }
		});

		addComponentListener(this);
		
//		Sets up size for File Input Panels
		resizeFileInputFields();
	}
	
	
	public static List<Component> getAllComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	
	public Component findCompWithName(String name){
		
		for (Component comp : allComps){
			if (comp.getName() != null && comp.getName().equals(name))
				return comp;
		}
		return null;
	}

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
			
		} catch(Exception e) { System.err.println("Error: " + e.getMessage()); }
		
		new MainGUI();
	}


	@Override
	public void componentResized(ComponentEvent ce) {
		resizeFileInputFields();
	}
	
	public void resizeFileInputFields(){
		//System.out.println(getSize());
		
		Dimension sizeDem = new Dimension(getWidth()-90, 30);
		srcField.setPreferredSize(sizeDem);
		destField.setPreferredSize(sizeDem);
		srcField.setSize(sizeDem);
		destField.setSize(sizeDem);
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
	
	public FileDisplayScrollPane getSourcePanel() {
		return sourcePanel;
	}


	public FileDisplayScrollPane getDestPanel() {
		return destPanel;
	}


	public JTextField getSrcField() {
		return srcField;
	}


	public JTextField getDestField() {
		return destField;
	}


	public JProgressBar getBar() {
		return bar;
	}
	
	public JLabel getTimeLeft() {
		return timeLeft;
	}


	public JLabel getBackupsLeft() {
		return backupsLeft;
	}


	public JLabel getElapsedTime() {
		return elapsedTime;
	}


	public JLabel getBackupInterval() {
		return backupInterval;
	}


	public JLabel getMaxCopies() {
		return maxCopies;
	}


	public JLabel getSuffix() {
		return suffix;
	}
	
//	public JButton getStart(){
//		return start;
//	}
	
	public static void say(Object s){
		System.out.println(s);
	}


	public JLabel getAsk() {
		return ask;
	}

}
