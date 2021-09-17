package bullet;

import entity.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utility.Movable;

public class Bullet extends Tile implements Movable {

	private boolean isDead;

	private final double birthPositionX;
	private final double birthPositionY;
	private final int DAMAGE;
	private final int row;

	public static final int SPEED = 10;
	public final static int TILE_SIZE = 128;

	public Bullet(double posRecX, double posRecY, Image image, int damage) {
		super(posRecX + (TILE_SIZE - image.getWidth()) / 2 + 30, posRecY + (TILE_SIZE - image.getHeight()) / 2, image);
		birthPositionX = posRecX + (TILE_SIZE - image.getWidth()) / 2 + 25;
		birthPositionY = posRecY + (TILE_SIZE - image.getHeight()) / 2;
		isDead = false;
		row = (int) (posRecY / 128) - 1;
		DAMAGE = damage;
	}

	@Override
	public void move(GraphicsContext gc) {
		// TODO Auto-generated method stub
		setPositionX(getPositionX() + SPEED);
		putOnScreen(gc);
		if (getPositionX() > 1280)
			setDead();
	}

	public boolean isDead() {
		return isDead;
	}

	public void reborn() {
		setPositionX(birthPositionX);
		setPositionY(birthPositionY);
		isDead = false;
	}

	public int getDamage() {
		return DAMAGE;
	}

	public void setDead() {
		isDead = true;
	}

	public int getRow() {
		return row;
	}


}
