package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import javafx.stage.control.Button;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300.0D, 275.0D));
        primaryStage.show();

    }

    public Main(){
        // filler method
    }


    public static void main(String[] args) {
        launch(args);
    }
}
