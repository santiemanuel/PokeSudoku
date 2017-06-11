package ui;

import java.util.ArrayList;
import utils.Position;

public class DrawIcons {
	
	private ArrayList<Position> positions;
	private ArrayList<Icon> myicons;

	
	public DrawIcons(){
		
		System.out.println("Inicio DrawIcons");
		ListImgPosition myList = new ListImgPosition();
		ArrayList<Icon> myicons = new ArrayList<Icon>();
		this.positions = myList.getListPosition();
		
		while (!positions.isEmpty()){
			Position element;
			element = positions.get(0);
			myicons.add(new Icon(element));
			positions.remove(0);
		}
		System.out.println("Fin DrawIcons");
		
	}
	
	public ArrayList<Icon> getMyicons(){
		System.out.println("Iconos entregados");
		return (this.myicons);
	}
	
}
