package code;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;

import GUI.MainGUI;
import GUI.PathLabel;

public class TimerManager extends SwingWorker<String, String> {

	private MainGUI gui;
	private Timer timer;
	private IncrementalCopy copier;

	public TimerManager(MainGUI mainGUI) {
		gui = mainGUI;
	}

	// This updates the UI
	protected void process(List<String> items) {
		for (String item : items) {
			
			if (item.charAt(0) == '1'){
				clearLabels();
			}
			else if (item.charAt(0) == '2'){
				gui.getTimeLeft().setText(timer.getTimeLeftFormattedString());
				gui.getElapsedTime().setText(timer.getTotalTimeElapsedFormattedString());
			}
			else if (item.charAt(0) == '3'){
				gui.getSourcePanel().addNewPathLabelToContainer(new PathLabel("Loading Files...", Color.BLACK));
				gui.getDestPanel().addNewPathLabelToContainer(new PathLabel("  ", Color.BLACK));
			}
			else if (item.charAt(0) == '4'){
				gui.getSourcePanel().addNewPathLabelToContainer(new PathLabel("Waiting For Timer...", Color.BLACK));
				gui.getDestPanel().addNewPathLabelToContainer(new PathLabel("  ", Color.BLACK));
			}
		}
	}

	@Override
	protected String doInBackground()/* throws Exception */{
		
		try{

		publish("1", "4");
		timer = new Timer();
		say(gui.getBackupInterval().getText());
		timer.setTimeInterval(timer.formatedStringToSeconds(gui.getBackupInterval()
				.getText()));
		timer.startTimer();

		while (timer.isRunning()) {
			
			
//			if (backupNow)
//				say("backup now");
//			
//			if (timer.timeForBackup()){
//				say("timer.timeForBackup()");
//				
//				if (copier == null)
//					say ("copier.getFolderCopier() == null ");
//				else if (copier.getFolderCopier().isDone())
//					say("copier.getFolderCopier().isDone()");
//			}
			
			
			if (backupNow || (timer.timeForBackup() && (copier == null || copier.getFolderCopier().isDone()))){// && (copier.getFileCopier() == null || copier.getFileCopier().isDone()))) {
				
				publish("1", "2", "3");
				say("starting");
				copier = new IncrementalCopy(gui);

				while (!copier.getFolderCopier().isDone()) {
					try {
						say("sleep (waiting for files to finish copying)");
						Thread.sleep(1000);
					} catch (InterruptedException e){
						say("interuppted");
						copier.getFolderCopier().cancel(true);
						return null;
					}
				}
				publish("4");
				
				if (backupNow){
					backupNow = false;
					if (resetTimer){
						resetTimer = false;
						timer.restart();
					}
				}
				else{
					timer.restart();
				}
				
			} else {
				
				publish("2");
				
				 try {
					 Thread.sleep(1000);
					 say("sleep (waiting for timer)");
				 } catch (InterruptedException e) {
					 say("interuppted");
					 return null;
				 }
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
	public static void say(Object s){
		System.out.println(s);
	}
	
	private void clearLabels(){
		gui.getSourcePanel().clearLabels();
		gui.getDestPanel().clearLabels();
	}
	
	boolean backupNow = false;
	boolean resetTimer = false;

	public void backupNow() {
		backupNow = true;
	}
	
	public void backupNowAndResetTimer() {
		backupNow = true;
		resetTimer = true;
	}
}
