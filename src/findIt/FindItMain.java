package findIt;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;


/**
 * ≤‚ ‘grammerAnalysis.keyWordExtraction()
 * @author SkyFire
 *
 */
/*
public class test{
	public static void main(String [] args){
		ArrayList<String> arrl=new ArrayList<String>();
		Scanner in=new Scanner(System.in);
		String tempStr=in.next();
		in.close();
		tempStr=grammerAnalysis.keyWordExtraction(tempStr,arrl);
		for(String st:arrl){
			System.out.println(st);
		}
		System.out.println(tempStr);
	}
}
*/


/**
 * ≤‚ ‘FindItFileList.getFileList()
 * @author SkyFire
 *
 */
/*
public class test222{
	public static void main(String [] args){
		ArrayList<String> arrl=new ArrayList<String>();
		Scanner in=new Scanner(System.in);
		String tempStr=in.next();
		in.close();
		arrl=FindItFileList.getFileList(tempStr);
		for(String st:arrl){
			System.out.println(st);
		}
	}
}
*/

/**
 * ∂¡»°pdf≤‚ ‘
 * @author SkyFire
 *
 */

/*
public class test222{
	public static void main(String [] args){
		Scanner in=new Scanner(System.in);
		String tempStr=in.next();
		in.close();
		String arrl=readFile.readPDF(tempStr);
		System.out.println(arrl);
	}
}

*/

/**
 * ∂¡word
 * @author SkyFire
 *
 */
/*

public class test222{
	public static void main(String [] args){
		Scanner in=new Scanner(System.in);
		String tempStr=in.next();
		in.close();
		String arrl=null;
		try{
			arrl=readFile.readOffice(tempStr);
		}catch(XmlException e){
			
		}catch(OpenXML4JException e){
			
		}catch(FileNotFoundException e){
			
		}catch(Exception e){
			
		}
		System.out.println(arrl);
	}
}
*/

/**
 * ≤‚ ‘readText
 * @author SkyFire
 *
 */
/*
public class test222{
	public static void main(String [] args){
		Scanner in=new Scanner(System.in);
		String tempStr=in.next();
		in.close();
		String arrl=null;
		try{
		arrl=readFile.readText(tempStr);
		}catch(FileNotFoundException e){
			
		}catch(Exception e){
			
		}
		System.out.println(arrl);
	}
}
*/


public class FindItMain{
	public static void main(String [] args) throws InvocationTargetException, InterruptedException{
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		});
		FindItGui fgui=new FindItGui();
	}
}
