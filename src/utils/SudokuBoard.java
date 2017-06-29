package utils;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuBoard.
 */
public class SudokuBoard extends Matrix {
	
	/** The board. */
	private Matrix board;
	
	/** The newnumbers. */
	private ArrayList<Integer> newnumbers;
	
	/** The Constant IMAGECOUNT. */
	private static final int IMAGECOUNT = 377;
	
	/**
	 * Instantiates a new sudoku board.
	 */
	public SudokuBoard(){
		
		//this.newnumbers = new ArrayList<Integer>();
		this.newnumbers = generateMyNumbers();
		this.board = new Matrix();
		this.board.setMynumbers(this.newnumbers);
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
				loadBoardCell(r, c, auxMat);  
			}
		}
			
	}
	
	/**
	 * Generate my numbers according to the images available.
	 *
	 * @return the array list
	 */
	private ArrayList<Integer> generateMyNumbers(){
		Random random = new Random();
		int genNumber;
		
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		
		auxList.add(0);
		while (auxList.size() < 10){
			genNumber = random.nextInt(IMAGECOUNT)+1;
			if (!auxList.contains(genNumber)){
				auxList.add(genNumber);
			}
		}
		
		return(auxList);
	}
	
	/**
	 * Load board cell.
	 *
	 * @param r The row
	 * @param c The column
	 * @param auxMat The auxiliary matrix
	 */
	private void loadBoardCell(int r, int c, int[][] auxMat){
		int auxNum = this.newnumbers.get(auxMat[r][c]);
		String auxStr = Integer.toString(auxNum)+".png";
		this.board.values[r][c] = new PokeVal(auxNum,auxStr);
	}
	
	/**
	 * Gets the newnumbers.
	 *
	 * @return the newnumbers
	 */
	public ArrayList<Integer> getNewnumbers(){
		return (this.newnumbers);
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Matrix getBoard() {
		return (this.board);
	}

	/**
	 * Sets the board.
	 *
	 * @param board The new board
	 */
	public void setBoard(Matrix board) {
		this.board = board;
	}
	

	/**
	 * Show sudoku matrix.
	 */
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

}
