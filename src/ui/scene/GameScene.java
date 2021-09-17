package ui.scene;

import java.util.ArrayList;


import entity.Unit;
import entity.UnitName;
import exceptions.MoneyNotEnoughException;
import flower.Flower;
import guard.Guard;
import image.Images;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.Main;
import tank.BlueTank;
import tank.GreenTank;
import tank.RedTank;
import tank.SandTank;
import tank.Tank;
import trap.Trap;
import truck.DarkTruck;
import truck.RedTruck;
import truck.Truck;
import ui.button.PauseResumeButton;
import ui.button.UnitButton;
import ui.shape.MarkerRectangle;
import utility.Audio;
import utility.GameState;

public class GameScene extends Scene {

	public static final int TILE_SIZE = 128;
	
	private ArrayList<UnitButton> unitButtons = new ArrayList<UnitButton>();
	private ArrayList<Unit> unitOnScreen = new ArrayList<Unit>();
	private ArrayList<Truck> trucks = new ArrayList<Truck>();
	private UnitName selectedUnit;
	private static AnimationTimer animationTimer;
	private static Timeline timeLine;
	private static ArrayList<Flower> flowers = new ArrayList<Flower>();
	private static ArrayList<MarkerRectangle> tiles = new ArrayList<MarkerRectangle>();
	private static boolean[] gameOverLog;
	private static boolean gameOver;
	private static boolean gameEnd;
	private static int money = 100;
	private static int score = 0;

	private GameState gameState = GameState.DEFAULT;

	public GameScene() {
		super(new StackPane());
		// TODO Auto-generated constructor stub
		gameOverLog = new boolean[] {false, false, false, false};
		initialize();

	}
	
	public static void resetGame() {
		flowers = new ArrayList<Flower>();
		tiles = new ArrayList<MarkerRectangle>();
		gameOverLog = new boolean[] {false, false, false, false};
		gameOver = false;
		gameEnd = false;
		money = 100;
		score = 0;
	}

	private void initialize() {
		HBox hbox = new HBox(); 				/* The entire screen */
		StackPane stackPane = new StackPane(); 	/* Right Zone */
		stackPane.setAlignment(Pos.TOP_LEFT);
		VBox vbox = new VBox(); 				/* Menu bar */
		vbox.setSpacing(1);
		vbox.setPrefWidth(130);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(4, 0, 0, 0));

		hbox.setBackground(new Background(new BackgroundFill(Color.web("#E7D4A9"), null, null)));

		// adding stuffs to Menu bar
		Label infoLabel = makeInfoLabel(); /* Info label */
		addUnitButton();
		addFlower();
		setOnUnitButtonHandler(infoLabel, vbox);
		PauseResumeButton pButton = new PauseResumeButton();
		
		vbox.getChildren().add(infoLabel);
		vbox.getChildren().add(pButton);
		hbox.getChildren().add(vbox);

		// creating canvas and GraphicContext
		Canvas cv = new Canvas(1150, 720);
		stackPane.getChildren().add(cv);
		GraphicsContext gc = cv.getGraphicsContext2D();
		Font theFont = 
				Font.loadFont(ClassLoader.getSystemResource("font/kenvector_future.ttf").toString(), 18);
		gc.setFont(theFont);
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		// adding animation
		timeLine = new Timeline(new KeyFrame(Duration.millis(100), (evt) -> {

			for (Flower f : flowers) {
				f.updateScale(); /* Flowers scale up and down every */
			}

			for (Unit unit : unitOnScreen) {
				if (unit instanceof Tank) {
					((Tank) unit).shake();
				}
			}
			

		}));
		timeLine.setCycleCount(Animation.INDEFINITE);
		timeLine.play();

		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long ctime) {
				handleGameOver(gc);
				resetStateProperties(gc);
				gc.drawImage(Images.map1Image, 0, 0);
				
				for (Flower f : flowers) {
					gc.drawImage(f.getFlowerImage(), (TILE_SIZE - f.getFlowerImage().getWidth() * f.getScale()) / 2,
							(f.getRow() + 1) * TILE_SIZE
									+ (TILE_SIZE - f.getFlowerImage().getHeight() * f.getScale()) / 2,
							f.getFlowerImage().getWidth() * f.getScale(),
							f.getFlowerImage().getHeight() * f.getScale());
				}
				
				gc.fillText("SCORE: " + score, 800, 65);

				setStateProperties(gc);
				gc.fillText("MONEY: " + money, 950, 65);

				unitHandle(gc);
				truckHandle(gc);
				
				for (Unit unit : unitOnScreen) {
					if(unit instanceof Tank) ((Tank)unit).handleShooting(gc);
				}
				
				
			}
		};
		animationTimer.start();

		// Tile Map
		Pane pane = new Pane();
		pane.setPrefHeight(720);
		pane.setPrefWidth(1150);
		tilesHandlerSetup(pane);
		flowerButtonSetup(pane);
		stackPane.getChildren().add(pane);

		hbox.getChildren().add(stackPane);
		setRoot(hbox);
	}
	
	private void unitHandle(GraphicsContext gc) {
		for (Unit unit : unitOnScreen) {
			try {
				if(unit.isDead()) {
					if(!unit.isBombing()) {
						unit.bomb(gc, unitOnScreen);
						tiles.get(unit.getRow()*8 + unit.getCol()).setMarked(false);
					} else {
						unit.putOnScreen(gc);
					}
					continue;
				}
				unit.putOnScreen(gc);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	private void truckHandle(GraphicsContext gc) {
		if(trucks.size()-1 < score/20 && !gameOver) {
			int rowToPut = (int)Math.round(Math.random()*3);
			while(gameOverLog[rowToPut]) {
				rowToPut = (rowToPut+1) % 4;
			}
			trucks.add(Math.random() > 0.5?
					new RedTruck(rowToPut, (score+5)*10)
					:new DarkTruck(rowToPut, (score+5)*10));
		}
		for(int i = 0; i < trucks.size(); i++) {
			try {
				Truck truck = trucks.get(i);
				if(truck.isDead()) {
					if(!truck.isBombing()) {
						truck.bomb(gc, trucks);
					} else {
						truck.putOnScreen(gc);
					}
					continue;
				}
				
				truck.move(gc);
				for(Unit unit: unitOnScreen) {
					truck.unitEffectsHandling(unit);
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	private void flowerButtonSetup(Pane pane) {
		// TODO Auto-generated method stub
		for(Flower f: flowers) {
			f.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					if(PauseResumeButton.isPause) return;
					if(f.getAccumulatedSunPower() >= f.getSunPowerLimit()) {
						money += 10;
						f.setAccumulatedSunPower(0);
						setTemporaryState(GameState.GAIN_MONEY);
						Audio.playMoneySound();
					}
					
				}
			});
			pane.getChildren().add(f);
		}
	}
	

	private void resetStateProperties(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
	}

	private void setStateProperties(GraphicsContext gc) {
		switch (gameState) {
		case LOSE_MONEY:
			gc.setFill(Color.ORANGE);
			break;
		case MONEY_NOT_ENOUGH:
			gc.setFill(Color.RED);
			break;
		case GAIN_MONEY:
			gc.setFill(Color.LIGHTGREEN);
		default:
			break;
		}
	}

	private void setOnUnitButtonHandler(Label infoLabel, VBox vbox) {
		for (UnitButton unitButton : unitButtons) {
			unitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					setOne(unitButton);
					unitButton.setPressed();
					infoLabel.setText(unitButton.getInfo());
					selectedUnit = unitButton.getUnitName();
				}

			});
			vbox.getChildren().add(unitButton);
		}
	}

	private Label makeInfoLabel() {
		Label infoLabel = new Label("");
		infoLabel.setPadding(new Insets(5, 5, 5, 5));
		infoLabel.setWrapText(true);
		return infoLabel;
	}

	private void addFlower() {
		// Set flower
		Flower f0 = new Flower(0, Images.redFlowerImage, 125);
		Flower f1 = new Flower(1, Images.blueFlowerImage, 100);
		Flower f2 = new Flower(2, Images.purpleFlowerImage, 150);
		Flower f3 = new Flower(3, Images.sandFlowerImage, 170);

		flowers.add(f0);
		flowers.add(f1);
		flowers.add(f2);
		flowers.add(f3);

	}

	private void addUnitButton() {
		// set menu bar(left panel)
		UnitButton redTankButton = new UnitButton(Images.redTankImageButton, 15, UnitName.RED_TANK, 25,
				"No special skill, yet the most powerful tank.");
		UnitButton blueTankButton = new UnitButton(Images.blueTankImageButton, 20, UnitName.BLUE_TANK, 15,
				"Freeze enemy for an amount of time. [0.2s]");
		UnitButton greenTankButton = new UnitButton(Images.greenTankImageButton, 25, UnitName.GREEN_TANK, 20,
				"Bounce enemy back every bullet hit.");
		UnitButton beigeTankButton = new UnitButton(Images.beigeTankImageButton, 30, UnitName.SAND_TANK, 15,
				"Permanent-exponentialy slow enemy down.");
		UnitButton brokenTankButton = new UnitButton(Images.brokenTankImageButton, 20, UnitName.BROKEN_TANK, 0,
				"A broken tank that can endure the hitting of truck 10 times.");
		UnitButton barricadeMetal = new UnitButton(Images.barricadeMetalButton, 25, UnitName.BARRICADE_METAL, 1000,
				"A Powerful trap with relative damage.");
		
		unitButtons.add(redTankButton);
		unitButtons.add(blueTankButton);
		unitButtons.add(greenTankButton);
		unitButtons.add(beigeTankButton);
		unitButtons.add(brokenTankButton);
		unitButtons.add(barricadeMetal);

	}

	private void tilesHandlerSetup(Pane pane) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				MarkerRectangle rec = new MarkerRectangle((j + 1) * TILE_SIZE, (i + 1) * TILE_SIZE, TILE_SIZE,
						TILE_SIZE);
				rec.setFill(Color.rgb(200, 200, 200, 0));
				rec.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						rec.setFill(Color.rgb(200, 200, 200, 0.5));
					}
				});

				rec.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						rec.setFill(Color.rgb(200, 200, 200, 0));
					}
				});

				rec.setOnMousePressed(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						rec.setFill(Color.rgb(200, 200, 200, 0.3));
						if (selectedUnit == null || PauseResumeButton.isPause)
							return;
						if (rec.isMarked())
							return;
						switch (selectedUnit) {
						case RED_TANK:
							purchase(new RedTank(rec.getX(), rec.getY()), rec);
							break;
						case BLUE_TANK:
							purchase(new BlueTank(rec.getX(), rec.getY()), rec);
							break;
						case GREEN_TANK:
							purchase(new GreenTank(rec.getX(), rec.getY()), rec);
							break;
						case SAND_TANK:
							purchase(new SandTank(rec.getX(), rec.getY()), rec);
							break;
						case BROKEN_TANK:
							purchase(new Guard(rec.getX(), rec.getY()), rec);
							break;
						case BARRICADE_METAL:
							purchase(new Trap(rec.getX(), rec.getY()), rec);
							break;
						}
					}
				});

				rec.setOnMouseReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						rec.setFill(Color.rgb(200, 200, 200, 0.5));
					}
				});
				tiles.add(rec);
				pane.getChildren().add(rec);
			}
		}
	}

	private void setOne(UnitButton clickedButton) {
		for (UnitButton unitButton : unitButtons) {
			unitButton.setUnPressed();
		}
	}

	private boolean purchase(Unit unit, MarkerRectangle rec) {
		try {
			if (money >= unit.getPrice()) {
				money -= unit.getPrice();
				unitOnScreen.add(unit);
				rec.setMarked(true);
	
				setTemporaryState(GameState.LOSE_MONEY);
				return true;
			} else {
				throw new MoneyNotEnoughException();
			}
		} catch (MoneyNotEnoughException er) {
			System.out.println(er.getMessage(unit.getPrice(), GameScene.money));
			setTemporaryState(GameState.MONEY_NOT_ENOUGH);
			return false;
		}
	}

	private void setTemporaryState(GameState gs) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				gameState = gs;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameState = GameState.DEFAULT;
			}
		});

		t.start();
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		GameScene.money = money;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameScene.score = score;
	}
	
	public static void setGameOver(int row) {
		gameOverLog[row] = true;
		gameOver = true;
		for(int i = 0; i < gameOverLog.length; i++) {
			if(!gameOverLog[i]) gameOver = false;
		}
		if(gameOver && !gameEnd) {
			Main.showGameOverStage();
			gameEnd = true;
		}
	}

	private void handleGameOver(GraphicsContext gc) {
		for(int i = 0; i < gameOverLog.length; i++) {
			if(gameOverLog[i] && !GameScene.flowers.get(i).isBombing()) {
				GameScene.flowers.get(i).bomb(gc, flowers);
				for(Unit u: unitOnScreen) {
					if(u.getRow() == i) u.bomb(gc, unitOnScreen);
				}
				for(Truck t: trucks) {
					if(t.getRow() == i) t.bomb(gc, trucks);
				}
//				for(int j = 8*i; j < (i+1)*8; i++) {
//					GameScene.tiles.get(j).setMarked(true);
//				}
			}
		}
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public static void stop() {
		animationTimer.stop();
		timeLine.stop();
	}
	
	public static void resume() {
		animationTimer.start();
		timeLine.playFromStart();
	}
}
