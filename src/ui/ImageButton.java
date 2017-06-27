package ui;


import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import utils.SudokuBoard;

public class ImageButton {
	
	private ArrayList<ImageButton> myimages;
	private ArrayList<ImageIcon> imagelist;
	private ArrayList<Integer> myimagesid;
	private ImageIcon[][] imageMatrix;
	private ImageIcon marked, pokebg;
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	private ImageIcon myimage;
	private int WIDTH;
	
	
	public ImageButton(SudokuBoard puzzle, int WIDTH){
		
		if (WIDTH > 600) this.WIDTH = 64;
		else this.WIDTH = 48;
		imageMatrix = new ImageIcon[ROWS][COLUMNS];
		this.myimagesid = new ArrayList<Integer>();
		this.myimagesid = puzzle.getNewnumbers();
		this.imagelist = new ArrayList<ImageIcon>();
		Image img, newimg;
		ImageIcon icon;
		URL url = null;
		
		for (int i=0;i<=9;i++){
			url = getClass().getResource("/"+Integer.toString(myimagesid.get(i))+".png");
			icon = new ImageIcon(url);
			img = icon.getImage();
			newimg = img.getScaledInstance(this.WIDTH, this.WIDTH, Image.SCALE_SMOOTH);
			icon.setImage(newimg);
			this.imagelist.add(icon);
			
		}
		url = ImageButton.class.getResource("/marked.png");
		icon = new ImageIcon(url);
		img = icon.getImage();
		newimg = img.getScaledInstance(this.WIDTH+20, this.WIDTH+20, Image.SCALE_SMOOTH);
		icon.setImage(newimg);
		this.marked = icon;
		
		
		url = ImageButton.class.getResource("/poke.png");
		icon = new ImageIcon(url);
		img = icon.getImage();
		newimg = img.getScaledInstance(this.WIDTH+10, this.WIDTH+10, Image.SCALE_SMOOTH);
		icon.setImage(newimg);
		this.pokebg = icon;
		
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				int id = puzzle.getBoard().getValue(row, column).getIDPoke();
				setImage(puzzle, row, column, id);
				imageMatrix[row][column] = getImage();
		}
		
		
	}
	
	public void setImageCell(int row, int col, int value){
		int index = this.myimagesid.indexOf(value);
		this.imageMatrix[row][col] = this.imagelist.get(index);
	}
	
	public void setMarkedCell(int row, int col){
		this.imageMatrix[row][col] = this.marked;
	}
	
	public ImageIcon getMarkedCell(){
		return (this.marked);
	}
	
	public ImageIcon getPokeCell(){
		return (this.pokebg);
	}
	
	
	public ImageIcon[][] getImageMatrix(){
		return (this.imageMatrix);
	}
	
	public ImageIcon getImageAt(int r, int c){
		return (this.imageMatrix[r][c]);
	}
	
	
	public ArrayList<ImageIcon> getImages(){
		return (this.imagelist);
	}
	
	public void setImage(SudokuBoard puzzle, int r, int c, int id){
		
		int index = puzzle.getNewnumbers().indexOf(id);	
		this.myimage = this.imagelist.get(index);
	
	}
	
	public ArrayList<ImageButton> getMyimages(){
		return (this.myimages);
	}
	
	public ImageIcon getImage(){
		return (this.myimage);
	}
	
	public void paint(Graphics2D g, SudokuPanel mypanel, int r, int c){
		this.imageMatrix[r][c].paintIcon(mypanel, g, 0, 0);
	}

}
