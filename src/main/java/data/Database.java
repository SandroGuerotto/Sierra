package data;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import jfxtras.scene.control.agenda.Agenda;
import view.ClassMember;
import view.ItemEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Database {

    private ObservableList<Request> requests = FXCollections.observableArrayList();
    private ObservableList<Gesuch> gesuche = FXCollections.observableArrayList();
    private ObservableList<ItemEvent> events = FXCollections.observableArrayList();
    private ObservableList<Agenda.Appointment> appointments = FXCollections.observableArrayList();
    private ObservableList<Mark> marks = FXCollections.observableArrayList();
    private Schoolclass schoolclass;
    private ObservableList<ClassMember> teachers = FXCollections.observableArrayList();

    public Database() {
        initRequest();
        initGesuch();
        initEvent();
        initAppointments();
        initMarks();
        initClass();
        initTeacher();
    }

    private void initTeacher() {
        ClassMember teacher = new ClassMember(5, true, false, "Streng", "Manfred", "12.02.1975", "079 352 64 87", "manfred.streng@schule.ch", "Deutsch, Englisch, Mathematik") ;
        teachers.add(teacher);
        teacher = new ClassMember(8, true, false, "Klainfus", "Benjamin", "19.10.1968", "079 253 78 02", "benjamin.klainfus@schule.ch", "Geschichte, Biologie, Naturwissenschaft") ;
        teachers.add(teacher);
    }

    private void initClass(){
        ObservableList<ClassMember> memberList = FXCollections.observableArrayList();
        ClassMember member = new ClassMember(5, true, false, "Streng", "Manfred", "12.02.1975", "079 352 64 87", "manfred.streng@schule.ch", "") ;
        memberList.add(member);
        member = new ClassMember(1, false, true, "Henrich", "Max", "05.06.1999", "078 205 25 23", "m.henrich@gmail.com", "Ersatzklassen: BI14a") ;
        memberList.add(member);
        member = new ClassMember(2, false, false, "Dunois", "Jean", "15.08.1999", "078 298 45 78", "jean.dunois@gmail.com", "Ersatzklassen: BI14a") ;
        memberList.add(member);
        member = new ClassMember(3, false, false, "Bauer", "Dieter", "23.09.2001", "078 852 23 01", "b.d2001@hotmail.com", "Ersatzklassen: AP14b") ;
        memberList.add(member);
        schoolclass = new Schoolclass(1, memberList.size(), "AP14a");

        schoolclass.setMemebers(memberList);
    }

    private void initMarks() {
        Mark mark = new Mark(1, "01.01.2017", "Mathematik", "Bruchrechnen", "2", "4.85", "4.2");
        marks.add(mark);
        mark = new Mark(2, "28.12.2016", "Deutsch", "Gedicht", "1", "4.2", "5");
        marks.add(mark);
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

    public ObservableList<Mark> getMarks(){ return marks; }

    public Schoolclass getSchoolclass(){ return  schoolclass; }

    public ObservableList<ClassMember> getTeachers(){ return teachers; }

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

    public void deleteAppointment(Agenda.Appointment old){
        appointments.remove(old);
    }

}
