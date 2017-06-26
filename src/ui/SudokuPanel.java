package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.SudokuGen;

public class SudokuPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageButton myimages;
	private Image background, sudomatrix;
	String mypath = "img\\";
	private SudokuGen puzzle;
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	private Point selectedLabel;
	private JLabel[][] mylabels;
	private int WIDTH;
	private int HEIGHT;

	public SudokuPanel(int WIDTH, int HEIGHT){
				
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		this.setLayout(new GridLayout(9,9,0,0));
		this.selectedLabel = new Point(0,0);
		URL url = null;
		url = getClass().getResource("/bg.png");		
		ImageIcon ico = new ImageIcon(url);
		background = ico.getImage();
		mylabels = new JLabel[ROWS][COLUMNS];
		
		url = getClass().getResource("/sudoku.png");
		ico = new ImageIcon(url);
		sudomatrix = ico.getImage();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				this.mylabels[row][column] = new JLabel(new ImageIcon(mypath+"0.png"));
				this.add(this.mylabels[row][column]);
		}
		
	}
	
	public void newSudoku(SudokuGen puzzle, ImageButton myimages){
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
			public void mouseClicked(MouseEvent e){
				if (puzzle.isCellMutable(r,c)){
					if (puzzle.getBoard().getMatrix()[selectedLabel.x][selectedLabel.y].getIDPoke() == 0){
						mylabels[selectedLabel.x][selectedLabel.y].setIcon(myimages.getImages().get(0));
					}
					selectedLabel.setLocation(r, c);
					puzzle.getBoard().getMatrix()[r][c].setIDPoke(0);
					mylabels[r][c].setIcon(myimages.getMarkedCell());
					revalidate();
					repaint();
				}

				
			}
		});
		return lbl;
	}
	
	
	public void msgButtonActionListener(String buttonValue){
		if (this.puzzle.isCellMutable(selectedLabel.x, selectedLabel.y)){
			int r = selectedLabel.x;
			int c = selectedLabel.y;
			if (this.puzzle.isValidMove(r, c, Integer.parseInt(buttonValue))){
				this.puzzle.makeMove(r, c, Integer.parseInt(buttonValue));
				myimages.setImageCell(r, c, Integer.parseInt(buttonValue));
				this.mylabels[r][c].setIcon(myimages.getImageMatrix()[r][c]);
			}
			
			revalidate();
			repaint();
		}
		
		
	}
	
	public class ButtonActionListener implements ActionListener	{
		@Override
		public void actionPerformed(ActionEvent e){
			msgButtonActionListener(e.getActionCommand());
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(this.sudomatrix, 5, 5, WIDTH-10, HEIGHT-10, null);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		
	}
}
