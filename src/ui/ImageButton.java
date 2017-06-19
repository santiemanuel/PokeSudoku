package ui;


import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import utils.SudokuBoard;

public class ImageButton {
	
	private ArrayList<ImageButton> myimages;
	private ArrayList<ImageIcon> imagelist;
	private ImageIcon[][] imageMatrix;
	String mypath = "C:\\Users\\SANTIAGO\\Documents\\Shuffle-sprites\\";
	private ImageIcon myimage;
	
	
	public ImageButton(SudokuBoard puzzle){
		
		imageMatrix = new ImageIcon[9][9];
		ArrayList<Integer> myimages = new ArrayList<Integer>();
		myimages = puzzle.getNewnumbers();
		this.imagelist = new ArrayList<ImageIcon>();
		Image img, newimg;
		ImageIcon icon;
		
		for (int i=0;i<9;i++){
			icon = new ImageIcon(mypath+Integer.toString(myimages.get(i))+".png");
			img = icon.getImage();
			newimg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			icon.setImage(newimg);
			this.imagelist.add(icon);
			
		}
		this.myimage = imagelist.get(0);
		
        for (int r = 0;r<9;r++)
        {
        	for (int c = 0;c<9;c++)
        	{
        		setImage(puzzle, r, c);
        		imageMatrix[r][c] = getImage();
         	}
        }
		
	}
	
	
	public ImageIcon[][] getImageMatrix(){
		return (this.imageMatrix);
	}
	
	
	public ArrayList<ImageIcon> getImages(){
		return (this.imagelist);
	}
	
	public void setImage(SudokuBoard puzzle, int r, int c){
		int number = puzzle.getBoard().getValue(r, c).getIDPoke();
		
		if (number == puzzle.getNewnumbers().get(0)) this.myimage = this.imagelist.get(0);
		if (number == puzzle.getNewnumbers().get(1)) this.myimage = this.imagelist.get(1);
		if (number == puzzle.getNewnumbers().get(2)) this.myimage = this.imagelist.get(2);
		if (number == puzzle.getNewnumbers().get(3)) this.myimage = this.imagelist.get(3);
		if (number == puzzle.getNewnumbers().get(4)) this.myimage = this.imagelist.get(4);
		if (number == puzzle.getNewnumbers().get(5)) this.myimage = this.imagelist.get(5);
		if (number == puzzle.getNewnumbers().get(6)) this.myimage = this.imagelist.get(6);
		if (number == puzzle.getNewnumbers().get(7)) this.myimage = this.imagelist.get(7);
		if (number == puzzle.getNewnumbers().get(8)) this.myimage = this.imagelist.get(8);
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
