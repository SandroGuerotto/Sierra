package handler;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MyMarksHandler implements Initializable {

    @FXML
    private GridPane pane_school;

    @FXML
    private JFXButton btn_printPDF;

    @FXML
    private TableView<?> table_marks;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_theme;

    @FXML
    private TableColumn<?, ?> col_subject;

    @FXML
    private TableColumn<?, ?> col_semester;

    @FXML
    private TableColumn<?, ?> col_mark;

    @FXML
    private TableColumn<?, ?> col_avgClass;

    @FXML
    private JFXListView<?> lv_nextTests;


    private Controller controller;

    public MyMarksHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void printPDF(){

    }
}
