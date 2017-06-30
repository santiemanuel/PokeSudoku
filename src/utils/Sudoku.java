package utils;

//External library to copy an object
import com.esotericsoftware.kryo.Kryo;

// TODO: Auto-generated Javadoc
/**
 * The Class Sudoku.
 */
public class Sudoku {
	
	/** The sudoku. */
	private SudokuGen sudoku;
	
	/**
	 * Instantiates a new sudoku.
	 */
	public Sudoku(int difficulty){
		
		SudokuGen auxSudoku = new SudokuGen(difficulty);
		
		while (!createUniqueSolution(auxSudoku)){
			auxSudoku = new SudokuGen(difficulty);
		}
	}
		
	/**
	 * Creates the unique solution.
	 *
	 * @param sudoku the sudoku puzzle
	 * @return true, if solution is unique
	 */
	private boolean createUniqueSolution(SudokuGen sudoku){

		Kryo kryo = new Kryo();
		
		//copy the sudoku puzzle before using it
		this.sudoku = kryo.copy(sudoku);
		Position availableMove = sudoku.getFirstAvailableMove();
		while (availableMove != null){
			int row,col;
			row = availableMove.getX();
			col = availableMove.getY();
			Integer value = sudoku.getValidvalue(row, col, 0);
			sudoku.makeMove(row, col, value);
			availableMove = sudoku.getFirstAvailableMove();
		}
		if (sudoku.getSolved())return true;
		else return false;
	}
	
	/**
	 * Sets the sudoku.
	 *
	 * @param sudoku the new sudoku
	 */
	public void setSudoku(SudokuGen sudoku){
		this.sudoku = sudoku;
	}
	
	/**
	 * Gets the sudoku.
	 *
	 * @return the sudoku
	 */
	public SudokuGen getSudoku(){
		return (this.sudoku);
	}
	
}
