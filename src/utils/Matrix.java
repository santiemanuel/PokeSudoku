package utils;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Matrix.
 */
public class Matrix implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -672406231147569867L;

	/** The rows. */
	protected final int ROWS = 9;
	
	/** The columns. */
	protected final int COLUMNS = 9;
	
	protected final int GROUPCNT = 3;
	
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
	
	public Matrix(Matrix mymatrix){
		this.values = new PokeVal[ROWS][COLUMNS];
		for (int i=0;i<ROWS*COLUMNS;i++){
			int row = i / ROWS;
			int col = i % COLUMNS;
			this.values[row][col] = new PokeVal(mymatrix.getValue(row, col).getIDPoke(),mymatrix.getValue(row, col).getNameImg());
		}
		this.mynumbers = mymatrix.getMynumbers();
	}
	
	public ArrayList<Integer> getMynumbers() {
		return mynumbers;
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
		int rows = group * GROUPCNT;

		for (int c=0;c<this.COLUMNS;c++)
		{
			ListAux.add(this.values[rows+row1][c]);
			this.values[rows+row1][c] = this.values[rows+row2][c];
			this.setValue(rows+row2, c, ListAux.get(c));
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
		int columns = group * GROUPCNT;
		
		for (int r=0;r<this.ROWS;r++)
		{
			ListAux.add(this.values[r][columns+column1]);
			this.values[r][columns+column1] = this.values[r][columns+column2];
			this.setValue(r, columns+column2, ListAux.get(r));
		}
	}
	
	public void SwapColumnBox(int group1, int group2){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int colgroup1 = group1 * GROUPCNT;
		int colgroup2 = group2 * GROUPCNT;
		
		for (int c=0;c<GROUPCNT;c++){
			for (int r=0;r<this.ROWS;r++)
			{
				ListAux.add(this.values[r][colgroup1+c]);
				this.values[r][colgroup1+c] = this.values[r][colgroup2+c];
				this.setValue(r, colgroup2+c, ListAux.get(r));
			}
			ListAux.removeAll(ListAux);
		}
		
	}
	
	public void SwapRowBox(int group1, int group2){
		ArrayList<PokeVal> ListAux = new ArrayList<PokeVal>();
		int rowgroup1 = group1 * GROUPCNT;
		int rowgroup2 = group2 * GROUPCNT;
		
		for (int r=0;r<GROUPCNT;r++){
			for (int c=0;c<this.COLUMNS;c++)
			{
				ListAux.add(this.values[rowgroup1+r][c]);
				this.values[rowgroup1+r][c] = this.values[rowgroup2+r][c];
				this.setValue(rowgroup2+r, c, ListAux.get(c));
			}
			ListAux.removeAll(ListAux);
		}
		
	}
	
	
	
}
