package utils;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuGen.
 */
public class SudokuGen extends SudokuBoard{

	/** The random. */
	private Random random;
	
	/** The genboard matrix. */
	private Matrix genboard;
	
	/** The size. */
	private final int SIZE = 9;
	
	/** The mutable matrix for elements. */
	private Boolean [][] mutable;
	
	/** The limit of shuffling. */
	private static final int LIMIT = 5;
	
	/**
	 * Instantiates a new sudoku gen.
	 */
	public SudokuGen(){
		super();
		random = new Random();
		this.mutable = new Boolean[SIZE][SIZE];
		this.genboard = new Matrix(); 
		this.genboard = super.getBoard();
		for (int i=0;i<random.nextInt(LIMIT);i++){
			super.SwapColumns(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapRows(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapVals(random.nextInt(9)+1, random.nextInt(9)+1);
			if (random.nextInt(9) % 2 == 0 ) super.rotate();
		}
		initMutable();
		removeValue();
		updateMutable();
	
	}
	
	/**
	 * Inits the mutable.
	 */
	private void initMutable(){
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				this.mutable[r][c] = true;
			}
		}
	}
	
	/**
	 * Gets the mutable.
	 *
	 * @return the mutable
	 */
	public Boolean[][] getMutable(){
		return (this.mutable);
	}
	
	/**
	 * Valid col. Checks the column
	 *
	 * @param col The col
	 * @param value The value
	 * @return true, if successful
	 */
	public boolean validCol(int col, int value){
		for (int r=0; r<SIZE;r++){
			if (this.genboard.getMatrix()[r][col].getIDPoke() == value){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Valid row. Checks the row
	 *
	 * @param row the row
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean validRow(int row, int value){
		for (int c=0; c<SIZE;c++){
			if (this.genboard.getMatrix()[row][c].getIDPoke() == value){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Valid box. Checks the local square
	 *
	 * @param col The col
	 * @param row The row
	 * @param value The value
	 * @return true, if successful
	 */
	public boolean validBox(int col, int row, int value){
		int boxRow = row / 3;
		int boxCol = col / 3;
		
		int startRow = (boxRow * 3);
		int startCol = (boxCol * 3);
		
		for (int r = startRow; r<= (startRow+3)-1;r++){
			for (int c = startCol; c<= (startCol+3)-1;c++){
				if (this.genboard.getMatrix()[r][c].getIDPoke() == value)
					return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Update mutable matrix checking for zeroes at the matrix.
	 */
	private void updateMutable(){
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				if (this.genboard.getMatrix()[r][c].getIDPoke() != 0 ){
					this.mutable[r][c] = false;
				}
			}
		}
	}
	
	/**
	 * Removes 40 cell values from the matrix.
	 */
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
	
	/**
	 * Checks if is valid move.
	 *
	 * @param row The row
	 * @param col The col
	 * @param value The value
	 * @return true, if is valid move
	 */
	public boolean isValidMove(int row, int col, int value){
		if (value == 0) return true;
		if ((this.validCol(col, value)) && (this.validRow(row, value)) && (this.validBox(col, row, value))){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is cell mutable.
	 *
	 * @param row The row
	 * @param col The col
	 * @return true, if is cell mutable
	 */
	public boolean isCellMutable(int row, int col){
		return this.mutable[row][col];
	}
	
	/**
	 * Make move.
	 *
	 * @param row The row
	 * @param col The col
	 * @param value The value
	 */
	public void makeMove(int row, int col, int value){
		if (this.isValidMove(row, col, value) && this.isCellMutable(row, col)){
			Integer num = new Integer(value);
			this.genboard.getMatrix()[row][col].setIDPoke(value);
			this.genboard.getMatrix()[row][col].setNameImg(num.toString()+".png");
			
		}
	}
	
	
	
	
}
