package handler;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.Menubanner;

/**
 * view controller for application.fxml
 * handles menu events and subscreens
 *
 * @author Sandro Guerotto
 * @since 17.12.2016
 * @version 0.1
 */
public class ApplicationHandler implements Initializable {
    @FXML
    private StackPane pane_main;
    @FXML
    private GridPane pane_container;
    @FXML
    private BorderPane spinner;

    private Controller controller;


    public ApplicationHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menubanner menubanner = new Menubanner(pane_container, pane_main, controller.getNotifications());
        initMenuListener(menubanner);

        pane_container.add(menubanner, 0, 0, 4, 1);

    }
    /**
     * initialize method to create menubanner
     * handles action events and subscreens (pane)
     * @param menubanner menubanner object
     */
    private void initMenuListener(Menubanner menubanner) {

        VBox wrapper = (VBox) menubanner.getChildren().get(0);
        FlowPane menu = (FlowPane) wrapper.getChildren().get(0);

        for (Node child : menu.getChildren()) {
            Button btn = (Button) child;
            if (!btn.getId().equals("notification") && !btn.getId().equals("settings")) {
                btn.setOnAction(event -> {
                    deletePane(); // delete current Pane and add new to parent Pane
                    pane_container.add(controller.getNextPane(btn.getId()), 1, 2, 2, 2);
                });
            }

        }
    }

    /**
     * delete current subscreen (pane)
     */
    private void deletePane() {
        for (Node node : pane_container.getChildren()) {
            try {
                if (node.getId().contains("pane_")) {
                    pane_container.getChildren().remove(node);
                    return;
                }
            } catch (Exception e) {
            }

        }
    }
    /**
     * called at program start to display home screen
     */
    public void setFirstDisplay() {
        Platform.runLater(() -> {
            spinner.setManaged(false);
            spinner.setDisable(true);
            spinner.setVisible(false);
            pane_container.add(controller.getNextPane("home"), 1, 2, 2, 2);
        });
    }
}
