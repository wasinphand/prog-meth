package ui.shape;

import javafx.scene.shape.Rectangle;

public class MarkerRectangle extends Rectangle{
	private boolean isMarked;
	
	public MarkerRectangle(double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		isMarked = false;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	
}
