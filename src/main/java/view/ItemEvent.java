package view;


import data.Appointment;
import helper.DateFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ItemEvent extends TitledPane {

    private Appointment appointment;

    public ItemEvent(Appointment appointment) {

        this.appointment = appointment;
        // header
        Label left = new Label(DateFormatter.LocalDateTimeToString(appointment.getStartLocalDateTime()));
        this.setGraphic(left);


        BorderPane content = new BorderPane();
        if (appointment.getSubject() != null && !appointment.getSubject().getName().isEmpty()) {
            content.setTop(new Label(appointment.getSubject().getName()));
        }else{
            content.setTop(new Label(appointment.getLocation()));
        }
        Label label = new Label(appointment.getSummary());
//        label.textProperty().bind(new SimpleStringProperty(appointment.getSummary()));
        content.setCenter(label);
        BorderPane.setAlignment(label, Pos.TOP_LEFT);

        this.setContent(content);

        this.setId("event");

    }
    public Appointment getAppointment() {
        return appointment;
    }
}
