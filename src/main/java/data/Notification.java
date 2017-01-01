package data;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by Sandro on 25.12.2016.
 */
public class Notification extends Pane {

    public Notification(){
        this.getChildren().add(new Label("test"));

    }
}
