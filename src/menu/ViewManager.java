//package menu;
//
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BackgroundPosition;
//import javafx.scene.layout.BackgroundRepeat;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundImage;
//import javafx.stage.Stage;
//import javafx.scene.control.Button;
//import javafx.event.EventHandler;
//import javafx.event.Event;
//import model.SpaceButton;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.effect.DropShadow;
//import model.SpaceSubScene;
//import javafx.event.ActionEvent;
//import java.util.ArrayList;
//import java.util.List;
//import model.CreditSubScene;
//import main.Main;
//public class viewManager {
//	private static final int WIDTH = 1000;
//	private static final int HEIGHT = 750;
//	private static final String BG_PATH = ClassLoader.getSystemResource("map/map1-fulledged.png").toString();
//	private final static int BTN_X = 100;
//	private final static int BTN_Y = 150;
//	private boolean gameStart = false;
//	
//	
//	private SpaceButton scrBtn;
//	
//	private CreditSubScene credit;
//	private SpaceSubScene score;
//	private SpaceSubScene exit;
//	private SpaceSubScene play;
//
//	private SpaceSubScene hideScene;
//		
//	List<SpaceButton> menuBtn;
//	private AnchorPane mainPane;
//	private Scene mainScene;
//	private Stage mainStage;
//	public viewManager() {
//		menuBtn = new ArrayList<SpaceButton>();
//		mainPane = new AnchorPane();
//	//	mainScene = new Scene(mainPane,WIDTH,HEIGHT);
//		mainScene = new Scene(mainPane);
//		mainStage = new Stage();
//		mainStage.setScene(mainScene);
//		createSubScenes();
//		createBackground();
//		createButtons();
//		//createLogo();
//		/*SpaceSubScene sc = new SpaceSubScene();
//		sc.setLayoutX(200);
//		sc.setLayoutY(100);
//		mainPane.getChildren().add(sc);*/
//		
//	}
//	public Scene getmainScene() {
//		return mainScene;
//	}
//	
//	private void showSubScene(SpaceSubScene ss) {
//		if(hideScene!=null) {
//			hideScene.moveSubScene();
//		}
//		ss.moveSubScene();
//		hideScene = ss;
//	}
//	
//	private void createSubScenes() {
//		credit = new CreditSubScene();	
//		score =  new SpaceSubScene();
//		exit =  new SpaceSubScene();
//		play =  new SpaceSubScene();
//		mainPane.getChildren().addAll(credit,exit,score,play);
//		
//	}
//	
//	public Stage getMainStage() {
//		return mainStage;
//	}
//	private void addBtn(SpaceButton btn) {
//		btn.setLayoutX(BTN_X);
//		btn.setLayoutY(BTN_Y + menuBtn.size()*100);
//		menuBtn.add(btn);
//		mainPane.getChildren().add(btn);
//	}
//	private void createButtons() {
//		createStartBtn();
//		createScoreBtn();
//		createCreditBtn();
//		createExitBtn();
//	}
//	private void createStartBtn() {
//		SpaceButton startBtn = new SpaceButton("PLAY");
//		addBtn(startBtn);
//		startBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println("daaa");
//				gameStart = true;
//				mainPane.getChildren().remove(scrBtn);
//				Main.showGameStage();
//			}
//		});
//	}
//	private void createScoreBtn() {
//		scrBtn = new SpaceButton("SCORES");
//		addBtn(scrBtn);
//		scrBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				showSubScene(score);
//			}
//		});
//	}
//	private void createCreditBtn() {
//		SpaceButton creditBtn = new SpaceButton("ABOUT US");
//		addBtn(creditBtn);
//		creditBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				showSubScene(credit);
//			}
//		});
//	}
//	private void createExitBtn() {
//		SpaceButton exit = new SpaceButton("EXIT");
//		addBtn(exit);
//		exit.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				mainStage.close();
//			}
//		});
//	}
//	
//	private void createBackground() {
//		Image bgImage =new Image(BG_PATH); //Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
//		BackgroundImage background = new BackgroundImage(bgImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
//		mainPane.setBackground(new Background(background));
//	}
//	private void createLogo() {
//		ImageView logo = new ImageView(ClassLoader.getSystemResource("images/LOGO.jpg").toString());
//		logo.setLayoutX(400);
//		logo.setLayoutY(50);
//		logo.setOnMouseReleased(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				logo.setEffect(new DropShadow());
//			}
//			
//		});
//		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				logo.setEffect(null);
//			}
//		});
//		mainPane.getChildren().add(logo);
//	}
//	public boolean getgameStart() {
//		return gameStart;
//	}
//	public void setgameStart(boolean x) {
//		this.gameStart = x;
//	}
//	public void close() {
//		System.out.print("sddf");
//	}
//}


package menu;



import image.Images;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SpaceSubScene;
import model.LabelGen;
import model.SpaceButton;
import model.SliderBar;
import main.Main;
public class ViewManager {

	private Stage mainStage;
	private Scene mainScene;
	private AnchorPane uiRoot;
	private SpaceSubScene creditSubScene;
	private SpaceSubScene optionSubScene;
	private SpaceSubScene playSubScene; 
	private SpaceSubScene curShowSubScene;
	private SpaceSubScene dummySubScene;
	private final String BACKGROUND_PATH = ClassLoader.getSystemResource("map/map1-fulledged.png").toString();


	public ViewManager() {
		uiRoot = new AnchorPane();
		mainScene = new Scene(uiRoot, 1280, 720);
		mainStage = new Stage();
		mainStage.setTitle("Some game");
		mainStage.setScene(mainScene);
		createBackground();
		createAllButtons();
	
		ImageView logo = new ImageView(Images.logo);
		logo.setLayoutX(570);
		logo.setLayoutY(60);
		uiRoot.getChildren().add(logo);
	
		createSubScene();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public Scene getMainScene() {
		return mainScene;
	}

	private void createAllButtons() {
		createPlayButton();
	
		createOptionButton();
		createExitButton();
	}

	private void createPlayButton() {
		SpaceButton butt = new SpaceButton("PLAY");
		butt.setLayoutX(200);
		butt.setLayoutY(200);
		uiRoot.getChildren().add(butt);
		butt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Main.closeMenuStage();
				Main.showGameStage();
			}
			
		});
	}

	
	private void createOptionButton() {
		SpaceButton opButt = new SpaceButton("OPTION");
		opButt.setLayoutX(200);
		opButt.setLayoutY(320);
		uiRoot.getChildren().add(opButt);
		opButt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					if (curShowSubScene.equals(optionSubScene)) {
						optionSubScene.transitionOut();
						curShowSubScene = dummySubScene;
					} else {
						curShowSubScene.transitionOut();
						optionSubScene.transitionIn();
						curShowSubScene = optionSubScene;
					}
				}
				arg0.consume();
			}
			
		});
	}

	private void createExitButton() {
		SpaceButton butt = new SpaceButton("EXIT");
		butt.setLayoutX(200);
		butt.setLayoutY(440);
		uiRoot.getChildren().add(butt);
		butt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (arg0.getButton().equals(MouseButton.PRIMARY)) {
					Platform.exit();
				}
			}
			
		});
	}

	private void createBackground() {
		uiRoot.setStyle("-fx-background-image: url(" + BACKGROUND_PATH + "); " + "-fx-background-size: cover;");
	}


	private void createSubScene() {
		curShowSubScene = new SpaceSubScene();
		dummySubScene = new SpaceSubScene();
		createCreditSubScene();
		createOptionSubScene();
		createPlaySubScene();
	}

	private void createPlaySubScene() {
		playSubScene = new SpaceSubScene();
		String path = ClassLoader.getSystemResource("images/yellow_panel.png").toString();
		playSubScene.getPane().setStyle("-fx-background-color: transparent; "
				+ "-fx-background-image: url(" + path + "); "
				+ "-fx-background-size: cover; ");
		LabelGen head_Tutorial = new LabelGen("Tutorial", 36);
		playSubScene.getPane().getChildren().add(head_Tutorial);
		head_Tutorial.setAlignment(Pos.CENTER);
		head_Tutorial.setPrefWidth(playSubScene.getWidth());
		head_Tutorial.setLayoutY(30);
		
		uiRoot.getChildren().add(playSubScene);
	}
	
	private void createCreditSubScene() {
		creditSubScene = new SpaceSubScene();
	
		uiRoot.getChildren().add(creditSubScene);
	
		LabelGen credit = new LabelGen("credit", 36);
		credit.setAlignment(Pos.CENTER);
		credit.setPrefWidth(creditSubScene.getWidth());
		credit.setLayoutY(30);
		creditSubScene.getPane().getChildren().addAll(credit);
	}
	
	private void createOptionSubScene() {
		optionSubScene = new SpaceSubScene();
		uiRoot.getChildren().add(optionSubScene);
	
		
		
		LabelGen head_option = new LabelGen("Option", 36);
		head_option.setAlignment(Pos.CENTER);
		head_option.setPrefWidth(optionSubScene.getWidth());
		head_option.setLayoutY(30);
		SliderBar slidebar = new SliderBar();
		slidebar.setAlignment(Pos.CENTER);
		slidebar.setLayoutX(100);
	slidebar.setLayoutY(180);
		optionSubScene.getPane().getChildren().addAll(head_option, slidebar);
		
	}



}

