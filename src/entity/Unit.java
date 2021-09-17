package entity;

import java.util.ArrayList;

import image.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Audio;

public class Unit extends Tile{
	
	private int price;
	private boolean isBombing;
	private boolean isDead;
	
	private final static int TILE_SIZE = 128;

	public Unit(double posRecX, double posRecY,
			Image image, int price) {
		
		super(posRecX + (TILE_SIZE - image.getWidth()) / 2,
				posRecY + (TILE_SIZE - image.getHeight()) / 2, image);

		this.price = price;
		this.isBombing = false;
		this.isDead = false;
	}

	public int getPrice() {
		return this.price;
	}
	
	public void bomb(GraphicsContext gc, ArrayList<Unit> units) {
		Audio.playExplosionSound();
		isBombing = true;
		Unit self = this;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i = 0; i < Images.bombAnimation.size(); i++) {
						setImage(Images.bombAnimation.get(i));
						Thread.sleep(100);
					}
					
					units.remove(self);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public boolean isBombing() {
		return isBombing;
	}
	
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public boolean isDead() {
		return isDead;
	}
}
