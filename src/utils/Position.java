package utils;

public class Position {
	protected int x;
	protected int y;
	
	public Position(int X, int Y)
	{
		this.x = X;
		this.y = Y;
	}
	
	public Position getPosition(){
		return (this);
	}
	
	public void setPosition(Position pos){
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int X) {
		this.x = X;
	}
	public int getY() {
		return y;
	}
	public void setY(int Y) {
		this.y = Y;
	}
	

}
