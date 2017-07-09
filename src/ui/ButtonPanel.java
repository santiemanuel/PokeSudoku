package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import utils.SudokuGen;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonPanel.
 */
public class ButtonPanel extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The buttons. */
	private JButton[] buttons;
	
	private int[] imgsize;
	private int index,count;
	private Timer timer;
	private ImageButton images;
	private static final int COUNT = 10;
	private static final int INITSIZE = 32;
	private static final int COLUMNS = 1;
	private static final int ROWS = 9;
	
	/**
	 * Instantiates a new button panel.
	 *
	 * @param puzzle The Sudoku matrix object
	 * @param images The images object
	 * @param sPanel The Sudoku JPanel
	 */
	public ButtonPanel(SudokuGen puzzle, ImageButton images, SudokuPanel sPanel){
		
		//Create an array of JButton with 9 elements
		this.buttons = new JButton[puzzle.getMyboard().getNewnumbers().size()];
		
		this.images = images;
		this.imgsize = new int[COUNT];
		for (int i=0;i<COUNT;i++){
			imgsize[i] = INITSIZE;
		}
		//Sets the layout to a 9*1 GridLayout with padding 5
		this.setLayout(new GridLayout(ROWS,COLUMNS,5,5));
		
		//Load an image, create the MouseListener and add it at index i
		for (int i=1;i<this.images.getImagelist().size();i++){
			ImageIcon image = new ImageIcon(this.images.getImagelist()
					.get(i).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
			buttons[i] = createMouseListener(image, i, puzzle, sPanel);
			buttons[i].setPreferredSize(new Dimension(100, 100));
			this.add(buttons[i]);
		}
		
		index = 1; //first button
		count = 0; //times to resize the button
		
		timer = new Timer(5,this);
		timer.start();	
	}
	
	public void set(int X){
		this.imgsize[index] = X;
	}
	
	/**
	 * Creates the mouse listener.
	 *
	 * @param image The image at index i
	 * @param i The index for the button i
	 * @param puzzle The Sudoku matrix object
	 * @param sPanel The Sudoku JPanel
	 * @return the JButton to add at index i
	 */
	private JButton createMouseListener(ImageIcon image, int i, SudokuGen puzzle, SudokuPanel sPanel){
		JButton button = new JButton();
		button.setIcon(image);
		button.setActionCommand(Integer.toString(puzzle.getMyboard().getNewnumbers().get(i)));
		button.addActionListener(sPanel.new ButtonActionListener());
		return button;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		count++;
		this.imgsize[index]++;
		
			ImageIcon image = new ImageIcon(this.images.getImagelist()
				.get(index).getImage().getScaledInstance(this.imgsize[index], this.imgsize[index], Image.SCALE_SMOOTH));
			buttons[index].setIcon(image);
			if (count > 24){
				count = 0;
				index++;
			}	
		if (count > 12 && index < 9)
		{
			this.imgsize[index+1]++;
			image = new ImageIcon(this.images.getImagelist()
					.get(index+1).getImage().getScaledInstance(this.imgsize[index+1], this.imgsize[index+1], Image.SCALE_SMOOTH));
				buttons[index+1].setIcon(image);
		}
		if (index == 10) timer.stop();
		repaint();
		
	}

}