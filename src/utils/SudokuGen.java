package utils;

import java.util.ArrayList;

public class SudokuGen extends Matrix {
	
	public void rotate()
	{
		Matrix matAux = new Matrix();
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				matAux.values[r][c] = this.values[c][r];
			}
		}
		this.values = matAux.values;
			
	}
	
	public SudokuGen SwapVals(PokeVal value1, PokeVal value2)

	{
		ArrayList<CellPosition> cellsFirst = new ArrayList<CellPosition>();
		ArrayList<CellPosition> cellsSecond = new ArrayList<CellPosition>();
		int row,column;
		
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				if (this.values[r][c].getIDPoke() == value1.getIDPoke()){
					CellPosition cell = new CellPosition(r,c);
					cellsFirst.add(cell);
				}
				if (this.values[r][c].getIDPoke() == value2.getIDPoke()){
					CellPosition cell = new CellPosition(r,c);
					cellsSecond.add(cell);
				}
			}
		}
		
		while(!cellsFirst.isEmpty()){
			CellPosition AuxPos = cellsFirst.get(0);
			cellsFirst.remove(0);
			row = AuxPos.getRow();
			column = AuxPos.getColumn();
			this.values[row][column] = value2;
		}
		
		while(!cellsSecond.isEmpty()){
			CellPosition AuxPos = cellsSecond.get(0);
			cellsSecond.remove(0);
			row = AuxPos.getRow();
			column = AuxPos.getColumn();
			this.values[row][column] = value1;
		}
		
		return (this);
			
	}
	
	public SudokuGen SwapRows(int row1, int row2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int rows = 0;
		PokeVal temp = null;
		if (group == 1)
			rows = 0;
		if (group == 2)
			rows = 3;
		if (group == 3)
			rows = 6;
		for (int c=0;c<this.COLUMNS;c++)
		{
			ListAux.add(this.values[rows+row1-1][c]);
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			this.values[rows+row1-1][c] = this.values[rows+row2-1][c];
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			temp = ListAux.get(0);
			this.values[rows+row2-1][c] = temp;
			ListAux.remove(0);
		}
		
		return (this);
	}
	
	public void SwapColumns(int column1, int column2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int columns = 0;
		PokeVal temp = null;
		switch (group){
			case 1: columns = 0;
			case 2: columns = 3;
			case 3: columns = 6;
		}
		for (int r=0;r<this.ROWS;r++)
		{
			ListAux.add(this.values[r][columns+column1]);
		}
		for (int r=0;r<this.ROWS;r++)
		{
			this.values[r][columns+column1] = this.values[r][columns+column2];
		}
		for (int r=0;r<this.ROWS;r++)
		{
			temp = ListAux.get(0);
			this.values[r][columns+column2] = temp;
			ListAux.remove(0);
		}
	}
	
	
}
