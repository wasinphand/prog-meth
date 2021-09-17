package ui.scene;


import exceptions.ScoreBeyondTierListException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.Main;
import model.SpaceButton;

public class GameOverScene extends Scene{
	private String[] tierList;
	
	public GameOverScene() {
		super(new StackPane());
		tierList = new String[] {"Beginner", "Not Bad", "Excellent", 
								"Superb", "Insane", "God-Like", "Greatest of All"};
		initializeScene();

	}

	private void initializeScene() {
		StackPane pane = new StackPane();
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		Label gameOverLabel = new Label("Game Over");
		Label scoreLabel = new Label("Your score is: " 
									+ GameScene.getScore() );
		Label tier;
		
		try {
			if(GameScene.getScore() > 1000) throw new ScoreBeyondTierListException();
			tier = new Label(tierList[(int)(GameScene.getScore()*7/1000)] + "!!!\n\n\n\n");
		} catch (ScoreBeyondTierListException e) {
			tier = new Label(tierList[6] + "!!!\n\n\n\n");
			System.out.println("Score is more than 1000, beyond 6 tiers in list.");
		}
		
		
		String FONT = ClassLoader.getSystemResource("fonts/joystix_monospace.ttf").toString();
		Font theFont = Font.loadFont(FONT, 24);
		gameOverLabel.setFont(theFont);
		scoreLabel.setFont(theFont);
		tier.setFont(theFont);
		
		SpaceButton titleButton = new SpaceButton("Back to Title Screen");
		titleButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Main.showMenuStage();
			}
		});
		
		FadeTransition ft = new FadeTransition(Duration.millis(3000), vBox);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		
		vBox.getChildren().add(gameOverLabel);
		vBox.getChildren().add(scoreLabel);
		vBox.getChildren().add(tier);
		vBox.getChildren().add(titleButton);
		
		pane.getChildren().add(vBox);
		pane.setPrefHeight(720);
		pane.setPrefWidth(1280);

		setRoot(pane);
	}
	

}
