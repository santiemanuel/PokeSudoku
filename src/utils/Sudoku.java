package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	private transient File dirPuzzles;
	private static final int PUZZLECOUNT = 10;
	private static final int DIFFCOUNT = 4;
	private HashMap<Integer, ArrayList<SudokuGen>> sudokulist;
	private Player player;

	/**
	 * Instantiates a new sudoku.
	 * Creates the folder data if not found, and load the file puzzles.ser if exists
	 */
	public Sudoku(Player playername){
		sudokulist = new HashMap<Integer, ArrayList<SudokuGen>>();
		for (int i=0;i<DIFFCOUNT;i++){
			sudokulist.put(i, new ArrayList<SudokuGen>());
		}
		
		this.player = playername;
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); 
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path+"data";
		dirPuzzles = new File(path);;
		
		System.out.println("Directorio data: "+dirPuzzles.toString());
		if (!dirPuzzles.exists()){
			dirPuzzles.mkdir();
		}
		
		String name = player.getName();
		
		File filePuzzles = new File(dirPuzzles+File.separator+"puzzles"+name+".ser");
		
		System.out.println("Archivo puzzle: "+filePuzzles);
		
		if (filePuzzles.exists()){
			sudokulist = load(filePuzzles);
			System.out.println("Puzzles cargados con exito");
		}
			
	}

	/**
	 * Load the file and deserialize it
	 *
	 * @param filepuzzle the filepuzzle
	 * @return the hash map
	 */
	public HashMap<Integer, ArrayList<SudokuGen>> load(File filepuzzle){
		try{
			System.out.println("Abriendo archivo de puzzles");
			FileInputStream inputstream = new FileInputStream(filepuzzle);
			ObjectInputStream objectstream = new ObjectInputStream(inputstream);
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, ArrayList<SudokuGen>> puzzles = (HashMap<Integer, ArrayList<SudokuGen>>) 
					objectstream.readObject();
			
			objectstream.close();
			inputstream.close();
			
			return puzzles;
		} catch (IOException e){
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Serialize the HashMap and save it to puzzles.ser
	 */
	public void save(Player playername){
		System.out.println("Guardando estado");
		String name = playername.getName();
		File puzzlefile = new File(dirPuzzles+File.separator+"puzzles"+name+".ser");
		try {
			FileOutputStream puzzlestream = new FileOutputStream(puzzlefile, false);
			OutputStream buffer = new BufferedOutputStream(puzzlestream);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(sudokulist);
			output.flush();
			output.close();
			System.out.println("Puzzles guardados.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Monitor the remaining amount of generated puzzles.
	 */
	public void savePuzzles(){
		int currentcount = 0;
		
		for (int i=0;i<DIFFCOUNT;i++){
			ArrayList<SudokuGen> sudokuDiff = sudokulist.get(i);
			if (sudokuDiff.size() == 0) currentcount++;
		}
		
		System.out.println("Dificultades sin puzzles: "+currentcount);
		save(player); //Save the updated file
	}
	
	/**
	 * Loads a sudoku to the board, if doesn't exist, then generate one
	 */
	public void newSudoku(int difficulty){

		this.difficulty = difficulty;
		ArrayList<SudokuGen> puzzlesforDiff = sudokulist.get(difficulty);
		int puzzleCount = puzzlesforDiff.size();
		if (puzzleCount == 0){
			System.out.println("No existen puzzles generados. Generando uno nuevo.");
			this.sudoku = genUnique(this.difficulty,this.player.getUnlockedmonster());
		}
		else{
			System.out.println("Existen "+puzzleCount+" puzzles disponibles, cargando.");
			this.sudoku = puzzlesforDiff.get(puzzleCount-1);
			puzzlesforDiff.remove(puzzleCount-1);
			this.sudokulist.remove(difficulty);
			this.sudokulist.put(difficulty, puzzlesforDiff);
		}
	}
	
	/**
	 * Generates a puzzle for the given difficulty.
	 *
	 * @param difficulty the difficulty
	 * @return the sudoku gen
	 */
	private SudokuGen genUnique(int difficulty, ArrayList<Integer> unlockedimgs){
		
		final long start = System.nanoTime();
		ExecutorService executor = Executors.newCachedThreadPool();

		List<Callable<SudokuGen>> callTasks = new ArrayList<Callable<SudokuGen>>();
		for (int i=0;i<2;i++) callTasks.add(new SudokuWorker(difficulty, unlockedimgs));
		
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
	
	/**
	 * Called when the game is on pause state.
	 */
	public void bgSudoku(){
		for (int i=0;i<DIFFCOUNT;i++){
			ArrayList<SudokuGen> sudokuDiff = sudokulist.get(i); //get the list of puzzles for diff i
			if (sudokuDiff.size() < PUZZLECOUNT){
				SudokuGen futurePuzzle = genUnique(i,this.player.getUnlockedmonster()); //generate new puzzle with diff i
				this.sudokulist.remove(i); //remove the list from the hashmap
				sudokuDiff.add(futurePuzzle); //add the new puzzle to the list
				System.out.println("Puzzles disponibles para dificultad "+i+": "+sudokuDiff.size());
				this.sudokulist.put(i, sudokuDiff); //add the list back to the hashset
			}
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