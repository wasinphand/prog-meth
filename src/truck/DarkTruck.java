package truck;

import image.Images;

public class DarkTruck extends Truck{

	public DarkTruck(int row, int hp) {
		super(row, Images.darkTruck, (int)(hp/2), Math.random()+2);
		// TODO Auto-generated constructor stub
	}

}