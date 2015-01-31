package testing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

public class testing {

	public testing() {
		// TODO Auto-generated constructor stub
	}
	
	private static File cutOffFrontPath(File file, File dest) {
		
		return new File(file.toString().substring(file.toString().lastIndexOf(dest.getName())));
	}

	public static void main(String[] args) {

		
		JOptionPane test = new JOptionPane();
		test.setMessage("Blah");
		test.setSize(200, 200);
		test.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

				say("Key typed");
				
				if (test.getInputValue().toString().length() > 30){
					test.setMessage("Max length is 30");
				}
			}
			
		});
		
		
		
		
		
		say("Reallllylyyly long nnnanamee".length());
		
		
		
		File file = new File("C:\\Users\\Cameron\\Desktop\\Tester\\Folder 1\\File 1-1.txt");
		File dest = new File("F:\\Copy inside this Folder:\\here\\Tester");
		
		
		say(cutOffFrontPath(file, dest));
		
		new FileCopyProgress();
	}
	
	public static void say(Object s){
		System.out.println(s);
	}

}

class FileCopyProgress {
    public FileCopyProgress() {
        System.out.println("copying file");
        File filein  = new File("C:\\Users\\Cameron\\TimedBackUp\\SavedSettings.txt");
        File fileout = new File("F:\\Copy inside this Folder\\testing.txt");
        FileInputStream  fin  = null;
        FileOutputStream fout = null;
        long length  = filein.length();
        long counter = 0;
        int r = 0;
        byte[] b = new byte[1];
        try {
            fin  = new FileInputStream(filein);
            fout = new FileOutputStream(fileout);
            while( (r = fin.read(b)) != -1) {
                counter += r;
                System.out.println( 1.0 * counter / length );
                System.out.println(b + "   " + 0 + "    " + r);
                fout.write(b, 0, r);
            }
        }
        catch(Exception e){
        	System.out.println("foo");
        }
    }
}
