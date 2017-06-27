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

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {
	
	private ImageButton myimages;
	private SudokuGen puzzle;
	private static final int ROWS = 9;
	private static final int COLUMNS = 9;
	private Point selectedLabel;
	private JPanel[][] mypanels;


	public SudokuPanel(int WIDTH, int HEIGHT, ImageButton myimages){
				
		this.setLayout(new GridLayout(9,9,0,0));
		this.mypanels = new JPanel[ROWS][COLUMNS];

		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				
				this.mypanels[row][column] = new JPanel();
				this.mypanels[row][column].add(new JLabel());
				((JLabel)mypanels[row][column].getComponent(0)).setIcon(myimages.getImages().get(0));
				this.add(this.mypanels[row][column]);
		}
		
	}
	
	public void newSudoku(SudokuGen puzzle, ImageButton myimages){
		this.removeAll();
		this.selectedLabel = new Point();
		this.puzzle = puzzle;
		this.myimages = myimages;
		for (int i=0;i<ROWS*COLUMNS;i++)
		{
				int row = i / ROWS;
				int column = i % ROWS;
				ImageIcon element = this.myimages.getImageAt(row,column);
				this.mypanels[row][column] = new JPanel();
				this.mypanels[row][column].setOpaque(false);
				this.mypanels[row][column].add(createGridLabel(element, row, column));
				this.add(this.mypanels[row][column]);
				
		}

	}
	
	private JLabel createGridLabel(ImageIcon element, int r, int c){
		JLabel lbl = new JLabel(element);
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setVerticalAlignment(JLabel.CENTER);
		
		lbl.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if (puzzle.isCellMutable(r, c)){
					if (SwingUtilities.isLeftMouseButton(e)){
						selectedLabel.setLocation(r, c);
					}
					else if (SwingUtilities.isRightMouseButton(e)){
						puzzle.makeMove(r, c, 0);
						((JLabel)mypanels[r][c].getComponent(0)).setIcon(myimages.getImages().get(0));
					}
				}
				revalidate();
				repaint();
				
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
				((JLabel)this.mypanels[r][c].getComponent(0)).setIcon(myimages.getImageAt(r,c));

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
        int x,y;
        Image img = myimages.getPokeCell().getImage();
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
        img = myimages.getMarkedCell().getImage();
        x = mypanels[selectedLabel.x][selectedLabel.y].getX();
        y = mypanels[selectedLabel.x][selectedLabel.y].getY();
        g.drawImage(img, x, y, null);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		
	}
	
	
}
