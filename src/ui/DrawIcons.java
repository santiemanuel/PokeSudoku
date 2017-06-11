package ui;

import java.util.ArrayList;
import utils.Position;

public class DrawIcons {
	
	private ArrayList<Position> positions;
	private ArrayList<Icon> myicons;

	
	public DrawIcons(){
		
		ListImgPosition myList = new ListImgPosition();
		this.myicons = new ArrayList<Icon>();
		this.positions = myList.getListPosition();
		
		while (!positions.isEmpty()){
			Position element;
			element = positions.get(0);
			myicons.add(new Icon(element));
			positions.remove(0);
		}
	}
	
	public ArrayList<Icon> getMyicons(){
		return (this.myicons);
	}
	
}
