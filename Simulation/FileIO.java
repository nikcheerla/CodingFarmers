
import java.io.*;
import java.util.*;
public class FileIO{
	public static void writeTo(String filename, String s){
		try{
			PrintStream out = new PrintStream(new File(filename));
			out.println(s);
		}
		catch(Exception e){
			System.err.println("File not written");
		}
	}
	public static void addTo(String filename, String s){
		String savedText = readFrom(filename);
		writeTo(filename, savedText + s);
	}
	public static String readFrom(String filename){
		try{
			Scanner s = new Scanner(new File(filename));
			String text = "";
			while(s.hasNextLine()){
				text += s.nextLine() + "\n";
			}
			return text;
		}
		catch(Exception e){
			return "";
		}
	}
}