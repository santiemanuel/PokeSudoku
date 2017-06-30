package utils;

import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaySound.
 */
public class PlaySound {
	
	/** The sound path. */
	private URL sound;
	
	/** The clip. */
	private AudioClip clip;
	
	/**
	 * Instantiates a new play sound.
	 *
	 * @param filename the sound filename
	 */
	public PlaySound(String filename){
		this.sound = getClass().getResource("/"+filename+".wav");
		this.clip = Applet.newAudioClip(sound);
	}
	
	/**
	 * Play the sound.
	 */
	public void play(){
		this.clip.play();
	}

}
