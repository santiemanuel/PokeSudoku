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


public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SudokuPanel sPanel;
	private ButtonPanel bPanel;
	private JPanel windowPanel, bgPanel;
	private JLayeredPane lp;
	private ImageButton images;
	private SudokuGen puzzle;
	private JButton mybutton;
	private static int WIDTH;
	private static int HEIGHT;
	
	public GameFrame(){
		
		this.setTitle("PokeSudoku");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		mybutton = new JButton("Nuevo juego");
	    mybutton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        rebuild();
	      }
	    });
		
	    
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle, WIDTH);
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, this.images);
		this.sPanel.setOpaque(false);
		this.bPanel = new ButtonPanel(puzzle, images, sPanel);
	    
		this.lp.add(this.bgPanel, new Integer(1));
		this.lp.add(this.sPanel, new Integer(2));
	    
		windowPanel.add(this.lp);
		windowPanel.add(mybutton, BorderLayout.SOUTH);
		windowPanel.add(this.bPanel, BorderLayout.LINE_END);
		
		this.add(windowPanel);
		
		
	}
	
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
	
	public void rebuild(){
	
		this.lp.removeAll();
		this.windowPanel.removeAll();
		
		this.bgPanel = new BackgroundPanel(WIDTH,HEIGHT);
		this.bgPanel.setSize(new Dimension(WIDTH,HEIGHT));
		
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle, WIDTH);
		
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT, this.images);
		this.sPanel.setOpaque(false);
		this.sPanel.setSize(new Dimension(WIDTH, HEIGHT));
		
		this.bPanel = new ButtonPanel(puzzle, images, sPanel);
		this.sPanel.newSudoku(this.puzzle, images);

		this.lp.add(bgPanel, new Integer(1));
		this.lp.add(sPanel, new Integer(2));
		windowPanel.add(this.lp, BorderLayout.LINE_START);
		windowPanel.add(mybutton, BorderLayout.SOUTH);
		windowPanel.add(this.bPanel, BorderLayout.LINE_END);
		this.add(windowPanel);
		sPanel.revalidate();
		sPanel.repaint();
		
	}

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
