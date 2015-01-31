package code;

import java.awt.Color;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.SwingWorker;

import GUI.MainGUI;
import GUI.OverwriteMenu;
import GUI.PathLabel;

public class CopyFolder extends SwingWorker<String, Object[]>{
	
	private HashMap <File , Exception> failedFiles = new HashMap <File , Exception>();
	private long currentSizeComplete = 0;
	private long totalFileSize = 0;
	//private boolean canceled = false;
	private MainGUI gui;
	private File sourceFolder;
	private File destToCopyInsideOf;
	private File dest;
	private File destWithoutSuffix;
	private Color darkGreen = new Color(34,139,34);
	
	public CopyFolder(MainGUI gui, File sourceFile, File destToCopyInsideOf){
		this.gui = gui;
		this.sourceFolder = sourceFile;
		this.destToCopyInsideOf = destToCopyInsideOf;
	}
	
	//This updates the UI
	protected void process(List<Object[]> list) {
		
		for (Object[] objects: list){
			
			if (objects.length == 3){
				if (objects[0] == MyEnums.toSource){
					gui.getSourcePanel().addNewPathLabelToContainer(new PathLabel((String) objects[1], (Color) objects[2]));
				}
				else{
					gui.getDestPanel().addNewPathLabelToContainer(new PathLabel((String) objects[1], (Color) objects[2]));
				}
			}
			else{
				err("Incorrect use of publish: " + Arrays.toString(objects));
			}
		}
		
		gui.getBar().setValue(this.getProgress());
		gui.getBar().setString(String.format("%.2f", getPercentComplete()) + "%");
		
    }
		//<Source, Dest>
	HashMap<File, File> myFiles = new HashMap<File, File>();

	public String doInBackground() /*throws Exception*/{
		
		say("at folder copier");
		
		//Set up Destination Folder
		String suffix = new SuffixHandler().getSuffix(gui.getSuffix().getText().trim());
		destWithoutSuffix = new File(destToCopyInsideOf + "\\" + sourceFolder.getName());
		dest = new File(destToCopyInsideOf + "\\" + sourceFolder.getName() + suffix);
		
		//Add All Files to Array
		addToAllFiles(sourceFolder.listFiles());
		
		//Print out Hashmap
//		printMyFiles();
		
		long startTime = System.nanoTime();
		
		for (Entry<File, File> entry : myFiles.entrySet()) {
			
			File src = entry.getKey();
			File dest = entry.getValue();
			boolean askBeforeOverwrite = Boolean.getBoolean(gui.getAsk().getText());
			
			try{
				publish(new Object[] {MyEnums.toSource, src.toString(), Color.black});
				
				boolean madeFolder = false;
				boolean overwritePermission = askBeforeOverwrite;
				
				while (true){
					try {
						
						if (overwritePermission)
							Files.copy(Paths.get(src.getPath()) , Paths.get(dest.getPath()), StandardCopyOption.REPLACE_EXISTING);
						else
							Files.copy(Paths.get(src.getPath()) , Paths.get(dest.getPath()));
								
						publish(new Object[] {MyEnums.toDest, dest.toString(), darkGreen});
						currentSizeComplete  += src.length();
						break;
					} catch (NoSuchFileException e) {
						
						if (!madeFolder){
							//If file doesn't exist, make it
							new File(dest.getParent()).mkdirs();
							madeFolder = true;
						}
						else{
							madeFolder = false;
							throw new NoSuchFileException("Could not make Folder: " + dest.getParent());
						}
					}
					catch (FileAlreadyExistsException e){
						
						say(dest + " AlreadyExists");
						
							//Ask if overwrite
							//If yes, set bool to true and run again
							//If no, break;
							
							new OverwriteMenu();

//							if (canceled)
//								throw new FileAlreadyExistsException("User Skipped File"); //Change this later (maybe)
					}
				}
				
				//update progress bar
				this.setProgress(Math.round(getPercentComplete()));
				
			}catch (Exception e){
				publish(new Object[] {MyEnums.toDest, dest.toString(), Color.red});
				failedFiles.put(dest, e);
			}
		    
			if (Thread.currentThread().isInterrupted()){
				return null;
			}
		}
		
		long endTime = System.nanoTime();
		
		double timeInSec = (endTime - startTime)/1000000000.0;
		
		say("It took " + String.format("%.3f", timeInSec) + " seconds to transfer: " + getConvertedSize(totalFileSize));
		//publish(("It took " + String.format("%.3f", timeInSec) + " seconds to transfer: " + getConvertedSize(totalFileSize)));
		
		say("Average Speed: " + getConvertedSize(totalFileSize/timeInSec)+ " /second");
		//publish("Average Speed: " + getConvertedSize(totalFileSize/timeInSec)+ " /second");
		
		//Print Errors
		for (Entry<File, Exception> entry : failedFiles.entrySet()) {
		    
			say(entry.getKey() + "  :  " + entry.getValue());
		}
		
		//Export Errors to Log
		if (!failedFiles.isEmpty())
			new writeFailedToLog(failedFiles, destToCopyInsideOf, sourceFolder.getName());
		else
			say("All Files Copied");
		
		return null;
	}
	
	
	private void addToAllFiles(File[] files) {
		
		for (File file : files){
			if (file.isFile()){
				myFiles.put(file, changeToSourceDirectory(file));
				totalFileSize += file.length(); //Use this to get percent complete
			}
			else if (file.isDirectory()){
				//Recursion!
				addToAllFiles(file.listFiles());
			}
			else{
				err("Unrecognizable File: " + file.getPath());
			}
		}
	}
	
	private File changeToSourceDirectory(File file) {
//		say("File: " + file);
//		say("Dest: " + dest);
		return new File(dest + file.toString().substring(file.toString().lastIndexOf(destWithoutSuffix.getName()) + destWithoutSuffix.getName().length()));
	}
	
	@SuppressWarnings("unused")
	private void printMyFiles(){
		for (Entry<File, File> entry : myFiles.entrySet()) {
//		    
			say(entry.getKey() + "  :  " + entry.getValue());
		}
	}

	private String getConvertedSize(double bytes){

		int count = 0;
		
		while (bytes >= 1024 && count < 4){
			bytes /= 1024;
			count++;
		}
		
		if (count == 0){
			return String.format("%.3f bytes", bytes);
		}
		else if (count ==1){
			return String.format("%.3f kilobytes", bytes);
		}
		else if (count ==2){
			return String.format("%.3f megabytes", bytes);
		}
		else if (count ==3){
			return String.format("%.3f gigabytes", bytes);
		}
		else{
			return String.format("%.3f terabytes", bytes);
		}
	}
	
	
	public static void say(Object s){
		System.out.println(s);
	}
	
	private void err(Object s) {
		System.err.println(s);
	}
	
	private float getPercentComplete(){
		return ((float)currentSizeComplete/totalFileSize) * 100;
	}

//	public static void main(String[] args) {
//
//	}

}
