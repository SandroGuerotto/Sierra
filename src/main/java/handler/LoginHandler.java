package handler;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.Controller;
import exception.LoginException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginHandler implements Initializable{

    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXPasswordField tf_password;

    @FXML
    private Button btn_login;

    @FXML
    private Label lbl_error;

    @FXML
    private VBox pane_login;

    private Controller controller;

    public LoginHandler(Controller controller){
        this.controller = controller;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> pane_login.requestFocus());
        lbl_error.setVisible(false);
    }

    /**
     * calls login method for Controller checks previously if all inputs are
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
     * automatically calls register or login depends on which option is selected
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
}
