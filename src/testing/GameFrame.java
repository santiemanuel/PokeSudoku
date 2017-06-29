package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.BackgroundPanel;
import ui.ButtonPanel;
import ui.ImageButton;
import ui.SudokuPanel;
import utils.SudokuGen;


// TODO: Auto-generated Javadoc
/**
 * The Class GameFrame.
 */
public class GameFrame extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sudoku JPanel. */
	private SudokuPanel sPanel;
	
	/** The Buttons JPanel. */
	private ButtonPanel iconsPanel;
	
	/** The window and background JPanel. */
	private JPanel windowPanel, bgPanel, buttonsPanel;
	
	/** The Layered Pane. */
	private JLayeredPane lp;
	
	/** The ImageButton object images. */
	private ImageButton images;
	
	/** The Sudoku matrix object. */
	private SudokuGen puzzle;
	
	/** The newgame and hint buttons. */
	private JButton newgame, hint;
	
	/** The width. */
	private static int WIDTH;
	
	/** The height. */
	private static int HEIGHT;
	
	/**
	 * Instantiates a new game frame.
	 */
	public GameFrame(){
		
		this.setTitle("PokeSudoku");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setResolution();
		
		this.lp = new JLayeredPane();
		this.lp.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.lp.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		
		this.windowPanel = new JPanel(new BorderLayout());
		windowPanel.setPreferredSize(new Dimension(WIDTH+100,HEIGHT+30));		
		windowPanel.setMinimumSize(new Dimension(WIDTH+100,HEIGHT+30));
	
		this.setResizable(false);
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.buttonsPanel = new JPanel(new BorderLayout());
		
		this.newgame = new JButton("Nuevo juego");
	    this.newgame.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        rebuild();
	      }
	    });
	    
	    this.hint = new JButton("Pista");
	    this.hint.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		getHint();
	    	}
	    });
		
	    
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle, WIDTH);
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, this.images);
		this.sPanel.setOpaque(false);
		this.iconsPanel = new ButtonPanel(puzzle, images, sPanel);
	    
		this.lp.add(this.bgPanel, new Integer(1));
		this.lp.add(this.sPanel, new Integer(2));
		
		this.buttonsPanel.add(newgame, BorderLayout.WEST);
	    
		this.windowPanel.add(this.lp);
		this.windowPanel.add(this.buttonsPanel, BorderLayout.SOUTH);
		
		this.add(windowPanel);
		
		
	}
	
	/**
	 * Sets the resolution.
	 */
	private void setResolution(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)dim.getWidth();
		if (width < 1920 ){
			WIDTH = 600;
			HEIGHT = 600;
		}
		else{
			WIDTH = 800;
			HEIGHT = 800;
		}
		
	}
	
	/**
	 * Rebuild.
	 */
	public void rebuild(){
	
		this.lp.removeAll();
		this.buttonsPanel.removeAll();
		this.windowPanel.removeAll();
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle, WIDTH);
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, this.images);
		this.sPanel.setOpaque(false);
		this.sPanel.setSize(new Dimension(WIDTH, HEIGHT));
		
		this.iconsPanel = new ButtonPanel(puzzle, images, sPanel);
		this.sPanel.newSudoku(this.puzzle, images);

		this.lp.add(bgPanel, new Integer(1));
		this.lp.add(sPanel, new Integer(2));
		windowPanel.add(this.lp, BorderLayout.LINE_START);
		
		this.buttonsPanel.add(newgame, BorderLayout.WEST);
		this.buttonsPanel.add(hint, BorderLayout.EAST);
		
		this.windowPanel.add(this.buttonsPanel, BorderLayout.SOUTH);
		this.windowPanel.add(this.iconsPanel, BorderLayout.LINE_END);
		
		this.add(windowPanel);
		
		this.sPanel.revalidate();
		this.sPanel.repaint();
		
	}
	
	public void getHint(){
		this.sPanel.getSudokuHint();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				GameFrame frame = new GameFrame();
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

	}

}
