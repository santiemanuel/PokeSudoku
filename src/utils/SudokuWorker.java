package utils;

import java.util.concurrent.Callable;

import com.esotericsoftware.kryo.Kryo;

public class SudokuWorker implements Callable<SudokuGen>{

	SudokuGen sudoku;
	int difficulty;
	
	public SudokuWorker(int difficulty){
		this.difficulty = difficulty;
	}
	
	@Override
	public SudokuGen call() throws Exception {
		
		SudokuGen sudo = new SudokuGen(difficulty);
		while (!createUniqueSolution(sudo) && !Thread.currentThread().isInterrupted()){
			sudo = new SudokuGen(difficulty);
		}
		
		return this.sudoku;
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
		kryo.reset();
		if (sudoku.getSolved()){
			return true;
		}
		else return false;
	}

}
