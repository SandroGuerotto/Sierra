package handler;

import com.jfoenix.controls.*;
import controller.Controller;
import data.Request;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import view.ItemEvent;
import view.Menubanner;
import view.ScheduleView;

import java.net.URL;
import java.util.ResourceBundle;


public class ApplicationHandler implements Initializable {

    @FXML
    private FlowPane pane_menu;

    //    @FXML
//    private JFXButton btn_home,  btn_notification, btn_mymarks, btn_myclass;
//
//    @FXML
//    private JFXButton btn_teacher, btn_class, btn_school, btn_settings;
    @FXML
    private JFXButton btn_addTask, btn_addNews, btnSaveJoker;

    @FXML
    private GridPane pane_home, pane_main;

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
    private JFXDatePicker date_Joker;

    @FXML
    private JFXTextField tf_reasonJoker;


    private Controller controller;


    public ApplicationHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButton();
        initCell();

        pane_main.add(new Menubanner(pane_main), 0, 0, 4, 1);

        lv_tasks.setItems(controller.getTasks());

        pane_schedule.setCenter(new ScheduleView().getPanel());

        table_joker.setItems(controller.getRequest());

    }


    /**
     * cellfactory of tableview.
     * create a custom cell for download column
     */
    private void initCell() {

        col_jokerDate.setCellValueFactory(new PropertyValueFactory<Request, String>("date"));
        col_jokerReason.setCellValueFactory(new PropertyValueFactory<Request, String>("reason"));
        col_jokerStatus.setCellValueFactory(new PropertyValueFactory<Request, String>("status"));


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
}
