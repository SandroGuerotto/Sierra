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
import view.ItemEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * view controller for myMaraks.fxml
 * handles all events at the my marks screen
 *
 * @author Sandro Guerotto
 * @since 30.12.2016
 * @version 0.1
 */
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
    private JFXListView<ItemEvent> lv_nextTests;


    private Controller controller;

    public MyMarksHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        table_marks.setItems(controller.getMarks());
        lv_nextTests.setItems(controller.getUpcomingTests());
    }

    /**
     * cellfactory of tableview.
     */
    private void initCol() {

        col_date.setCellValueFactory(new PropertyValueFactory<Mark, String>("date"));
        col_theme.setCellValueFactory(new PropertyValueFactory<Mark, String>("theme"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Mark, String>("subject"));
        col_semester.setCellValueFactory(new PropertyValueFactory<Mark, String>("semester"));
        col_mark.setCellValueFactory(new PropertyValueFactory<Mark, String>("mark"));
        col_avgClass.setCellValueFactory(new PropertyValueFactory<Mark, String>("avgClass"));
        
        // Set fixed column widths that resize automatically
        // Values are weighted to be a fraction of a total of 41 (arbitrary)
        col_date.prefWidthProperty().bind(table_marks.widthProperty().multiply(4.0 / 41.0));
        col_theme.prefWidthProperty().bind(table_marks.widthProperty().multiply(16.8 / 41.0));
        col_subject.prefWidthProperty().bind(table_marks.widthProperty().multiply(6.0 / 41.0));
        col_semester.prefWidthProperty().bind(table_marks.widthProperty().multiply(5.0 / 41.0));
        col_mark.prefWidthProperty().bind(table_marks.widthProperty().multiply(4.0 / 41.0));
        col_avgClass.prefWidthProperty().bind(table_marks.widthProperty().multiply(5.0 / 41.0));
        
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
