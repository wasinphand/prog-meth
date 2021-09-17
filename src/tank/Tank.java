package tank;

import bullet.Bullet;
import entity.Unit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import truck.Truck;

public class Tank extends Unit{
	private int atk;
	private double shakeFactor;
	private Bullet bullet;
	
	public Tank(int price, Image image, double posRecX, 
				double posRecY, int atk, Image bulletImage) {
		super(posRecX, posRecY, image, price);
		this.atk = atk;
		this.shakeFactor = -(Math.random()/2+0.5);
		this.bullet = new Bullet( posRecX, posRecY, bulletImage, atk );
	}

	public int getAtk() {
		return atk;
	}
	
	public void shake() {
		shakeFactor = -shakeFactor;
		setPositionY(getPositionY() + shakeFactor);
	}
	
	private void shoot() {
		bullet.reborn();
	}
	
	public void handleShooting(GraphicsContext gc) {
		if(!bullet.isDead()) {
			bullet.move(gc);
		} else {
			shoot();
		}
	}

	public Bullet getBullet() {
		return bullet;
	}
	
	public void makeEffect(Truck truck) {
		truck.getAttack(3);
	}
	
}
