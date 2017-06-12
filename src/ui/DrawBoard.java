package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import utils.Matrix;
import utils.SudokuBoard;


@SuppressWarnings("serial")
public class DrawBoard extends JPanel {
    
	private DrawIcons myicons;
	private SudokuBoard sudokuB;
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	private Icon[][] myIconMatrix;
    
	public DrawBoard (){
		super();
		this.myicons = new DrawIcons();
		this.sudokuB = new SudokuBoard();
        ArrayList<Icon> listIcons = new ArrayList<Icon>();
        this.myIconMatrix = new Icon[ROWS][COLUMNS];
        listIcons = this.myicons.getMyicons();
        
        System.out.println(listIcons.size());
        
        this.myIconMatrix = this.getMatrix(listIcons);
		DrawBoard.this.repaint();
		
		
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
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int r = 0;r<ROWS;r++)
        {
        	for (int c = 0;c<COLUMNS;c++)
        	{
        		this.myIconMatrix[r][c].drawIcon(g2d);
        	}
        }
        
        for (int r = 0;r<ROWS;r++)
        {
        	for (int c = 0;c<COLUMNS;c++)
        	{
        		int posX = sudokuB.getBoard().getValue(r, c).getPos().getX()-15;
        		int posY = sudokuB.getBoard().getValue(r, c).getPos().getY()-5;
        		String number = Integer.toString(sudokuB.getBoard().getValue(r, c).getIDPoke());
        		g.setColor(Color.BLACK);
        		g.drawString(number, posX, posY);
        	}
        }
           
    }
	
	
	
}
