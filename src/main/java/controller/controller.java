package controller;

import handler.LoginHandler;
import javafx.stage.Stage;

import exception.LoginException;

/**
 * Created by Sandro on 13.12.2016.
 */
public class Controller {
    private Stage stage;

    public Controller(){

    }

    public void start(Stage stage){
        this.stage = stage;
        goToLogin();
    }

    public void goToLogin(){
        new StarterLogin().start(stage, new LoginHandler((this)));

    }

    public void gotToApplication(){

    }

    public void login(String username, String password) throws LoginException{
        if (username.equals("test") && password.equals("1234")){
//            gotToApplication();
            System.out.println("eingeloggt");
        }else{
            throw new LoginException();
        }
    }
}
