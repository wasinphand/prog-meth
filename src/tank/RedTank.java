package tank;

import image.Images;

public class RedTank extends Tank{
	
	private static final int PRICE = 15;
	private static final int ATK = 20;

	public RedTank(double posRecX, double posRecY) {
		super(PRICE, Images.redTankImage, posRecX, posRecY, ATK, Images.shotRed);
		// TODO Auto-generated constructor stub
	}



}
