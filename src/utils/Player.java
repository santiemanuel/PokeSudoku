package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1593082799446683616L;
	private String name;
	private int level;
	private ArrayList<Integer> unlockedmonster;
	private int experience;
	private int availablehints;
	private int[] wonbydiff;
	
	private static final int IMGCOUNT = 377;
	
	public Player(String name, int amount){
		this.setName(name);
		this.unlockedmonster = new ArrayList<Integer>();
		this.setUnlockedmonster(generateMyNumbers(amount, unlockedmonster));
		this.setLevel();
		this.experience = 0;
		this.setAvailablehints(10);
		this.wonbydiff = new int[4];
		for (int i=0;i<4;i++) this.wonbydiff[i] = 0;
	}
	
	public int calculateLevel(){
		int level;
		level = (int) (25 + Math.sqrt(625 + 100 * experience)) / 50;		
		return (level);
	}

	public ArrayList<Integer> getUnlockedmonster() {
		return unlockedmonster;
	}

	public void setUnlockedmonster(ArrayList<Integer> unlockedmonster) {
		while (!unlockedmonster.isEmpty()){
			Integer imgID = unlockedmonster.get(0);
			this.unlockedmonster.add(imgID);
			unlockedmonster.remove(0);
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel() {
		this.level = calculateLevel();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int[] getWonbydiff() {
		return wonbydiff;
	}

	public void setWonbydiff(int[] wonbydiff) {
		this.wonbydiff = wonbydiff;
	}

	public int getAvailablehints() {
		return availablehints;
	}
	
	public void useHint(){
		this.availablehints--;
	}

	public void setAvailablehints(int availablehints) {
		this.availablehints = availablehints;
	}
	
	private ArrayList<Integer> generateMyNumbers(int amount, ArrayList<Integer> unlocked){
		Random random = new Random();
		int genNumber;
		
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		
		auxList.add(0);
		while (auxList.size() < amount){
			genNumber = random.nextInt(IMGCOUNT)+1;
			if (!auxList.contains(genNumber) && !unlocked.contains(genNumber)){
				auxList.add(genNumber);
			}
		}
		
		return(auxList);
	}
	
	public int expToNextLevel(int level){
		int levelexp = (int) (Math.pow(50*level-25, 2) - 625) / 100;
		return levelexp;
	}
	
	public int currentExp(){
		int current = (this.getExperience() * 100) / (expToNextLevel(this.getLevel()+1));
		return current;
	}
	
	public void winGame(int difficulty){
		setAvailablehints(this.getAvailablehints()+10);
		setExperience(this.getExperience()+20+10*difficulty);
		setLevel();
		this.wonbydiff[difficulty]++;
	}
	
}
