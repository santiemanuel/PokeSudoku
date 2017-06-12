package testing;
import utils.*;

public class MainTest {

	public static void main(String[] args) {
		SudokuBoard sudo = new SudokuBoard();
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		PokeVal val1 = new PokeVal(2,"",null);
		PokeVal val2 = new PokeVal(5,"",null);
		
		sudo.setBoard(sudo.getBoard().SwapVals(val1, val2));
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		sudo.setBoard(sudo.getBoard().SwapRows(1, 3, 1));
		
		sudo.showSudokuMatrix();
		

	}

}
