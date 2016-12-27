package model;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import handler.ApplicationHandler;
import handler.HomeHandler;
import handler.LoginHandler;
import handler.SchoolHandler;

import java.io.IOException;

/**
 * Created by Sandro on 27.12.2016.
 */
public class ViewModel {

    private HomeHandler homeHandler;
    private SchoolHandler schoolHandler;
    private ApplicationHandler applicationHandler;
    private LoginHandler loginHandler;

    private GridPane pane_home;
    private GridPane pane_school;
    private GridPane pane_login;
    private StackPane pane_main;


    private Controller controller;

    public ViewModel(Controller controller) {
        this.controller = controller;

    }

    public void loadHandler() {
        homeHandler = new HomeHandler(controller);
        schoolHandler = new SchoolHandler(controller);
        applicationHandler = new ApplicationHandler(controller);
        loginHandler = new LoginHandler(controller);

    }

    public void loadFxml() {
        FXMLLoader loader;
        try {
            // Login Screen
            loader = new FXMLLoader(this.getClass().getResource("/view/login.fxml"));
            loader.setController(loginHandler);
            pane_login = loader.load();

            // home
            loader = new FXMLLoader(this.getClass().getResource("/view/home.fxml"));
            loader.setController(homeHandler);
            pane_home = loader.load();

            // school
            loader = new FXMLLoader(this.getClass().getResource("/view/school.fxml"));
            loader.setController(schoolHandler);
            pane_school = loader.load();

            // Application
            loader = new FXMLLoader(this.getClass().getResource("/view/application.fxml"));
            loader.setController(applicationHandler);
            pane_main = loader.load();

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
            default:
                return new Pane();
        }

    }

}
