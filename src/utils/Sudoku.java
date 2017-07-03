package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO: Auto-generated Javadoc
/**
 * The Class Sudoku.
 */
public class Sudoku {
	
	/** The sudoku. */
	private SudokuGen sudoku;
	private int difficulty;
	
	/**
	 * Instantiates a new sudoku.
	 */
	public Sudoku(int difficulty){
		this.difficulty = difficulty;
		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<Callable<SudokuGen>> callTasks = new ArrayList<Callable<SudokuGen>>();
		for (int i=0;i<2;i++) callTasks.add(new SudokuWorker(this.difficulty));
		
		final long start = System.nanoTime();
		
		try {
			this.sudoku = executor.invokeAny(callTasks);
			executor.shutdownNow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final long duration = System.nanoTime() - start;
		System.out.println(duration/1000000+" ms");	
	}
		
	
	/**
	 * Sets the sudoku.
	 *
	 * @param sudoku the new sudoku
	 */
	public void setSudoku(SudokuGen sudoku){
		this.sudoku = sudoku;
	}
	
	/**
	 * Gets the sudoku.
	 *
	 * @return the sudoku
	 */
	public SudokuGen getSudoku(){
		return (this.sudoku);
	}
	
}
