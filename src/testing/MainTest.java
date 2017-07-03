package testing;

import utils.Sudoku;

// TODO: Auto-generated Javadoc
/**
 * The Class MainTest.
 */
public class MainTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Sudoku sudo = new Sudoku(2);
		sudo.getSudoku().getMyboard().showSudokuMatrix();

		System.out.println();
		
		

	}

}
