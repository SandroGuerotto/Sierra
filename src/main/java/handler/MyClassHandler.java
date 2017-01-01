package handler;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


public class MyClassHandler implements Initializable {

    private Controller controller;

    @FXML
    private GridPane pane_myClass; // root pane

    @FXML
    private Label lbl_class;

    @FXML
    private Label lbl_studentcount;

    @FXML
    private FlowPane pane_class;


    public MyClassHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_class.setText(controller.getClassname());
        lbl_studentcount.setText(Integer.toString(controller.getStudentcount()));
        pane_class.getChildren().setAll(controller.getMemebers());

    }

}
