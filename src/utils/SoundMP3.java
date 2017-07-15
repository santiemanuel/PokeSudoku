package utils;

import java.net.URL;

import jaco.mp3.player.MP3Player;

public class SoundMP3 extends Thread {
	
	MP3Player player;
	URL filename;
	
	public SoundMP3(URL name){
		filename = name;
	}
	
	public void run(){
		try
		{
			player = new MP3Player(filename);
			player.play();
		}catch(Exception e)
		{ System.err.println(e);}
	}
	
	public void setMP3(URL name){
		filename = name;
	}
	
	public MP3Player getPlayer(){
		return player;
	}
}
