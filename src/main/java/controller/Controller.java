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
import view.ClassMember;
import view.ItemEvent;
import view.ScheduleView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public ObservableList<Mark> getMarks(){ return database.getMarks(); }

    public ObservableList<Absent> getAbsents(){ return database.getAbsents(); }

    public void addJoker(LocalDate date, String reason) {
        Request request = new Request(getRequests().size() + 1, DateFormatter.LocalDateToString(date), reason, "RQ");
        database.addRequest(request);
    }

    public void addGesuch(LocalDate date, String reason, String content) {
        Gesuch gesuch = new Gesuch(getGesuche().size() + 1, DateFormatter.LocalDateToString(date), reason, content, "RQ");
        database.addGesuch(gesuch);
    }

    public void addAbsent(String reason, LocalDateTime from, LocalDateTime to){
        Absent absent = new Absent(getAbsents().size() + 1, DateFormatter.LocalDateTimeToString(from), DateFormatter.LocalDateTimeToString(to), reason, false);
        database.addAbsent(absent);
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
    public void deleteAppointment(Agenda.Appointment old) {
        database.deleteAppointment(old);
        scheduleView.getAgenda().appointments().remove(old);
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

    public String getTotalAbsentTime(){
        int difdays = 0, difHours = 0;
        String text = "";
        for (Absent absent : database.getAbsents()) {
            LocalDateTime to = DateFormatter.StringToLocalDateTime(absent.getDateto());
            LocalDateTime from = DateFormatter.StringToLocalDateTime(absent.getDatefrom());
            difdays += DateFormatter.differenceInDays(from, to);
            difHours += DateFormatter.differenceInHours(from, to);
        }
        if (difdays == 0 && difHours == 0){
            text = "Keine Absenz";
        }else{
            text = Integer.toString(difdays) + " Tage " + Integer.toString(difHours) + " Stunden";
        }

        return text;
    }


}
