package model;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.animation.TranslateTransition;
public class SpaceSubScene extends SubScene{
	private final static String BG_IMG =  ClassLoader.getSystemResource("images/yellow_panel.png").toString();
	private AnchorPane SubPane;
	private boolean show = false;
	public SpaceSubScene() {
		super(new AnchorPane(),600,400); //type,width,height
		prefWidth(600);
		prefHeight(400);
		BackgroundImage img = new BackgroundImage( new Image(BG_IMG,600,400,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null    );
		SubPane = (AnchorPane) this.getRoot();
		SubPane.setBackground(new Background(img));
	

		
		
		setLayoutX(1374);
		setLayoutY(180);
		
	}
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this); //which element should be moved
		transition.setToX(-676);
	}
	public AnchorPane getSubPane() {
		return SubPane;
	}
	public void transitionIn() {
		show = true;
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setToX(-800);
		t.setToY(0);
		t.setDuration(new Duration(300));
		t.play();
	}
	
	public void transitionOut() {
		show = false;
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setToX(875);
		t.setToY(0);
		t.setDuration(new Duration(300));
		t.play();
	}
	
	public boolean isShow() {
		return show;
	}
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
