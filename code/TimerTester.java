package code;

public class TimerTester{
	
	TimerTester(){
		Timer timer = new Timer();
		
		timer.setTimeInterval(65);
		
		timer.startTimer();
		
		while (!timer.timeForBackup())
			System.out.println(timer.getTimeLeftFormattedString() + "     " + timer.getTimeElapsedSinceLastBackupFormattedString());
		
	}
	
	public static void main( String args[] )
	{
		new TimerTester();
	}
	
}