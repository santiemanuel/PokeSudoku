package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.BackgroundPanel;
import ui.ButtonPanel;
import ui.ImageButton;
import ui.SudokuPanel;
import utils.Sudoku;


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
	private Sudoku puzzle;
	
	/** The newgame and hint buttons. */
	private JButton newgame, hint, clear;
	
	private String[] difficulty = {"Facil", "Normal", "Dificil", "Desafio"};
	
	/** The selected diff. */
	private int selectedDiff;
	
	/** The difficulty combo box. */
	private JComboBox<String> diffComboBox;
	
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
		
		this.selectedDiff = 0;
		this.lp = new JLayeredPane();
		this.lp.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.lp.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		
		this.windowPanel = new JPanel(new BorderLayout());
		windowPanel.setPreferredSize(new Dimension(WIDTH+100,HEIGHT+30));		
		windowPanel.setMinimumSize(new Dimension(WIDTH+100,HEIGHT+30));
	
		this.setResizable(false);
		
		this.diffComboBox = new JComboBox<String>(difficulty);
		ActionListener diffActionListener = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){ 
			
				String sel = (String) diffComboBox.getSelectedItem();
			
				switch (sel){
				case "Facil":
					selectedDiff = 0;
					break;
				case "Normal":
					selectedDiff = 1;
					break;
				case "Dificil":
					selectedDiff = 2;
					break;
				case "Desafio":
					selectedDiff = 3;
					break;
				}
			}
		};
		
		this.diffComboBox.addActionListener(diffActionListener);
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.buttonsPanel = new JPanel();
		
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
	    
		    
		this.lp.add(this.bgPanel, new Integer(1));
		
		this.buttonsPanel.add(this.diffComboBox);
		this.buttonsPanel.add(this.newgame);
	    
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
		
		this.puzzle = null;
		this.images = null;
		this.lp.removeAll();
		this.buttonsPanel.removeAll();
		this.windowPanel.removeAll();
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.puzzle = new Sudoku(this.selectedDiff);
		this.images = new ImageButton(this.puzzle.getSudoku(), WIDTH);
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, this.images);
		this.sPanel.setOpaque(false);
		this.sPanel.setSize(new Dimension(WIDTH, HEIGHT));
		
		this.iconsPanel = new ButtonPanel(this.puzzle.getSudoku(), images, sPanel);
		this.sPanel.newSudoku(this.puzzle.getSudoku(), images);

		this.lp.add(bgPanel, new Integer(1));
		this.lp.add(sPanel, new Integer(2));

		this.windowPanel.add(this.lp, BorderLayout.LINE_START);
		
		this.clear = new JButton("Limpiar");
	    this.clear.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		clearPanel();
	    	}
	    });
		
		
		this.buttonsPanel.add(newgame);
		this.buttonsPanel.add(diffComboBox);
		this.buttonsPanel.add(clear);
		this.buttonsPanel.add(hint);
		
		this.windowPanel.add(this.buttonsPanel, BorderLayout.SOUTH);
		this.windowPanel.add(this.iconsPanel, BorderLayout.LINE_END);
		
		this.add(windowPanel);
		
		this.sPanel.revalidate();
		this.sPanel.repaint();
	}
	
	public void clearPanel(){
		this.sPanel.resetPuzzle();
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
