package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.jfoenix.controls.JFXPopup;

import controller.Controller;
import data.Appointment;
import helper.DateFormatter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	private StringProperty displayedWeekNumberProperty;
	private ObjectProperty<LocalDate> displayedLocalDateProperty;
	
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
        
        setWeekNumber();
    	agenda.displayedLocalDateTime().addListener(new ChangeListener<LocalDateTime>() {
			@Override
			public void changed(ObservableValue<? extends LocalDateTime> arg0, LocalDateTime arg1, LocalDateTime arg2) {
				 setWeekNumber();
			}
		});
    }
    /**
     * set displayed week number
     */
    private void setWeekNumber(){
		int weekNumber = agenda.getDisplayedLocalDateTime().toLocalDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
//		setDisplayedLocalDate(DateFormatter.LocalDateToString(agenda.getDisplayedLocalDateTime().toLocalDate()));
		setWeekNumberProperty("Woche " + weekNumber);
		setdisplayedLocalDate(agenda.getDisplayedLocalDateTime().toLocalDate());
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


        // displayed calendar
        {
            LocalDateTimeTextField lLocalDateTimeTextField = new LocalDateTimeTextField();
            lGridPane.add(lLocalDateTimeTextField, new GridPane.C().row(1).col(lRowIdx));
            lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
        }
        lRowIdx++;

        return lGridPane;
    }


    public void addAppointement(data.Appointment appointment){
        agenda.appointments().add(appointment);
    }


    public Agenda getAgenda(){ return agenda; }


    // Week number
	public void setWeekNumberProperty(String value) {
        this.displayedWeekNumberProperty().set(value);
    }

    public String getWeekNumberProperty() {
        return this.displayedWeekNumberProperty().get();
    }

    public StringProperty displayedWeekNumberProperty() {
        if (this.displayedWeekNumberProperty == null) {
            this.displayedWeekNumberProperty = new SimpleStringProperty(this, "displayedWeekNumberProperty");
        }
        return this.displayedWeekNumberProperty;
    }
    
    // Week number
	public void setdisplayedLocalDate(LocalDate value) {
        this.displayedLocalDateProperty().set(value);
    }

    public LocalDate getdisplayedLocalDatey() {
        return this.displayedLocalDateProperty().get();
    }

    public ObjectProperty<LocalDate> displayedLocalDateProperty() {
        if (this.displayedLocalDateProperty == null) {
            this.displayedLocalDateProperty = new SimpleObjectProperty<LocalDate>(this, "displayedLocalDateProperty");
        }
        return this.displayedLocalDateProperty;
    }

}