package data;


import helper.DateFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Notification extends BorderPane implements Initializable {


    private Appointment appointment;

    public Notification(Appointment appointment){
        this.appointment = appointment;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/itemNotification.fxml"));
        loader.setController(this);
        try {
            this.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Label lbl_datetime;

    @FXML
    private Label lbl_subject;

    @FXML
    private TextArea ta_descr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_datetime.setText(DateFormatter.LocalDateTimeToString(appointment.getStartLocalDateTime()));
        lbl_subject.setText(appointment.getSubject().getName());
        ta_descr.setText(appointment.getSummary());
    }
}
