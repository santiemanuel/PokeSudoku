package testing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ui.SudokuPanel;
import utils.SudokuBoard;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private SudokuPanel sPanel;
	private SudokuBoard puzzle;
	private Random random;
	private JButton mybutton;
	private Timer mytime;
	private Boolean done;
	
	public GameFrame(){
		mytime = new Timer(200, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				swap();
				if (done){
					mytime.stop();
				}
				mytime.start();
			}
		});
		random = new Random();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel windowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		windowPanel.setPreferredSize(new Dimension(1200,800));
		
		windowPanel.setMinimumSize(new Dimension(1200,800));
		//this.setResizable(false);
		this.sPanel = new SudokuPanel();
		mybutton = new JButton("Click");
	    mybutton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        mytime.start();
	        System.out.println("Recibido");
	      }
	    });
		windowPanel.add(this.sPanel);
		windowPanel.add(mybutton);
		this.add(windowPanel);
		rebuild();
		
	}
	
	public void swap(){
		this.puzzle.SwapVals(random.nextInt(7)+1, random.nextInt(7)+1);
		this.puzzle.SwapColumns(random.nextInt(2), random.nextInt(2), random.nextInt(2));
		this.puzzle.SwapRows(random.nextInt(2), random.nextInt(2), random.nextInt(2));
		this.sPanel.removeAll();
		this.sPanel.updateSudoku(this.puzzle);
		this.sPanel.revalidate();
		this.sPanel.repaint();
		this.mybutton.doClick();
		this.done = true;
	}
	
	
	public void rebuild(){
		this.puzzle = new SudokuBoard();
		this.sPanel.newSudoku(this.puzzle);
		this.sPanel.repaint();
		this.puzzle.showSudokuMatrix();
		
		
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
