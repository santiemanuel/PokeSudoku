package utils;

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
	
}
