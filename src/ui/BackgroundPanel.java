package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	private Image background, sudomatrix;
	private int WIDTH;
	private int HEIGHT;
	
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

	@Override
	protected void paintComponent(Graphics g) {
		
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(this.sudomatrix, 5, 5, WIDTH-10, HEIGHT-10, null);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		
	}
	
}
