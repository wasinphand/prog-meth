package tank;

import image.Images;
import truck.Truck;

public class BlueTank extends Tank{

	private static final int PRICE = 20;
	private static final int ATK = 15;

	public BlueTank(double posRecX, double posRecY) {
		super(PRICE, Images.blueTankImage, posRecX, posRecY, ATK, Images.shotThin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeEffect(Truck truck) {
		truck.setSpeed(0.1, 200);
	}

}
