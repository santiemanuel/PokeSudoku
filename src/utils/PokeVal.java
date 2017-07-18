package utils;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PokeVal.
 */
public class PokeVal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1302097317810607755L;

	/** The ID poke. */
	private int IDPoke;
	
	/** The name of the image. */
	private String nameImg;
	
	/**
	 * Instantiates a new PokeVal.
	 *
	 * @param ID The id
	 * @param name The name
	 */
	public PokeVal(int ID, String name){
		this.IDPoke = ID;
		this.nameImg = name;
	}
	
	public PokeVal(){}

	/**
	 * Gets the IDpoke.
	 *
	 * @return the IDpoke
	 */
	public int getIDPoke() {
		return IDPoke;
	}

	/**
	 * Sets the ID poke.
	 *
	 * @param IDPoke The new IDpoke
	 */
	public void setIDPoke(int IDPoke) {
		this.IDPoke = IDPoke;
	}

	/**
	 * Gets the name img.
	 *
	 * @return the name img
	 */
	public String getNameImg() {
		return nameImg;
	}

	/**
	 * Sets the name img.
	 *
	 * @param nameImg The new nameimg
	 */
	public void setNameImg(String nameImg) {
		this.nameImg = nameImg;
	}

}
