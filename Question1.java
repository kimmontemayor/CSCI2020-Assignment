import javafx.application.Application;
import java.util.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Question1 extends Application {

  @Override
  public void start(Stage primaryStage) {
    
    //Creating an ArrayList to store index/names of cards
    ArrayList<String> cards = new ArrayList<>();

    //Initializes cards with integers as a string
    for(int i = 1; i <= 52; i++){
      cards.add(Integer.toString(i));
    }

    //Randomize ArrayList
    Collections.shuffle(cards);

    //Creating a pane
    Pane pane = new HBox(10);
    pane.setPadding(new Insets(5, 5, 5, 5));

    //Create 3 images and add them to pane
    Image image1 = new Image("file:///C:/Users/kimru/Downloads/Assignment/Cards/" + cards.get(0) + ".png");
    pane.getChildren().add(new ImageView(image1));

    Image image2 = new Image("file:///C:/Users/kimru/Downloads/Assignment/Cards/" + cards.get(1) + ".png");
    pane.getChildren().add(new ImageView(image2));

    Image image3 = new Image("file:///C:/Users/kimru/Downloads/Assignment/Cards/" + cards.get(2) + ".png");
    pane.getChildren().add(new ImageView(image3));

    //Create Scene
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Question 1");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
