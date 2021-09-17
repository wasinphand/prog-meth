package guard;


import entity.Unit;
import image.Images;

public class Guard extends Unit{
	
	private static final int PRICE = 20;
	
	private int durability;

	public Guard(double posRecX, double posRecY) {
		super(posRecX, posRecY,
				Images.brokenTankImage, PRICE);
		this.durability = 10;
	}
	
	public void guarding() {
		durability -= 1;
		if(durability == 0) setDead(true);
	}
	
	public int getDurability() {
		return durability;
	}
	
	

}
