import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

public class Question4 extends Application{

  BorderPane pane = new BorderPane();
  TextField text = new TextField("C:/Users/");


  @Override
  public void start(Stage primaryStage){
    primaryStage.setTitle("Question 4");

    pane.setPadding(new Insets(5, 5, 5, 5));
    //creating panel BorderPane
    //this is for the label, TextField, and view button
    BorderPane panel = new BorderPane();
    panel.setPadding(new Insets(5,5,5,5));

    //setting label to left
    panel.setLeft(new Label("File: "));

    //setting text alignment to left and positioned in centre
    text.setAlignment(Pos.BOTTOM_LEFT);
    panel.setCenter(text);

    //creating "view" button and positioning it to the right
    Button viewButton = new Button("View");
    panel.setRight(viewButton);

    //setting panel to bottom of pane
    pane.setBottom(panel);

    //creating scene
    Scene scene = new Scene(pane, 800, 400);
    primaryStage.setScene(scene);
    primaryStage.show();

    //When view button is pressed, it calls the countChar() method which reads from a File
    //and count the occurrences of each letter. Then, the drawHistorgram() method
    //is called and displays the data as a histogram
    viewButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e){
        try{
          int[] count = countChar();
          drawHistorgram(count);
        }
        catch(Exception f){
          f.printStackTrace();
        }
      }
    });
  }

  public int[] countChar() throws Exception{

    // Create a File instance
    File file = new File(text.getText());

    // Create a Scanner for the file
    Scanner input = new Scanner(file);

    //creating array to store information
    int[] letterCount = new int[26];

    //loop to read words from file
    while(input.hasNext()){
      String a = input.next();

      //converting each word to lower case and converting to character array
      a = a.toLowerCase();
      char[] arr = a.toCharArray();

      //counting the number of occurrences
      for(char c :arr){
            letterCount[c - 'a']++;
      }
    }

    // Close the file
    input.close();

    return letterCount;
  }

  //method to draw histogram
  public void drawHistorgram(int[] count){
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> histogram = new BarChart<String,Number>(xAxis,yAxis);

    //hide y axis, gridlines, and legend
    histogram.getYAxis().setTickLabelsVisible(false);
    histogram.getYAxis().setOpacity(0);
    histogram.setHorizontalGridLinesVisible(false);
    histogram.setVerticalGridLinesVisible(false);
    histogram.setLegendVisible(false);

    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    XYChart.Series series = new XYChart.Series();

    //adding data to histogram
    for (int i = 0; i < 26; i++){
      series.getData().add(new XYChart.Data(Character.toString(alph.charAt(i)), count[i]));
    }

    histogram.getData().add(series);
    pane.setCenter(histogram);

    //changing bar colours to darkgray
    histogram.lookupAll(".default-color0.chart-bar").forEach(n -> n.setStyle("-fx-bar-fill: darkgray;"));
  }

  public static void main(String[] args){
    launch(args);
  }
}
