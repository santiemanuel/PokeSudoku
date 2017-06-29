package utils;

public class Sudoku {
	
	private SudokuGen sudoku;
	
	public Sudoku(){
		
		SudokuGen auxSudoku = new SudokuGen();
		
		while (!createUniqueSolution(auxSudoku)){
			auxSudoku = new SudokuGen();
		}
		this.sudoku.getMyboard().showSudokuMatrix();
	}
		
	private boolean createUniqueSolution(SudokuGen sudoku){

		this.sudoku = new SudokuGen(sudoku);
		this.sudoku.getMyboard().showSudokuMatrix();
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
		if (sudoku.getSolved()){
			
			System.out.println("Unique solution");
			return true;
		}
		else{
			System.out.println("Solution is not unique");
			return false;
		}
	}
	
	public void setSudoku(SudokuGen sudoku){
		this.sudoku = sudoku;
	}
	
	public SudokuGen getSudoku(){
		return (this.sudoku);
	}
	
}
