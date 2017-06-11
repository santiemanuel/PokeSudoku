package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DrawBoard extends JPanel {
    
	private DrawIcons myicons;
    
	public DrawBoard (){
		super();
		this.myicons = new DrawIcons();
		DrawBoard.this.repaint();
		
		
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Icon> listIcons = new ArrayList<Icon>();
        listIcons = this.myicons.getMyicons();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Icon myicon: listIcons){
        	myicon.drawIcon(g2d);
        }
    }
	
	
	
}
