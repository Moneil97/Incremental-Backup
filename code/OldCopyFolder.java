//package code;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.Files;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//import javax.swing.JOptionPane;
//
//public class OldCopyFolder {
//
//	public void oldWay(){
//		String removeFromStart = sourceFolder.toString().substring(0, sourceFolder.toString().lastIndexOf(sourceFolder.getName()));
//		File folderWithSuffix = new File(sourceFolder.toString().substring(removeFromStart.length()) + new SuffixHandler().getSuffix(gui.getSuffix().getText().trim()));
//		
//		//String removeFromStart2 = selectedSourceFile.toString().substring(0, selectedSourceFile.toString().lastIndexOf(selectedSourceFile.getName()) + new SuffixHandler().getSuffix(myGUI.getSuffix().getText().trim()).length());
//		String removeFromStart2 = removeFromStart + sourceFolder.getName();
//		say("here");
//		
//		
//		
//		long startTime = System.nanoTime();
//		
//		for (File file : allFiles){
//			
//			try{
//				publish("s" + file);
//				copyFile(file, destToCopyInsideOf, folderWithSuffix, removeFromStart2);
//				publish("b" + destToCopyInsideOf + "\\" + folderWithSuffix + "\\" + file.getName());
//			}
//			catch(Exception e){
//				e.printStackTrace();
//				failedFiles.put(new File(destToCopyInsideOf + "\\" + file.toString().substring(removeFromStart2.length())), e);
//				publish("r" + destToCopyInsideOf + "\\" + folderWithSuffix + "\\" + file.getName());
//			}
//			
//			currentSizeComplete  += file.length();
//			gui.getBar().setValue(Math.round(getPercentComplete()));
//			gui.getBar().setString(String.format("%.2f", getPercentComplete()) + "%");
//		}
//		
//		long endTime = System.nanoTime();
//		
//		double timeInSec = (endTime - startTime)/1000000000.0;
//		
//		say("It took " + String.format("%.3f", timeInSec) + " seconds to transfer: " + getConvertedSize(totalFileSize));
//		publish(("It took " + String.format("%.3f", timeInSec) + " seconds to transfer: " + getConvertedSize(totalFileSize)));
//		
//		say("Average Speed: " + getConvertedSize(totalFileSize/timeInSec)+ " /second");
//		publish("Average Speed: " + getConvertedSize(totalFileSize/timeInSec)+ " /second");
//		
//		if (!failedFiles.isEmpty())
//			new writeFailedToLog(failedFiles, destToCopyInsideOf, sourceFolder.getName());
//		else
//			say("All Files Copied");
//		
//	}
//	
//	private void copyFile(File file, File parentDestDir, File folderWithSuffix, String removeFromStart) throws IOException {
//		
//		Path destPath = Paths.get(parentDestDir + "\\" + folderWithSuffix + file.toString().substring(removeFromStart.length()));
//		say("\n" + parentDestDir + "    "  + folderWithSuffix + "   " + file.toString().substring(removeFromStart.length()));
//		say("destPath: " + destPath);
//		Path source = file.toPath();
//		
//		try {
//			try {
//				//Copy File, no overwrite
//				Files.copy(source, destPath);
//			}
//			catch (NoSuchFileException e){
//				
//				say("NoSuchFile");
//				
//				//Make the folder Path
//				//File makeThisFolder = new File(parentDestDir + "\\" + folderWithSuffix);
//				File makeThisFolder = new File(parentDestDir + "\\" + folderWithSuffix + file.toString().substring(removeFromStart.length(), file.toString().length() - file.getName().length()));
//				say("Made Folder: " + makeThisFolder);
//				makeThisFolder.mkdirs();
//				
//				//Try again - Copy File, no overwrite
//				Files.copy(source, destPath);
//			}
//		}
//		
//		catch (FileAlreadyExistsException e){
//			
//			say("FileAlreadyExists");
//			
//			if (canceled)
//				throw new FileAlreadyExistsException("User Skipped File"); //Change this later (maybe)
//			
//			int input = JOptionPane.showConfirmDialog(null, "Would you like to overwrite file " + destPath + " ?    Cancel = skip all");
//			
//			if (input == JOptionPane.YES_OPTION){
//				//Copy File and overwrite previous file
//				Files.copy(source, destPath, StandardCopyOption.REPLACE_EXISTING);
//			}
//			else if (input == JOptionPane.CANCEL_OPTION){
//				canceled = true;
//				throw new FileAlreadyExistsException("User Skipped File");
//			}
//			else{
//				throw new FileAlreadyExistsException("User Skipped File");
//			}
//		}
//	}
//}
