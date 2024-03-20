import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
  private TextField value = new TextField();
  private TextField unit1 = new TextField();
  private TextField unit2 = new TextField();
  private TextField value2 = new TextField();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a pane and set its properties
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER); // Set center alignment
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5); // Set vGap to 5.5px
    
    // Place nodes in the pane
    Text text1 = new Text(20, 20, "Welcome to the metric converter!\nSupported units: cm, m, in, ft");
    text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));   
    pane.add(text1, 0, 0);
    
    pane.add(new Label("Enter value:"), 0, 1);
    pane.add(value, 1, 1);
    pane.add(new Label("Enter units:"), 0, 2); 
    pane.add(unit1, 1, 2);
    pane.add(new Label("Enter units to convert to:"), 0, 3);
    pane.add(unit2, 1, 3);
    Button btAdd = new Button("Convert");
    pane.add(btAdd, 1, 4);
    GridPane.setHalignment(btAdd, HPos.RIGHT);
    pane.add(new Label("Converted value:"), 0, 5);
    pane.add(value2, 1, 5);

    btAdd.setOnAction(e -> calculate());
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Metric Converter"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public void calculate(){
    //Gets values from input
    double valueC = Double.parseDouble(value.getText());
    String unit1C = unit1.getText();
    String unit2C = unit2.getText();
    double value2C = 0;
    int err = 0;

    //Converts value
    //(Code from assignment 3)
    switch(unit1C){
        case "cm":
            switch(unit2C){
                case "m": 
                    value2C = valueC/100;  
                    break;
                case "in": 
                    value2C = valueC/2.54;
                    break;
                case "ft": 
                    value2C = valueC/30.48;
                    break;
                default:
                    err = 1;
                    value2.setText(unit2C+" is not a vaild unit.");
                    break;
            }
            break;

        case "m":
            switch(unit2C){
                case "cm": 
                    value2C = valueC*100;
                    break;
                case "in": 
                    value2C = valueC*39.37;
                    break;
                case "ft": 
                    value2C = valueC*3.2808399;
                    break;
                default:
                    err = 1;
                    value2.setText(unit2C+" is not a vaild unit.");
                    break;
            }
            break;

        case "in":
            switch(unit2C){
                case "m": 
                    value2C = valueC/39.37;
                    break;
                case "cm": 
                    value2C = valueC*2.54;
                    break;
                case "ft": 
                    value2C = valueC/12;
                    break;
                default:
                    err = 1;
                    value2.setText(unit2C+" is not a vaild unit.");
                    break;
            }
            break;

        case "ft":
            switch(unit2C){
                case "m": 
                    value2C = valueC/3.2808399;
                    break;
                case "in": 
                    value2C = valueC*12;
                    break;
                case "cm": 
                    value2C = valueC*30.48;
                    break;
                default:
                    err = 1;
                    value2.setText(unit2C+" is not a vaild unit.");
                    break;
            }
            break;

        default:
            value2.setText(unit1C+" is not a vaild unit.");
            break;
    }
    //Displays converted value
    if(err == 0){
        value2.setText(String.valueOf(value2C)+" "+unit2C);
    }    
  }

  public static void main(String[] args) {
    launch(args);
  }
} 