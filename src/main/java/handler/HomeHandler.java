package handler;

import com.jfoenix.controls.*;
import controller.Controller;
import data.Gesuch;
import data.Request;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import view.ItemEvent;
import view.ScheduleView;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeHandler implements Initializable {


    @FXML
    private JFXButton btn_addTask, btn_addNews, btnSaveJoker, btnSaveGesuch;

    @FXML
    private GridPane pane_home, pane_container;

    @FXML
    private JFXListView<ItemEvent> lv_tasks;

    @FXML
    private JFXListView<?> lv_news;

    @FXML
    private BorderPane pane_schedule;

    @FXML
    private JFXToggleButton btn_schedule;

    @FXML
    private TableView<Request> table_joker;

    @FXML
    private TableColumn<Request, String> col_jokerDate, col_jokerReason, col_jokerStatus;

    @FXML
    private JFXDatePicker date_Joker, date_gesuch;

    @FXML
    private JFXTextField tf_reasonJoker, tf_reasonGesuch;

    @FXML
    private TableView<Gesuch> table_gesuche;

    @FXML
    private TableColumn<Gesuch, String> col_gesuchDate, col_gesuchReason, col_gesuchStatus;

    @FXML
    private JFXTextArea tf_textGesuch;

    @FXML
    private StackPane pane_main;

    private Controller controller;


    public HomeHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButton();
        initCol();


        lv_tasks.setItems(controller.getTasks());

        pane_schedule.setCenter(new ScheduleView().getPanel());

        table_joker.setItems(controller.getRequests());
        table_gesuche.setItems(controller.getGesuche());

    }


    /**
     * cellfactory of tableview.
     * create a custom cell for download column
     */
    private void initCol() {

        col_jokerDate.setCellValueFactory(new PropertyValueFactory<Request, String>("date"));
        col_jokerReason.setCellValueFactory(new PropertyValueFactory<Request, String>("reason"));
        col_jokerStatus.setCellValueFactory(new PropertyValueFactory<Request, String>("status"));

        col_gesuchDate.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("date"));
        col_gesuchReason.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("reason"));
        col_gesuchStatus.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("status"));


    }

    private void initButton() {
        // setup menu icon
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.BELL);
        icon.setSize("2.5em");
        icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        btn_addTask.setGraphic(icon);
        btn_addTask.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        btn_addNews.setGraphic(icon);
        btn_addNews.setText(null);
    }



    @FXML
    private void saveJoker(){
        if (!tf_reasonJoker.getText().isEmpty() && date_Joker.getValue() != null){
            controller.addJoker(date_Joker.getValue(), tf_reasonJoker.getText());
            date_Joker.setValue(null);
            tf_reasonJoker.setText(null);
        }
    }

    @FXML
    private void saveGesuch(){
        if (!tf_reasonGesuch.getText().isEmpty() && !tf_textGesuch.getText().isEmpty() && date_gesuch.getValue() != null){
            controller.addGesuch(date_gesuch.getValue(), tf_reasonGesuch.getText(), tf_textGesuch.getText());
            date_gesuch.setValue(null);
            tf_reasonGesuch.setText(null);
            tf_textGesuch.setText(null);
        }
    }

}
