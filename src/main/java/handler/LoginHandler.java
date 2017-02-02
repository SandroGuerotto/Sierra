package handler;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.Controller;
import exception.LoginException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * view controller for login.fxml
 * handles all events at the login screen
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 */
public class LoginHandler implements Initializable {

    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXPasswordField tf_password;

    @FXML
    private Button btn_login;

    @FXML
    private Label lbl_error;
    @FXML
    private AnchorPane pane_root;
    @FXML
    private Text lbl_welcome;

    @FXML
    private VBox pane_login;

    private Controller controller;
    private boolean loaded = false;

    public LoginHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane_login.setVisible(false);
        btn_login.setVisible(false);
        lbl_error.setVisible(false);
        if (!loaded) loadSplashScreen();
    }

    /**
     * calls view.login method for Controller checks previously if all inputs are
     * filled out
     */
    @FXML
    private void login() {
        if (tf_username.getText().isEmpty() || tf_password.getText().isEmpty()) {
            lbl_error.setText("Bitte alles ausfüllen!");
            lbl_error.setDisable(false);
            lbl_error.setVisible(true);
        } else {
            try {
                controller.login(tf_username.getText(), tf_password.getText());
            } catch (LoginException e) {
                lbl_error.setText(e.getMessage());
                lbl_error.setDisable(false);
                lbl_error.setVisible(true);
            }
        }

    }

    /**
     * automatically calls register or view.login depends on which option is selected
     *
     * @param ke Keyevent too listen on Enter
     */
    @FXML
    private void onEnter(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            login();
        } else {
            return;
        }
    }
    
    /**
     * initialize loading screen
     */
    private void loadSplashScreen() {
        loaded = true;

        Pane pane = controller.getNextPane("loading");
        pane_root.getChildren().addAll(pane);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });

        fadeOut.setOnFinished((e) -> {
            pane_login.setVisible(true);
            btn_login.setVisible(true);
            pane_root.getChildren().remove(pane);
        });
    }

}
