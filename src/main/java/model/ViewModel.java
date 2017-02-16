package model;

import controller.Controller;
import handler.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

/**
 * Loader and holder for all primary FXML files. 
 * Setups all FXML with a controller and initialize them
 * @author Sandro Guerotto
 * @since 27.12.2016
 * @version 0.1
 *
 */
public class ViewModel {

    private HomeHandler homeHandler;
    private SchoolHandler schoolHandler ;
    private ApplicationHandler applicationHandler;
    private LoginHandler loginHandler;
    private MyMarksHandler myMarksHandler;
    private MyClassHandler myClassHandler;
    private TeacherHandler teacherHandler;

    private GridPane pane_home;
    private GridPane pane_school;
    private AnchorPane pane_login;
    private StackPane pane_main;
    private StackPane pane_loader;
    private GridPane pane_mymarks;
    private GridPane pane_myclass;
    private GridPane pane_teacher;


    private Controller controller;

    /**
     * Constructor for ViewModel
     * @param controller gets passed to FXML controller
     */
    public ViewModel(Controller controller) {
        this.controller = controller;
    }
    
    public void loadLogin(){
    	loginHandler = new LoginHandler(controller);
    	FXMLLoader loader;
    	try {
	    	//loading screen
	        loader = new FXMLLoader(this.getClass().getResource("/view/loadinglogin.fxml"));
	        pane_loader = loader.load();
	
	        // Login Screen
	        loader = new FXMLLoader(this.getClass().getResource("/view/login.fxml"));
	        loader.setController(loginHandler);
	        pane_login = loader.load();
    	 } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    /**
	 * loads all Controller for FXML files and pass them the overall controller
	 */
    public void loadHandler() {
        homeHandler = new HomeHandler(controller);
        schoolHandler = new SchoolHandler(controller);
        applicationHandler = new ApplicationHandler(controller);
        
        myMarksHandler = new MyMarksHandler(controller);
        myClassHandler = new MyClassHandler(controller);
        teacherHandler = new TeacherHandler(controller);

    }

    /**
     * load all FXML files and save them in their corresponding Pane
     */
    public void loadFxml() {
        FXMLLoader loader;
        try {
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

            //myClass
            loader = new FXMLLoader(this.getClass().getResource("/view/myClass.fxml"));
            loader.setController(myClassHandler);
            pane_myclass = loader.load();

            //teacher
            loader = new FXMLLoader(this.getClass().getResource("/view/teachers.fxml"));
            loader.setController(teacherHandler);
            pane_teacher = loader.load();
            System.out.println("fertig load fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * used to navigate through the application
     * only in use from menu buttons
     * @param name of Pane that has to be returned
     * @return loaded pane. if not loaded or invalid name a new Pane is returned
     */
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
            case "myClass":
                return pane_myclass;
            case "teacher":
                return pane_teacher;
            default:
                return new Pane();
        }

    }
    /**
     * at first time the application controller sets up the screen for first display
     */
    public void setFirstDisplay(){
        applicationHandler.setFirstDisplay();
    }

    /**
     * getter method to receive main_Pane
     * @return main Stackpain
     */
    public StackPane getPane_main(){return pane_main;}
}
