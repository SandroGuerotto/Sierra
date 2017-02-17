package controller;

import data.*;
import exception.LoginException;
import helper.DateFormatter;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ViewModel;
import view.AgendaView;
import view.ClassMember;
import view.ItemEvent;
import view.ScheduleView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Controller class for all functions
 * Interface for GUI to get Data from Database
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 *
 */
public class Controller {
	
	private final boolean skiplogin = true;
	
    private Stage stage;
    private Database database;
    private ViewModel viewModel;
    private AgendaView agendaView;
    private ScheduleView scheduleView;
    
    private Person loggedinUser;

    private Task<Void> loadApplicationThread;
    private Task<Void> loadLoginThread;

    /**
     * Standard constructor for Controller
     */
    public Controller() {
        database = new Database();
        viewModel = new ViewModel(this);
        Controller controller = this;
        loadApplicationThread = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	Platform.runLater(() ->{
            		agendaView = new AgendaView(controller);
                    scheduleView = new ScheduleView(controller);
            	});
                
                viewModel.loadHandler();
                viewModel.loadFxml();
                return null;
            }
        };
        loadApplicationThread.setOnSucceeded(event -> {
            gotToApplication();
        });
        
        loadLoginThread = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                viewModel.loadLogin();
                return null;
            }
        };
        loadLoginThread.setOnSucceeded(event -> {
            goToLogin();
        });
       
    }

    /**
     * start method to run program. 
     * starts loading thread
     * @param stage Primary stage from Application.launch()
     */
    void start(Stage stage) {
        this.stage = stage;
        if (skiplogin) {
        	this.setLoggedinUser(database.getPeople().filtered(t -> "jean.kalt".equals(t.getUsername()) && "1234".equals(t.getPassword())).get(0));
        	new Thread(loadApplicationThread).start();
		}else{
			new Thread(loadLoginThread).start();
		}
        
        
    }

    /**
     * navigation method to get from the application to the login screen
     */
    private void goToLogin() {
        new StarterGui().start(stage, viewModel.getFxml("login"), false);
    }

    /**
     * navigation method to get from login to the application
     */
    private void gotToApplication() {
        new StarterGui().start(stage, viewModel.getFxml("application"), true);
        new Thread(() -> viewModel.setFirstDisplay()).start();
    }

    /**
     * Temporary login method to check if user inputs are valid and in the database
     * @param username User specified value
     * @param password User specified value
     * @throws LoginException throws when user values are not found
     */
    public void login(String username, String password) throws LoginException {
    	Person login = null;
    	try{
    		login = database.getPeople().filtered(t -> username.equals(t.getUsername()) && password.equals(t.getPassword())).get(0);
    	}catch(Exception e){
    		throw new LoginException();
    	}
    	
        if (login != null) {
        	this.setLoggedinUser(login);
        	new Thread(loadApplicationThread).start();
            System.out.println("eingeloggt");
        } else {
        	throw new LoginException();
        }
    }

    public void setLoggedinUser(Person user){
    	this.loggedinUser = user;
    }
    
    public Person getLoggedinUser(){
    	return this.loggedinUser;
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
    	String code = this.generateCode();
        Absent absent = new Absent(getAbsents().size() + 1, DateFormatter.LocalDateTimeToString(from), DateFormatter.LocalDateTimeToString(to), reason, false, code);
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
    	System.out.println("add to list");
        database.addAppointment(appointment);
        agendaView.addAppointement(appointment);
    }
    
    public void updateAppointment(Appointment old, Appointment appointment){
    	System.out.println("copy");
    	old.setTeacher(appointment.getTeacher());
    	old.setDescription(appointment.getDescription());
    	old.setLocation(appointment.getLocation());
    	old.setStartLocalDateTime(appointment.getStartLocalDateTime());
    	old.setEndLocalDateTime(appointment.getEndLocalDateTime());
    	old.setSummary(appointment.getSummary());
    	old.setSubject(appointment.getSubject());
    	old.setType(appointment.getType());
    	
    	this.deleteAppointment(old);
    	this.addAppointment(appointment);
    }

    public void deleteAppointment(Appointment old) {
    	System.out.println("delete method");
        database.deleteAppointment(old);
        System.out.println("delete from list");
        agendaView.getAgenda().appointments().remove(old);
        System.out.println("delete from agenda");
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

    /**
     * Calculates the total absent time from the logged in user.
     * @return total absent time in format x Days x Hours
     */
    public SimpleStringProperty getTotalAbsentTime() {
    	return database.getTotalAbsentTime();
    }

    private String generateCode(){
    	String code = UUID.randomUUID().toString().substring(0, 7);
    	return code;
    }
    
    public boolean verifyCode(String code){
    	for (Absent absent : database.getAbsents()) {
			if (absent.getSecureCode().equals(code)) {
				absent.setExcused(true);
				return true;
			}
		}
    	return false;
    }

}
