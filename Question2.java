import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.Math;

public class Question2 extends Application{
  @Override
  public void start(Stage primaryStage){
    //Creating GridPane
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.TOP_LEFT);
    pane.setPadding(new Insets(5, 5, 5, 5));
    pane.setHgap(10);
    pane.setVgap(5);

    //Creating Investment Amount Label
    Label invAmntLabel = new Label("Investment Amount");
    pane.add(invAmntLabel, 0, 0);

    //Creating Investment Amount TextField
    TextField invAmntField = new TextField();
    invAmntField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(invAmntField, 1, 0);

    //Creating Years Label
    Label yearsLabel = new Label("Years");
    pane.add(yearsLabel, 0, 1);

    //Creating Years TextField
    TextField yearsField = new TextField();
    yearsField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(yearsField, 1, 1);

    //Creating Annual Interest Rate Label
    Label AIRLabel = new Label("Annual Interest Rate");
    pane.add(AIRLabel, 0, 2);

    //Creating Annual Interest Rate TextField
    TextField AIRField = new TextField();
    AIRField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(AIRField, 1, 2);

    //Creating Future Value Label
    Label futValLabel = new Label("Future Value");
    pane.add(futValLabel, 0, 3);

    //Creating Future Value TextField
    //Future Value TextField will display the calculated amount
    TextField futValField = new TextField();
    futValField.setDisable(true);
    futValField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(futValField, 1, 3);

    //creating calculate button
    Button calculateButton = new Button("Calculate");
    pane.add(calculateButton, 1, 4);
    GridPane.setHalignment(calculateButton, HPos.RIGHT);

    //Creating Scene
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Question 2");
    primaryStage.setScene(scene);
    primaryStage.show();

    //calculateButton Action
    //When Calculate button is pressed, the Future Value is then calculated
    //and displayed in its designated TextField.
    calculateButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {

      //converting TextField to double
      double invAmount = Double.parseDouble(invAmntField.getText());
      double annualIR = Double.parseDouble(AIRField.getText())/100;
      double monthlyIR = annualIR/12;
      double years = Double.parseDouble(yearsField.getText());

      //Formula - investmentAmount * (1 + monthlyInvestmentRate)^(years * 12)
      double futVal = invAmount * Math.pow(1 + monthlyIR, years*12);
      futVal = Math.round(futVal*100.0)/100.0;

      //converting calculation to String and setting text
      String futValString = String.valueOf(futVal);
      futValField.setText(futValString);
    }
  });

  }

  public static void main(String[] args) {
    launch(args);
  }
}
