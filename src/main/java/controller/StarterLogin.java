package controller;
/**
 * Starter class für das Login GUI
 * @author     Sandro Guerotto
 * @create     13.12.2016
 * @package    controller
 * @version    1.0
 */

import handler.LoginHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StarterLogin {
	

	public void start(Stage stage, LoginHandler handler) {
		try {

			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Login.fxml"));
			loader.setController(handler);
			Parent root = loader.load();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("/css/application.css").toExternalForm());

			//set font style
			Font.loadFont(getClass().getResourceAsStream("/font/Abel-Regular.ttf"), 14);
			
			
//			stage.setOnCloseRequest(t -> controller.kill());
			
//			stage.getIcons().add(new Image("@/../icons/logo/logo.png"));
			stage.setTitle("Sierra"); // Titel
			stage.setScene(scene);

			stage.setResizable(false);
			stage.show();
		} catch (Exception var5) {
			var5.printStackTrace();
		}

	}

}
