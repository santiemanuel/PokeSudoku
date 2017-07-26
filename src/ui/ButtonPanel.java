package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.coobird.thumbnailator.*;
import net.coobird.thumbnailator.filters.Transparency;
import utils.SudokuGen;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonPanel.
 */
public class ButtonPanel extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The buttons. */
	private JButton[] buttons;
	
	/** The array of image sizes. */
	private double[] imgsize;
	
	private int index,framecount1,framecount2,angle1,angle2;
	private double alpha1,alpha2;
	private Timer timer;
	private ImageButton images;
	private static final int IMGCOUNT = 10;
	private static final double	INITSIZE = 0.4;
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
		this.imgsize = new double[IMGCOUNT];
		for (int i=0;i<IMGCOUNT;i++){
			imgsize[i] = INITSIZE; //same initial scale for all buttons
		}
		//Sets the layout to a 9*1 GridLayout with padding 5
		this.setLayout(new GridLayout(ROWS,COLUMNS,5,5));
		
		//Load an image, create the MouseListener and add it at index i
		for (int i=1;i<this.images.getImagelist().size();i++){
			buttons[i] = createMouseListener(i, puzzle, sPanel);
			buttons[i].setPreferredSize(new Dimension(100, 100));
			this.add(buttons[i]);
		}
		
		index = 1; //first button
		framecount1 = 0; //times to resize the button
		framecount2 = 0;
		alpha1 = 0.0f; //starting transparency for the image
		alpha2 = 0.0f;
		angle1 = -36; //starting angle for the image
		angle2 = -36;
		
		ActionListener animationListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update(index);
				if (index > IMGCOUNT-1) timer.stop();
			}
		};
		
		timer = new Timer(5, animationListener);
		
		timer.start(); //start animation each tick
		
			
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
	private JButton createMouseListener(int i, SudokuGen puzzle, SudokuPanel sPanel){
		JButton button = new JButton();
		button.setActionCommand(Integer.toString(puzzle.getMyboard().getNewnumbers().get(i)));
		button.addActionListener(sPanel.new ButtonActionListener());
		return button;		
	}
	
	private void update(int first){

		if (first == 10) timer.stop();
		framecount1++;
		if (framecount1 < 36 && timer.isRunning()){ //the animation will have max 36 frames
			alpha1+=0.028; //increase the transparency
			angle1+=1.0; //increase the angle rotation by 1 degree
			if (alpha1 > 1.0) alpha1 = 1.0; //cap the transparency to 1.0
			imgsize[first]+=0.02; //increase the size of the image at index first
			
			ImageIcon image = new ImageIcon
					(resizedImage(first, this.imgsize[first], angle1, alpha1)); //create the image modified with scale, rotation and alpha values
			buttons[first].setIcon(image); //set the image for the icon at index first
			
			if (framecount1 > 18 && first+1<IMGCOUNT){
				framecount2++;
				alpha2+=0.028;
				angle2+=1.0;
				if (alpha2 > 1.0) alpha2 = 1.0;
				imgsize[first+1]+=0.02;
				image = new ImageIcon
						(resizedImage(first+1, this.imgsize[first+1], angle2, alpha2)); //create the image modified with scale, rotation and alpha values
				buttons[first+1].setIcon(image); //set the image for the icon at index first
				
			}
		}
		else{ //if animated 36 times the button at index first, reset and move to the next one
			
			framecount1 = framecount2;
			alpha1 = alpha2;
			angle1 = angle2;
			framecount2 = 0; //times to animate
			alpha2 = 0.0f; //initial transparency
			angle2 = -36; //initial angle
			index++;
		}
	
	}
	
	/**
	 * Resized image.
	 *
	 * @param index the index of the button
	 * @param size the new scale of the image
	 * @param angle the new angle
	 * @param alpha the new alpha
	 * @return the buffered image
	 */
	public BufferedImage resizedImage(int index, double size, int angle, double alpha){

		BufferedImage image = (BufferedImage) this.images.getImagelist()
				.get(index).getImage();

		try {
			image = Thumbnails.of(image)
					.scale(size)
					.rotate(angle)
					.addFilter(new Transparency(alpha))
					.asBufferedImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
		
	}	

}