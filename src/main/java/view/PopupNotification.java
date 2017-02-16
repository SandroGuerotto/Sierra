package view;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import data.Notification;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
/**
 * Popup object to show notification
 * view controller for popupNotification.fxml 
 * 
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 */
public class PopupNotification extends JFXPopup {

	private JFXListView<Notification> listView;
    private ObservableList<Notification> notifications;

    public PopupNotification(ObservableList<Notification> notifications) {
    	this.notifications = notifications;
    	updateContent();

    }
    public void updateContent(){
        BorderPane root = new BorderPane();
        this.getChildren().clear();
        
        root.getChildren().removeAll();
       
        if (notifications.size() > 0){
        	listView = new JFXListView<>();
            listView.setId("notification");
            listView.setItems(this.notifications);
            
            root.setPrefWidth(350);
            root.setMaxHeight(360);
            root.setCenter(listView);
            this.setContent(root);
        }else{
            Label label = new Label("Keine Benachrichtigungen!");
            label.setStyle("-fx-background-color: white");
            label.getStyleClass().add("text-content");
            label.setPadding(new Insets(5,5,5,5));
            root.setCenter(label);
            this.setContent(root);
        }
    }
    public void clearSelection(){
        listView.getSelectionModel().clearSelection();
    }
}
