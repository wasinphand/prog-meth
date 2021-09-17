package tank;

import image.Images;

public class BrokenTank extends Tank{

	private static final int PRICE = 20;
	private static final int ATK = 0;

	public BrokenTank(double posRecX, double posRecY) {
		super(PRICE, Images.beigeTankImage, posRecX, posRecY, ATK, Images.barricadeMetal);
		// TODO Auto-generated constructor stub
	}

	
}
