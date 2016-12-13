package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Sandro on 13.12.2016.
 */
public class Sierra extends Application {

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Controller Controller = new Controller();
        Controller.start(primaryStage);
    }
}
