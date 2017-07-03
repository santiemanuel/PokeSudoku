package utils;

import java.util.concurrent.Callable;

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

		//copy the sudoku puzzle before using it
		this.sudoku = new SudokuGen(sudoku);
		Position availableMove = sudoku.getFirstAvailableMove();
		while (availableMove != null && !Thread.currentThread().isInterrupted()){
			int row,col;
			row = availableMove.getX();
			col = availableMove.getY();
			Integer value = sudoku.getValidvalue(row, col, 0);
			sudoku.makeMove(row, col, value);
			availableMove = sudoku.getFirstAvailableMove();
		}

		if (sudoku.getSolved()){
			return true;
		}
		else{
			return false;
		}
	}

}
