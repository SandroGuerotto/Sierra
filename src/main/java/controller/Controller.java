package controller;

import data.Database;
import data.Gesuch;
import data.Mark;
import data.Request;
import exception.LoginException;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import jfxtras.scene.control.agenda.Agenda;
import model.ViewModel;
import helper.DateFormatter;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import view.ClassMember;
import view.ItemEvent;
import view.ScheduleView;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private Stage stage;
    private Database database;
    private ViewModel viewModel;
    private ScheduleView scheduleView;

    private Task loadThread;

    public Controller() {
        database = new Database();
        viewModel = new ViewModel(this);
        Controller controller = this;
        loadThread = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                scheduleView = new ScheduleView(controller);
                viewModel.loadHandler();
                viewModel.loadFxml();
                return null;
            }
        };
        loadThread.setOnSucceeded(event -> {
                    gotToApplication();
//            goToLogin();
        });

    }

    public void start(Stage stage) {
        this.stage = stage;
        new Thread(loadThread).start();

    }

    public void goToLogin() {
        new StarterGui().start(stage, viewModel.getFxml("login"), false);

    }

    public void gotToApplication() {
        new StarterGui().start(stage, viewModel.getFxml("application"), true);
        new Thread(() -> {
            viewModel.setFirstDisplay();
        }).start();

    }

    public void login(String username, String password) throws LoginException {
        if (username.equals("test") && password.equals("1234")) {
            gotToApplication();
            System.out.println("eingeloggt");
        } else {
            throw new LoginException();
        }
    }


    public ObservableList<ItemEvent> getTasks() {
        return database.getTasks();
    }

    public ObservableList<Request> getRequests() {
        return database.getRequests();
    }

    public ObservableList<Gesuch> getGesuche() {
        return database.getGesuche();
    }

    public ObservableList<Mark> getMarks(){ return database.getMarks(); }

    public void addJoker(LocalDate date, String reason) {
        Request request = new Request();
        request.setDate(DateFormatter.LocalDateToString(date));
        request.setReason(reason);
        request.setStatus("RQ");
        database.addRequest(request);
    }

    public void addGesuch(LocalDate date, String reason, String content) {
        Gesuch gesuch = new Gesuch();
        gesuch.setDate(DateFormatter.LocalDateToString(date));
        gesuch.setReason(reason);
        gesuch.setContent(content);
        gesuch.setStatus("RQ");
        database.addGesuch(gesuch);
    }

    public Pane getNextPane(String name) {
        return viewModel.getFxml(name);
    }
    public StackPane getPane_main(){
        return viewModel.getPane_main();
    }
    public ObservableList<Agenda.Appointment> getAppointments() {
        return database.getAppointments();
    }
    public ScheduleView getScheduleView(){ return scheduleView; }

    public void addAppointment(Agenda.Appointment appointment) {
        database.addAppointment(appointment);
        scheduleView.addAppointement(appointment);
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

    public ObservableList<ClassMember> getTeachers(){ return  database.getTeachers(); }
}
