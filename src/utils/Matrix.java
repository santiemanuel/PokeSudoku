package utils;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Matrix.
 */
public class Matrix {
	
	/** The rows. */
	protected final int ROWS = 9;
	
	/** The columns. */
	protected final int  COLUMNS = 9;
	
	/** The values. */
	protected PokeVal values[][];
	
	protected ArrayList<Integer> mynumbers;
	
	/**
	 * Instantiates a new matrix.
	 */
	public Matrix()
	{
		this.values = new PokeVal[ROWS][COLUMNS];
	}
	
	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows()
	{
		return (ROWS);
	}
	
	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public int getColumns()
	{
		return (COLUMNS);
	}
	
	/**
	 * Gets the value.
	 *
	 * @param row The row
	 * @param column The column
	 * @return the value
	 */
	public PokeVal getValue(int row, int column)
	{	
		return(this.values[row][column]); 
	}
	
	/**
	 * Sets the matrix.
	 *
	 * @param board The new matrix
	 */
	public void setMatrix(PokeVal[][] board){
		this.values = board;
	}
	
	/**
	 * Gets the matrix.
	 *
	 * @return the matrix
	 */
	public PokeVal[][] getMatrix(){
		return (this.values);
	}
	
	/**
	 * Sets the value.
	 *
	 * @param row The row
	 * @param column The column
	 * @param value The value
	 */
	public void setValue(int row, int column, PokeVal value)
	{
		this.values[row][column] = value;
	}
	
	public void setMynumbers(ArrayList<Integer> numbers){
		this.mynumbers = numbers;
	}
	
	/**
	 * Rotates the matrix (transpose).
	 */
	public void rotate()
	{
		Matrix matAux = new Matrix();
		matAux.setMynumbers(this.mynumbers);
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				matAux.setValue(r, c, this.getValue(c, r));
			}
		}
		setMatrix(matAux.getMatrix());
			
	}
	
	/**
	 * Swap values in the matrix.
	 *
	 * @param value1 The first value
	 * @param value2 The second value
	 */
	public void SwapVals(int value1, int value2)

	{
		ArrayList<Position> cellsFirst = new ArrayList<Position>();
		ArrayList<Position> cellsSecond = new ArrayList<Position>();
		int row,column;
		
		//Loads two Position ArrayLists looking for the values 
		for (int r=0;r<this.ROWS;r++)
		{
			for (int c=0;c<this.COLUMNS;c++)
			{
				if (this.values[r][c].getIDPoke() == this.mynumbers.get(value1)){
					Position cell = new Position(r,c);
					cellsFirst.add(cell);
				}
				if (this.values[r][c].getIDPoke() == this.mynumbers.get(value2)){
					Position cell = new Position(r,c);
					cellsSecond.add(cell);
				}
			}
		}
		
		Integer cellval;
		
		//Puts the value2 value where value1 values are
		while(!cellsFirst.isEmpty()){
			cellval = this.mynumbers.get(value2);
			Position AuxPos = cellsFirst.get(0);
			cellsFirst.remove(0);
			row = AuxPos.getX();
			column = AuxPos.getY();
			this.setValue(row, column, new PokeVal(cellval,cellval.toString()+".png"));
		}
		
		//Puts the value1 value where value2 values are
		while(!cellsSecond.isEmpty()){
			cellval = this.mynumbers.get(value1);
			Position AuxPos = cellsSecond.get(0);
			cellsSecond.remove(0);
			row = AuxPos.getX();
			column = AuxPos.getY();
			this.setValue(row, column, new PokeVal(cellval,cellval.toString()+".png"));
		}
		
	}
	
	/**
	 * Swap rows.
	 *
	 * @param row1 The row 1
	 * @param row2 The row 2
	 * @param group The group of rows. The matrix has 3 groups
	 */
	public void SwapRows(int row1, int row2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int rows = 0;
		PokeVal temp = null;
		if (group == 0)	rows = 0;
		if (group == 1)	rows = 3;
		if (group == 2)	rows = 6;
		for (int c=0;c<this.COLUMNS;c++)
		{
			ListAux.add(this.values[rows+row1][c]);
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			this.values[rows+row1][c] = this.values[rows+row2][c];
		}
		for (int c=0;c<this.COLUMNS;c++)
		{
			temp = ListAux.get(0);
			this.setValue(rows+row2, c, temp);
			ListAux.remove(0);
		}
		
	}
	
	/**
	 * Swap columns.
	 *
	 * @param column1 The column 1
	 * @param column2 The column 2
	 * @param group The group of columns. The matrix has 3 groups
	 */
	public void SwapColumns(int column1, int column2, int group){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int columns = 0;
		PokeVal temp = null;
		if (group == 0)	columns = 0;
		if (group == 1)	columns = 3;
		if (group == 2)	columns = 6;
		
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
			this.setValue(r, columns+column2, temp);
			ListAux.remove(0);
		}
	}
	
	
	
}
