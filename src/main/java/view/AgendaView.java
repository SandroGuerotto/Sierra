package view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import com.jfoenix.controls.JFXPopup;

import controller.Controller;
import data.Appointment;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.layout.GridPane;

/**
 * dynamic agenda view. shows all appointment that are not schedule plan
 * 
 * @author Sandro Guerotto
 * @since 25.12.2016
 * @version 0.1
 */
public class AgendaView {
	
    public AgendaView(Controller controller) {
        agenda = new Agenda();

        // setup appointment groups
        final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
        for (Agenda.AppointmentGroup lAppointmentGroup : agenda.appointmentGroups()) {
            lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
        }

        // accept new appointments
        agenda.newAppointmentCallbackProperty().set(dateTimeRange -> 
        new Appointment(dateTimeRange.getStartLocalDateTime(), dateTimeRange.getEndLocalDateTime(), "", "",  null, null, "Aufgabe")
        	.withAppointmentGroup(lAppointmentGroupMap.get("group04")));
        
        agenda.setAppointmentChangedCallback(param -> {
        	controller.updateAppointment((Appointment) param, (Appointment) param);
            return null;
        });
        
        agenda.appointments().addAll(controller.getAppointments());
        setSettings();
    }
    /**
	 * set initial setting to display standard schedule plan from logged in student.
	 */
    private void setSettings() {
    	agenda.setAllowDragging(true);
    	agenda.setAllowResize(true);
    }
    
    final Agenda agenda;
    
    /**
     * getter method to get agenda view
     * @return agenda view
     */
    public Node getPanel() {
        return agenda;
    }

    public Node getControlPanel() {
        // the result
        GridPane lGridPane = new GridPane();
        lGridPane.setVgap(2.0);
        lGridPane.setHgap(2.0);

        // setup the grid so all the labels will not grow, but the rest will
        ColumnConstraints lColumnConstraintsAlwaysGrow = new ColumnConstraints();
        lColumnConstraintsAlwaysGrow.setHgrow(Priority.ALWAYS);
        ColumnConstraints lColumnConstraintsNeverGrow = new ColumnConstraints();
        lColumnConstraintsNeverGrow.setHgrow(Priority.NEVER);
        lGridPane.getColumnConstraints().addAll(lColumnConstraintsNeverGrow, lColumnConstraintsAlwaysGrow);
        int lRowIdx = 0;

        // skin
        {
            lGridPane.add(new Label("Skin"), new GridPane.C().row(2).col(lRowIdx).halignment(HPos.RIGHT));
            AgendaSkinSwitcher lAgendaSkinSwitcher = new AgendaSkinSwitcher(agenda);
            lGridPane.add(lAgendaSkinSwitcher, new GridPane.C().row(1).col(lRowIdx));
        }
        lRowIdx++;

        // displayed calendar
        {
            lGridPane.add(new Label("Display"), new GridPane.C().row(2).col(lRowIdx).halignment(HPos.RIGHT));
            LocalDateTimeTextField lLocalDateTimeTextField = new LocalDateTimeTextField();
            lGridPane.add(lLocalDateTimeTextField, new GridPane.C().row(1).col(lRowIdx));
            lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
        }
        lRowIdx++;

        // AllowDragging
        {
            lGridPane.add(new Label("Allow dragging"), new GridPane.C().row(2).col(lRowIdx).halignment(HPos.RIGHT));
            CheckBox lCheckBox = new CheckBox();
            lCheckBox.setSelected(true);
            lGridPane.add(lCheckBox, new GridPane.C().row(1).col(lRowIdx));
            agenda.allowDraggingProperty().bind(lCheckBox.selectedProperty());
        }
        lRowIdx++;

        // AllowResize
        {
            lGridPane.add(new Label("Allow resize"), new GridPane.C().row(2).col(lRowIdx).halignment(HPos.RIGHT));
            CheckBox lCheckBox = new CheckBox();
            lCheckBox.setSelected(true);
            lGridPane.add(lCheckBox, new GridPane.C().row(1).col(lRowIdx));
            agenda.allowResizeProperty().bind(lCheckBox.selectedProperty());
        }
        lRowIdx++;

        return lGridPane;
    }


    public void addAppointement(data.Appointment appointment){
        agenda.appointments().add(appointment);
    }


    public Agenda getAgenda(){ return agenda; }


}