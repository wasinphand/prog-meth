package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelGen extends Label{

	public LabelGen(String text, int size) {
		super(text);
		setWrapText(true);
		setAlignment(Pos.TOP_CENTER);
		setFont(new Font("Joystix Monospace", size));
	}	
	
}
