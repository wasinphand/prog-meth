package tank;

import image.Images;
import truck.Truck; 

public class GreenTank extends Tank{

	private static final int PRICE = 25;
	private static final int ATK = 15;

	public GreenTank(double posRecX, double posRecY) {
		super(PRICE, Images.greenTankImage, posRecX, posRecY, ATK, Images.shotOrange);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeEffect(Truck truck) {
		truck.bouncing(-1, 200);
	}
}
