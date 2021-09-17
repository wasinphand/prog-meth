package utility;

import javafx.scene.media.AudioClip;

public class Audio {
	
	public static void playExplosionSound() {
		String path = ClassLoader.getSystemResource("song/explosion.mp3").toString();
	    AudioClip ac = new AudioClip(path);
	    ac.play();
	}
	
	public static void playMoneySound() {

		String path = ClassLoader.getSystemResource("song/coin-collect.wav").toString();
	    AudioClip ac = new AudioClip(path);
	    ac.play();
	}

}
