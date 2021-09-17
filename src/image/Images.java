package image;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Images {
	public static Image buttonNormal;
	public static Image buttonPressed;
	
	public static Image redTankImage;
	public static Image blueTankImage;
	public static Image greenTankImage;
	public static Image beigeTankImage;
	public static Image brokenTankImage;
	public static Image barricadeMetal;
	
	public static Image redTankImageButton;
	public static Image blueTankImageButton;
	public static Image greenTankImageButton;
	public static Image beigeTankImageButton;
	public static Image brokenTankImageButton;
	public static Image barricadeMetalButton;
	
	public static Image grayFlowerImage;
	public static Image redFlowerImage;
	public static Image blueFlowerImage;
	public static Image sandFlowerImage;
	public static Image purpleFlowerImage;
	
	public static Image shotLarge;
	public static Image shotOrange;
	public static Image shotRed;
	public static Image shotThin;
	
	public static Image redTruck;
	public static Image darkTruck;
	
	public static Image icon;
	public static Image logo;
	
	public static ArrayList<Image> bombAnimation;
	
	public static Image map1Image;
	
	public static void loadResources() {
		buttonNormal = loadImage("images/normal_btn.png", 250, 80);
		buttonPressed = loadImage("images/pressed_btn.png", 250, 80);
		
		redTankImage = loadImage("tank/tank_red.png", 71, 58);
		blueTankImage = loadImage("tank/tank_blue.png", 71, 58);
		greenTankImage = loadImage("tank/tank_green.png", 71, 58);
		beigeTankImage = loadImage("tank/tank_sand.png", 71, 58);
		brokenTankImage = loadImage("tank/tankBody_dark.png", 71, 58);
		barricadeMetal = loadImage("barricade/barricadeMetal.png", 60, 58);
		
		redTankImageButton = loadImage("tank/tank_red.png", 46, 38);
		blueTankImageButton = loadImage("tank/tank_blue.png", 46, 38);
		greenTankImageButton = loadImage("tank/tank_green.png", 46, 38);
		beigeTankImageButton = loadImage("tank/tank_sand.png", 46, 38);
		brokenTankImageButton = loadImage("tank/tankBody_dark.png", 46, 38);
		barricadeMetalButton = loadImage("barricade/barricadeMetal.png", 46, 38);
		
		grayFlowerImage = loadImage("flower/GrayFlower.png", 42, 50);
		redFlowerImage = loadImage("flower/foliagePack_062.png", 42, 50);
		blueFlowerImage = loadImage("flower/foliagePack_001.png", 42, 50);
		sandFlowerImage = loadImage("flower/foliagePack_000.png", 42, 50);
		purpleFlowerImage = loadImage("flower/foliagePack_100.png", 42, 50);
		
		shotLarge = loadImage("bullet/shotLarge.png", 26, 16);
		shotOrange = loadImage("bullet/shotOrange.png", 26, 16);
		shotRed = loadImage("bullet/shotRed.png", 26, 16);
		shotThin = loadImage("bullet/shotThin.png", 26, 16);
		
		redTruck = loadImage("truck/truckRed.png", 80, 65);
		darkTruck = loadImage("truck/truckDark.png", 80, 65);
		
		icon = loadImage("icons/gameicon.png", 512, 512);
		logo = loadImage("icons/logo-no-background.png", 600, 600);
		
		bombAnimation = new ArrayList<Image>();
		for(int i = 1; i < 7; i++) {
			bombAnimation.add(loadImage("bomb/explosion" + i + ".png", 60, 60));
		}
		
		map1Image = loadImage("map/map1-fulledged.png", 1150, 720);
	}
	
	public static Image loadImage(String path, double width, double height){
        return new Image(ClassLoader.getSystemResource(path).toString(), width, height, false, false);
    }
	
	public static Image loadImage(String path){
        return new Image(ClassLoader.getSystemResource(path).toString());
    }
}
