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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.SudokuGen;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuPanel.
 */
@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {
	
	/** The myimages. */
	private ImageButton myimages;
	
	/** The Sudoku matrix object. */
	private SudokuGen puzzle;
	
	/** The Constant ROWS. */
	private static final int ROWS = 9;
	
	/** The Constant COLUMNS. */
	private static final int COLUMNS = 9;
	
	/** The selected JPanel. */
	private Point selPanel;
	
	/** The mypanels. */
	private JPanel[][] mypanels;


	/**
	 * Instantiates a new sudoku panel.
	 *
	 * @param WIDTH The width of the JPanel
	 * @param HEIGHT The height of the JPanel
	 * @param myimages The ImageButton object myimages 
	 */
	public SudokuPanel(int WIDTH, int HEIGHT, ImageButton myimages){
		
		//Sets the layout to a 9*9 GridLayout with padding 0
		this.setLayout(new GridLayout(9,9,0,0));
		
		//Creates a matrix of JPanel
		this.mypanels = new JPanel[ROWS][COLUMNS];

		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		//Sets all cells to zero
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				
				this.mypanels[row][column] = new JPanel();
				this.mypanels[row][column].add(new JLabel());
				((JLabel)mypanels[row][column].getComponent(0)).setIcon(myimages.getImagelist().get(0));
				this.add(this.mypanels[row][column]);
		}
		
	}
	
	/**
	 * New sudoku.
	 *
	 * @param puzzle The Sudoku matrix object
	 * @param myimages The ImageButton object myimages
	 */
	public void newSudoku(SudokuGen puzzle, ImageButton myimages){
		this.removeAll();
		this.selPanel = new Point();
		this.puzzle = puzzle;
		this.myimages = myimages;
		
		//Adds a JLabel to each JPanel at (row, col)
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int col = i % ROWS;
				ImageIcon element = this.myimages.getImageAt(row,col);
				this.mypanels[row][col] = new JPanel();
				this.mypanels[row][col].setOpaque(false);
				this.mypanels[row][col].add(createGridLabel(element, row, col));
				this.add(this.mypanels[row][col]);
				
		}

	}
	
	/**
	 * Creates the grid label.
	 *
	 * @param element The element
	 * @param r The row
	 * @param c The column
	 * @return The JLabel to load at (row, col)
	 */
	private JLabel createGridLabel(ImageIcon element, int r, int c){

		JLabel lbl = new JLabel(element);
		
		//Anonymous class to define the MouseListener for the given JLabel
		lbl.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				//Checks if cell is editable
				if (puzzle.isCellMutable(r, c)){
					//If left click, sets the location to put an icon there
					if (SwingUtilities.isLeftMouseButton(e)){
						selPanel.setLocation(r, c);
					}
					//If right click, removes the image and sets the cell value to zero
					else if (SwingUtilities.isRightMouseButton(e)){
						puzzle.makeMove(r, c, 0);
						((JLabel)mypanels[r][c].getComponent(0)).setIcon(myimages.getImagelist().get(0));
					}
				}
				revalidate();
				repaint();
				
			}
		});
		return lbl;
	}
	
	
	/**
	 * Msg button action listener.
	 *
	 * @param buttonValue The button value
	 */
	public void msgButtonActionListener(String buttonValue){
		//Checks if cell is not a starting cell
		if (this.puzzle.isCellMutable(selPanel.x, selPanel.y)){
			int r = selPanel.x;
			int c = selPanel.y;
			//Checks for validity according to Sudoku rules
			if (this.puzzle.isValidMove(r, c, Integer.parseInt(buttonValue))){
				this.puzzle.makeMove(r, c, Integer.parseInt(buttonValue));
				myimages.setImageCell(r, c, Integer.parseInt(buttonValue));
				((JLabel)this.mypanels[r][c].getComponent(0)).setIcon(myimages.getImageAt(r,c));

			}
			
			revalidate();
			repaint();
		}
		
		
	}
	
	/**
	 * The listener interface for receiving buttonAction events.
	 * The class that is interested in processing a buttonAction
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addButtonActionListener<code> method. When
	 * the buttonAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ButtonActionEvent
	 */
	public class ButtonActionListener implements ActionListener	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			msgButtonActionListener(e.getActionCommand());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        int x,y;
        Image img = myimages.getPokeCell().getImage();
        
        //Draws the background for starting cells
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				if (!this.puzzle.isCellMutable(row, column)){
					x = mypanels[row][column].getX()+5;
					y = mypanels[row][column].getY()+10;
					g.drawImage(img, x, y, null);
					
				}
				
		}
		
		//Draws the marked cell state at the cell
        img = myimages.getMarkedCell().getImage();
        x = mypanels[selPanel.x][selPanel.y].getX();
        y = mypanels[selPanel.x][selPanel.y].getY();
        g.drawImage(img, x, y, null);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		
	}
	
	
}
