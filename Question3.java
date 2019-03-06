import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.Math;
import java.util.Random;
import javafx.geometry.Point2D;

public class Question3 extends Application{

  //radius of the 3 circles
  private double pointRadius = 10;

  //creating 3 lines
  private Line line1 = new Line();
  private Line line2 = new Line();
  private Line line3 = new Line();

  //creating text shapes
  private Text[] text = {new Text(), new Text(), new Text()};

  public void start(Stage primaryStage){

    //creating pane
    Pane pane = new Pane();

    //radius of main circle
    double radius = 200;

    //creating scene
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Question 3");
    primaryStage.setScene(scene);
    primaryStage.show();

    //stage cannot be resized
    primaryStage.setResizable(false);

    //generating centre of circle coordinates
    double mainX = primaryStage.getWidth()/2;
    double mainY = primaryStage.getHeight()/2;

    //drawing main circle
    Circle mainCircle = new Circle(mainX, mainY, radius);
    mainCircle.setStroke(Color.BLACK);
    mainCircle.setFill(Color.WHITE);

    Random random = new Random();

    double angle[] = new double[3];

    //creating random angles
    for (int i = 0; i < 3; i++){
      angle[i] = random.nextDouble()*Math.PI*2;
    }

    //creating random coordinates for the three circle points
    double[] xCentre = {mainX + Math.cos(angle[0])*radius, mainX + Math.cos(angle[1])*radius, mainX + Math.cos(angle[2])*radius};
    double[] yCentre = {mainY + Math.sin(angle[0])*radius, mainY + Math.sin(angle[1])*radius, mainY + Math.sin(angle[2])*radius};
    Circle[] circle = {new Circle(xCentre[0], yCentre[0], pointRadius), new Circle(xCentre[1], yCentre[1], pointRadius),
      new Circle(xCentre[2], yCentre[2], pointRadius)};

    //colouring each small circle red
    for (int j = 0; j < 3; j++){
      circle[j].setFill(Color.RED);
    }

    //calling setLines for default positions
    setLines(circle);

    //When first circle is dragged, it updates it's position, the angles, and
    //the positions of the lines
    circle[0].setOnMouseDragged(e -> {
      Point2D newCentre = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
      Point2D mouse = new Point2D(e.getX(), e.getY());
      Point2D centerToMouse = mouse.subtract(newCentre);
      Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
      Point2D newPoint = centerToNewPoint.add(newCentre);
      circle[0].setCenterX(newPoint.getX());
      circle[0].setCenterY(newPoint.getY());
      setLines(circle);
   });

   //When second circle is dragged, it updates it's position, the angles, and
   //the positions of the lines
   circle[1].setOnMouseDragged(e -> {
     Point2D newCentre = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
     Point2D mouse = new Point2D(e.getX(), e.getY());
     Point2D centerToMouse = mouse.subtract(newCentre);
     Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
     Point2D newPoint = centerToNewPoint.add(newCentre);
     circle[1].setCenterX(newPoint.getX());
     circle[1].setCenterY(newPoint.getY());
     setLines(circle);
  });

  //When third circle is dragged, it updates its position, the angles, and
  //the positions of the lines
  circle[2].setOnMouseDragged(e -> {
    Point2D newCentre = new Point2D(mainCircle.getCenterX(), mainCircle.getCenterY());
    Point2D mouse = new Point2D(e.getX(), e.getY());
    Point2D centerToMouse = mouse.subtract(newCentre);
    Point2D centerToNewPoint = centerToMouse.normalize().multiply(mainCircle.getRadius());
    Point2D newPoint = centerToNewPoint.add(newCentre);
    circle[2].setCenterX(newPoint.getX());
    circle[2].setCenterY(newPoint.getY());
    setLines(circle);
 });

  //adding all shapes to pane
  pane.getChildren().addAll(mainCircle, line1, line2, line3, circle[0], circle[1],
      circle[2], text[0], text[1], text[2]);

  }

  //setLines method displays the connected lines to each circle and calculates new angle
  public void setLines(Circle[] circle){
    //connect line from first circle to second
    line1.setStartX(circle[0].getCenterX());
    line1.setStartY(circle[0].getCenterY());
    line1.setEndX(circle[1].getCenterX());
    line1.setEndY(circle[1].getCenterY());

    //connect line from second circle to third
    line2.setStartX(circle[1].getCenterX());
    line2.setStartY(circle[1].getCenterY());
    line2.setEndX(circle[2].getCenterX());
    line2.setEndY(circle[2].getCenterY());

    //connect line from third circle to first
    line3.setStartX(circle[2].getCenterX());
    line3.setStartY(circle[2].getCenterY());
    line3.setEndX(circle[0].getCenterX());
    line3.setEndY(circle[0].getCenterY());

    //calculate distance from one circle to the other
    double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[1].getCenterX(), circle[1].getCenterY());
    double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());
    double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());

    double[] newAngle = new double[3];

    //find new angle
    newAngle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
    newAngle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
    newAngle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

    //display new angle above circle
    for (int i = 0; i < 3; i++) {
      text[i].setX(circle[i].getCenterX() - pointRadius);
      text[i].setY(circle[i].getCenterY() - pointRadius);
      text[i].setText(String.format("%.2f", Math.toDegrees(newAngle[i])));
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
