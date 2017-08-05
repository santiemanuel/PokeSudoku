package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Pokemon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4347022008570354547L;

	private String[] pokenames = {"Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle",
			"Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Rattata", 
			"Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu", "Sandshrew", "Sandslash", "Nidoran F", "Nidorina", "Nidoqueen", 
			"Nidoran M", "Nidorino", "Nidoking", "Clefairy", "Clefable", "Vulpix", "Ninetails", "Jigglypuff", "Wigglytuff", "Zubat", "Golbat", "Oddish",
			"Gloom", "Vileplume", "Paras", "Parasect", "Venonat", "Venomoth", "Diglett", "Dugtrio", "Meowth", "Persian", "Psyduck", "Golduck", "Mankey", 
			"Primeape", "Growlite", "Arcanine", "Poliwag", "Poliwhirl", "Poliwrath", "Abra", "Kadabra", "Alakazam", "Machop", "Machoke", "Machamp", "Beelsprout", 
			"Weepinbell", "Victreebel", "Tentacool", "Tentacruel", "Geodude", "Graveler", "Golem", "Ponyta", "Rapidash", "Slowpoke", "Slowbro", "Magnemite", "Magneton", 
			"Farfetch'd", "Doduo", "Dodrio", "Seel", "Dewgong", "Grimer", "Muk", "Shelder", "Cloyster", "Gastly", "Haunter", "Gengar", "Onix", "Drowzee", "Hypno", "Krabby", 
			"Kingler", "Voltorb", "Electrode", "Exeggcute", "Exeggutor", "Cubone", "Marowak", "Hitmonlee", "Hitmonchan", "Lickitung", "Koffing", "Weezing", "Rhyhorn", "Rhydon", 
			"Chansey", "Tangela", "Kangaskhan", "Horsea", "Seadra", "Goldeen", "Seaking", "Staryu", "Starmie", "Mr. Mime", "Scyther", "Jynx", "Electabuzz", "Magmar", "Pinsir", "Tauros", 
			"Magikarp", "Gyarados", "Lapras", "Ditto", "Eevee", "Vaporeon", "Jolteon", "Flaeron", "Porygon", "Omanyte", "Omastar", "Kabuto", "Kabutops", "Aerodactyl", "Snorlax", "Articuno", 
			"Zapdos", "Moltres", "Dratini", "Dragonair", "Dragonite", "Mewtwo", "Mew", "Chikorita", "Bayleef", "Meganium", "Cyndaquil", "Quilava", "Typhlosion", "Totodile", "Croconaw", 
			"Feraligatr", "Sentret", "Furret", "Hoothoot", "Noctowl", "Ledyba", "Ledian", "Spinarak", "Ariados", "Crobat", "Chinchou", "Lanturn", "Pichu", "Cleffa", "Igglybuff", "Togepi", 
			"Togetic", "Natu", "Xatu", "Mareep", "Flaaffy", "Ampharos", "Bellossom", "Maqrill", "Azumarill", "Sudowoodo", "Politoed", "Hoppip", "Skiploom", "Jumpluff", "Aipom", "Sunkern", "Sunflora", 
			"Yanma", "Wooper", "Quagsire", "Espeon", "Umbreon", "Murkrow", "Slowking", "Misdreavus", "Unown", "Wobbuffet", "Girafarig", "Pineco", "Forretress", "Dunsparce", "Gligar", "Steelix", 
			"Snubbull", "Granbull", "Qwilfish", "Scizor", "Shuckle", "Heracross", "Sneasel", "Teddiursa", "Ursaring", "Slugma", "Magcargo", "Swinub", "Piloswine", "Corsola", "Remoraid", 
			"Octillery", "Delibird", "Mantine", "Skarmory", "Houndour", "Houndoom", "Kingdra", "Phanpy", "Donphan", "Porygon2", "Stantler", "Smeargle", "Tyrongue", "Hitmontop", "Smoochum", 
			"Elekid", "Magby", "Miltank", "Blissey", "Raikou", "Entei", "Suicune", "Larvitar", "Pupitar", "Tyranitar", "Lugia", "Ho-Oh", "Celebi", "Treecko", "Grovyle", "Sceptile", "Torchic", 
			"Combusken", "Blaziken", "Mudkip", "Marshtomp", "Swampert", "Poochyena", "Mightyena", "Zigzagoon", "Linoone", "Wurmple", "Silcoon", "Beautifly", "Cascoon", "Dustox", "Lotad", 
			"Lombre", "Ludicolo", "Seedot", "Nuzleaf", "Shiftry", "Taillow", "Swellow", "Wingull", "Pelipper", "Ralts", "Kirlia", "Gardevoir", "Surskit", "Masquerain", "Shroomish", "Breloom", 
			"Slakoth", "Vigoroth", "Slaking", "Nincada", "Ninjask", "Shedinja", "Whismur", "Loudred", "Exploud", "Makuhita", "Hariyama", "Azurill", "Nosepass", "Skitty", "Delcatty", "Sableye", 
			"Mawile", "Aron", "Lairon", "Aggron", "Meditite", "Medicham", "Electrike", "Manectric", "Plusle", "Minum", "Volbeat", "Illumise", "Roselia", "Gulpin", "Swalot", "Carvanha", "Sharpedo",
			"Wailmer", "Wailord", "Numel", "Camerupt", "Torkoal", "Spoink", "Grumpig", "Spinda", "Trapinch", "Vibrava", "Flygon", "Cacnea", "Cacturne", "Swablu", "Altaria", "Zangoose", "Seviper", 
			"Lunatone", "Solrock", "Barboach", "Whiscash", "Corphish", "Crawdaunt", "Baltoy", "Claydol", "Lileep", "Cradily", "Anorith", "Armaldo", "Feebas", "Milotic", "Castform", "Kecleon", 
			"Shuppet", "Banette", "Duskull", "Dusclops", "Tropius", "Chimecho", "Absol", "Wynaut", "Snorunt", "Glalie", "Spheal", "Sealeo", "Walrein", "Clamperl", "Huntail", "Gorebyss", "Relicanth",
			"Luvdisc", "Bagon", "Shelgon", "Salamence", "Beldum", "Metang", "Metagross", "Regirock", "Regice", "Registeel", "Latias", "Latios", "Kyogre", "Groudon", "Rayquaza", "Jirachi", "Deoxys"};
	
	private String[] unownname = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "?"};
	private String[] type = {"Normal", "Agua", "Fuego", "Planta", "Electrico", "Hielo", "Lucha", "Veneno", "Bicho", "Dragon", "Tierra", "Fantasma",  "Volador", "Siniestro", "Acero", "Psiquico", "Roca", "Hada"};
	
	private String[] poketypes = {"37", "37", "37", "2", "2", "2C", "1", "1", "1", "8", "8", "8C", "87",
								  "87", "87", "0C", "0C", "0C", "0", "0", "0C", "0C", "7", "7", "4", "4",
								  "A", "A", "7", "7", "7A", "7", "7", "7A", "H", "H", "2", "2", "0H", "0H",
								  "7C", "7C", "37", "37", "37", "83", "83", "87", "87", "A", "A", "0", "0",
								  "1", "1", "6", "6", "2", "2", "1", "1", "16", "F", "F", "F", "6", "6", "6",
								  "37", "37", "37", "17", "17", "GA", "GA", "GA", "2", "2", "1F", "1F", "4E",
								  "4E", "0C", "0C", "0C", "1", "15", "7", "7", "1", "15", "B7", "B7", "B7",
								  "GA", "F", "F", "1", "1", "4", "4", "3F", "3F", "A", "A", "6", "6", "0", "7",
								  "7", "GA", "GA", "0", "3", "0", "1", "1", "1", "1", "1", "1F", "FH", "8C",
								  "5F", "4", "2", "8", "0", "1", "1C", "15", "0", "0", "1", "4", "2", "0", "1G",
								  "1G", "1G","1G", "CG", "0", "5C", "4C", "2C", "9", "9", "9C", "F", "F", "3", "3",
								  "3", "2", "2", "2", "1", "1", "1", "0", "0", "0C", "0C", "8C", "8C", "87", "87",
								  "7C", "14", "14", "4", "H", "0H", "H", "HC", "FC", "FC", "4", "4", "4", "3", "1H",
								  "1H", "G", "1", "3C", "3C", "3C", "0", "3", "3", "8C", "1A", "1A", "F", "D", "DC",
								  "1F", "B", "F", "F", "0F", "8", "8E", "0", "AC", "EA", "H", "H", "17", "8E", "8G",
								  "86", "D5", "0", "0", "2", "2G", "5A", "5A", "1G", "1", "1", "5C", "1C", "EC", "D2",
								  "D2", "19", "A", "A", "0", "0", "0", "6", "6", "5F", "4", "2", "0", "0", "4", "2",
								  "1", "GA", "GA", "GD", "FC", "2C", "F3", "3", "3", "3", "2", "26", "26", "1", "1A", "1A", "D",
								  "D", "0", "0", "8", "8", "8C", "8", "87", "13", "13", "13", "3", "3D", "3D", "0C", "0C",
								  "1C", "1C", "FH", "FH", "FH", "81", "8C", "3", "36", "0", "0", "0", "8A", "8C", "8B", "0",
								  "0", "0", "6", "6", "0H", "G", "0", "0", "DB", "EH", "EG", "EG", "EG", "6F", "6F", "4", "4",
								  "4", "4", "8", "8", "37", "7", "7", "1D", "1D", "1", "1", "2A", "2A", "2", "F", "F", "0",
								  "A", "A9", "A9", "3", "3D", "0C", "9C", "0", "7", "GF", "GF", "1A", "1A", "1", "1D", "AF", "AF",
								  "G3", "G3", "G8", "G8", "1", "1", "0", "0", "B", "B", "B", "B", "3C", "F", "D", "F", "5", "5",
								  "51", "51", "51", "1", "1", "1", "1G", "1", "9", "9", "9C", "EF", "EF", "EF", "G", "5", "E", "9F",
								  "9F", "1", "A", "9C", "EF", "F"};
	
	private ArrayList<String> indexlist = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H"));
			
								
	
	private ArrayList<ArrayList<Integer>> pokemontype;

	public Pokemon(){
		pokemontype = new ArrayList<ArrayList<Integer>>();
		for (int i=0;i<1028;i++){
			pokemontype.add(i, new ArrayList<Integer>());
		}
		initTypes();
	}
	
	public String getName(int pos){
		if (pos < 1000){
			return (pokenames[pos]);
		}else{
			return (unownname[pos % 1000 + 1]);
		}
	}
	
	public void initTypes(){
		for (int i=0;i<386;i++){
			System.out.println("Tipo: "+poketypes[i]);
			String singletype = poketypes[i];
			int length = singletype.length();
			for (int j=0;j<length;j++){
				String onetype = Character.toString(singletype.charAt(j));
				int position = indexlist.indexOf(onetype);
				pokemontype.get(i).add(j, position);
			}
		}
	}
	
	public ArrayList<String> getTypeStr(int pos){
		ArrayList<String> arraytypes = new ArrayList<String>();
		for (int i=0;i<pokemontype.get(pos).size();i++){
			arraytypes.add(type[pokemontype.get(pos).get(i)]);
		}
		return arraytypes;
	}
	
	public String getTypesolo(int pos){
		return (type[pos]);
	}
	
	
	public ArrayList<Integer> getTypeInt(int pos){
		ArrayList<Integer> arraytypes = new ArrayList<Integer>();
		for (int i=0;i<pokemontype.get(pos).size();i++){
			arraytypes.add(pokemontype.get(pos).get(i));
		}
		return arraytypes;
	}
}
