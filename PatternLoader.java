package uk.ac.cam.dsjw2.oopjava.tick3;

import java.io.Reader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.URL;
import java.net.URLConnection;

public class PatternLoader {
	 public static List<Pattern> load(Reader r) throws IOException {

		 BufferedReader buff = new BufferedReader(r);
		 List <Pattern> resultList = new LinkedList<Pattern>();
		 
		 //TODO: \t to tab betwn stuff
		 String line;
		 
		 //Read and add all valid patterns into resultList
		 while ( (line = buff.readLine()) != null) {
			 try {
				 Pattern p = new Pattern(line);
				 resultList.add(p);
			 } catch (PatternFormatException e) {
				 System.err.println(e.getMessage()+" for "+line);
			 }
		 }
		 //returns a list of valid patterns
		return resultList;
	  }
	 
	 public static List<Pattern> loadFromURL(String url) throws IOException {
	   //Creates a URL object from the String representation.
		 URL destination = new URL(url);
	   /*Returns a URLConnection instance that represents a connection to 
	    * the remote object referred to by the URL.
	    */
	   URLConnection conn = destination.openConnection();
	   return load(new InputStreamReader(conn.getInputStream()));
	 }

	 public static List<Pattern> loadFromDisk(String filename) throws IOException {
	   return load(new FileReader(filename));
	 }
	
}
