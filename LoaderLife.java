package uk.ac.cam.dsjw2.oopjava.tick3;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedList;

public class LoaderLife {

	public static void play(World world) throws java.io.IOException {
		   
		   int userResponse = 0;
		   WorldViewer viewer = new WorldViewer();
		   Writer w = new PrintWriter(System.out);
		   
		   while (userResponse != 'q') {
		      world.print(w);
		      viewer.show(world);
		      userResponse = System.in.read();
		      world = world.nextGeneration(0);
		   }
		   viewer.close();
	}

	public static void main(String[] args) throws java.io.IOException {

		//create world according to --array or --long
		List <Pattern> validPatternList;
		String storageOption;
		int fileAddressArg = 0, chosenLine=0;

		if (args.length != 1 && args.length != 2 && args.length != 3) {
			System.out.println("Invalid syntax: [optional storageType] [file/url] [Optional line]");
			return;
		}
		
		//take in storage option if first arg starts with -- 
		//file address arg shud be 1 if arg0 is storage option
		if ((args[0].substring(0, 2)).equals("--"))  {
			storageOption = args[0];
			fileAddressArg = 1;
		} else { storageOption = "--array"; }
		
		//load pattern from either disk or url - returns pattern list
		if (args[fileAddressArg].startsWith("http://"))  {
			validPatternList = new LinkedList<Pattern>(PatternLoader.loadFromURL(args[fileAddressArg]));
		} else {
			validPatternList = new LinkedList<Pattern>(PatternLoader.loadFromDisk(args[fileAddressArg]));
		}
		
		World world = null;
		
		//print lines of num, name and author
		int i=0;
		for (Pattern p: validPatternList) {
			System.out.println(i+"\t"+p.getName()+"\t"+p.getAuthor());
			i++;
		}
		
		//set the line to get the pattern from
		try {
			if (3 == args.length) chosenLine = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("Error: 3rd arg should be an integer");
		}
		
		//create the correct type of world
		if (storageOption.equals("--array")) {
			world = new ArrayWorld((validPatternList.get(chosenLine)).getWidth(), (validPatternList.get(chosenLine)).getHeight());
		} else if (storageOption.equals("--long")) {
			world = new PackedWorld();
		} else if (storageOption.equals("--aging")){
			world = new AgingWorld((validPatternList.get(chosenLine)).getWidth(), (validPatternList.get(chosenLine)).getHeight());
		}	else {
			System.out.println("Error: unknown type "+storageOption);
			return;
		}
		
		//initialize pattern and play
		try {
			(validPatternList.get(chosenLine)).initialise(world); 
			play(world);
		} catch (PatternFormatException pe) {
			System.out.println(pe.getMessage());
		}
	}

}
