package code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Configs {
	
	String textFileName = "/SavedSettings.txt";
	File saveLocation = new File(System.getProperty("user.home")
			+ "/TimedBackUp/");

	
	public String[] getNames(){
		
		ArrayList<String> names = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new File(saveLocation + textFileName));
			
			while (scan.hasNextLine()){
				if (scan.nextLine().equals("*")){
					scan.next(); //removes Name:
					names.add(scan.nextLine().trim());
				}
			}
			
			scan.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return names.toArray(new String[names.size()]);
	}
	
	public String[] getConfigSettings(String name){
		
		ArrayList<String> settings = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new File(saveLocation + textFileName));
			
			while (scan.hasNextLine()){
				if (scan.nextLine().equals("*")){
					scan.next(); //removes Name: 
					if (scan.nextLine().trim().equals(name)){
						while (true){
							String next = scan.next();
							if (next.equals("@"))
								break;
							else{
								settings.add(scan.nextLine().trim());
							}
						}
					}
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return settings.toArray(new String[settings.size()]);
	}
	
	public void deleteConfig(String configName){
		
		configName = "Name: " + configName;
		
		ArrayList<String> oldLinesMinusDeleted = new ArrayList<String>();
		
		try {
			Scanner scan = new Scanner(new File(saveLocation + textFileName));
			
			while (scan.hasNextLine()){
				String nextLine = scan.nextLine().trim();
				
				if (!nextLine.equals(configName.trim())){
					oldLinesMinusDeleted.add(nextLine);
				}
				else{
					
					oldLinesMinusDeleted.remove(oldLinesMinusDeleted.size()-1);
					
					while(!scan.nextLine().trim().equals("@")){
						//Do Nothing so that config is not re-saved
					}
				}
			}
			
			scan.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		PrintWriter out = null;
		
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
		
		for (String newLine : oldLinesMinusDeleted){
			System.out.println(newLine);
			out.println(newLine);
		}
		out.close();
	}
	
	
	public boolean defaultExists(){
		String[] names = getNames();
		for (String name: names)
			if (name.equalsIgnoreCase("Default"))
				return true;
		return false;
	}

	public void addDefault() {
		
		if (!defaultExists()){
			String UserProfile = System.getProperty("user.home");
			System.out.println(UserProfile);
			PrintWriter out = null;

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
			
			out.println("*");
			out.println("Name: " + "Default");
			out.println("srcInput: C:\\");
			out.println("destInput: D:\\");
			out.println("backupInterval: 00:20:00");
			out.println("txtSuffix: <HH-mm-ss>");
			out.println("backupsAmountToggle: Disabled");
			out.println("oldestBackupToggle: Disabled");
			out.println("backupsAmount: 0");
			out.println("deleteOldestSpinner: 0");
			out.println("askDropDown: 0");
			out.println("@");
			
			
			for (String oldLine : oldLines)
				out.println(oldLine);
			
			out.close();
		}
		
	}
	
	public void readFile(){
		
		try {
			Scanner scan = new Scanner(new File(saveLocation + textFileName));
			
			while (scan.hasNext()){
				System.out.println(scan.next());
			}
			
			scan.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void say(Object s){
		System.out.println(s);
	}
	
	public static void main(String args[]){
		say(Arrays.toString(new Configs().getConfigSettings("ACDC")));
		
		new Configs().deleteConfig("Default");
		new Configs().addDefault();
	}

}
