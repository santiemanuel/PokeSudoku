package ui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.SudokuGen;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonPanel.
 */
public class ButtonPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The buttons. */
	private JButton[] buttons;
	
	/**
	 * Instantiates a new button panel.
	 *
	 * @param puzzle The Sudoku matrix object
	 * @param images The images object
	 * @param sPanel The Sudoku JPanel
	 */
	public ButtonPanel(SudokuGen puzzle, ImageButton images, SudokuPanel sPanel){
		
		//Create an array of JButton with 9 elements
		buttons = new JButton[puzzle.getNewnumbers().size()];
		
		//Sets the layout to a 9*1 GridLayout with padding 5
		this.setLayout(new GridLayout(9,1,5,5));
		
		//Load an image, create the MouseListener and add it at index i
		for (int i=1;i<images.getImagelist().size();i++){
			ImageIcon image = new ImageIcon(images.getImagelist().get(i).getImage());
			buttons[i] = createMouseListener(image, i, puzzle, sPanel);
			this.add(buttons[i]);
		}
		
	}
	
	/**
	 * Creates the mouse listener.
	 *
	 * @param image The image at index i
	 * @param i The index i
	 * @param puzzle The Sudoku matrix object
	 * @param sPanel The Sudoku JPanel
	 * @return the JButton to add at index i
	 */
	private JButton createMouseListener(ImageIcon image, int i, SudokuGen puzzle, SudokuPanel sPanel){
		JButton button = new JButton();
		button.setIcon(image);
		button.setActionCommand(Integer.toString(puzzle.getNewnumbers().get(i)));
		button.addActionListener(sPanel.new ButtonActionListener());
		return button;
		
		
	}

}
