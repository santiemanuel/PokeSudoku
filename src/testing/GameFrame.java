package testing;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui.SudokuPanel;
import utils.SudokuBoard;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private SudokuPanel sPanel;
	
	public GameFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel windowPanel = new JPanel();
		windowPanel.setPreferredSize(new Dimension(800,800));
		
		windowPanel.setMinimumSize(new Dimension(800,800));
		this.sPanel = new SudokuPanel();
		windowPanel.add(this.sPanel);
		this.add(windowPanel);
		rebuild();
	}
	
	public void rebuild(){
		SudokuBoard puzzle = new SudokuBoard();
		puzzle.SwapColumns(0, 1, 1);
		this.sPanel.newSudoku(puzzle);
		this.sPanel.repaint();
		this.sPanel.revalidate();
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
