package utils;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuGen.
 */
public class SudokuGen{

	/** The random. */
	private Random random;
	
	/** The genboard matrix. */
	private Matrix genboard;
	
	private SudokuBoard myboard;
	
	/** The size. */
	private final int SIZE = 9;
	
	/** The mutable matrix for elements. */
	private Boolean [][] mutable;
	
	private Boolean Solved;
	
	private ArrayList<Integer> [][] validvalues;
	
	private ArrayList<Position> mutableCells, lockedCells;
	
	/** The limit of shuffling. */
	private static final int LIMIT = 5;
	
	/**
	 * Instantiates a new sudoku gen.
	 */
	public SudokuGen(){
		this.myboard = new SudokuBoard();
		random = new Random();
		this.mutable = new Boolean[SIZE][SIZE];
		this.genboard = this.myboard.getBoard();
		this.setSolved(false);
		this.setMutableCells(new ArrayList<Position>());
		this.setLockedCells(new ArrayList<Position>());
		for (int i=0;i<random.nextInt(LIMIT);i++){
			this.genboard.SwapColumns(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			this.genboard.SwapRows(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			this.genboard.SwapVals(random.nextInt(9)+1, random.nextInt(9)+1);
			if (random.nextInt(9) % 2 == 0 ) this.genboard.rotate();
		}
		initMutables();
		removeValues();
		updateMutable();
		updateLocked();
		loadValidvalues();
	
	}
	
	public SudokuGen(SudokuGen sudoku){
		this.genboard = sudoku.genboard;
		this.lockedCells = sudoku.lockedCells;
		this.mutableCells = sudoku.mutableCells;
		this.mutable = sudoku.mutable;
		this.myboard = sudoku.myboard;
		this.Solved = sudoku.Solved;
		this.validvalues = sudoku.validvalues;
		
	}
	
	public boolean isSolved(){
	
		for (int i=0;i<this.getMutableCells().size();i++)
		{
				int row = this.getMutableCells().get(i).getX();
				int col = this.getMutableCells().get(i).getY();
				if (this.genboard.getMatrix()[row][col].getIDPoke() == 0){
					return false;
				}
		}
		return true;
	}
	
	/**
	 * Inits the valid values.
	 */
	@SuppressWarnings("unchecked")
	private void initValidvalues(){
		this.validvalues = new ArrayList[SIZE][SIZE];
		for (int i=0;i<SIZE*SIZE;i++)
		{
				int row = i / SIZE;
				int col = i % SIZE;
				this.validvalues[row][col] = new ArrayList<Integer>();
		}
	}
	
	public void loadValidvalues(){
		ArrayList<Integer> validnumbers = this.myboard.getNewnumbers();
		int row,col;
		initValidvalues();
		for (int i=0;i<this.getMutableCells().size();i++){
			row = this.getMutableCells().get(i).getX();
			col = this.getMutableCells().get(i).getY();
			for (int j = 1; j < validnumbers.size();j++){
				int number = validnumbers.get(j);
				if (this.genboard.getMatrix()[row][col].getIDPoke() == 0){
					if (isValidMove(row, col, number, 0))
						this.validvalues[row][col].add(number);
				}
				
			}
			
		}
	}
	
	public Matrix getGenboard() {
		return genboard;
	}

	public void setGenboard(Matrix genboard) {
		this.genboard = genboard;
	}

	public SudokuBoard getMyboard() {
		return myboard;
	}

	public void setMyboard(SudokuBoard myboard) {
		this.myboard = myboard;
	}

	public void ShowValidvalues(int r, int c){
		for (int j=0;j<this.validvalues[r][c].size();j++){
			System.out.print(getValidvalue(r, c, j)+" ");
		}
		System.out.println();
	}
	
	public void ShowAllValidvalues(){
		for (int i=0;i<this.getMutableCells().size();i++)
		{
				int row = this.getMutableCells().get(i).getX();
				int col = this.getMutableCells().get(i).getY();
				System.out.println("Valores para ("+row+", "+col+"): ");
				ShowValidvalues(row, col);
		}
	}
	
	public Integer getValidvalue(int r, int c, int index){
		return (this.validvalues[r][c].get(index));
	}
	
	/**
	 * Inits the mutable.
	 */
	private void initMutables(){
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
	
	public Position getFirstAvailableMove(){
		int row,col;
		int index = 0;
		
		while (index < this.getMutableCells().size()){
			row = this.getMutableCells().get(index).getX();
			col = this.getMutableCells().get(index).getY();
			if (this.validvalues[row][col].size() == 1) return this.getMutableCells().get(index);
			index++;
		}
		return null;
		
	}
	
	/**
	 * Valid col. Checks the column
	 *
	 * @param col The col
	 * @param value The value
	 * @return true, if successful
	 */
	public boolean validCol(int col, int value){
		if (value == 0) return true;
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
		if (value == 0) return true;
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
		
		if (value == 0) return true;
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
	
	private void updateLocked(){
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				if (this.mutable[r][c] == false){
					this.lockedCells.add(new Position(r,c));
				}
			}
		}
	}
	
	/**
	 * Removes 40 cell values from the matrix.
	 */
	private void removeValues(){
		int spaces = 40;
		int index = 0;
		int r, c;
		
		while (index < spaces){
			r = random.nextInt(9);
			c = random.nextInt(9);
			if (this.genboard.getMatrix()[r][c].getIDPoke() != 0){
				this.mutableCells.add(new Position(r,c));
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
	public boolean isValidMove(int row, int col, int value, int code){
		if (value == 0 && code == 1) return true;
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
		if (this.isValidMove(row, col, value, 1) && this.isCellMutable(row, col)){
			Integer num = new Integer(value);
			this.genboard.getMatrix()[row][col].setIDPoke(value);
			this.genboard.getMatrix()[row][col].setNameImg(num.toString()+".png");
			this.setSolved(isSolved()); 
		}
	}

	public ArrayList<Position> getMutableCells() {
		return mutableCells;
	}

	public void setMutableCells(ArrayList<Position> mutableCells) {
		this.mutableCells = mutableCells;
	}

	public ArrayList<Position> getLockedCells() {
		return lockedCells;
	}

	public void setLockedCells(ArrayList<Position> lockedCells) {
		this.lockedCells = lockedCells;
	}

	public Boolean getSolved() {
		return Solved;
	}

	public void setSolved(Boolean solved) {
		Solved = solved;
	}
	
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	
	
}