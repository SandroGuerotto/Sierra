package controller;

import data.Database;
import data.Gesuch;
import data.Request;
import exception.LoginException;
import handler.ApplicationHandler;
import handler.LoginHandler;
import helper.DateFormatter;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import view.ItemEvent;

import java.time.LocalDate;

public class Controller {
    private Stage stage;
    private Database database;

    public Controller(){
        database = new Database();
    }

    public void start(Stage stage){
        this.stage = stage;
//        goToLogin();
        gotToApplication();
    }

    public void goToLogin(){
        new StarterGui().start(stage, new LoginHandler(this), "Login");

    }

    public void gotToApplication(){
        new StarterGui().start(stage, new ApplicationHandler(this), "Application");
    }

    public void login(String username, String password) throws LoginException{
        if (username.equals("test") && password.equals("1234")){
            gotToApplication();
            System.out.println("eingeloggt");
        }else{
            throw new LoginException();
        }
    }


    public ObservableList<ItemEvent> getTasks(){
        return  database.getTasks();
    }

    public ObservableList<Request> getRequests(){
        return  database.getRequests();
    }
    public ObservableList<Gesuch> getGesuche(){
        return  database.getGesuche();
    }

    public void addJoker(LocalDate date, String reason){
        Request request = new Request();
        request.setDate(DateFormatter.LocalDateToString(date));
        request.setReason(reason);
        request.setStatus("RQ");
        database.addRequest(request);
    }

    public void addGesuch(LocalDate date, String reason, String content){
        Gesuch gesuch = new Gesuch();
        gesuch.setDate(DateFormatter.LocalDateToString(date));
        gesuch.setReason(reason);
        gesuch.setContent(content);
        gesuch.setStatus("RQ");
        database.addGesuch(gesuch);
    }


}
