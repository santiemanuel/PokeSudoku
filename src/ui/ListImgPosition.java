package ui;
import java.util.ArrayList;

import utils.Position;

public class ListImgPosition {
	private static final int WIDTH = 320;
	private static final int HEIGHT = 320;
	
	public ArrayList<Position> getListPosition(){
		
		ArrayList<Position> myList = new ArrayList<Position>();
		for (int x = 0; x <= WIDTH; x+=40)
		{
			for (int y = 0; y <= HEIGHT; y+=40)
			{	
				Position cell = new Position(x,y);
				myList.add(cell);
			}
		}
		
		return (myList);
	}

}
