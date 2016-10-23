package uk.ac.cam.dsjw2.oopjava.tick3;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.io.PrintWriter;
import java.awt.Graphics;
import java.awt.Color;

public abstract class WorldImp implements World{
	   private int width;
	   private int height;
	   private int generation;

	   protected WorldImp(int width, int height) {
	      this.width = width;
	      this.height = height;
	      this.generation = 0;
	   }
	  
	   protected WorldImp(WorldImp prev) {
	      this.width = prev.width;
	      this.height = prev.height;
	      this.generation = prev.generation + 1;
	   } 

	   public int getWidth() { return this.width; }

	   public int getHeight() { return this.height; }
	  
	   public int getGeneration() { return this.generation; }
	 
	   public int getPopulation() { return 0; }

	   protected String getCellAsString(int col,int row) {
	      return getCell(col,row) ? "#" : "_";
	   }

	   protected Color getCellAsColour(int col,int row) {
	      return getCell(col,row) ? Color.BLACK : Color.WHITE;
	   }  
	   
	   public void draw(Graphics g,int width, int height) {
		   int worldWidth = getWidth();
		   int worldHeight = getHeight();
	  
		   double colScale = (double)width/(double)worldWidth;
		   double rowScale = (double)height/(double)worldHeight;
	   
		   for(int col=0; col<worldWidth; ++col) {
			   for(int row=0; row<worldHeight; ++row) {
				   int colPos = (int)(col*colScale);
				   int rowPos = (int)(row*rowScale);
				   int nextCol = (int)((col+1)*colScale);
				   int nextRow = (int)((row+1)*rowScale);

				   if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
					   g.setColor(getCellAsColour(col, row));
					   g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
				   }
			   } 
		    }  
	   }
	  
	   public World nextGeneration(int log2StepSize) {
	      //Remember to call nextGeneration 2^log2StepSize times
		   WorldImp world = this;
		  
		    //Repeat the statement in curly brackets 2^log2StepSize times
		    for (int i=0; i<(1 << log2StepSize); i++){
		         world = world.nextGeneration();
		     }
		     return world;
	   }

	   public void print(Writer w) {
	      //Use getCellAsString to get text representation of the cell
		   PrintWriter pw = new PrintWriter(w);
		   pw.println("-"); 
		   pw.flush();
		   for (int row = 0; row < height; row++) { 
		      for (int col = 0; col < width; col++) {
		         pw.print(getCellAsString(col,row)); 
		         pw.flush();
		      }
		      System.out.println(); 
		      pw.flush();
		   } 
	   }
	 
	   protected int countNeighbours(int col, int row) {
	      //Compute the number of live neighbours
			int neighbours=0;
			//loop thro the 8 surrounding cells and count neighbours
			for (int r=row-1; r<(row+2); r++) {
				for (int c=col-1; c<(col+2); c++) {
					if (c==col && r==row) continue; //skip for the cell itself
					else if (getCell(c, r)) neighbours++;
				}
			}
			return neighbours;
	   }

	   protected boolean computeCell(int col, int row) {
	      //Compute whether this cell is alive or dead in the next generation
	      //using "countNeighbours"
		 //returns the state of the cell in the next gen
			int neighbours = countNeighbours(col,row);
			
			//fewer than 2 neighbours die (depression), more than 3 neighbours die (too popular) 
			if (neighbours<2 || neighbours>3) return false;
			
			//life remains the same if you only have 2 friends
			else if (neighbours ==2) return getCell(col,row);
			
			//things get exciting when you have 3 friends
			else return true;
	   }

	   //Return true if cell (col,row) is alive.
	   public abstract boolean getCell(int col,int row);

	   //Set a cell to be live or dead.
	   public abstract void setCell(int col, int row, boolean alive);

	   //Step forward one generation.
	   protected abstract WorldImp nextGeneration();
}
