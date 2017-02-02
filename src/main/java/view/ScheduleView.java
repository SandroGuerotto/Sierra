package view;

import controller.Controller;
import javafx.scene.Node;
import jfxtras.scene.control.agenda.Agenda;

import java.util.Map;
import java.util.TreeMap;

/**
 *  Viewclass to display Schedule plan from logged in student. 
 *  Handles all events from schedule
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 */
public class ScheduleView {
    public ScheduleView(Controller controller) {
        schedule = new Agenda();

        // setup appointment groups
        final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
        for (Agenda.AppointmentGroup lAppointmentGroup : schedule.appointmentGroups()) {
            lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
        }

        setSettings();
        schedule.appointments().addAll(controller.getStandardTable());
    }

    final Agenda schedule;

    /**
     * getter method to get schedule view
     * @return schedule view
     */
    public Node getPanel() {
        return schedule;
    }
	/**
	 * set initial setting to display standard schedule plan from logged in student.
	 */
    private void setSettings() {
        schedule.setAllowDragging(false);
        schedule.setAllowResize(false);
        schedule.setEditAppointmentCallback(param -> null);
    }

}