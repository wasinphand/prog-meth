package ui.button;


import entity.UnitName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UnitButton extends Button{
	private Image unitImage;
	private int unitPrice;
	private Color currentColor;
	private UnitName unitName;
	private int atk;
	private String description;
	
	public UnitButton(Image unitImage, int unitPrice, UnitName unitName,
						int atk, String description) {
		this.unitImage = unitImage;
		this.unitPrice = unitPrice;
		this.unitName = unitName;
		this.atk = atk;
		this.description = description;
		
		System.out.println(unitImage.getUrl());

		// specific to t
		setGraphic(new ImageView(this.unitImage));
		setText("$" + this.unitPrice);
		setFont(Font.loadFont(ClassLoader.getSystemResource("font/kenvector_future.ttf").toString(), 15));
		setContentDisplay(ContentDisplay.TOP);
		setAlignment(Pos.CENTER);
		
		setPrefWidth(125);
		setPrefHeight(80);
		setPadding(Insets.EMPTY);
		setUnPressed();
	
		setOnMouseEntered(e -> setBackground(new Background(new BackgroundFill(Color.web("9dd8c8"), null, null))));
		setOnMouseExited(e -> setBackground(new Background(new BackgroundFill(currentColor, null, null))));
		
	}
	
	public void setPressed() {
		currentColor = Color.web("36b5b0");
		setBackground(new Background(new BackgroundFill(currentColor, null, null)));
	}
	
	public void setUnPressed() {
		currentColor = Color.web("fcf5b0");
		setBackground(new Background(new BackgroundFill(currentColor , null, null)));
	}

	public Image getUnitImage() {
		return unitImage;
	}
	
	public String getInfo() {
		return unitName.name() +
				"\nATK: " + atk + 
				"\n\n" + description;
	}

	public UnitName getUnitName() {
		return unitName;
	}
	
	
	
	
}
