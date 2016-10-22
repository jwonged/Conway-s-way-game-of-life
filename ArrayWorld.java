package uk.ac.cam.dsjw2.oopjava.tick3;

public class ArrayWorld extends WorldImp{
	
	private boolean[][] cells;
	
	public ArrayWorld(int width, int height) {
		super(width, height);
		cells = new boolean[height][width];
	}
	
	public ArrayWorld (ArrayWorld prev) {
		super(prev);
		cells = new boolean[prev.getHeight()][prev.getWidth()];
	}
	
	//Return true if cell (col,row) is alive.
	public boolean getCell(int col,int row) {
		if (row < 0 || row > getHeight() - 1) return false;
		if (col < 0 || col > getWidth() - 1) return false;
		return cells[row][col];
	}

	   //Set a cell to be live or dead.
	 public void setCell(int col, int row, boolean alive) {
		 if (row < 0 || row > getHeight() - 1) return;
		 if (col < 0 || col > getWidth() - 1) return;
		cells[row][col] = alive;
	 }

	   //Step forward one generation.
	 protected WorldImp nextGeneration() {
		//Construct a new TestArrayWorld object to hold the next generation:
	      ArrayWorld world = new ArrayWorld(this);
	      //Populate next "world"
	      for (int row=0; row<getHeight(); row++) {
				for (int col=0; col<getWidth(); col++) {
					world.setCell(col,row, computeCell(col,row));
				}
			}
	      return world;
	 }
}
