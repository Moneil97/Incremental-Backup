package GUI;

import javax.swing.JProgressBar;

public class MyProgressBar extends JProgressBar{

	private static final long serialVersionUID = 1L;

	public MyProgressBar() {

		this.setName("Bar");
		
		setString("0%");
		setMinimum(0);
		setMaximum(100);
	}
	
//	public MyProgressBar updatePercent(int fileSizeCompleted){
//		setValue(fileSizeCompleted);
//		setString((getPercentComplete() * 100) + "%");
//		return this;
//	}
	
	public MyProgressBar paintString(boolean b){
		setStringPainted(b);
		return this;
	}
	
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Title");
//		frame.setSize(800, 600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(new  MyProgressBar(5000).updatePercent(4000).paintString(true));
//		frame.setVisible(true);
//	}

}
