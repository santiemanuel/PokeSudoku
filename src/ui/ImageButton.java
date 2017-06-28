package ui;


import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import utils.SudokuGen;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageButton.
 */
public class ImageButton {
	
	/** The myimages. */
	private ArrayList<ImageButton> myimages;
	
	/** The imagelist. */
	private ArrayList<ImageIcon> imagelist;
	
	/** The myimagesid. */
	private ArrayList<Integer> myimagesid;
	
	/** The image matrix. */
	private ImageIcon[][] imageMatrix;
	
	/** The pokebg, marked ImageIcons. */
	private ImageIcon marked, pokebg;
	
	/** The rows. */
	private static int ROWS = 9;
	
	/** The columns. */
	private static int COLUMNS = 9;
	
	/** The width of the JPanel. */
	private int WIDTH;
	
	
	/**
	 * Instantiates a new image button.
	 *
	 * @param puzzle The Sudoku matrix object
	 * @param WIDTH The width of the Sudoku JPanel
	 */
	public ImageButton(SudokuGen puzzle, int WIDTH){
		
		//Sets the resolution of the icons depending on the size of the JPanel 
		if (WIDTH > 600) this.WIDTH = 64; else this.WIDTH = 48;
		
		this.imageMatrix = new ImageIcon[ROWS][COLUMNS];
		
		//Sets the ids of the images used in this puzzle
		this.myimagesid = puzzle.getNewnumbers();
		this.imagelist = new ArrayList<ImageIcon>();
		Image img;
		ImageIcon icon;
		URL url = null;
		
		//Loads the list of images needed to load the matrix of images
		for (int i=0;i<=9;i++){
			url = getClass().getResource("/"+Integer.toString(myimagesid.get(i))+".png");
			icon = new ImageIcon(url);
			img = icon.getImage().getScaledInstance(this.WIDTH, this.WIDTH, Image.SCALE_SMOOTH);
			icon.setImage(img);
			this.imagelist.add(icon);		
		}
		
		//Sets the icon for a marked cell
		url = getClass().getResource("/marked.png");
		icon = new ImageIcon(url);
		img = icon.getImage().getScaledInstance(this.WIDTH+20, this.WIDTH+20, Image.SCALE_SMOOTH);
		icon.setImage(img);
		this.marked = icon;
		
		//Sets the icon for starting cells
		url = getClass().getResource("/poke.png");
		icon = new ImageIcon(url);
		img = icon.getImage().getScaledInstance(this.WIDTH+10, this.WIDTH+10, Image.SCALE_SMOOTH);
		icon.setImage(img);
		this.pokebg = icon;
		
		//Loads the matrix according to the id at that (row, column)
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				int id = puzzle.getBoard().getValue(row, column).getIDPoke();
				int index = puzzle.getNewnumbers().indexOf(id);
				this.imageMatrix[row][column] = this.imagelist.get(index);
		}
	}
	
	/**
	 *  Updates the image of a cell at (row, col).
	 *
	 * @param row The row
	 * @param col The col
	 * @param value The new value
	 */
	public void setImageCell(int row, int col, int value){
		int index = this.myimagesid.indexOf(value);
		this.imageMatrix[row][col] = this.imagelist.get(index);
	}
	
	/**
	 * Sets the marked cell.
	 *
	 * @param row The row
	 * @param col The col
	 */
	public void setMarkedCell(int row, int col){
		this.imageMatrix[row][col] = this.marked;
	}
	
	/**
	 * Gets the marked cell.
	 *
	 * @return The marked cell
	 */
	public ImageIcon getMarkedCell(){
		return (this.marked);
	}
	
	/**
	 * Gets the poke cell.
	 *
	 * @return The poke cell
	 */
	public ImageIcon getPokeCell(){
		return (this.pokebg);
	}
	
	/**
	 * Gets the image matrix.
	 *
	 * @return the image matrix
	 */
	public ImageIcon[][] getImageMatrix(){
		return (this.imageMatrix);
	}
	
	/**
	 * Gets the image at.
	 *
	 * @param r The row
	 * @param c The column
	 * @return the image at
	 */
	public ImageIcon getImageAt(int r, int c){
		return (this.imageMatrix[r][c]);
	}
	
	/**
	 * Gets the images.
	 *
	 * @return the images
	 */
	public ArrayList<ImageIcon> getImagelist(){
		return (this.imagelist);
	}
	
	/**
	 * Gets the myimages.
	 *
	 * @return the myimages
	 */
	public ArrayList<ImageButton> getMyimages(){
		return (this.myimages);
	}
	
	/**
	 * Paint.
	 *
	 * @param g The graphics
	 * @param mypanel The Sudoku JPanel
	 * @param r The row
	 * @param c The column
	 */
	public void paint(Graphics2D g, SudokuPanel mypanel, int r, int c){
		this.imageMatrix[r][c].paintIcon(mypanel, g, 0, 0);
	}

}
