package controller;

import data.Tasks;
import exception.LoginException;
import handler.ApplicationHandler;
import handler.LoginHandler;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import view.ItemEvent;

public class Controller {
    private Stage stage;

    public Controller(){

    }

    public void start(Stage stage){
        this.stage = stage;
        goToLogin();
//        gotToApplication();
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
        return  new Tasks().getTasks();
    }

}
