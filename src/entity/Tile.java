package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
	private double width;
	private double height;
	private double positionX;
	private double positionY;
	
	private Image image;
	
	public Tile(double positionX, double positionY, Image image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.positionX = positionX;
		this.positionY = positionY;
		this.image = image;
	}
	
	public void putOnScreen(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY, width, height);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public int getRow() {
		return (int) (positionY / 128) - 1;
	}
	
	public int getCol() {
		return (int) (positionX / 128) - 1;
	}
	
	
}
 