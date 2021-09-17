package model;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import image.Images;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.input.MouseButton;
import javafx.scene.effect.DropShadow;

public class SpaceButton extends Button{
	private final String FONT = ClassLoader.getSystemResource("fonts/joystix_monospace.ttf").toString();
	public SpaceButton(String text) {
		Font theFont = Font.loadFont(FONT, 24);
		setFont(theFont);
		setText(text);
		
		setPrefWidth(250);
		setPrefHeight(80);
		setButtonReleasedStyle();
		initializeButtonListeners();
	}

	private void setButtonPressedStyle() { // examine
		BackgroundImage backgroundImage = new BackgroundImage(Images.buttonPressed,null, null,null,null);
        Background background = new Background(backgroundImage);
        setBackground(background);
		setLayoutY(getLayoutY()+4);
	}
	private void setButtonReleasedStyle() {
		System.out.println(Images.buttonNormal.toString());
		BackgroundImage backgroundImage = new BackgroundImage(Images.buttonNormal,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        setBackground(background);
		setLayoutY(getLayoutY()-4);
	}
	private void initializeButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
					
				}
			}
			
		});
		
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
					
				}
			}
			
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
