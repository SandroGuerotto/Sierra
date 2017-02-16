package view;

import com.jfoenix.controls.JFXPopup;
import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Popup object to edit settings or log out view controller for
 * popupSettings.fxml
 * 
 * @author Sandro Guerotto
 * @since 01.01.2017
 * @version 0.1
 */
public class PopupSetting extends JFXPopup implements Initializable {

	@FXML
	private Label lbl_forename, lbl_name, lbl_email;

	private Controller controller;
	private BorderPane content;

	public PopupSetting(Controller controller) {
		this.controller = controller;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/popupSetting.fxml"));
		loader.setController(this);
		try {
			content = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setContent(content);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbl_forename.textProperty().bind(controller.getLoggedinUser().forenameProperty());
		lbl_name.textProperty().bind(controller.getLoggedinUser().nameProperty());
		lbl_email.textProperty().bind(controller.getLoggedinUser().emailProperty());
	}

	@FXML
	private void exit() {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	private void goToSetting() {
		System.out.println("settings");
	}
}
