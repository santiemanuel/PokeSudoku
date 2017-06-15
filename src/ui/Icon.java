package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Position;
import utils.SudokuBoard;


public class Icon {
	private int x = 0;
	private int y = 0;
	private Color mycolor;
	private static String COLOR1 = "#03256C";
	private static String COLOR2 = "#BEB542";
	private static String COLOR3 = "#83C350";
	private static String COLOR4 = "#4B3F72";
	private static String COLOR5 = "#119DA4";
	private static String COLOR6 = "#FFC857";
	private static String COLOR7 = "#D52941";
	private static String COLOR8 = "#E2ABBF";
	private static String COLOR9 = "#D5573B";
	
	public Icon(Position cell){
		this.x = cell.getX();
		this.y = cell.getY();
	}
	
	public void setIconColor(SudokuBoard myboard, int r, int c){
		int number = myboard.getBoard().getValue(r, c).getIDPoke();
		if (number == 1) mycolor = Color.decode(COLOR1);
		if (number == 2) mycolor = Color.decode(COLOR2);
		if (number == 3) mycolor = Color.decode(COLOR3);
		if (number == 4) mycolor = Color.decode(COLOR4);
		if (number == 5) mycolor = Color.decode(COLOR5);
		if (number == 6) mycolor = Color.decode(COLOR6);
		if (number == 7) mycolor = Color.decode(COLOR7);
		if (number == 8) mycolor = Color.decode(COLOR8);
		if (number == 9) mycolor = Color.decode(COLOR9);
	}
	
	public void drawIcon(Graphics2D g){
		g.setColor(mycolor);
		g.fillOval(this.x, this.y, 64, 64);
	}

}
