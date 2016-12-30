package data;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import jfxtras.scene.control.agenda.Agenda;
import view.ItemEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Database {

    private ObservableList<Request> requests = FXCollections.observableArrayList();
    private ObservableList<Gesuch> gesuche = FXCollections.observableArrayList();
    private ObservableList<ItemEvent> events = FXCollections.observableArrayList();
    private ObservableList<Agenda.Appointment> appointments = FXCollections.observableArrayList();

    public Database() {
        initRequest();
        initGesuch();
        initEvent();
        initAppointments();
    }


    private void initRequest() {
        Request request = new Request();
        request.setID(1);
        request.setDate("25.12.2016");
        request.setReason("Aufgrund von Gründen");
        request.setStatus("OK");
        requests.addAll(request);
    }

    private void initGesuch() {
        Gesuch gesuch = new Gesuch();
        gesuch.setID(1);
        gesuch.setDate("10.02.2016");
        gesuch.setReason("Sommerferien Verlängerung");
        gesuch.setContent("Ich will frei haben, weil Baum :)");
        gesuch.setStatus("XX");
        gesuche.addAll(gesuch);
    }

    private void initEvent() {
        ItemEvent item;
        item = new ItemEvent(1, "Mathematik Test: Bruchrechnen", "12.12.2016", "15:00", "Lernziele 1-4 \n" +
                "Einfaches Rechnen 1: S12 - S24 \n" + "Zeit: 45min", "Test");
        events.add(item);
        item = new ItemEvent(2, "Bla bla bla", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "News");
        events.add(item);
        item = new ItemEvent(3, "Btestst tst", "06.05.2017", "11:00", "afsa fs fsj kfls jlfjjf ", "asfd");
        events.add(item);
    }

    public ObservableList<Request> getRequests() {
        return requests;
    }

    public ObservableList<Gesuch> getGesuche() {
        return gesuche;
    }

    public ObservableList<ItemEvent> getTasks() {
        return events;
    }

    public ObservableList<Agenda.Appointment> getAppointments() {
        return appointments;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void addAppointment(Agenda.Appointment appointment) {
        appointments.add(appointment);
    }

    public void addGesuch(Gesuch gesuch) {
        gesuche.add(gesuch);
    }

    private void initAppointments() {
        Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 00)))
                .withEndLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30)))
                .withSummary("A")
                .withDescription("A much longer test description");
        appointments.add(appointment);
        appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)))
                .withEndLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 00)))
                .withSummary("B")
                .withDescription("A description ");
        appointments.add(appointment);
        appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)))
                .withEndLocalDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30)))
                .withSummary("C")
                .withDescription("A description ");
        appointments.add(appointment);
        appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(9, 00)))
                .withEndLocalDateTime(LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(13, 30)))
                .withSummary("D")
                .withDescription("A description ");
        appointments.add(appointment);
        appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 30)))
                .withEndLocalDateTime(LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(11, 00)))
                .withSummary("E")
                .withDescription("A description ");
        appointments.add(appointment);
    }

}
