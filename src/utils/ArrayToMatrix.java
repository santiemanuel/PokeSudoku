package utils;

import java.util.ArrayList;

import ui.Icon;

public class ArrayToMatrix {
	
	private static int ROWS = 9;
	private static int COLUMNS = 9;
	
			
	
	public Icon[][] getMatrix(ArrayList<Icon> myicons){
		
		Icon[][] myiconMatrix = new Icon[ROWS][COLUMNS];
		
		for (int r = 0;r<ROWS;r++)
		{
			for (int c = 0;c<COLUMNS;c++)
			{
				myiconMatrix[r][c] = myicons.get(0);
				myicons.remove(0);
			}
		}
		
		return (myiconMatrix);
	}

}
