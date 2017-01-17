package controller;

import data.*;
import exception.LoginException;
import helper.DateFormatter;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import model.ViewModel;
import view.AgendaView;
import view.ClassMember;
import view.ItemEvent;
import view.ScheduleView;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Controller {
    private Stage stage;
    private Database database;
    private ViewModel viewModel;
    private AgendaView agendaView;
    private ScheduleView scheduleView;

    private Task loadThread;

    public Controller() {
        database = new Database();
        viewModel = new ViewModel(this);
        Controller controller = this;
        loadThread = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                agendaView = new AgendaView(controller);
                scheduleView = new ScheduleView(controller);
                viewModel.loadHandler();
                viewModel.loadFxml();
                return null;
            }
        };
        loadThread.setOnSucceeded(event -> {
//            gotToApplication();
            goToLogin();
        });

    }

    void start(Stage stage) {
        this.stage = stage;
        new Thread(loadThread).start();

    }

    private void goToLogin() {
        new StarterGui().start(stage, viewModel.getFxml("login"), false);

    }

    private void gotToApplication() {
        new StarterGui().start(stage, viewModel.getFxml("application"), true);
        new Thread(() -> viewModel.setFirstDisplay()).start();
    }

    public void login(String username, String password) throws LoginException {
        if (database.getPeople().filtered(t -> username.equals(t.getUsername()) && password.equals(t.getPassword())).size() > 0) {
            gotToApplication();
            System.out.println("eingeloggt");
        } else {
            throw new LoginException();
        }
    }

    // DB Logik
    public ObservableList<ItemEvent> getTasks() {
        return database.getTasks();
    }

    public ObservableList<Request> getRequests() {
        return database.getRequests();
    }

    public ObservableList<Gesuch> getGesuche() {
        return database.getGesuche();
    }

    public ObservableList<Mark> getMarks() {
        return database.getMarks();
    }

    public ObservableList<Absent> getAbsents() {
        return database.getAbsents();
    }

    public void addJoker(LocalDate date, String reason) {
        Request request = new Request(getRequests().size() + 1, DateFormatter.LocalDateToString(date), reason, "RQ");
        database.addRequest(request);
    }

    public void addGesuch(LocalDate date, String reason, String content) {
        Gesuch gesuch = new Gesuch(getGesuche().size() + 1, DateFormatter.LocalDateToString(date), reason, content, "RQ");
        database.addGesuch(gesuch);
    }

    public void addAbsent(String reason, LocalDateTime from, LocalDateTime to) {
        Absent absent = new Absent(getAbsents().size() + 1, DateFormatter.LocalDateTimeToString(from), DateFormatter.LocalDateTimeToString(to), reason, false);
        database.addAbsent(absent);
    }

    public Pane getNextPane(String name) {
        return viewModel.getFxml(name);
    }

    public StackPane getPane_main() {
        return viewModel.getPane_main();
    }

    public ObservableList<Appointment> getAppointments() {
        return database.getAppointments();
    }

    public AgendaView getAgendaView() {
        return agendaView;
    }

    public ScheduleView getScheduleView() {
        return scheduleView;
    }

    public void addAppointment(Appointment appointment) {
        database.addAppointment(appointment);
        agendaView.addAppointement(appointment);
    }

    public void deleteAppointment(Agenda.Appointment old) {
        database.deleteAppointment(old);
        agendaView.getAgenda().appointments().remove(old);
    }

    public int getStudentcount() {
        return database.getSchoolclass().getStudentcount();
    }

    public ObservableList<ClassMember> getMemebers() {
        return database.getSchoolclass().getMemebers();
    }

    public String getClassname() {
        return database.getSchoolclass().getClassname();
    }

    public ObservableList<ClassMember> getMyTeachers() {
        return database.getMyTeachers();
    }

    public ObservableList<Person> getTeachers() {
        return database.getPeople().filtered(Person::isTeacher);
    }

    public ObservableList<Subject> getSubjects() {
        return database.getSubjects();
    }

    public ObservableList<Notification> getNotifications() {
        return database.getNotifications();
    }

    public ObservableList<ItemEvent> getUpcomingTests() { return database.getUpcomingTests(); }

    public ObservableList<Appointment> getStandardTable(){ return database.getStandardTable(); }

    public ObservableList<ItemEvent> getNews(){ return database.getNews(); }

    public String getTotalAbsentTime() {
        int difdays = 0, difHours = 0;
        String text;
        for (Absent absent : database.getAbsents()) {
            LocalDateTime to = DateFormatter.StringToLocalDateTime(absent.getDateto());
            LocalDateTime from = DateFormatter.StringToLocalDateTime(absent.getDatefrom());
            difdays += DateFormatter.differenceInDays(from, to);
            difHours += DateFormatter.differenceInHours(from, to);
        }
        if (difdays == 0 && difHours == 0) {
            text = "Keine Absenz";
        } else {
            text = Integer.toString(difdays) + " Tage " + Integer.toString(difHours) + " Stunden";
        }

        return text;
    }


}
