package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import utils.PlaySound;
import utils.Position;
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
	
	/** The lockedcells. */
	private ArrayList<Position> lockedcells;
	
	/** The Constant ROWS. */
	private static final int ROWS = 9;
	
	/** The Constant COLUMNS. */
	private static final int COLUMNS = 9;
	
	/** The selected JPanel. */
	private Point selPanel;
	
	/** The mypanels. */
	private JPanel[][] mypanels;
	
	/** The label BG. */
	private Color labelBG, valueColor;
	
	/** The sound effects. */
	private PlaySound[] effects;

	private Timer tanim;

	/**
	 * Instantiates a new sudoku panel.
	 *
	 * @param WIDTH The width of the JPanel
	 * @param HEIGHT The height of the JPanel
	 * @param myimages The ImageButton object myimages 
	 */
	public SudokuPanel(int WIDTH, int HEIGHT, ImageButton myimages){
		
		//Sets the layout to a 9*9 GridLayout with padding 0
		//this.setLayout(new GridLayout(ROWS,COLUMNS,0,0));
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		//Creates a matrix of JPanel
		this.mypanels = new JPanel[ROWS][COLUMNS];

		//this.setPreferredSize(new Dimension(576,576));
		
		this.labelBG = new Color(0,64,128,128);
		this.valueColor = new Color(64,128,0,192);
		
		//Sets all cells to zero
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int col = i % ROWS;
				
				this.mypanels[row][col] = new JPanel();
				this.mypanels[row][col].setLayout(new BorderLayout());
				this.mypanels[row][col].add(new JLabel());
				((JLabel)mypanels[row][col].getComponent(0)).setIcon(myimages.getImagelist().get(0));
				cons.weightx = cons.weighty = 1d;
				cons.gridx = row;
				cons.gridy = col;
				cons.fill = GridBagConstraints.BOTH;
				this.add(this.mypanels[row][col], cons);
		}
		
		//Loads sound effects to play according to the move
		this.effects = new PlaySound[3];
		this.effects[0] = new PlaySound("error");
		this.effects[1] = new PlaySound("hint");
		this.effects[2] = new PlaySound("right");
		
	}
	
	/**
	 * New sudoku.
	 *
	 * @param puzzle The Sudoku matrix object
	 * @param myimages The ImageButton object myimages
	 */
	public void newSudoku(SudokuGen puzzle, ImageButton myimages){
		this.removeAll();
		GridBagConstraints cons = new GridBagConstraints();
		this.selPanel = new Point();
		this.puzzle = puzzle;
		this.myimages = myimages;
		this.lockedcells = puzzle.getLockedCells();
		Insets Margin = new Insets(0, 0, 0, 0);
		cons.insets = Margin;

		
		//Adds a JLabel to each JPanel at (row, col)
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int col = i % ROWS;
				ImageIcon element = this.myimages.getImageAt(row,col);
				this.mypanels[row][col] = new JPanel();
				this.mypanels[row][col].setOpaque(false);
				this.mypanels[row][col].add(createGridLabel(element, row, col));
				cons.weightx = cons.weighty = 1d;
				cons.gridx = row;
				cons.gridy = col;
				cons.fill = GridBagConstraints.BOTH;
				this.add(this.mypanels[row][col], cons);
				
		}
		ActionListener selListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickedanim(selPanel);
				
			}
			
		};
		
		tanim = new Timer(5,selListener);
	}
	
	private void clickedanim(Point cell){
		
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
		
		//hides the background and sets the color
		lbl.setOpaque(false);
		lbl.setBackground(labelBG);
		
		//Anonymous class to define the MouseListener for the given JLabel
		lbl.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				//Checks if cell is editable
				cleanPanels();
				if (puzzle.isCellMutable(r, c)){
					//If left click, sets the location to put an icon there
					if (SwingUtilities.isLeftMouseButton(e)){
						selPanel.setLocation(r, c);
						paintRow(r);
						paintColumn(c);
						paintBox(r,c);
					}
					//If right click, removes the image and sets the cell value to zero
					else if (SwingUtilities.isRightMouseButton(e)){
						puzzle.makeMove(r, c, 0);
						((JLabel)mypanels[r][c].getComponent(0)).setIcon(myimages.getImagelist().get(0));
					}
				}
				int value = puzzle.getGenboard().getValue(r, c).getIDPoke();
				if (value != 0) paintValue(r,c);
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
	public void msgButtonActionListener(Integer buttonValue, int source){
		//Checks if cell is not a starting cell
		if (this.puzzle.isCellMutable(selPanel.x, selPanel.y)){
			int r = selPanel.x;
			int c = selPanel.y;
			//Checks for validity according to Sudoku rules, code 1 for GUI usage
			if (this.puzzle.isValidMove(r, c, buttonValue, 1)){
				if (source == 0) this.effects[2].play();
				this.puzzle.makeMove(r, c, buttonValue);
				myimages.setImageCell(r, c, buttonValue);
				((JLabel)this.mypanels[r][c].getComponent(0)).setIcon(myimages.getImageAt(r,c));
			}else this.effects[0].play();
			
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
	 */
	public class ButtonActionListener implements ActionListener	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			Integer value = Integer.parseInt(e.getActionCommand());
			int source = 0;
			msgButtonActionListener(value, source);
			if (puzzle.getSolved()) JOptionPane.showMessageDialog(null, "Felicidades! Resolviste el puzzle.");
		}
		
	}
	
	/**
	 * Gets the sudoku hint and plays the first available move (1 candidate).
	 *
	 */
	public void getSudokuHint() {
		if (puzzle.getSolved())	JOptionPane.showMessageDialog(null, "El puzzle ya esta resuelto!");
		else{
			int source = 1;
			cleanPanels();
			Position cellHint = this.puzzle.getFirstAvailableMove();
			if (cellHint != null){
				int row,col;
				row = cellHint.getX();
				col = cellHint.getY();
				this.selPanel = new Point(row, col);			
				Integer value = this.puzzle.getValidvalue(row, col, 0);
				msgButtonActionListener(value, source);
				
				paintRow(row);
				paintColumn(col);
				paintBox(row,col);
				this.effects[1].play();
				
				if (puzzle.getSolved()) JOptionPane.showMessageDialog(null, "Felicidades! Resolviste el puzzle.");
			};
		}
	}
	
	public void resetPuzzle(){
		int index = 0;
		
		while (index < this.puzzle.getMutableCells().size()){
			int row = this.puzzle.getMutableCells().get(index).getX();
			int col = this.puzzle.getMutableCells().get(index).getY();
			selPanel.setLocation(row, col);
			this.puzzle.makeMove(row, col, 0);
			((JLabel)mypanels[row][col].getComponent(0)).setIcon(myimages.getImagelist().get(0));
			index++;
		}
		this.puzzle.initValidvalues();
		this.puzzle.loadValidvalues();

		revalidate();
		repaint();
	}

	/**
	 * Paints the current row.
	 *
	 * @param row the row
	 */
	public void paintRow(int row){
		for (int c=0; c<ROWS;c++){
			((JLabel)mypanels[row][c].getComponent(0)).setBackground(labelBG);
			((JLabel)mypanels[row][c].getComponent(0)).setOpaque(true);
		}
	}
	
	/**
	 * Paints the current column.
	 *
	 * @param col the col
	 */
	public void paintColumn(int col){
		for (int r=0; r<COLUMNS;r++){
			((JLabel)mypanels[r][col].getComponent(0)).setBackground(labelBG);
			((JLabel)mypanels[r][col].getComponent(0)).setOpaque(true);
		}
	}
	
	/**
	 * Paints the current box.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void paintBox(int row, int col){
		int boxRow = row / 3;
		int boxCol = col / 3;
		
		int startRow = (boxRow * 3);
		int startCol = (boxCol * 3);
		
		for (int r = startRow; r<= (startRow+3)-1;r++){
			for (int c = startCol; c<= (startCol+3)-1;c++){
				((JLabel)mypanels[r][c].getComponent(0)).setBackground(labelBG);
				((JLabel)mypanels[r][c].getComponent(0)).setOpaque(true);
			}
		}
	}
	
	public void paintValue(int row, int col){
		int value = this.puzzle.getGenboard().getValue(row, col).getIDPoke();
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int r = i / ROWS;
				int c = i % ROWS;
				if (this.puzzle.getGenboard().getValue(r, c).getIDPoke() == value){
					((JLabel)mypanels[r][c].getComponent(0)).setBackground(valueColor);
					((JLabel)mypanels[r][c].getComponent(0)).setOpaque(true);
				}
		}
	}
	
	/**
	 * Cleans all panels before painting again.
	 */
	public void cleanPanels(){
		for (int r = 0; r< ROWS ;r++){
			for (int c = 0; c< COLUMNS;c++){
				((JLabel)mypanels[r][c].getComponent(0)).setOpaque(false);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        int x, y;
        Image img = myimages.getPokeCell().getImage();
        
        //Draws the background for starting cells
		for (int i=0;i<this.lockedcells.size();i++){
			int row = this.lockedcells.get(i).getX();
			int column = this.lockedcells.get(i).getY();
			x = mypanels[row][column].getX()+5;
			y = mypanels[row][column].getY()+10;
			g.drawImage(img, x, y, null); 
		}
				
		//Draws the marked cell state at the cell
        img = myimages.getMarkedCell().getImage();
        x = mypanels[selPanel.x][selPanel.y].getX()+10;
        y = mypanels[selPanel.x][selPanel.y].getY()+10;
        g.drawImage(img, x, y, null);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);              	
	}	
}
