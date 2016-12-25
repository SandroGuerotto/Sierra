package view;


import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Menubanner extends HBox {

    // student menu
    private JFXButton btn_home, btn_notification, btn_mymarks;
    private JFXButton btn_teacher, btn_myclass, btn_school, btn_settings;


    // management menu
    private JFXButton btn_person, btn_allclass, btn_misc;

    private VBox wrapper;
    private FlowPane menu;

    private static Paint GREEN = Paint.valueOf("#b4d878");
    private static Paint GRAY = Paint.valueOf("#4d4d4d");

    private ObservableList<Node> items = FXCollections.observableArrayList();;


    public Menubanner(GridPane parent) {
        this.setFillHeight(true);

        wrapper = new VBox();
        menu = new FlowPane();
        menu.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        wrapper.prefWidthProperty().bind(parent.prefWidthProperty());
        wrapper.setAlignment(Pos.CENTER_LEFT);

        createStudentMenu();
        setIcon();

//        menu.getButtons().addAll(items );
        menu.getChildren().addAll(items );
        wrapper.getChildren().add(menu);
        this.getChildren().addAll(wrapper, btn_settings);

    }


    private void setIcon() {
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.BELL);
        icon.setSize("2.5em");
        btn_notification.setGraphic(icon);
        icon.setFill(GRAY);

        icon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        icon.setSize("2.5em");
        btn_settings.setGraphic(icon);
        icon.setFill(GRAY);
    }

    private void createStudentMenu() {
        btn_home = new JFXButton("Home");
        btn_home.setButtonType(JFXButton.ButtonType.FLAT);
        btn_home.setRipplerFill(GREEN);
        btn_home.setPrefWidth(150);
        btn_home.getStyleClass().add("btn");

        btn_notification = new JFXButton("");
        btn_notification.setButtonType(JFXButton.ButtonType.FLAT);
        btn_notification.setRipplerFill(GREEN);
        btn_notification.getStyleClass().add("btn");
        btn_notification.setMaxWidth(50);

        btn_mymarks = new JFXButton("Meine Noten");
        btn_mymarks.setButtonType(JFXButton.ButtonType.FLAT);
        btn_mymarks.setRipplerFill(GREEN);
        btn_mymarks.setPrefWidth(USE_COMPUTED_SIZE);
        btn_mymarks.getStyleClass().add("btn");

        btn_myclass = new JFXButton("Meine Klasse");
        btn_myclass.setButtonType(JFXButton.ButtonType.FLAT);
        btn_myclass.setRipplerFill(GREEN);
        btn_myclass.setPrefWidth(USE_COMPUTED_SIZE);
        btn_myclass.getStyleClass().add("btn");

        btn_teacher = new JFXButton("Lehrpersonen");
        btn_teacher.setButtonType(JFXButton.ButtonType.FLAT);
        btn_teacher.setRipplerFill(GREEN);
        btn_teacher.setPrefWidth(USE_COMPUTED_SIZE);
        btn_teacher.getStyleClass().add("btn");

        btn_allclass = new JFXButton("Alle Klassen");
        btn_allclass.setButtonType(JFXButton.ButtonType.FLAT);
        btn_allclass.setRipplerFill(GREEN);
        btn_allclass.setPrefWidth(USE_COMPUTED_SIZE);
        btn_allclass.getStyleClass().add("btn");

        btn_school = new JFXButton("Über Schule");
        btn_school.setButtonType(JFXButton.ButtonType.FLAT);
        btn_school.setRipplerFill(GREEN);
        btn_school.setPrefWidth(USE_COMPUTED_SIZE);
        btn_school.getStyleClass().add("btn");

        btn_settings = new JFXButton("");
        btn_settings.setButtonType(JFXButton.ButtonType.FLAT);
        btn_settings.setRipplerFill(GREEN);
        btn_settings.setPrefWidth(USE_COMPUTED_SIZE);
        btn_settings.getStyleClass().add("btn");
        btn_settings.setMaxWidth(50);

        items.addAll(btn_home, btn_notification, btn_mymarks, btn_myclass, btn_teacher, btn_allclass, btn_school);

    }
}
