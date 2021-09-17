package ui.button;


import javafx.scene.control.Button;

import javafx.scene.input.MouseButton;
import ui.scene.GameScene;

public class PauseResumeButton extends Button {
	
	private final double BUTTON_WIDTH = 50;
	public static boolean isPause = false;		
	private final String NORM_PAUSE_PATH = ClassLoader.getSystemResource("ui/button/pause_norm.png").toString();
	private final String PRESSED_PAUSE_PATH = ClassLoader.getSystemResource("ui/button/pause_pressed.png").toString();
	private final String NORM_PLAY_PATH = ClassLoader.getSystemResource("ui/button/play_norm.png").toString();
	private final String PRESSED_PLAY_PATH = ClassLoader.getSystemResource("ui/button/play_pressed.png").toString();
	private final String PAUSE_NORM_STYLE = "-fx-background-color: transparent; " + "-fx-background-image: url(" + NORM_PAUSE_PATH + "); " + "-fx-background-size: cover; ";
	private final String PAUSE_ONPRESS_STYLE = "-fx-background-color: transparent; " + "-fx-background-image: url(" + PRESSED_PAUSE_PATH + "); " + "-fx-background-size: cover; ";
	private final String PLAY_NORM_STYLE = "-fx-background-color: transparent; " + "-fx-background-image: url(" + NORM_PLAY_PATH + "); " 	+ "-fx-background-size: cover; ";
	private final String PLAY_ONPRESS_STYLE = "-fx-background-color: transparent; " + "-fx-background-image: url(" + PRESSED_PLAY_PATH + "); " + "-fx-background-size: cover; ";
		
	public PauseResumeButton() {
		setButtonSize();
		setNormStyle();
		setActionFromMouse();
	}
	
	private void setButtonSize() {
		setPrefWidth(BUTTON_WIDTH);
	}
	

	
	private void setNormStyle() {
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 100);
		if (isPause) {
			setStyle(PLAY_NORM_STYLE);
		} else {
			setStyle(PAUSE_NORM_STYLE);
		}
	}
	
	private void setPressedStyle() {
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 100);
		if (isPause) {
			setStyle(PLAY_ONPRESS_STYLE);
		} else {
			setStyle(PAUSE_ONPRESS_STYLE);
		}
	}
	
	private void setActionFromMouse() {
		
	
		
		setOnMouseExited(e -> {
				setEffect(null);
				e.consume();
		});
		
		setOnMousePressed(e -> {
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					setPressedStyle();
				}
				e.consume();
		});
		
		setOnMouseReleased(e -> {
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					if(isPause) GameScene.resume();
					else GameScene.stop();
					isPause = (isPause) ? false : true;
					setNormStyle();
				}
				e.consume();
		});
		
	}

}