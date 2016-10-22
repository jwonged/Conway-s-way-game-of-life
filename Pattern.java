package uk.ac.cam.dsjw2.oopjava.tick3;

import uk.ac.cam.acr31.life.World;

public class Pattern {
	   private String name;
	   private String author;
	   private int width;
	   private int height;
	   private int startCol;
	   private int startRow;
	   private String cells;
	   //NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS
	   //"Glider:Richard Guy:20:20:1:1:010 001 111"

	   public String getName() {
	      return name;
	   }
	   public int getHeight() {
		   return height;
	   }
	   public int getWidth() {
		   return width;
	   }
	   public String getAuthor() {
		   return author;
	   }
	   
	   public Pattern(String format) throws PatternFormatException {
		 String[] inputParts = format.split(":");
		 if (inputParts.length != 7) {
			 throw new PatternFormatException("Invalid pattern format: incorrect nmber of fields in pattern - found: "+inputParts.length);
		 }
		 
		 //initialise all fields with contents of format
		 try {
		   name = inputParts[0];
		   author = inputParts[1];
		   width = Integer.parseInt(inputParts[2]);   
		   height = Integer.parseInt(inputParts[3]);
		   startCol = Integer.parseInt(inputParts[4]);
		   startRow = Integer.parseInt(inputParts[5]);
		   cells = inputParts[6];
		 } catch (NumberFormatException N) {
			  throw new PatternFormatException("Error: values not integers");
		 }
		 //check for incorrect cell format 
		 char[] cellChars = cells.toCharArray(); 
		 //Check for properly formatted white space
		 if (cells.length() != 11) throw new PatternFormatException("Error: incorrect number of cells - "+cells);
		 if (!( (Character.isWhitespace(cellChars[3])) && (Character.isWhitespace(cellChars[7])) )) throw new PatternFormatException("Error: incorrect cell format - "+cells);
		 //check to make sure the rest are 1s and 0s
		 String flushedcells = inputParts[6];
		 char[] flushedchars = (flushedcells.replaceAll("\\s","")).toCharArray();
		 for (int i=0; i<9; i++) {
			 if (flushedchars[i] != '1' && flushedchars[i] != '0') throw new PatternFormatException("Error: incorrect cell format - "+cells);
		 }
		 
	   }

	   public void initialise(World world) throws PatternFormatException{
	      //Create the glider using input cells, throw exception if invalid value
		  try {
			  String[] cellState = cells.split(" ");
			  if (cellState.length != 3) throw new PatternFormatException("Error: Invalid Cell Format - "+cells);
			  for (int row=0; row<3; row++) {
				  if (cellState[row].length() != 3) throw new PatternFormatException("Error: Invalid Cell Format - "+cells);
				  for (int col=0; col<3; col++) {
					  //loop through all 9 glider cells
					  if (cellState[row].toCharArray()[col]=='1') {
						  world.setCell(col + startCol, row + startRow, true);
					  } else if (cellState[row].toCharArray()[col]=='0'){
						  world.setCell(col + startCol, row + startRow, false);
					  } else {
						  throw new PatternFormatException("Error: Invalid Cell Format - "+cells);
					  }
				  }
			  }
		  } catch (ArrayIndexOutOfBoundsException A) {
			  throw new PatternFormatException("Error: not enough arguments in cells");
		  }
	   }
}
