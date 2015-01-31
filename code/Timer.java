package code;

import java.util.Scanner;

public class Timer {

	long startNanos, timeIntervalSex, startSinceLastBackupSex;
	private boolean running = false;
	
	//Sex = secs = seconds
	
	public Timer() {
		
	}
	
	public void startTimer(){
		
		running  = true;
		startNanos = System.nanoTime();
		startSinceLastBackupSex = nanosToSex(startNanos);
		
	}
	
	public void stopTimer(){
		running = false;
	}
	
	public void restart() {
		setStartSinceLastBackupNow();
	}
	
	
	public void setTimeInterval(long seconds){
		
		timeIntervalSex = seconds;
		
	}
	
	public boolean timeForBackup(){
		
		if (getTimeLeftSex() > 0)
			return false;
		else
			return true;
		
	}
	
	public long getTimeLeftSex(){
	
		return (timeIntervalSex - getElapsedTimeSinceLastBackupSex());
		
	}
	
	public long getTotalElapsedTimeSex() {
		
		return (getSexNow() - nanosToSex(startNanos));
		
	}

	public long getElapsedTimeSinceLastBackupSex() {
		
		return (getSexNow() - startSinceLastBackupSex);
		
	}
	
	public long getSexNow(){
		return (nanosToSex(System.nanoTime()));
	}
	
	public long getNowNanos(){
		return System.nanoTime();
	}
	
	private long nanosToSex(long nanos){
		return (nanos/1000000000);
	}
	
	@SuppressWarnings("unused")
	private long sexToNanos(long sex){
		return (sex * 1000000000);
	}
	
	public void setStartSinceLastBackupNow(){
		startSinceLastBackupSex = getSexNow();
	}
	
	public long sexToMin(long sex){
		return (sex / 60);
	}
	
	public long minToSex(long mins){
		return (mins * 60);
	}
	
	public long minToHours(long mins){
		return (mins / 60);
	}
	
	public long hoursToMins(long hours){
		return (hours * 60);
	}

	public String getTimeLeftFormattedString() {
		
		long sex = getTimeLeftSex();
		return sexToFormattedString(sex);
	}
	
	public String getTimeElapsedSinceLastBackupFormattedString(){
		
		long sex = getElapsedTimeSinceLastBackupSex();
		return sexToFormattedString(sex);
	}
	
	public String getTotalTimeElapsedFormattedString(){
		
		long sex = getTotalElapsedTimeSex();
		return sexToFormattedString(sex);
	}
	
	public String sexToFormattedString(long sex){
		return (getStringHoursLeft(sex) + ":" + getStringMinsLeft(sex) + ":" + getStringSexLeft(sex));
	}
	
	private String getStringSexLeft(long sex){
		
		while (sex >= 60)
			sex -= 60;
		
		if (sex < 10)
			return ("0" + sex);
		else
			return String.valueOf(sex);
	}
	
	private String getStringMinsLeft(long sex){
		
		long mins = sexToMin(sex);
		
		while (mins >= 60)
			mins -= 60;
		
		if (mins < 10)
			return ("0" + mins);
		return String.valueOf(mins);
	}
	
	private String getStringHoursLeft(long sex){
		
		long hours = minToHours(sexToMin(sex));
		
		if (hours < 10)
			return ("0" + hours);
		return String.valueOf(hours);
	}

	public long formatedStringToSeconds(String text) {

		Scanner scan = new Scanner(text);
		scan.useDelimiter(":");
		long sex = scan.nextInt()*3600 + scan.nextInt()*60 + scan.nextInt();		
		scan.close();
		return sex;
	}

	public boolean isRunning() {
		return running;
	}

	

}


