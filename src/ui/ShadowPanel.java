package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ShadowPanel extends JPanel {

	private Color bgcolor;
	private int sign;
	private int alpha;
	private Timer alphatimer;
	
	public ShadowPanel(int WIDTH, int HEIGHT){
		
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.sign = 1;
		this.alpha = 0;
		
		this.bgcolor = new Color(0,0,0,0);
		
		ActionListener alphaListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				updateAlpha();
				repaint();
			}
		};
		this.alphatimer = new Timer(5, alphaListener);
	}
	
	private void updateAlpha(){
		if (sign>0){
			if (alpha<200){
				alpha+=4;
				this.bgcolor = new Color(0,0,0,alpha);
			}
			else
			{
				sign = -1;
				this.alphatimer.stop();
			}
		}
		else
		{
			if (alpha>1){
				alpha-=4;
				this.bgcolor = new Color(0,0,0,alpha);
			}
			else
			{
				sign = 1;
				this.alphatimer.stop();
			}
		}
			
	}
	
	public void setColor(){
		this.alphatimer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
       super.paintComponent(g);        
       g.setColor(bgcolor);
       g.fillRect(0, 0, getSize().width, getSize().height);       
		
	}
}
