package utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
	private int[] catchcount;
	private int ownedpokemon;
	private int experience;
	private int availablehints;
	private int[] wonbydiff;
	private SimpleDateFormat date = new SimpleDateFormat("mm:ss");
	private String playtime;
	private long totaltime;
	private int profilepic;
	private long[] records;
	private String[] bestplay;
	private int[] typecount;
	private final int TYPES = 18;
	private final int UNOWN = 201;
	private final int HINTS = 10;
	private Pokemon pokemon;
	
	private final int IMGCOUNT = 388;
	private final int DIFFCOUNT = 4;
	
	public Player(String name, int amount){
		this.setName(name);
		this.pokemon = new Pokemon();
		this.typecount = new int[TYPES];
		this.unlockedmonster = new ArrayList<Integer>();
		this.unlockedmonster.add(0);
		this.catchcount = new int[1028];
		this.setUnlockedmonster(initMyNumbers(amount));
		this.setLevel();
		this.experience = 0;
		this.setAvailablehints(HINTS);
		this.wonbydiff = new int[DIFFCOUNT];
		this.records = new long[DIFFCOUNT];
		this.bestplay = new String[DIFFCOUNT];
		this.totaltime = 0;
		this.setPlaytime(date.format(totaltime));
		for (int i=0;i<DIFFCOUNT;i++){
			this.wonbydiff[i] = 0;
			this.records[i] = 0;
			this.bestplay[i] = date.format(this.records[i]);
		}
		this.setProfilepic(getUnlockedmonster().get(1));
		this.ownedpokemon = countPokemon(); 
		
	}
	
	private int countPokemon(){
		int counter = 0;
		for (int i=1;i<1028;i++){
			counter += this.catchcount[i];
		}
		return (counter);
	}
		
	private ArrayList<Integer> initMyNumbers(int amount){
		Random random = new Random();
		int genNumber;
		int unique = 9;
		
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		
		while (auxList.size() < unique){
			genNumber = random.nextInt(IMGCOUNT)+1;
			if (!auxList.contains(genNumber)){
				if (genNumber == UNOWN){
					genNumber = random.nextInt(28)+1000;
					if (!auxList.contains(genNumber)){
						auxList.add(genNumber);
						addPokemonToType(pokemon.getTypeInt(UNOWN));
						this.catchcount[genNumber]++;
					}
				} else{
					auxList.add(genNumber);
					addPokemonToType(pokemon.getTypeInt(genNumber));
					this.catchcount[genNumber]++;
				}
			}
		}
		return auxList;
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
			if (this.unlockedmonster.indexOf(imgID) == -1){
				this.unlockedmonster.add(imgID);	
			}
			unlockedmonster.remove(0);
		}
		this.ownedpokemon = countPokemon();
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
	
	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

	public long getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}

	public int getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(int profilepic) {
		this.profilepic = profilepic;
	}

	public String[] getBestplay() {
		return bestplay;
	}

	public void setBestplay(String[] bestplay) {
		this.bestplay = bestplay;
	}

	public int[] getCatchcount() {
		return catchcount;
	}

	public void setCatchcount(int[] catchcount) {
		this.catchcount = catchcount;
	}

	public int getOwnedpokemon() {
		return ownedpokemon;
	}

	public void setOwnedpokemon(int ownedpokemon) {
		this.ownedpokemon = ownedpokemon;
	}

	public int[] getTypecountlist() {
		return typecount;
	}

	public void setTypecount(int[] typecount) {
		this.typecount = typecount;
	}
	
	public void addPokemonToType(ArrayList<Integer> pokemontype){
		for (int i=0;i<pokemontype.size();i++){
			this.typecount[pokemontype.get(i)]++;
		}
	}
	
	public int getTypeCount(int pokemontype){
		return (this.typecount[pokemontype]);
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public ArrayList<Integer> generateMyNumbers(int amount){
		Random random = new Random();
		int genNumber;
		int index = 0;
		
		ArrayList<Integer> auxList = new ArrayList<Integer>();
		
		while (index < amount){
			genNumber = random.nextInt(IMGCOUNT)+1;
			if (genNumber == UNOWN){
				genNumber = random.nextInt(28)+1000;
				auxList.add(genNumber);
				this.catchcount[genNumber]++;
			} else{
				auxList.add(genNumber);
				this.catchcount[genNumber]++;
			}
			index++;
		}
		return(auxList);
	}
	
	public int expToNextLevel(int level){
		int levelexp = (int) (Math.pow(50*level-25, 2) - 625) / 100;
		return levelexp;
	}
	
	public int currentExp(){
		
		int bottom = expToNextLevel(this.getLevel());
		int top = expToNextLevel(this.getLevel()+1);
		int difference = this.getExperience() - bottom;
		
		System.out.println("Current experience: "+difference);
		int levelrange = top-bottom;
		
		System.out.println("level experience: "+levelrange);
		
		int cexp = (difference * 100) / levelrange;

		return cexp;
	}
	
	public void winGame(int difficulty, long time){
		
		setTotaltime(getTotaltime()+time);
		setPlaytime(date.format(getTotaltime()));
		setAvailablehints(this.getAvailablehints()+10);
		setExperience(this.getExperience()+30+3*difficulty);
		
		if (this.records[difficulty] == 0){
			this.records[difficulty] = time;
			this.bestplay[difficulty] = date.format(this.records[difficulty]);
		}
		else if (time < this.records[difficulty]){
			this.records[difficulty] = time;
			this.bestplay[difficulty] = date.format(this.records[difficulty]);
		}
		
		int before = getLevel();
		setLevel();
		int after = getLevel();
		if (after > before)this.setUnlockedmonster(generateMyNumbers(15));
			
		this.wonbydiff[difficulty]++;
	}
	
}
