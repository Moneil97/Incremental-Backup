package code;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

public class writeFailedToLog {

	private HashMap<File, Exception> failedFiles;
	private File file;
	private String sourceFolderName;

	public writeFailedToLog(HashMap<File,Exception> failedFiles, File file, String sourceFolderName) {
		this.failedFiles = failedFiles;
		this.file = file;
		this.sourceFolderName = sourceFolderName;
		
		writeToFile();
	}


	private void writeToFile(){
		
		String textFileName = "/FailedLog.txt";
		File saveLocation = null;
		PrintWriter out = null;

		try {
			
			//Get GUI input
			MyEnums choice = MyEnums.Put_Log_In_Selected_Folder;
			
			if (choice == MyEnums.Put_Log_In_Parent_Folder){
				saveLocation = new File(file + textFileName);
			}
			else if (choice == MyEnums.Put_Log_In_Selected_Folder){
				saveLocation = new File(file + "/" +sourceFolderName + textFileName);
			}
			
		    out = new PrintWriter(new BufferedWriter(new FileWriter(saveLocation)));
		    
		    for (Entry<File, Exception> entry : failedFiles.entrySet()) {
			    
				out.println(entry.getKey() + "  :  " + entry.getValue());
			}
		
		    
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		   try {
			   out.close();
		   }
		   catch (Exception e) {
			   e.printStackTrace();
		   }
		}
		
	}
	
	public static void say(Object s){
		System.out.println(s);
	}
	
	
	//Write to a log and save in the folder
	//Have it toggleable
	//button to open most recent log
	//java.awt.Desktop.getDesktop().edit(file);
	
	
//	public static void main(String[] args) {
//		
//	}
}
