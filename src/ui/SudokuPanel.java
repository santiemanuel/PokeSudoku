package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	private Point selectedLabel;
	private JLabel[][] mylabels;

	public SudokuPanel(){
		this.setLayout(new GridLayout(9,9,0,0));
		this.selectedLabel = new Point();
		ImageIcon ico = new ImageIcon((mypath+"bg.png"));
		background = ico.getImage();
		mylabels = new JLabel[ROWS][COLUMNS];
		ico = new ImageIcon((mypath+"sudoku.png"));
		sudomatrix = ico.getImage();
		this.setPreferredSize(new Dimension(800,800));
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				this.mylabels[row][column] = new JLabel(new ImageIcon(mypath+"0.png"));
				this.add(this.mylabels[row][column]);
		}
		
	}
	
	public void newSudoku(SudokuBoard puzzle, ImageButton myimages){
		this.removeAll();
		mylabels = new JLabel[ROWS][COLUMNS];
		this.puzzle = puzzle;
		this.myimages = myimages;
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				ImageIcon element = myimages.getImageMatrix()[row][column];
				this.mylabels[row][column] = createGridLabel(element, row, column);
				this.add(this.mylabels[row][column]);
		}

	}
	
	private JLabel createGridLabel(ImageIcon element, int r, int c){
		JLabel lbl = new JLabel(element);
		lbl.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				selectedLabel.setLocation(r, c);
			}
		});
		return lbl;
	}
	
	public void updateSudoku(SudokuBoard puzzle){
		this.myimages = new ImageButton(puzzle);
		this.puzzle = puzzle;
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				this.mylabels[row][column] = new JLabel(myimages.getImageMatrix()[row][column]);
				this.add(this.mylabels[row][column]);
		}
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, null);
        g.drawImage(this.sudomatrix, 10, 10, null);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
