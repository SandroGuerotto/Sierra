package view;


import com.jfoenix.effects.JFXDepthManager;
import data.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI object to display a classmember
 * @author Sandro Guerotto
 * @since 01.01.2017
 * @version 0.1
 */
public class ClassMember extends Pane implements Initializable {

    @FXML
    private BorderPane pane_root;

    @FXML
    private Label lbl_roll;

    @FXML
    private BorderPane pane_content;

    @FXML
    private ImageView iv_pic;

    @FXML
    private Label lbl_forename, lbl_name, lbl_birthdate;

    @FXML
    private Label lbl_telnr, lbl_email, lbl_addtext;


    private boolean isTeacher = false;  // teacher
    private boolean isRepresentative = false;  //Klassensprecher
    private boolean isClassTeacher = false;  //Klassensprecher

    private StringProperty roll;
    // roll = text for user information
    private int id;
    
    private Person person;

    public ClassMember(Person person ){
    	this.person = person;

        if(this.person.isTeacher() && !this.isClassTeacher){
        	this.rollProperty().set("Lehrer");
        }else if( this.person.isRepresentative()){
        	this.rollProperty().set("Klassensprecher");
        }else{
        	this.rollProperty().set("Schüler");
        }
        
    loadFXML();
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	lbl_name.textProperty().bind(person.nameProperty());
    	lbl_forename.textProperty().bind(person.forenameProperty());
    	lbl_birthdate.textProperty().bind(person.birthdateProperty());
    	lbl_telnr.textProperty().bind(person.telnrProperty());
    	lbl_email.textProperty().bind(person.emailProperty());
    	lbl_addtext.textProperty().bind(person.addTextProperty());
    	lbl_roll.textProperty().bind(this.rollProperty());
    	

        if (this.person.getAddText().isEmpty()){
            lbl_addtext.setManaged(false);
            lbl_addtext.setVisible(false);
            lbl_addtext.setDisable(true);
        }

        JFXDepthManager.setDepth(this, 1);

    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/classmember.fxml"));
        loader.setController(this);
        try {
            pane_root = loader.load();

            this.getChildren().setAll(pane_root);

        } catch (IOException e) {
            pane_root = new BorderPane();
        }

    }

    public boolean isTeacher() {
        return person.isTeacher();
    }

    public String getName() {
        return person.getName();
    }

    public String getForename() {
        return person.getForename();
    }

    public String getTelnr() {
        return person.getTelnr();
    }
    
	// roll
	public String getRoll() {
		return this.rollProperty().get();
	}
	public void setRoll(String roll) {
		this.rollProperty().set(roll);
	}
	public StringProperty rollProperty(){
		if (this.roll == null) {
            this.roll = new SimpleStringProperty(this, "roll");
        }
        return this.roll;
	}

    public void setIsClassTeacher(boolean isClassTeacher) {
        this.isClassTeacher = isClassTeacher;
        this.rollProperty().set("Klassenlehrer");
    }

}
