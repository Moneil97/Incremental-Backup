package code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SaveSettings {

	PrintWriter out = null;

	public SaveSettings(String newConfigName) {

		String UserProfile = System.getProperty("user.home");
		System.out.println(UserProfile);

		String textFileName = "/SavedSettings.txt";
		File saveLocation = new File(System.getProperty("user.home")
				+ "/TimedBackUp/");
		
		//Save Previous Configs
		ArrayList<String> oldLines = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new File(saveLocation + textFileName));
			
			while (scan.hasNextLine()){
				oldLines.add(scan.nextLine());
			}
			
			scan.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		

		// try twice
		for (int i = 0; i < 2; i++) {
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(
						saveLocation + textFileName)));
				break;
			} catch (FileNotFoundException e) {
				File makeThisFolder = saveLocation;
				makeThisFolder.mkdirs();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (String oldLine : oldLines)
			out.println(oldLine);
		
		out.println("*");
		out.println("Name: " + newConfigName.trim());

	}

	

	public void addJTextFields(JTextField srcInput, JTextField destInput,
			JTextField backupInterval, JTextField txtSuffix) {
		
		out.println("srcInput: " + srcInput.getText().trim());
		out.println("destInput: " + destInput.getText().trim());
		out.println("backupInterval: " + backupInterval.getText().trim());
		out.println("txtSuffix: " + txtSuffix.getText().trim());
	}

	public void addToggles(JToggleButton backupsAmountToggle,
			JToggleButton oldestBackupToggle) {

		out.println("backupsAmountToggle: " + backupsAmountToggle.getText().trim());
		out.println("oldestBackupToggle: " + oldestBackupToggle.getText().trim());
	}

	public void addSpinners(JSpinner backupsAmount, JSpinner deleteOldestSpinner) {

		out.println("backupsAmount: " + backupsAmount.getValue());
		out.println("deleteOldestSpinner: " + deleteOldestSpinner.getValue());
	}

	public void addDropDowns(JComboBox<String> askDropDown) {
		out.println("askDropDown: " + askDropDown.getSelectedIndex());
	}

	public void close() {

		try {
			out.println("@");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	public static void main(String args[]) {
//		new SaveSettings("test").close();
//	}

}
