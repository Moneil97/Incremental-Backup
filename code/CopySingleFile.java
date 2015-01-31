package code;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

import GUI.MainGUI;

public class CopySingleFile {

	public CopySingleFile(MainGUI gui) {
//		try{
//			publish("s" + selectedSourceFile);
//			copySingleFile(selectedSourceFile, parentDestDir);
//			publish("b" + parentDestDir + "\\" + selectedSourceFile);
//		}
//		catch(Exception e){
//			failedFiles.put(new File(parentDestDir + "\\" + selectedSourceFile), e);
//			publish("r" + parentDestDir + "\\" + selectedSourceFile);
//		}
	}
	
	private void copySingleFile(File file, File destDir) {
		
		//Get File Name plus Extension (No Path)
		String name = file.getName();

		Path source = file.toPath();

		//Creates path and sets to original name
		Path destPath = Paths.get(destDir.toPath() + "/" + name);
		
		try {
			//Copy File, no overwrite
			Files.copy(source, destPath);
		} catch (FileAlreadyExistsException e1) {
			
			if (JOptionPane.showConfirmDialog(null, "Would you like to overwrite file " + destPath + " ?") == JOptionPane.YES_OPTION){
				try {
					//Copy File and overwrite previous file
					Files.copy(source, destPath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e2) {
					
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
