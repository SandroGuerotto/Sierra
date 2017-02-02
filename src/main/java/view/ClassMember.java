package view;


import com.jfoenix.effects.JFXDepthManager;
import data.Person;
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

    private String name, forename, birthdate, telnr, email, roll = "Schüler", addText;
    // roll = text for user information
    private int id;

    public ClassMember(Person person ){
        this.id = person.getId();
        this.isTeacher = person.isTeacher();
        this.isRepresentative = person.isRepresentative();
        this.name = person.getName();
        this.forename = person.getForename();
        this.birthdate = person.getBirthdate();
        this.telnr = person.getTelnr();
        this.email = person.getEmail();
        this.addText = person.getAddText();

        if (this.isTeacher && this.isClassTeacher){
            this.roll = "Klassenlehrer";
        }else if(this.isTeacher && !this.isClassTeacher){
            this.roll = "Lehrer";
        }else if( this.isRepresentative){
            this.roll = "Klassensprecher";
        }
    loadFXML();
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lbl_name.setText(this.name);
        lbl_forename.setText(this.forename);
        lbl_birthdate.setText(this.birthdate);
        lbl_telnr.setText(this.telnr);
        lbl_email.setText(this.email);
        lbl_roll.setText(this.roll);
        lbl_addtext.setText(this.addText);

        if (this.addText.isEmpty()){
            lbl_addtext.setManaged(false);
            lbl_addtext.setVisible(false);
            lbl_addtext.setDisable(true);
        }

        JFXDepthManager.setDepth(this, 1);

    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/classmember.fxml"));
        loader.setController(this);
        try {
            pane_root = loader.load();

            this.getChildren().setAll(pane_root);

        } catch (IOException e) {
            pane_root = new BorderPane();
        }

    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public String getName() {
        return name;
    }

    public String getForename() {
        return forename;
    }

    public String getTelnr() {
        return telnr;
    }

    public void setIsClassTeacher(boolean isClassTeacher) {
        this.isClassTeacher = isClassTeacher;
        this.roll = "Klassenlehrer";
    }

}
