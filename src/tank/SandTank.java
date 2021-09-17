package tank;

import image.Images;
import truck.Truck;

public class SandTank extends Tank{

	private static final int PRICE = 30;
	private static final int ATK = 15;

	public SandTank(double posRecX, double posRecY) {
		super(PRICE, Images.beigeTankImage, posRecX, posRecY, ATK, Images.shotLarge);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void makeEffect(Truck truck) {
		truck.setSpeed(0.95);
	}

}
