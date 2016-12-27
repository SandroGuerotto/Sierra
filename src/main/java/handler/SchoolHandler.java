package handler;

import controller.Controller;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sandro on 27.12.2016.
 */
public class SchoolHandler implements Initializable {

    private Controller controller;

    public SchoolHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
