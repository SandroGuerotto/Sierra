package view;


import com.jfoenix.effects.JFXDepthManager;
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

public class ClassMember extends Pane implements Initializable{

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

    private String name, forename, birthdate, telnr, email, roll = "Schüler", addText;

    private int id;



    public ClassMember(int id, boolean isTeacher, boolean isRepresentative, String name, String forename, String birthdate, String telnr, String email, String addText) {
        this.id = id;
        this.isTeacher = isTeacher;
        this.isRepresentative = isRepresentative;
        this.name = name;
        this.forename = forename;
        this.birthdate = birthdate;
        this.telnr = telnr;
        this.email = email;
        this.addText = addText;

        if (this.isTeacher){
            this.roll = "Klassenlehrer";
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

        new JFXDepthManager().setDepth(this, 1);

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

}
