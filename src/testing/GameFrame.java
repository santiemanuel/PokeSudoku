package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.ButtonPanel;
import ui.ImageButton;
import ui.SudokuPanel;
import utils.SudokuBoard;
import utils.SudokuGen;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private SudokuPanel sPanel;
	private ButtonPanel bPanel;
	private JPanel lPanel, windowPanel;
	private ImageButton images;
	private SudokuBoard puzzle;
	private Random random;
	private JButton mybutton;
	
	public GameFrame(){
		
		/*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)dim.getWidth();
		int height = (int)dim.getHeight();
		
		System.out.println("Tamaño pantalla: "+width+"*"+height);*/
		random = new Random();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.windowPanel = new JPanel(new BorderLayout());
		this.lPanel = new JPanel();
		lPanel.setPreferredSize(new Dimension(800,800));
		lPanel.setMinimumSize(new Dimension(800,800));
		lPanel.setMaximumSize(new Dimension(800,800));
	
		windowPanel.setPreferredSize(new Dimension(900,820));		
		windowPanel.setMinimumSize(new Dimension(900,820));
		//this.setResizable(false);
		
		mybutton = new JButton("Nuevo juego");
	    mybutton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        rebuild();
	      }
	    });
		
		this.puzzle = new SudokuGen();
		this.images = new ImageButton(puzzle);
		this.sPanel = new SudokuPanel();
		//this.sPanel.newSudoku(this.puzzle, this.images);
		this.bPanel = new ButtonPanel(puzzle, images);
	    lPanel.add(this.sPanel, BorderLayout.LINE_START);
		windowPanel.add(lPanel, BorderLayout.LINE_START);
		windowPanel.add(mybutton, BorderLayout.LINE_END);
		windowPanel.add(this.bPanel, BorderLayout.SOUTH);
		this.add(windowPanel);		

	    sPanel.repaint();
		
		
	}
	
	
	public void rebuild(){
	
		lPanel.removeAll();
		windowPanel.removeAll();
		this.sPanel.newSudoku(this.puzzle, images);
	    lPanel.add(this.sPanel, BorderLayout.LINE_START);
		windowPanel.add(lPanel, BorderLayout.LINE_START);
		windowPanel.add(mybutton, BorderLayout.LINE_END);
		windowPanel.add(this.bPanel, BorderLayout.SOUTH);
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
