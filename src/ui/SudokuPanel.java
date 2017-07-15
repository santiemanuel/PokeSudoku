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
import java.awt.image.BufferedImage;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Transparency;
import utils.Position;
import utils.SoundMP3;
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
	private URL[] sounds;
	private SoundMP3 sound;

	private Timer tanim,rotateanim;
	
	private double initsize, angle;
	private int sign;
	
	private boolean enabled;
	

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
				cons.fill = GridBagConstraints.NONE;
				this.add(this.mypanels[row][col], cons);
		}
		
		this.sounds = new URL[4];
		this.sounds[0] = getClass().getResource("/error.mp3");
		this.sounds[1] = getClass().getResource("/hint.mp3");
		this.sounds[2] = getClass().getResource("/right.mp3");
		this.sounds[3] = getClass().getResource("/win.mp3");
		
		
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
		initsize = 1.0;
		angle = 0.0;
		sign = -1;
		
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
				cons.fill = GridBagConstraints.NONE;
				this.add(this.mypanels[row][col], cons);
				
		}
		
		selPanel.setLocation(0,0);
		ActionListener selListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickedanim(selPanel);
			}
			
		};	
		
		tanim = new Timer(10,selListener);
		
		ActionListener rotateListener = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				rotateanim(selPanel);
			}
		};
		
		rotateanim = new Timer(5, rotateListener);
		enabled = true;
	}
	
	private void rotateanim(Point cell){
		angle+=4;
		if (angle == 360.0){
			angle = 0.0;
			rotateanim.stop();
		}
		int posX = cell.x;
		int posY = cell.y;
		ImageIcon image = new ImageIcon
				(resizedImage(myimages.getMarkedCell(),1.0,angle,1.0));
		
		((JLabel)this.mypanels[posX][posY].getComponent(0)).setIcon(image);
	}
	
	private void clickedanim(Point cell){
		int posX = cell.x;
		int posY = cell.y;
		
		ImageIcon image = new ImageIcon
				(resizedImage(myimages.getImageAt(posX,posY),initsize,0,1.0));
		
		((JLabel)this.mypanels[posX][posY].getComponent(0)).setIcon(image);
		initsize+=sign*(0.015);
		
		if (initsize < 0.8) sign = 1;
		
		if (initsize == 1.0){
			sign = -1;
			tanim.stop();
		}
		
	}
	
	public BufferedImage resizedImage(ImageIcon index, double size, double angle, double alpha){

		BufferedImage image = (BufferedImage) index.getImage();
		try {
			image = Thumbnails.of(image)
					.scale(size)
					.rotate(angle)
					.addFilter(new Transparency(alpha))
					.asBufferedImage();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		return image;
		
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
				if (enabled){
				//Checks if cell is editable
				cleanPanels();
				if (puzzle.isCellMutable(r, c)){
					//If left click, sets the location to put an icon there
					if (SwingUtilities.isLeftMouseButton(e)){
						selPanel.setLocation(r, c);
						if (puzzle.getGenboard().getValue(r, c).getIDPoke() != 0)
							tanim.start();
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
			}
		});
		return lbl;
	}
	
	public void switchClickState(){
		if (this.enabled) this.enabled = false;
		else this.enabled = true;
	}
	
	
	/**
	 * Msg button action listener.
	 *
	 * @param buttonValue The button value
	 */
	public void msgButtonActionListener(Integer buttonValue, int source){
		if (!enabled) return;
		//Checks if cell is not a starting cell
		if (this.puzzle.isCellMutable(selPanel.x, selPanel.y)){
			int r = selPanel.x;
			int c = selPanel.y;
			//Checks for validity according to Sudoku rules, code 1 for GUI usage
			if (this.puzzle.isValidMove(r, c, buttonValue, 1)){
				if (source == 0){
					this.sound = new SoundMP3(this.sounds[2]);
					this.sound.start();
				}
				this.puzzle.makeMove(r, c, buttonValue);
				myimages.setImageCell(r, c, buttonValue);
				tanim.start();
				((JLabel)this.mypanels[r][c].getComponent(0)).setIcon(myimages.getImageAt(r,c));
			}else{
				this.sound = new SoundMP3(this.sounds[0]);
				this.sound.start();
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
	 * @throws InterruptedException 
	 *
	 */
	public void getSudokuHint() throws InterruptedException {
		if (!enabled) return;
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

				this.sound = new SoundMP3(this.sounds[1]);
				this.sound.start();
				
				if (puzzle.getSolved()){
					this.sound = new SoundMP3(this.sounds[3]);
					this.sound.start();
					JOptionPane.showMessageDialog(null, "Felicidades! Resolviste el puzzle.");
				}
			};
		}
	}
	
	public void resetPuzzle(){
		
		if (!enabled) return;
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
