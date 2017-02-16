package view;


import data.Appointment;
import helper.DateFormatter;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * defines layout of list item in task listview
 * 
 * @author Sandro Guerotto
 * @since 17.12.2016
 * @version 0.1
 */
public class ItemEvent extends TitledPane {

    private Appointment appointment;

    public ItemEvent(Appointment appointment) {

        this.appointment = appointment;
        // header
        Label left = new Label();
        left.textProperty().bind(Bindings.createStringBinding(() ->
        DateFormatter.LocalDateTimeToString(appointment.getStartLocalDateTime())));
        this.setGraphic(left);


        BorderPane content = new BorderPane();
        Label title = new Label();
        if (appointment.getSubject() != null && !appointment.getSubject().getName().isEmpty()) {
        	title.textProperty().bind(appointment.getSubject().nameProperty());
        }else{
        	title.textProperty().bind(appointment.locationProperty());
        }
        content.setTop(title);
        Label summary = new Label();
        summary.textProperty().bind(appointment.summaryProperty());
        content.setCenter(summary);
        BorderPane.setAlignment(summary, Pos.TOP_LEFT);

        this.setContent(content);

        this.setId("event");

    }
    public Appointment getAppointment() {
        return appointment;
    }
}
