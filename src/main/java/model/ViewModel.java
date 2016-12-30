package model;

import controller.Controller;
import handler.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.nio.file.StandardWatchEventKinds;

/**
 * Created by Sandro on 27.12.2016.
 */
public class ViewModel {

    private HomeHandler homeHandler;
    private SchoolHandler schoolHandler ;
    private ApplicationHandler applicationHandler;
    private LoginHandler loginHandler;
    private MyMarksHandler myMarksHandler;

    private GridPane pane_home;
    private GridPane pane_school;
    private AnchorPane pane_login;
    private StackPane pane_main;
    private StackPane pane_loader;
    private GridPane pane_mymarks;


    private Controller controller;

    public ViewModel(Controller controller) {
        this.controller = controller;

    }

    public void loadHandler() {
        homeHandler = new HomeHandler(controller);
        schoolHandler = new SchoolHandler(controller);
        applicationHandler = new ApplicationHandler(controller);
        loginHandler = new LoginHandler(controller);
        myMarksHandler = new MyMarksHandler(controller);

    }

    public void loadFxml() {
        FXMLLoader loader;
        try {
            //loading screen
            loader = new FXMLLoader(this.getClass().getResource("/view/loadinglogin.fxml"));
//            loader.setController(loginHandler);
            pane_loader = loader.load();

            // Login Screen
            loader = new FXMLLoader(this.getClass().getResource("/view/login.fxml"));
            loader.setController(loginHandler);
            pane_login = loader.load();



            // Application
            loader = new FXMLLoader(this.getClass().getResource("/view/application.fxml"));
            loader.setController(applicationHandler);
            pane_main = loader.load();

            // home
            loader = new FXMLLoader(this.getClass().getResource("/view/home.fxml"));
            loader.setController(homeHandler);
            pane_home = loader.load();

            // school
            loader = new FXMLLoader(this.getClass().getResource("/view/school.fxml"));
            loader.setController(schoolHandler);
            pane_school = loader.load();

            //myMarks
            loader = new FXMLLoader(this.getClass().getResource("/view/myMarks.fxml"));
            loader.setController(myMarksHandler);
            pane_mymarks = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Pane getFxml(String name){
        switch (name){
            case "login":
                return pane_login;
            case "application":
                return pane_main;
            case "home":
                return pane_home;
            case "school":
                return pane_school;
            case "myMarks":
                return pane_mymarks;
            case "loading":
                return pane_loader;
            default:
                return new Pane();
        }

    }

    public void setFirstDisplay(){
        applicationHandler.setFirstDisplay();
    }

    public StackPane getPane_main(){return pane_main;}
}
