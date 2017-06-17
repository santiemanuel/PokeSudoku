package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utils.SudokuBoard;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {
	
	private ImageButton myimages;
	private Image background, sudomatrix;
	String mypath = "C:\\Users\\SANTIAGO\\Documents\\Shuffle-sprites\\";
	private SudokuBoard puzzle;
	private static int ROWS = 9;
	private static int COLUMNS = 9;

	public SudokuPanel(){
		ImageIcon ico = new ImageIcon((mypath+"bg.png"));
		background = ico.getImage();
		ico = new ImageIcon((mypath+"sudoku.png"));
		sudomatrix = ico.getImage();
		this.setPreferredSize(new Dimension(800,800));
		this.setMinimumSize(new Dimension(800,800));
	}
	
	public void newSudoku(SudokuBoard puzzle){
		this.puzzle = puzzle;
		this.myimages = new ImageButton(puzzle);			      
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		setOpaque(false);
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, null);
        g.drawImage(this.sudomatrix, 0, 0, null);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int r = 0;r<ROWS;r++)
        {
        	for (int c = 0;c<COLUMNS;c++)
        	{
        		this.myimages.paint(g2d, this, r, c);
        	}
        }
        
        Font f = new Font("Roboto", Font.BOLD, 16);
		g2d.setFont(f);
		
		/*int posX, posY;
		String number;
        
       for (int r = 0;r<ROWS;r++)
       {
       	for (int c = 0;c<COLUMNS;c++)
        	{
        		posX = this.puzzle.getPosition(r,c).getX();
        		posY = this.puzzle.getPosition(r,c).getY()+40;
        		number = Integer.toString(this.puzzle.getBoard().getValue(r, c).getIDPoke());
        		g2d.setColor(Color.BLACK);
        		g2d.drawString(number, posX, posY);
        	}
        }*/
	}
}
