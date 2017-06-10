package utils;

public class SudokuBoard {
	
	private SudokuGen board;
	
	public SudokuBoard(){
		this.board = new SudokuGen();
		int[][] auxMat=new int[][]{
			{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9},				
		};
		
		for (int r=0;r<this.board.ROWS;r++)
		{
			for (int c=0;c<this.board.COLUMNS;c++)
			{
				this.board.values[r][c] = new PokeVal(0,"");
				this.board.values[r][c].setIDPoke(auxMat[r][c]);
			}
		}
		
	}
	
	public void showSudokuMatrix(){
		for (int r=0;r<board.ROWS;r++)
		{
			for (int c=0;c<board.COLUMNS;c++)
			{
				System.out.print(board.values[r][c].getIDPoke()+" ");
			}
			System.out.println();
		}
		
	}

	public SudokuGen getBoard() {
		return board;
	}

	public void setBoard(SudokuGen board) {
		this.board = board;
	}
	
	

}
