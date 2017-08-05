package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JViewport;
import javax.swing.Timer;

public class MyViewport extends JViewport {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private float numcolor;
	private int sign;
	private Color color1, color2;

	public MyViewport() {
		this.numcolor = 0f;
		this.sign = 1;
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(360,800));
        this.color1 = new Color(128,62,153,255);
        this.color2 = new Color(192,201,200,255);
        ActionListener action = new ActionListener(){
        	
        	@Override
        	public void actionPerformed(ActionEvent evt){
        		
        		if (numcolor < 720f) numcolor+=1.5f*sign;
        		else{
        			Color aux = color1;
        			color1 = color2;
        			color2 = aux;
        			numcolor = 0f;
        		}

        		revalidate();
        		repaint();
        	};
        };
        timer = new Timer(20,action);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        final BufferedImage image = new BufferedImage(
        		this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2d = image.createGraphics();
        GradientPaint prim = new GradientPaint(0f, 0f, color1,
        		numcolor, 0f, color2);

        g2d.setPaint(prim);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.drawImage(image, 0, 0, null);
        		
    }
}
