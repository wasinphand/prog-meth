package trap;

import entity.Unit;
import image.Images;

public class Trap extends Unit{
	
	private static final int PRICE = 25;

	public Trap(double posRecX, double posRecY) {
		super(posRecX, posRecY, 
				Images.barricadeMetal, PRICE);
		// TODO Auto-generated constructor stub
	}

}
