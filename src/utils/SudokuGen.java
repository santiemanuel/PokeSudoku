package utils;

import java.util.Random;

public class SudokuGen extends SudokuBoard{

	private Random random;
	private Matrix genboard;
	private static int LIMIT = 5;
	public SudokuGen(){
		super();
		random = new Random();
		for (int i=0;i<random.nextInt(LIMIT);i++){
			super.SwapColumns(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapRows(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapVals(random.nextInt(9)+1, random.nextInt(9)+1);
		}
		this.genboard = new Matrix();
		this.genboard = super.getBoard();
		removeValue();
	
	}
	
	private void removeValue(){
		int spaces = 40;
		int index = 0;
		
		while (index < spaces){
			int r = random.nextInt(9);
			int c = random.nextInt(9);
			if (this.genboard.getMatrix()[r][c].getIDPoke() != 0){
				index++;
				this.genboard.getMatrix()[r][c].setIDPoke(0);
				this.genboard.getMatrix()[r][c].setNameImg("0.png");
			}
		}
	}
	
	
	
	
}
