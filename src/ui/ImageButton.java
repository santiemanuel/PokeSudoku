package ui;


import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.SudokuBoard;

public class ImageButton {
	
	private ArrayList<ImageButton> myimages;
	private ArrayList<ImageIcon> imagelist;
	private ImageIcon[][] imageMatrix;
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	String mypath = "C:\\Users\\SANTIAGO\\Documents\\Shuffle-sprites\\";
	private ImageIcon myimage;
	
	
	public ImageButton(SudokuBoard puzzle){
		
		imageMatrix = new ImageIcon[9][9];
		ArrayList<Integer> myimages = new ArrayList<Integer>();
		myimages = puzzle.getNewnumbers();
		this.imagelist = new ArrayList<ImageIcon>();
		Image img, newimg;
		ImageIcon icon;
		
		for (int i=0;i<=9;i++){
			icon = new ImageIcon(mypath+Integer.toString(myimages.get(i))+".png");
			img = icon.getImage();
			newimg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			icon.setImage(newimg);
			this.imagelist.add(icon);
			
		}
		
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				int id = puzzle.getBoard().getValue(row, column).getIDPoke();
				setImage(puzzle, row, column, id);
				imageMatrix[row][column] = getImage();
		}
		
		
	}
	
	
	public ImageIcon[][] getImageMatrix(){
		return (this.imageMatrix);
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
