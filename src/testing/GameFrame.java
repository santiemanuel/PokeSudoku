package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.ButtonPanel;
import ui.ImageButton;
import ui.SudokuPanel;
import utils.SudokuGen;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private SudokuPanel sPanel;
	private ButtonPanel bPanel;
	private JPanel lPanel, windowPanel;
	private ImageButton images;
	private SudokuGen puzzle;
	private JButton mybutton;
	private static int WIDTH;
	private static int HEIGHT;
	
	public GameFrame(){
			
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResolution();
		this.windowPanel = new JPanel(new BorderLayout());
		this.lPanel = new JPanel();
		lPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		lPanel.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		lPanel.setMaximumSize(new Dimension(WIDTH,HEIGHT));
	
		windowPanel.setPreferredSize(new Dimension(WIDTH+100,HEIGHT+30));		
		windowPanel.setMinimumSize(new Dimension(WIDTH+100,HEIGHT+30));
		this.setResizable(false);
		
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
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT);
		this.bPanel = new ButtonPanel(puzzle, images, sPanel);
	    lPanel.add(this.sPanel, BorderLayout.LINE_START);
		windowPanel.add(lPanel, BorderLayout.LINE_START);
		windowPanel.add(mybutton, BorderLayout.SOUTH);
		windowPanel.add(this.bPanel, BorderLayout.LINE_END);
		this.add(windowPanel);		

	    sPanel.repaint();
		
		
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
	
		lPanel.removeAll();
		windowPanel.removeAll();
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle, WIDTH);
		this.sPanel = new SudokuPanel(WIDTH, HEIGHT);
		this.bPanel = new ButtonPanel(puzzle, images, sPanel);
		this.sPanel.newSudoku(this.puzzle, images);
	    lPanel.add(this.sPanel, BorderLayout.LINE_START);
		windowPanel.add(lPanel, BorderLayout.LINE_START);
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
				frame.setVisible(true);
				frame.pack();
			}
		});

	}

}
