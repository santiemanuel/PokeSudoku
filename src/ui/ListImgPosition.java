package ui;
import java.util.ArrayList;

import utils.Position;

public class ListImgPosition {
	private static final int WIDTH = 640;
	private static final int HEIGHT = 640;
	
	private ArrayList<Position> position;
	
	public ListImgPosition(){
		
		ArrayList<Position> myList = new ArrayList<Position>();
		for (int x = 0; x <= WIDTH; x+=80)
		{
			for (int y = 0; y <= HEIGHT; y+=80)
			{	
				Position cell = new Position(x,y);
				myList.add(cell);
			}
		}
		
		this.position = myList;
	}

	public ArrayList<Position> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Position> position) {
		this.position = position;
	}
	
	

}
