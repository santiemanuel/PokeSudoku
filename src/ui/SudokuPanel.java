package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import utils.SudokuBoard;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {
	
	private DrawIcons myicons;
	private SudokuBoard puzzle;
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	private Icon[][] myIconMatrix;

	public SudokuPanel(){
		this.setPreferredSize(new Dimension(800,800));
		this.setMinimumSize(new Dimension(800,800));
		this.myicons = new DrawIcons();
        ArrayList<Icon> listIcons = new ArrayList<Icon>();
        this.myIconMatrix = new Icon[ROWS][COLUMNS];
        listIcons = this.myicons.getMyicons();
        
        this.myIconMatrix = this.getMatrix(listIcons);
	}
	
	public void newSudoku(SudokuBoard puzzle){
		this.puzzle = puzzle;
	}
	
	private Icon[][] getMatrix(ArrayList<Icon> myicons){
		Icon[][] myiconMatrix = new Icon[ROWS][COLUMNS];
		
		for (int r = 0;r<ROWS;r++)
		{
			for (int c = 0;c<COLUMNS;c++)
			{
				myiconMatrix[r][c] = myicons.get(0);
				myicons.remove(0);
			}
		}
		
		return (myiconMatrix);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("Pintando");
		setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int r = 0;r<ROWS;r++)
        {
        	for (int c = 0;c<COLUMNS;c++)
        	{
        		
        		this.myIconMatrix[c][r].setIconColor(this.puzzle,r,c);
        		this.myIconMatrix[r][c].drawIcon(g2d);
        		
        	}
        }
        
        Font f = new Font("Roboto", Font.BOLD, 24);
		g2d.setFont(f);
		
		int posX, posY;
		String number;
        
        for (int r = 0;r<ROWS;r++)
        {
        	for (int c = 0;c<COLUMNS;c++)
        	{
        		posX = this.puzzle.getBoard().getValue(r, c).getPos().getX();
        		posY = this.puzzle.getBoard().getValue(r, c).getPos().getY();
        		number = Integer.toString(this.puzzle.getBoard().getValue(r, c).getIDPoke());
        		g2d.setColor(Color.BLACK);
        		g2d.drawString(number, posX, posY);
        	}
        }
	}
}
