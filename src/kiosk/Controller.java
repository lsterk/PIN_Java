package kiosk;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.util.Random;

import static java.awt.SystemColor.text;

/**
 *
 * @author ljs34
 */
public class Controller implements Initializable {

    private String pin_answer;  //stores the user's PIN
    private String pin_entered;
    private final int MAX_PIN = 10000;
    @FXML
    private Label text_output;
    @FXML
    private Label instructions;
    private Random rand;

    //initialize button ids
    @FXML private Button btn0;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn6;
    @FXML private Button btn7;
    @FXML private Button btn8;
    @FXML private Button btn9;

    @FXML
    private void handlePinPress(ActionEvent event) {
        Button src = (Button) event.getSource();
        pin_entered += src.getText();
        text_output.setText(pin_entered);
//        System.out.println("Button " + src.getText() + " was clicked");
    }

    @FXML
    private void clearPIN(ActionEvent event) {
        System.out.println("Clearing the entered PIN");
        pin_entered = "";
        text_output.setText("");
        instructions.setText("Click \"New PIN\"");

    }

    @FXML
    private void generatePIN(ActionEvent event) {
        System.out.println("Randomly generating a new PIN");
        pin_answer = Integer.toString(rand.nextInt(MAX_PIN));
        while (pin_answer.length() < 4){
            pin_answer = "0" + pin_answer;
        }
        instructions.setText("Enter PIN: " + pin_answer);
        System.out.println("New PIN: " + pin_answer);
        pin_entered = "";           //Clears entered pin to prevent confusion
        text_output.setText("");
    }

    @FXML
    private void checkPIN(ActionEvent event) {
        System.out.format("Checking the entered PIN (%s) against the master (%s)\n", pin_entered, pin_answer);
        if (pin_answer.equals(pin_entered)){
            // PIN is good
            text_output.setText("PIN accepted!");
            instructions.setText("Click \"New PIN\"");
        }
        else {
            // PIN rejeceted
            text_output.setText("Incorrect PIN");
            //instructions.setText("Try again, or generate a new PIN");
        }
        pin_entered = "";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Initializing the system");
        rand = new Random();
        pin_answer = "invalid";
        pin_entered = "";
        text_output.setText("Click \"New PIN\" to begin!");
        Button[] pin_btn = {btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};

        //handle pin button style changes
        for(int i=0; i<10; i++){
            pin_btn[i].setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
                public void handle(javafx.scene.input.MouseEvent me) {
                    //System.out.println("Mouse Pressed");
                    Button src = (Button) me.getSource();
                    src.setTextFill(Color.rgb(255,255,255));
                    src.setStyle("-fx-background-color: #97252b; -fx-border-color: #97252b; -fx-border-width: 4px;");
                }
            });

            pin_btn[i].setOnMouseReleased(new EventHandler<javafx.scene.input.MouseEvent> () {
                public void handle(javafx.scene.input.MouseEvent me) {
                    //System.out.println("Mouse Released");
                    Button src = (Button) me.getSource();
                    src.setTextFill(Color.rgb(151,37,43));
                    src.setStyle("-fx-background-color: #ffffff; -fx-border-color: #97252b; -fx-border-width: 4px;");
                }
            });
        }



    }

}