package truck;

import image.Images;

public class RedTruck extends Truck{

	public RedTruck(int row, int hp) {
		super(row, Images.redTruck, hp, Math.random()*2+1);
		// TODO Auto-generated constructor stub
	}

}
