package uk.ac.cam.dsjw2.oopjava.tick3;

import uk.ac.cam.dsjw2.prejava.ex1.PackedLong;

public class PackedWorld extends WorldImp {
	
	private long cells;
	
	public PackedWorld() {
		super(8,8);
		cells = 0;
	}
	
	public PackedWorld(PackedWorld prev) {
		super(prev);
		cells = 0;
	}
	
	//Return true if cell (col,row) is alive.
	public boolean getCell(int col,int row) {
		if (col>7 || row>7 || col<0 || row<0) return false;
		else return PackedLong.get( cells, col + (8*row));
	}

	// Will be implemented by child class. Set a cell to be live or dead.
	public void setCell(int col, int row, boolean alive) {
		if (col>7 || row>7 || col<0 || row<0) return;
		else cells = PackedLong.set(cells, col+(8*row), alive);
	}

	// Will be implemented by child class. Step forward one generation.
	protected WorldImp nextGeneration() {
		PackedWorld world = new PackedWorld(this);
	      //Populate next "world"
	      for (int row=0; row<getHeight(); row++) {
				for (int col=0; col<getWidth(); col++) {
					world.setCell(col,row, computeCell(col,row));
				}
			}
	      return world;
	}
}
