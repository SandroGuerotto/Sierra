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


public class PopupSetting extends JFXPopup implements Initializable {

    @FXML
    private Label lbl_name_forename, lbl_email;

    private Controller controller;
    private BorderPane content;

    public PopupSetting(){
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
        lbl_name_forename.setText("Maximori" + " " + "Dunois" );
        lbl_email.setText("maximori.dunois@gmail.com");

    }

    @FXML
    private void exit(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void goToSetting(){
        System.out.println("settings");
    }
}
