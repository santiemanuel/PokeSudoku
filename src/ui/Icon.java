package ui;

import java.awt.Graphics;

import utils.Position;


public class Icon {
	int x = 0;
	int y = 0;
	
	public Icon(Position cell){
		this.x = cell.getX();
		this.y = cell.getY();
	}
	
	public void drawIcon(Graphics g){
		g.fillOval(this.x, this.y, 10, 10);
	}

}
