package ui;

import java.awt.Graphics2D;

import utils.Position;


public class Icon {
	int x = 0;
	int y = 0;
	
	public Icon(Position cell){
		this.x = cell.getX();
		this.y = cell.getY();
	}
	
	public void drawIcon(Graphics2D g){
		g.fillOval(this.x, this.y, 20, 20);
	}

}
