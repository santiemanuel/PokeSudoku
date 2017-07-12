package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class BackgroundPanel.
 */
@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	
	/** The sudomatrix. */
	private Image background, sudomatrix;
	
	/** The width. */
	private int WIDTH;
	
	/** The height. */
	private int HEIGHT;
	
	/**
	 * Instantiates a new background panel.
	 *
	 * @param WIDTH the width of the JPanel
	 * @param HEIGHT the height of the JPanel
	 */
	public BackgroundPanel(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		URL url = null;
		url = getClass().getResource("/bg.png");		
		ImageIcon ico = new ImageIcon(url);
		this.background = ico.getImage();
		url = getClass().getResource("/sudoku.png");
		ico = new ImageIcon(url);
		this.sudomatrix = ico.getImage();
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
       super.paintComponent(g);
       g.drawImage(this.background, 0, 0, WIDTH, HEIGHT, null);
       g.drawImage(this.sudomatrix, 5, 5, WIDTH-10, HEIGHT-10, null);
        
       Graphics2D g2d = (Graphics2D) g;

       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		
	}
	
}
