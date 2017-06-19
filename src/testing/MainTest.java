package testing;
import java.util.ArrayList;

import utils.*;

public class MainTest {

	public static void main(String[] args) {
		SudokuBoard sudo = new SudokuBoard();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers = sudo.getNewnumbers();
		int index = 0;
		while (index < numbers.size()){
			System.out.print(numbers.get(index)+" ");
			index++;
		}
		System.out.println();
		System.out.println();
		
		sudo.showSudokuMatrix();
		
		System.out.println();
		
		sudo.SwapVals(4, 5);
		
		
		
		System.out.println();
		
		sudo.showSudokuMatrix();
		
		
		

	}

}
