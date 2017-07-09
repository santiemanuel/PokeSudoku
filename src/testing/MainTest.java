package testing;

import utils.*;

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
		
		SudokuGen sudo = new SudokuGen(0);
		sudo.getMyboard().showSudokuMatrix();
		System.out.println();
		
	}

}
