package main;



import image.Images;
//import application.GameManager;
//import application.MediaManager;
//import application.UIManager;
//import application.GameManager;
//import application.MediaManager;
//import application.UIManager;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ui.scene.GameOverScene;
import ui.scene.GameScene;
import menu.ViewManager;
import javafx.scene.Scene;

public class Main extends Application {

	private static Stage primaryStage;	
	private static ViewManager menu;
	private static GameScene gameScene;
	private static Scene menuScene;
	private static Scene gameOverScene;
	private static double volume = 0.5;
	private static MediaPlayer mediaPlayer;

	public static void main(String[] args) {
		launch(args);
	}
	


	@Override
	public void start(Stage primaryStage) throws Exception {
		Images.loadResources();
		Main.primaryStage = primaryStage;
		menu = new ViewManager();
		primaryStage.setTitle("Tanks VS Trucks");

		String path = ClassLoader.getSystemResource("song/menu_theme.mp3").toString();
		Media media = new Media(path);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		Thread detectSound = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(50);
					updateSound();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		detectSound.start();
		showMenuStage();
	
		primaryStage.getIcons().add(Images.icon);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.show();

	}
	
	
	public static void showMenuStage() {
		playMusic("menu_theme");
        
		menuScene = menu.getMainScene();
		primaryStage.setScene(menuScene);
	
	}
	
	public static void showGameOverStage() {
		gameOverScene = new GameOverScene();
		primaryStage.setScene(gameOverScene);
	
	}
	
	public void updateSound() {
		mediaPlayer.setVolume(volume);
	}
	
	public static void showGameStage() {
		playMusic("glass_land");
        
		if(gameScene != null) gameScene.stop();
		GameScene.resetGame();
		gameScene = new GameScene();
		primaryStage.setScene(gameScene);
	}
	public static void closeMenuStage() {
	}
	
	private static void playMusic(String name) {
		mediaPlayer.stop();
		String path = ClassLoader.getSystemResource("song/" + name + ".mp3").toString();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(5000);
        mediaPlayer.play();
	}
	
	public static void setVolume(double volume) {
		Main.volume = volume;
	}
	
	public static double getVolume() {
		return volume;
	}
}