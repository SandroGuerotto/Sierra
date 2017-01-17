package view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import data.Notification;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;


public class Menubanner extends HBox {

    private JFXButton btn_notification, btn_settings;

    private PopupNotification popup_notifcation;
    private PopupSetting popupSetting;

    private ObservableList<Node> items = FXCollections.observableArrayList();
    private ObservableList<Notification> notifications;

    public Menubanner(GridPane parent, StackPane main , ObservableList<Notification> notifications) {
        this.notifications = notifications;
        this.setFillHeight(true);
        VBox wrapper = new VBox();
        wrapper.setId("wrapper");
        FlowPane menu = new FlowPane();
        menu.setId("menu");
        menu.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        menu.setAlignment(Pos.TOP_LEFT);

        wrapper.prefWidthProperty().bind(parent.prefWidthProperty());
        wrapper.setAlignment(Pos.TOP_LEFT);

        createStudentMenu();
        setIcon();

        menu.getChildren().addAll(items );
        wrapper.getChildren().add(menu);
        this.getChildren().addAll(wrapper, btn_settings);

        initNotificationPopup(main);

        initSettingPopup(main);

    }

    private void setIcon() {
        MaterialDesignIconView icon;
        if (notifications == null || notifications.isEmpty() ){
            icon = new MaterialDesignIconView(MaterialDesignIcon.BELL_OUTLINE);
        }else{
            icon = new MaterialDesignIconView(MaterialDesignIcon.BELL);
        }
        icon.setSize("2.5em");
        btn_notification.setGraphic(icon);
        icon.setFill(Color.GRAY);

        icon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        icon.setSize("2.5em");
        btn_settings.setGraphic(icon);
        icon.setFill(Color.GRAY);

    }

    private void createStudentMenu() {

        JFXButton btn_home = new JFXButton("Home");
        btn_home.setId("home");
        btn_home.setButtonType(JFXButton.ButtonType.FLAT);
        btn_home.setRipplerFill(Color.GREEN);
        btn_home.setPrefWidth(100);
        btn_home.getStyleClass().add("btn");

        btn_notification = new JFXButton("");
        btn_notification.setId("notification");
        btn_notification.setButtonType(JFXButton.ButtonType.FLAT);
        btn_notification.setRipplerFill(Color.GREEN);
        btn_notification.getStyleClass().add("btn");
        btn_notification.setMaxWidth(50);

        JFXButton btn_mymarks = new JFXButton("Meine Noten");
        btn_mymarks.setId("myMarks");
        btn_mymarks.setButtonType(JFXButton.ButtonType.FLAT);
        btn_mymarks.setRipplerFill(Color.GREEN);
        btn_mymarks.setPrefWidth(USE_COMPUTED_SIZE);
        btn_mymarks.getStyleClass().add("btn");

        JFXButton btn_myclass = new JFXButton("Meine Klasse");
        btn_myclass.setId("myClass");
        btn_myclass.setButtonType(JFXButton.ButtonType.FLAT);
        btn_myclass.setRipplerFill(Color.GREEN);
        btn_myclass.setPrefWidth(USE_COMPUTED_SIZE);
        btn_myclass.getStyleClass().add("btn");

        JFXButton btn_teacher = new JFXButton("Lehrpersonen");
        btn_teacher.setId("teacher");
        btn_teacher.setButtonType(JFXButton.ButtonType.FLAT);
        btn_teacher.setRipplerFill(Color.GREEN);
        btn_teacher.setPrefWidth(USE_COMPUTED_SIZE);
        btn_teacher.getStyleClass().add("btn");

        JFXButton btn_allclass = new JFXButton("Alle Klassen");
        btn_allclass.setId(("allClass"));
        btn_allclass.setButtonType(JFXButton.ButtonType.FLAT);
        btn_allclass.setRipplerFill(Color.GREEN);
        btn_allclass.setPrefWidth(USE_COMPUTED_SIZE);
        btn_allclass.getStyleClass().add("btn");

        JFXButton btn_school = new JFXButton("Über Schule");
        btn_school.setId("school");
        btn_school.setButtonType(JFXButton.ButtonType.FLAT);
        btn_school.setRipplerFill(Color.GREEN);
        btn_school.setPrefWidth(USE_COMPUTED_SIZE);
        btn_school.getStyleClass().add("btn");

        btn_settings = new JFXButton("");
        btn_settings.setId("settings");
        btn_settings.setButtonType(JFXButton.ButtonType.FLAT);
        btn_settings.setRipplerFill(Color.GREEN);
        btn_settings.setPrefWidth(USE_COMPUTED_SIZE);
        btn_settings.getStyleClass().add("btn");
        btn_settings.setMaxWidth(50);

        items.addAll(btn_home, btn_notification, btn_mymarks, btn_myclass, btn_teacher, btn_allclass, btn_school);

    }

    private void initNotificationPopup(StackPane main){
        popup_notifcation = new PopupNotification(notifications);
        popup_notifcation.setPopupContainer(main);
        popup_notifcation.setSource(btn_notification);
        btn_notification.setOnMouseClicked((e)->{
            if (e.getButton().equals(MouseButton.PRIMARY)){
                popup_notifcation.clearSelection();
                popup_notifcation.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 50);
            }
        });
    }

    private void initSettingPopup(StackPane main){
        popupSetting = new PopupSetting();
        popupSetting.setPopupContainer(main);
        popupSetting.setSource(btn_settings);
        btn_settings.setOnMouseClicked((e)->{
            if (e.getButton().equals(MouseButton.PRIMARY)){
                popupSetting.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -5, 50);
            }
        });
    }

}
