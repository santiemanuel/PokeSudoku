package utils;

public class CellPosition {
	protected int row;
	protected int column;
	
	public CellPosition(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	

}
