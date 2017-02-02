package handler;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * view controller for teachers.fxml
 *
 * @author Sandro Guerotto
 * @since 01.01.2017
 * @version 0.1
 */
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
