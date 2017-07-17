package utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class Sudoku.
 */
public class Sudoku implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** The sudoku. */
	private transient SudokuGen sudoku;
	private transient int difficulty;
	private HashMap<Integer, ArrayList<SudokuGen>> sudokulist;

	
	public Sudoku(){
		sudokulist = new HashMap<Integer, ArrayList<SudokuGen>>();
		for (int i=0;i<4;i++){
			sudokulist.put(i, new ArrayList<SudokuGen>());
		}
		
		File dirPuzzles = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()+File.separator+"data");
		if (!dirPuzzles.exists()){
			dirPuzzles.mkdir();
		}
		System.out.println(dirPuzzles);
		
		File filePuzzles = new File(dirPuzzles+File.separator+"puzzles.ser");
		
		if (filePuzzles.exists()){
			
		} else
			try {
				filePuzzles.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
	}
	
	
	/**
	 * Instantiates a new sudoku.
	 */
	public void newSudoku(int difficulty){

		this.difficulty = difficulty;
		ArrayList<SudokuGen> puzzlesforDiff = sudokulist.get(difficulty);
		int puzzleCount = puzzlesforDiff.size();
		if (puzzleCount == 0){
			System.out.println("No existen puzzles generados. Generando uno nuevo.");
			this.sudoku = genUnique(this.difficulty);
		}
		else{
			System.out.println("Existen "+puzzleCount+" puzzles disponibles, cargando.");
			this.sudoku = puzzlesforDiff.get(puzzleCount-1);
			puzzlesforDiff.remove(puzzleCount-1);
			sudokulist.remove(difficulty);
			sudokulist.put(difficulty, puzzlesforDiff);
		}
	}
	
	private SudokuGen genUnique(int difficulty){
		
		final long start = System.nanoTime();
		ExecutorService executor = Executors.newCachedThreadPool();

		List<Callable<SudokuGen>> callTasks = new ArrayList<Callable<SudokuGen>>();
		for (int i=0;i<2;i++) callTasks.add(new SudokuWorker(difficulty));
		
		try {
			SudokuGen generatedsudoku = executor.invokeAny(callTasks);
			executor.shutdown();
			final long duration = System.nanoTime() - start;
			System.out.println("Dificultad: "+difficulty+" Se genero en: "+duration/1000000+" ms");
			executor.awaitTermination(2, TimeUnit.SECONDS);
			return generatedsudoku;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			executor.shutdownNow();
		}
		
		return null;
		
	}
	
	public void bgSudoku(){
		for (int i=0;i<4;i++){
			SudokuGen futurePuzzle = genUnique(i); //generate new puzzle with diff i
			ArrayList<SudokuGen> sudokuDiff = sudokulist.get(i); //get the list of puzzles for diff i
			sudokulist.remove(i); //remove the list from the hashmap
			sudokuDiff.add(futurePuzzle); //add the new puzzle to the list
			System.out.println("Puzzles disponibles para dificultad "+i+": "+sudokuDiff.size());
			sudokulist.put(i, sudokuDiff); //add the list back to the hashset
		}
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