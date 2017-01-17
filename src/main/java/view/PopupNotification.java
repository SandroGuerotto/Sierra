package view;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import data.Notification;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PopupNotification extends JFXPopup {

    JFXListView<Notification> listView;

    public PopupNotification(ObservableList<Notification> notifications) {
        BorderPane root = new BorderPane();
        root.setPrefWidth(350);
        root.setMaxHeight(360);

        listView = new JFXListView<>();
        listView.setId("notification");
        if (notifications.size() > 0){
            listView.setItems(notifications);
            root.setCenter(listView);
            this.setContent(root);
        }else{
            Label label = new Label("Keine Benachrichtigungen!");
            label.setStyle("-fx-background-color: white");
            label.getStyleClass().add("text-content");
            label.setPadding(new Insets(5,5,5,5));
            this.setContent(label);
        }
    }
    public void clearSelection(){
        listView.getSelectionModel().clearSelection();
    }
}
