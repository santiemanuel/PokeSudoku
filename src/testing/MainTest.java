package testing;
import utils.*;

public class MainTest {

	public static void main(String[] args) {
		SudokuBoard sudo = new SudokuBoard();
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		PokeVal val1 = new PokeVal(2,"");
		PokeVal val2 = new PokeVal(5,"");
		
		sudo.SwapVals(val1, val2);
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		//sudo.rotate();
		
		sudo.SwapColumns(0, 1, 1);
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		
		

	}

}
