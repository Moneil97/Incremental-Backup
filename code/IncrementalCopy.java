package code;

import java.io.File;

import GUI.MainGUI;

public class IncrementalCopy{
	
	MyEnums mode;
	CopyFolder folderCopier;
	private CopySingleFile fileCopier;
	
	public IncrementalCopy(MainGUI gui) {
		
		//Get Source File
		File selectedSourceFile = new File(gui.getSrcField().getText().trim());
		//Get Destination Folder
		File destToCopyInsideOf = new File(gui.getDestField().getText().trim());
		
		gui.getSourcePanel().setHeaderText("Source: " + selectedSourceFile.toString());
		gui.getDestPanel().setHeaderText("Destination: " + destToCopyInsideOf);
		
		//If a File (Not Folder)
		if (selectedSourceFile.isFile()){
			mode = MyEnums.fileMode;
			fileCopier = new CopySingleFile(gui);
			
		}
		else if (selectedSourceFile.isDirectory()){
			mode = MyEnums.folderMode;
			folderCopier = new CopyFolder(gui, selectedSourceFile, destToCopyInsideOf);
			folderCopier.execute();
		}
		else {
			err(selectedSourceFile + " is not a supported File");
		}
	}
	
	public CopyFolder getFolderCopier(){
		return folderCopier;
	}
	
	public static void err(Object s){
		System.err.println(s);
	}

	public Object getFileCopier() {
		return fileCopier;
	}
}
