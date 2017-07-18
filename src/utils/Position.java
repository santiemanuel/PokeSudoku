package utils;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Position.
 */
public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 449713886761515430L;

	/** The x. */
	protected int x;
	
	/** The y. */
	protected int y;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param X The x
	 * @param Y The y
	 */
	public Position(int X, int Y)
	{
		this.x = X;
		this.y = Y;
	}
	
	public Position(){}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition(){
		return (this);
	}
	
	/**
	 * Sets the position.
	 *
	 * @param pos The new position
	 */
	public void setPosition(Position pos){
		this.x = pos.x;
		this.y = pos.y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param X The new x
	 */
	public void setX(int X) {
		this.x = X;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param Y The new y
	 */
	public void setY(int Y) {
		this.y = Y;
	}
	

}
