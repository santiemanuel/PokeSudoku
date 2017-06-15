package utils;

public class PokeVal {
	private int IDPoke;
	private String nameImg;
	private Position pos;
	
	public PokeVal(int ID, String name, Position pos){
		this.IDPoke = ID;
		this.nameImg = name;
		this.pos = pos;
	}

	public int getIDPoke() {
		return IDPoke;
	}

	public void setIDPoke(int IDPoke) {
		this.IDPoke = IDPoke;
	}

	public String getNameImg() {
		return nameImg;
	}

	public void setNameImg(String nameImg) {
		this.nameImg = nameImg;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}
