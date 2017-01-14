package handler;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherHandler implements Initializable {

    @FXML
    private FlowPane pane_teacher;


    private Controller controller;

    public TeacherHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane_teacher.getChildren().setAll(controller.getMyTeachers());
    }
}
