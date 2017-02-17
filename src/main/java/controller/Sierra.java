package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Start class for Sierra Application
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 *
 */
public class Sierra extends Application {

	/**
	 * main method to start program
	 * @param args start arguments
	 */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller Controller = new Controller();
        Controller.start(primaryStage);
    }
}
