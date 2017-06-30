package utils;

import com.esotericsoftware.kryo.Kryo;

public class Sudoku {
	
	private SudokuGen sudoku;
	
	public Sudoku(){
		
		SudokuGen auxSudoku = new SudokuGen();
		
		while (!createUniqueSolution(auxSudoku)){
			auxSudoku = new SudokuGen();
		}
	}
		
	private boolean createUniqueSolution(SudokuGen sudoku){

		Kryo kryo = new Kryo();
		this.sudoku = kryo.copy(sudoku);
		Position availableMove = sudoku.getFirstAvailableMove();
		while (availableMove != null){
			int row,col;
			row = availableMove.getX();
			col = availableMove.getY();
			Integer value = sudoku.getValidvalue(row, col, 0);
			sudoku.makeMove(row, col, value);
			sudoku.loadValidvalues();
			availableMove = sudoku.getFirstAvailableMove();
		}
		if (sudoku.getSolved())return true;
		else return false;
	}
	
	public void setSudoku(SudokuGen sudoku){
		this.sudoku = sudoku;
	}
	
	public SudokuGen getSudoku(){
		return (this.sudoku);
	}
	
}
