package handler;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.Controller;
import data.Mark;
import helper.MyMarksPDF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyMarksHandler implements Initializable {

    @FXML
    private GridPane pane_school;

    @FXML
    private JFXButton btn_printPDF;

    @FXML
    private TableView<Mark> table_marks;

    @FXML
    private TableColumn<Mark, String> col_date;

    @FXML
    private TableColumn<Mark, String> col_theme;

    @FXML
    private TableColumn<Mark, String> col_subject;

    @FXML
    private TableColumn<Mark, String> col_semester;

    @FXML
    private TableColumn<Mark, String> col_mark;

    @FXML
    private TableColumn<Mark, String> col_avgClass;

    @FXML
    private JFXListView<?> lv_nextTests;


    private Controller controller;

    public MyMarksHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        table_marks.setItems(controller.getMarks());
    }

    /**
     * cellfactory of tableview.
     * create a custom cell for download column
     */
    private void initCol() {

        col_date.setCellValueFactory(new PropertyValueFactory<Mark, String>("date"));
        col_theme.setCellValueFactory(new PropertyValueFactory<Mark, String>("theme"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Mark, String>("subject"));
        col_semester.setCellValueFactory(new PropertyValueFactory<Mark, String>("semester"));
        col_mark.setCellValueFactory(new PropertyValueFactory<Mark, String>("mark"));
        col_avgClass.setCellValueFactory(new PropertyValueFactory<Mark, String>("avgClass"));


    }

    @FXML
    private void printPDF() {
        MyMarksPDF document = new MyMarksPDF(table_marks.getItems());
        document.create();
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\" + document.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
