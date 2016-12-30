package handler;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.Menubanner;

import java.net.URL;
import java.util.ResourceBundle;


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
        Menubanner menubanner = new Menubanner(pane_container, pane_main, null);
        try {
            initMenuListener(menubanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pane_container.add(menubanner, 0, 0, 4, 1);
    }

    private void initMenuListener(Menubanner menubanner) throws Exception {

        VBox wrapper = (VBox) menubanner.getChildren().get(0);
        FlowPane menu = (FlowPane) wrapper.getChildren().get(0);

        for (Node child : menu.getChildren()) {
            Button btn = (Button) child;
            if (!btn.getId().equals("notification") && !btn.getId().equals("settings")) {
                btn.setOnAction(event -> {
                    deletePane(); // delete current Pane and add to parent Pane
                    pane_container.add(controller.getNextPane(btn.getId()), 1, 2, 2, 2);

                });
            }

        }
    }


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

    public void setFirstDisplay() {
        Platform.runLater(() -> {
            spinner.setManaged(false);
            spinner.setDisable(true);
            spinner.setVisible(false);
            pane_container.add(controller.getNextPane("home"), 1, 2, 2, 2);
        });
    }
}
