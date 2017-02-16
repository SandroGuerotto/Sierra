package data;

import helper.DateFormatter;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ListView Item for Popup list Notification
 * extend BorderPane and implements Initializable
 * Uses a fxml to define layout
 * @author Sandro Guerotto
 * @since 26.12.2016
 * @version 0.1
 */
public class Notification extends BorderPane implements Initializable {


    private Appointment appointment;

    /**
     * loads List item
     * @param appointment data source
     */
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
        lbl_datetime.textProperty().bind(Bindings.createStringBinding(() ->
        DateFormatter.LocalDateTimeToString(appointment.getStartLocalDateTime())));
        
        lbl_subject.textProperty().bind(appointment.getSubject().nameProperty());
        ta_descr.textProperty().bind(appointment.summaryProperty());
    }

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
       
}
