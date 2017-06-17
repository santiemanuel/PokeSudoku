package testing;
import utils.*;

public class MainTest {

	public static void main(String[] args) {
		SudokuBoard sudo = new SudokuBoard();
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		sudo.SwapColumns(0, 1, 0);
		
		System.out.println();
		
		sudo.showSudokuMatrix();
		
		
		

	}

}
