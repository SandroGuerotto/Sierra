package view;

import controller.Controller;
import javafx.scene.Node;
import jfxtras.scene.control.agenda.Agenda;

import java.util.Map;
import java.util.TreeMap;

public class ScheduleView {
    public ScheduleView(Controller controller) {
        agenda = new Agenda();

        // setup appointment groups
        final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
        for (Agenda.AppointmentGroup lAppointmentGroup : agenda.appointmentGroups()) {
            lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
        }

        setSettings();
    }

    final Agenda agenda;


    public Node getPanel() {
        return agenda;
    }

    private void setSettings() {
        agenda.setAllowDragging(false);
        agenda.setAllowResize(false);
    }

}