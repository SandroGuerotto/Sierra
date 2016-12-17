package handler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.Controller;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import view.ItemEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class ApplicationHandler implements Initializable {

    @FXML
    private FlowPane pane_menu;

    @FXML
    private JFXButton btn_home,  btn_notification, btn_mymarks, btn_myclass;

    @FXML
    private JFXButton btn_teacher, btn_class, btn_school, btn_settings, btn_addTask, btn_addNews;

    @FXML
    private GridPane pane_home;

    @FXML
    private JFXListView<ItemEvent> lv_tasks;

    @FXML
    private JFXListView<?> lv_news;


    private Controller controller;


    public ApplicationHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // setup menu icon
        MaterialDesignIconView icon =  new MaterialDesignIconView(MaterialDesignIcon.BELL);
        icon.setSize("2.5em");
        btn_notification.setGraphic(icon);
        btn_notification.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        icon.setSize("2.5em");
        btn_settings.setGraphic(icon);
        btn_settings.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        btn_addTask.setGraphic(icon);
        btn_addTask.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        btn_addNews.setGraphic(icon);
        btn_addNews.setText(null);

        lv_tasks.setItems(controller.getTasks());

    }
}
