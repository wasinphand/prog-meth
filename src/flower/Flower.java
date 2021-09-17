package flower;


import java.util.ArrayList;

import image.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utility.Audio;

public class Flower extends Rectangle{
	private int row;
	private Image flowerImageGray;
	private Image flowerImage;
	private double scale;
	private double upDateFactor;
	private long accumulatedSunPower;
	private long sunPowerLimit;
	private boolean isBombing;
	
	public Flower(int row, Image flowerImage, long sunPowerLimit) {
		super(0, (row+1)*128, 128, 128);
		this.row = row;						/* start with 0 */
		this.flowerImage = flowerImage;
		this.flowerImageGray = Images.grayFlowerImage;
		this.scale = 1 + (Math.random()-0.5)/10;
		this.upDateFactor = 0.025;
		this.sunPowerLimit = sunPowerLimit;
		this.isBombing = false;
		setFill(Color.color(0,0,0,0));
	}
	

	public int getRow() {
		return row;
	}

	public Image getFlowerImage() {
		if(accumulatedSunPower < sunPowerLimit) return flowerImageGray;
		return flowerImage;
	}
	
	public void updateScale() {
		accumulatedSunPower++;
		if(this.scale >= 1.1 || this.scale <= 0.9) 
			this.upDateFactor = -this.upDateFactor;
		this.scale += this.upDateFactor;
	}
	
	public double getScale() {
		return this.scale;
	}


	public long getSunPowerLimit() {
		return sunPowerLimit;
	}


	public void setSunPowerLimit(long sunPowerLimit) {
		this.sunPowerLimit = sunPowerLimit;
	}


	public long getAccumulatedSunPower() {
		return accumulatedSunPower;
	}


	public void setAccumulatedSunPower(long accumulatedSunPower) {
		this.accumulatedSunPower = accumulatedSunPower;
	}
	
	public void bomb(GraphicsContext gc, ArrayList<Flower> flowers) {
		isBombing = true;
		Audio.playExplosionSound();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i = 0; i < Images.bombAnimation.size(); i++) {
						setFlowerImage(Images.bombAnimation.get(i));
						setFlowerImageGray(Images.bombAnimation.get(i));
						Thread.sleep(100);
					}
				upDateFactor = 0;
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}


	public void setFlowerImageGray(Image flowerImageGray) {
		this.flowerImageGray = flowerImageGray;
	}


	public void setFlowerImage(Image flowerImage) {
		this.flowerImage = flowerImage;
	}
	
	public boolean isBombing() {
		return isBombing;
	}
	
}
