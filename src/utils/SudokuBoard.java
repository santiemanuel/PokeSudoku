package utils;

import java.util.ArrayList;
import java.util.Random;

public abstract class SudokuBoard extends Matrix {
	
	private Matrix board;
	private ArrayList<Integer> newnumbers;
	
	private ArrayList<Integer> generateMyNumbers(){
		Random random = new Random();
		int genNumber;
		
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		
		auxList.add(0);
		while (auxList.size() < 10){
			genNumber = random.nextInt(377)+1;
			if (!auxList.contains(genNumber)){
				auxList.add(genNumber);
			}
		}
		
		return(auxList);
	}
	
	private void loadBoard(int r, int c, int[][] auxMat){
		int auxNum = this.newnumbers.get(auxMat[r][c]);
		String auxStr = Integer.toString(auxNum)+".png";
		this.board.values[r][c] = new PokeVal(auxNum,auxStr);
	}
	
	
	public SudokuBoard(){
		
		this.newnumbers = new ArrayList<Integer>();
		this.newnumbers = generateMyNumbers();
		this.board = new Matrix();
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
				loadBoard(r, c, auxMat);  
			}
		}
			
	}
	
	public void showSudokuMatrix(){
		for (int r=0;r<this.board.ROWS;r++)
		{
			for (int c=0;c<this.board.COLUMNS;c++)
			{
				System.out.print(this.board.values[r][c].getIDPoke()+" ");
			}
			System.out.println();
		}
		
	}
	
	public ArrayList<Integer> getNewnumbers(){
		return (this.newnumbers);
	}

	public Matrix getBoard() {
		return (this.board);
	}

	public void setBoard(Matrix board) {
		this.board = board;
	}
	
	public void rotate()
	{
		Matrix matAux = new Matrix();
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				matAux.setValue(r, c, this.board.getValue(c, r));
			}
		}
		
		
		
		board.setMatrix(matAux.getMatrix());
			
	}
	
	public void SwapVals(int value1, int value2)

	{
		ArrayList<Position> cellsFirst = new ArrayList<Position>();
		ArrayList<Position> cellsSecond = new ArrayList<Position>();
		int row,column;
		
		
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				if (this.board.values[r][c].getIDPoke() == this.newnumbers.get(value1)){
					Position cell = new Position(r,c);
					cellsFirst.add(cell);
				}
				if (this.board.values[r][c].getIDPoke() == this.newnumbers.get(value2)){
					Position cell = new Position(r,c);
					cellsSecond.add(cell);
				}
			}
		}
		
		Integer cellval;
		
		while(!cellsFirst.isEmpty()){
			cellval = this.newnumbers.get(value2);
			Position AuxPos = cellsFirst.get(0);
			cellsFirst.remove(0);
			row = AuxPos.getX();
			column = AuxPos.getY();
			this.board.setValue(row, column, new PokeVal(cellval,cellval.toString()+".png"));
		}
		
		while(!cellsSecond.isEmpty()){
			cellval = this.newnumbers.get(value1);
			Position AuxPos = cellsSecond.get(0);
			cellsSecond.remove(0);
			row = AuxPos.getX();
			column = AuxPos.getY();
			this.board.setValue(row, column, new PokeVal(cellval,cellval.toString()+".png"));
		}
		
	}
	
	public void SwapRows(int row1, int row2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int rows = 0;
		PokeVal temp = null;
		if (group == 0)	rows = 0;
		if (group == 1)	rows = 3;
		if (group == 2)	rows = 6;
		for (int c=0;c<this.COLUMNS;c++)
		{
			ListAux.add(this.board.values[rows+row1][c]);
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			this.board.values[rows+row1][c] = this.board.values[rows+row2][c];
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			temp = ListAux.get(0);
			this.board.setValue(rows+row2, c, temp);
			ListAux.remove(0);
		}
		
	}
	
	public void SwapColumns(int column1, int column2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int columns = 0;
		PokeVal temp = null;
		if (group == 0)	columns = 0;
		if (group == 1)	columns = 3;
		if (group == 2)	columns = 6;
		
		for (int r=0;r<this.ROWS;r++)
		{
			ListAux.add(this.board.values[r][columns+column1]);
		}
		for (int r=0;r<this.ROWS;r++)
		{
			this.board.values[r][columns+column1] = this.board.values[r][columns+column2];
		}
		for (int r=0;r<this.ROWS;r++)
		{
			temp = ListAux.get(0);
			this.board.setValue(r, columns+column2, temp);
			ListAux.remove(0);
		}
	}

}
