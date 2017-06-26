package utils;

import java.util.Random;

public class SudokuGen extends SudokuBoard{

	private Random random;
	private Matrix genboard;
	private final int SIZE = 9;
	private Boolean [][] mutable;
	private static int LIMIT = 5;
	public SudokuGen(){
		super();
		random = new Random();
		this.mutable = new Boolean[SIZE][SIZE];
		this.genboard = new Matrix(); 
		this.genboard = super.getBoard();
		for (int i=0;i<random.nextInt(LIMIT);i++){
			super.SwapColumns(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapRows(random.nextInt(3), random.nextInt(3), random.nextInt(3));
			super.SwapVals(random.nextInt(9)+1, random.nextInt(9)+1);
		}
		initMutable();
		removeValue();
		updateMutable();
	
	}
	
	private void initMutable(){
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				this.mutable[r][c] = true;
			}
		}
	}
	
	public Boolean[][] getMutable(){
		return (this.mutable);
	}
	
	public boolean validCol(int col, int value){
		for (int r=0; r<SIZE;r++){
			if (this.genboard.getMatrix()[r][col].getIDPoke() == value){
				return false;
			}
		}
		return true;
	}
	
	public boolean validBox(int col, int row, int value){
		int boxRow = row / 3;
		int boxCol = col / 3;
		
		int startRow = (boxRow * 3);
		int startCol = (boxCol * 3);
		
		for (int r = startRow; r<= (startRow+3)-1;r++){
			for (int c = startCol; c<= (startCol+3)-1;c++){
				if (this.genboard.getMatrix()[r][c].getIDPoke() == value)
					return false;
			}
		}
		return true;
		
	}
	
	public boolean validRow(int row, int value){
		for (int c=0; c<SIZE;c++){
			if (this.genboard.getMatrix()[row][c].getIDPoke() == value){
				return false;
			}
		}
		return true;
	}
	
	private void updateMutable(){
		for (int r=0; r<SIZE; r++){
			for (int c=0; c<SIZE; c++){
				if (this.genboard.getMatrix()[r][c].getIDPoke() != 0 ){
					this.mutable[r][c] = false;
				}
			}
		}
	}
	
	private void removeValue(){
		int spaces = 40;
		int index = 0;
		
		while (index < spaces){
			int r = random.nextInt(9);
			int c = random.nextInt(9);
			if (this.genboard.getMatrix()[r][c].getIDPoke() != 0){
				index++;
				this.genboard.getMatrix()[r][c].setIDPoke(0);
				this.genboard.getMatrix()[r][c].setNameImg("0.png");
			}
		}
	}
	
	public boolean isValidMove(int row, int col, int value){
		if ((this.validCol(col, value)) && (this.validRow(row, value)) && (this.validBox(col, row, value))){
			return true;
		}
		return false;
	}
	
	public boolean isCellMutable(int row, int col){
		return this.mutable[row][col];
	}
	
	public void makeMove(int row, int col, int value){
		if (this.isValidMove(row, col, value) && this.isCellMutable(row, col)){
			Integer num = new Integer(value);
			this.genboard.getMatrix()[row][col].setIDPoke(value);
			this.genboard.getMatrix()[row][col].setNameImg(num.toString()+".png");
			
		}
	}
	
	
	
	
}
