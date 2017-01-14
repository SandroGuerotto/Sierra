package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.scene.control.agenda.Agenda;
import view.ClassMember;
import view.ItemEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Database {

    private ObservableList<Request> requests = FXCollections.observableArrayList();
    private ObservableList<Gesuch> gesuche = FXCollections.observableArrayList();
    private ObservableList<ItemEvent> events = FXCollections.observableArrayList();
    private ObservableList<Agenda.Appointment> appointments = FXCollections.observableArrayList();
    private ObservableList<Mark> marks = FXCollections.observableArrayList();
    private Schoolclass schoolclass;
    private ObservableList<ClassMember> teachers = FXCollections.observableArrayList();
    private ObservableList<Absent> absents = FXCollections.observableArrayList();
    private ObservableList<Person> people = FXCollections.observableArrayList();
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    public Database() {
        initPeople();
        initRequest();
        initGesuch();
        initEvent();
        initMarks();
        initClass();
        initTeacher();
        initAbsent();
        initSubject();
        initAppointments();
    }

    private void initSubject() {
        Subject subject = new Subject(1, "Mathematik", "#00ff00");
        subjects.add(subject);
        subject = new Subject(2, "Französisch", "#ff00ff");
        subjects.add(subject);
    }

    private void initPeople() {
        Person person = new Person(1, "Dunois", "Maximori", "02.11.2016", "078 245 02 21", "maximori.dunois@gmail.com", "Spetklasse AP14a", false, false, "1234", "maximori.dunois");
        people.add(person);
        person = new Person( 3, "Henrich", "Max", "05.06.1999", "078 205 25 23", "m.henrich@gmail.com", "Ersatzklassen: BI14a" , false, true, "1234", "max.henrich");
        people.add(person);
        person = new Person( 4, "Kalt", "Jean", "15.08.1999", "078 298 45 78", "jean.dunois@gmail.com", "Ersatzklassen: BI14a", false, false, "1234", "jean.kalt");
        people.add(person);
        person = new Person( 5, "Bauer", "Dieter", "23.09.2001", "078 852 23 01", "b.d2001@hotmail.com", "Ersatzklassen: AP14b", false, false, "1234", "dieter.bauer");
        people.add(person);
        // teacher
        person = new Person(2, "Streng", "Manfred", "12.02.1975", "079 352 64 87", "manfred.streng@schule.ch", "Deutsch, Englisch, Mathematik", true, false, "1234", "manfred.streng");
        people.add(person);
        person = new Person(6, "Klainfus", "Benjamin", "19.10.1968", "079 253 78 02", "benjamin.klainfus@schule.ch", "Geschichte, Biologie, Naturwissenschaft", true, false, "1234", "benjamin.klainfus");
        people.add(person);
    }

    private void initAbsent() {
        Absent absent = new Absent(1, "01.01.2017 08:00", "02.01.2017 12:00", "Krank", false);
        absents.add(absent);
        absent = new Absent(2, "28.03.2017 10:00", "28.03.2017 19:00", "Arzt", true);
        absents.add(absent);
    }

    private void initTeacher() {
        for (Person person: people.filtered(Person::isTeacher)) {
            teachers.add(new ClassMember(person));
        }
    }

    private void initClass() {
        ObservableList<ClassMember> memberList = FXCollections.observableArrayList();
        ClassMember member = new ClassMember(people.filtered(t -> t.getId()== 2).get(0));
        member.setIsClassTeacher(true);
        memberList.add(member);
        member = new ClassMember(people.get(0));
        memberList.add(member);
        member = new ClassMember(people.get(1));
        memberList.add(member);
        member = new ClassMember(people.get(2));
        memberList.add(member);
        member = new ClassMember(people.get(3));
        memberList.add(member);
        schoolclass = new Schoolclass(1, memberList.filtered(t -> !t.isTeacher()).size(), "AP14a");

        schoolclass.setMemebers(memberList);
    }

    private void initMarks() {
        Mark mark = new Mark(1, "01.01.2017", "Mathematik", "Bruchrechnen", "2", "4.85", "4.2");
        marks.add(mark);
        mark = new Mark(2, "28.12.2016", "Deutsch", "Gedicht", "1", "4.2", "5");
        marks.add(mark);
    }


    private void initRequest() {
        Request request = new Request(1, "25.12.2016", "Aufgrund von Gründen", "OK");
        requests.add(request);
    }

    private void initGesuch() {
        Gesuch gesuch = new Gesuch(1, "10.02.2016", "Sommerferienverlängerung", "Ich will frei haben!", "XX");
        gesuche.add(gesuch);
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

    public ObservableList<Mark> getMarks() {
        return marks;
    }

    public Schoolclass getSchoolclass() {
        return schoolclass;
    }

    public ObservableList<ClassMember> getMyTeachers() {
        return teachers;
    }

    public ObservableList<Absent> getAbsents() {
        return absents;
    }

    public ObservableList<Person> getPeople(){ return people; }

    public ObservableList<Subject> getSubjects() { return subjects; }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addGesuch(Gesuch gesuch) {
        gesuche.add(gesuch);
    }

    public void addAbsent(Absent absent) {
        absents.add(absent);
    }

    private void initAppointments() {
        Appointment appointment = new Appointment(2, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)), LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 00)), "B", "A Description", subjects.get(0), people.get(1));
        appointments.add(appointment);
        appointment = new Appointment(3, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 30)), LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 30)), "C", "Blablabla" , subjects.get(0), people.get(1));
        appointments.add(appointment);
        appointment = new Appointment(3, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(9, 00)), LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(13, 30)), "D", "Blablabla" , subjects.get(0), people.get(1));
        appointments.add(appointment);
    }

    public void deleteAppointment(Agenda.Appointment old) {
        appointments.remove(old);
    }

}
