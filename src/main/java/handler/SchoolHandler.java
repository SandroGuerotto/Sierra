package handler;

import com.jfoenix.effects.JFXDepthManager;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class SchoolHandler implements Initializable {

    private Controller controller;

    @FXML
    private ImageView iv_2, iv_1;

    public SchoolHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDepthManager.setDepth(iv_1, 1);
        JFXDepthManager.setDepth(iv_2, 1);
    }
}
