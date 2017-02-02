package controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * setups screen with corresponding FXML
 *
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 */
public class StarterGui {

	/**
	 * creates scene and set settings to display current stage
	 * @param stage current stage
	 * @param root current pane to display
	 * @param fullscreen bool to set fullscreen mode
	 */
    public void start(Stage stage, Pane pane, boolean fullscreen) {
        // get screen props
        final double ypos = Screen.getPrimary().getVisualBounds().getMinY();
        final double xpos = Screen.getPrimary().getVisualBounds().getMinX();
        final double width = Screen.getPrimary().getVisualBounds().getWidth();
        final double height = Screen.getPrimary().getVisualBounds().getHeight();

        try {

            Scene scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

            //set font style
            Font.loadFont(getClass().getResourceAsStream("/font/Abel-Regular.ttf"), 14);


            stage.setOnCloseRequest(t -> {
                Platform.exit();
                System.exit(0);
            });

			stage.getIcons().add(new Image("/icon/logo.png"));
            stage.setTitle("Sierra"); // Titel
            stage.setScene(scene);
            if (!fullscreen) {
                stage.setResizable(false);
            }else{
                    stage.setResizable(false);
                    stage.setX(xpos);
                    stage.setY(ypos);
                    stage.setWidth(width);
                    stage.setHeight(height);
                    stage.setMaximized(true);
                    stage.setResizable(true);
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
