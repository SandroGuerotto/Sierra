package controller;
/**
 * Starter class für das Login GUI
 *
 * @author Sandro Guerotto
 * @create 13.12.2016
 * @package controller
 * @version 1.0
 */

import handler.LoginHandler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StarterGui {


    public void start(Stage stage, Object handler, String fxml) {
        // get screen props
        final double ypos = Screen.getPrimary().getVisualBounds().getMinY();
        final double xpos = Screen.getPrimary().getVisualBounds().getMinX();
        final double width = Screen.getPrimary().getVisualBounds().getWidth();
        final double height = Screen.getPrimary().getVisualBounds().getHeight();

        try {

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/" + fxml + ".fxml"));
            loader.setController(handler);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getResource("/css/application.css").toExternalForm());

            //set font style
            Font.loadFont(getClass().getResourceAsStream("/font/Abel-Regular.ttf"), 14);


            stage.setOnCloseRequest(t -> {
                Platform.exit();
                System.exit(0);
            });

//			stage.getIcons().add(new Image("@/../icons/logo/logo.png"));
            stage.setTitle("Sierra"); // Titel
            stage.setScene(scene);
            switch (fxml){
                case "Login":
                    stage.setResizable(false);
                    break;
                case "Application":
                    stage.setResizable(false);
                    stage.setX(xpos);
                    stage.setY(ypos);
                    stage.setWidth(width);
                    stage.setHeight(height);
                    stage.setMaximized(true);
                    stage.setResizable(true);
                    break;
            }

            stage.show();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

}
