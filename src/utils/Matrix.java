package utils;

import java.util.ArrayList;

import ui.Icon;

public class Matrix {
	protected final int ROWS = 9;
	protected final int  COLUMNS = 9;
	protected PokeVal values[][];
	
	public Matrix()
	{
		this.values = new PokeVal[ROWS][COLUMNS];
	}
	
	public int getRows()
	{
		return (ROWS);
	}
	
	public int getColumns()
	{
		return (COLUMNS);
	}
	
	public PokeVal getValue(int row, int column)
	{	
		return(this.values[row][column]); 
	}
	
	public void setValue(int row, int column, PokeVal value)
	{
		this.values[row][column] = value;
	}
	
}
