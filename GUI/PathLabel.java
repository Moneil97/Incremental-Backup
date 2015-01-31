package GUI;

import java.awt.Color;

import javax.swing.JLabel;

public class PathLabel extends JLabel{
	
	private static final long serialVersionUID = 1L;

	public PathLabel(){
		setLabelText("Default Text");
		setLabelTextColor(Color.black);
	}
	
	public PathLabel(String text, Color color){
		setLabelText(text);
		setLabelTextColor(color);
	}
	
	public PathLabel setLabelText(String text){
		setText(text);
		return this;
	}
	
	public PathLabel setLabelTextColor(Color color){
		setForeground(color);
		return this;
	}
}
