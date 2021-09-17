package truck;

import java.util.ArrayList;

import bullet.Bullet;
import entity.Tile;
import entity.Unit;
import guard.Guard;
import image.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tank.Tank;
import trap.Trap;
import ui.scene.GameScene;
import utility.Audio;
import utility.Movable;

public class Truck extends Tile implements Movable{
	
	private int hp;
	private int row;
	private boolean isBombing;
	private double speed;
	private double CSPEED;
	

	public Truck(int row, Image image,int hp, double speed) {	// row Start with 0 (0, 1, 2, 3)
		super(1300,
			(row+1) * GameScene.TILE_SIZE + (GameScene.TILE_SIZE-image.getHeight())/2,
			image);				
		// TODO Auto-generated constructor stub
		this.hp = hp;
		this.row = row;
		this.speed = speed;
		this.CSPEED = speed;
		this.isBombing = false;
	}

	@Override
	public void move(GraphicsContext gc) {
		// TODO Auto-generated method stub
		setPositionX(getPositionX() - speed);
		putOnScreen(gc);
		checkGameOver();
	}
	
	public void getAttack(Bullet bullet) {
		hp -= bullet.getDamage();
		bullet.setDead();
	}
	
	public void getAttack(int damage) {
		hp -= damage;
	}
	
	public boolean isDead() {
		return hp <= 0 || getPositionX() < -5;
	}
	
	public int getRow() {
		return row;
	}
	
	public void bouncing() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					speed = -1;
					Thread.sleep(400);
					speed = CSPEED;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void bouncing(int sp, int duration) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					speed = sp;
					Thread.sleep(duration);
					speed = CSPEED;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void bomb(GraphicsContext gc, ArrayList<Truck> trucks) {
		GameScene.setScore(GameScene.getScore()+5);
		isBombing = true;
		Truck self = this;
		Audio.playExplosionSound();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i = 0; i < Images.bombAnimation.size(); i++) {
						setImage(Images.bombAnimation.get(i));
						Thread.sleep(100);
					}
					
					if(!trucks.remove(self)) trucks.remove(self);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public boolean isBombing() {
		return isBombing;
	}
	
	public void unitEffectsHandling(Unit unit) {
		if(unit instanceof Tank) {
			Tank t = (Tank)unit;
			Bullet b = ((Tank)unit).getBullet();
			if(!b.isDead() && b.getRow() == getRow()
					&& b.getPositionX()+15 > getPositionX()
					&& b.getPositionX()+26 < getPositionX()+80) {
				getAttack(b);
				t.makeEffect(this);
			}
			
			if(!t.isDead() && t.getRow() == getRow()
					&& t.getPositionX()-20 > getPositionX()
					&& t.getPositionX() < getPositionX()+getWidth()) {
				t.setDead(true);
			}
		}
		
		if(unit instanceof Guard) {
			Guard guard = (Guard)unit;
			if(speed < 0) return;
			if(!guard.isDead() && guard.getRow() == getRow()
					&& guard.getPositionX()+guard.getWidth() > getPositionX()
					&& guard.getPositionX() < getPositionX()) {
				bouncing();
				guard.guarding();
			}
		}
		
		if(unit instanceof Trap) {
			Trap trap = (Trap)unit;
			if(!trap.isDead() && trap.getRow() == getRow()
					&& trap.getPositionX()+trap.getWidth() > getPositionX()
					&& trap.getPositionX() < getPositionX()) {
				getAttack((GameScene.getScore()+1)*40);
				trap.setDead(true);
			}
		}
		
	}
	
	private void checkGameOver() {
		if(getPositionX() < 25) {
			GameScene.setGameOver(row);
		}
	}
	
	public void setSpeed(double speed, int duration) {
		Truck self = this;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					self.speed = speed;
					Thread.sleep(duration);
					self.speed = CSPEED;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void setSpeed(double factor) {
		this.speed = speed*factor;
	}
}
