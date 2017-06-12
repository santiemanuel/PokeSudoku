package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import utils.Position;


public class Icon {
	int x = 0;
	int y = 0;
	private Color backgroundColour;
	
	public Icon(Position cell){
		Random random = new Random();
		this.x = cell.getX();
		this.y = cell.getY();
		this.backgroundColour = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), (float) (1.0));
	}
	
	public void drawIcon(Graphics2D g){
		g.setColor(this.backgroundColour);
		g.fillOval(this.x, this.y, 20, 20);
	}

}
