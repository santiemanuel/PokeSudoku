package utils;

public class PokeVal {
	private int IDPoke;
	private String nameImg;
	
	public PokeVal(int ID, String name){
		this.IDPoke = ID;
		this.nameImg = name;	
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

}
