package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
public class ButtonPanel extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The buttons. */
	private JButton[] buttons;
	
	private double[] imgsize;
	private int index,count,angle1;
	private double alpha1;
	private Timer timer;
	private ImageButton images;
	private static final int COUNT = 10;
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
		this.imgsize = new double[COUNT];
		for (int i=0;i<COUNT;i++){
			imgsize[i] = INITSIZE;
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
		count = 0; //times to resize the button
		alpha1 = 0.0f;
		
		angle1 = -36;
		
		timer = new Timer(5,this);
		timer.start();	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		count++;
		update(index);
		repaint();
		
	}
	
	private void update(int first){

		if (count < 36){
			alpha1+=0.028;
			angle1+=1.0;
			if (alpha1 > 1.0) alpha1 = 1.0;
			imgsize[first]+=0.02;
			
			ImageIcon image = new ImageIcon
					(resizedImage(first, this.imgsize[first], angle1, alpha1));
			buttons[first].setIcon(image);
		}
		else{
			count = 0;
			alpha1 = 0.0f;
			angle1 = -36;
			index++;
		}
	
	if (index > ROWS) timer.stop();
	
	}
	
	private BufferedImage resizedImage(int index, double size, int angle, double alpha){

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
	
	public BufferedImage blackImage(BufferedImage image){
		BufferedImage shadow = new BufferedImage(
	            image.getWidth() ,
	            image.getHeight() ,
	            BufferedImage.TYPE_INT_ARGB);
	        
	        Graphics2D g2 = shadow.createGraphics();
	        g2.drawImage(image, 0, 0, null);
	        
	        g2.setComposite(AlphaComposite.SrcIn);
	        g2.setColor(Color.BLACK);
	        g2.fillRect(0, 0, shadow.getWidth(), shadow.getHeight());       
	        
	        g2.dispose();      
	        
	        return shadow;
	}
	

}