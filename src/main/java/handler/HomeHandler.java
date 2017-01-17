package handler;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import controller.Controller;
import data.Absent;
import data.Appointment;
import data.Gesuch;
import data.Request;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class HomeHandler implements Initializable {


    @FXML
    private JFXButton btn_addTask, btn_addNews, btnSaveJoker, btnSaveGesuch;

    @FXML
    private GridPane pane_home, pane_container;

    @FXML
    private JFXListView<ItemEvent> lv_tasks;

    @FXML
    private JFXListView<ItemEvent> lv_news;

    @FXML
    private BorderPane pane_schedule;

    @FXML
    private JFXToggleButton btn_schedule;

    @FXML
    private TableView<Request> table_joker;

    @FXML
    private TableColumn<Request, String> col_jokerDate, col_jokerReason, col_jokerStatus;

    @FXML
    private JFXDatePicker date_Joker, date_gesuch;

    @FXML
    private JFXTextField tf_reasonJoker, tf_reasonGesuch;

    @FXML
    private TableView<Gesuch> table_gesuche;

    @FXML
    private TableColumn<Gesuch, String> col_gesuchDate, col_gesuchReason, col_gesuchStatus;

    @FXML
    private JFXTextArea tf_textGesuch;

    @FXML
    private VBox box_absent;

    @FXML
    private JFXComboBox<String> cb_reason;

    @FXML
    private JFXDatePicker dp_startdate, dp_starttime;

    @FXML
    private JFXDatePicker dp_enddate, dp_endtime;

    @FXML
    private JFXTextField tf_code;

    @FXML
    private JFXButton btn_verifyCode;

    @FXML
    private TableView<Absent> table_absent;

    @FXML
    private TableColumn<Absent, String> col_AbsentFrom, col_AbsentTo, col_absentReason;

    @FXML
    private TableColumn<Absent, Boolean> col_absentExcuse;

    @FXML
    private Label lbl_AbsentTotal, lbl_teacher, lbl_telnrTeacher;


    @FXML
    private StackPane pane_main;

    private PopupNewTask popup_addTask;

    private Controller controller;


    public HomeHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButton();
        initCol();
        initPopup();
        lv_tasks.setItems(controller.getTasks());
        lv_news.setItems(controller.getNews());

        pane_schedule.setCenter(controller.getScheduleView().getPanel());

//        pane_schedule.setBottom(controller.getAgendaView().getControlPanel());

        table_joker.setItems(controller.getRequests());
        table_gesuche.setItems(controller.getGesuche());
        table_absent.setItems(controller.getAbsents());

        editAppointment();

        initAbsentView();


    }

    private void initAbsentView() {

        ObservableList<String> reasons = FXCollections.observableArrayList();
        reasons.addAll("Arzt", "Krank", "Unfall", "Sonstiges");
        cb_reason.setItems(reasons);

        JFXDepthManager.setDepth(box_absent, 1);
        ClassMember teacher = controller.getMemebers().stream().filter(ClassMember::isTeacher).collect(Collectors.toList()).get(0);
        lbl_teacher.setText(teacher.getForename() + " " + teacher.getName());
        lbl_telnrTeacher.setText(teacher.getTelnr());

        lbl_AbsentTotal.setText(controller.getTotalAbsentTime());
    }

    private void initPopup() {
        Platform.runLater(() -> {
            popup_addTask = new PopupNewTask(controller);
            popup_addTask.setPopupContainer(controller.getPane_main());
            popup_addTask.setSource(btn_addTask);
        });
    }

    /**
     * cellfactory of tableview.
     * create a custom cell for download column
     */
    private void initCol() {

        col_jokerDate.setCellValueFactory(new PropertyValueFactory<Request, String>("date"));
        col_jokerReason.setCellValueFactory(new PropertyValueFactory<Request, String>("reason"));
        col_jokerStatus.setCellValueFactory(new PropertyValueFactory<Request, String>("status"));

        col_gesuchDate.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("date"));
        col_gesuchReason.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("reason"));
        col_gesuchStatus.setCellValueFactory(new PropertyValueFactory<Gesuch, String>("status"));

        col_AbsentFrom.setCellValueFactory(new PropertyValueFactory<Absent, String>("datefrom"));
        col_AbsentTo.setCellValueFactory(new PropertyValueFactory<Absent, String>("dateto"));
        col_absentReason.setCellValueFactory(new PropertyValueFactory<Absent, String>("reason"));
        col_absentExcuse.setCellValueFactory(new PropertyValueFactory<Absent, Boolean>("isExcused"));

        col_absentExcuse.setCellFactory(param -> new TableCell<Absent, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                MaterialDesignIconView icon = null;
                if (!empty && item) {
                    icon = new MaterialDesignIconView(MaterialDesignIcon.CHECK);
                    icon.setSize("2.5em");
                    icon.setFill(Color.GRAY);
                } else if (!empty) {
                    icon = new MaterialDesignIconView(MaterialDesignIcon.CLOSE);
                    icon.setSize("2.5em");
                    icon.setFill(Color.GRAY);
                }
                this.setText("");
                this.setAlignment(Pos.CENTER);
                this.setGraphic(icon);
            }
        });
    }

    private void initButton() {
        // setup menu icon
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        icon.setFill(Color.GRAY);
        btn_addTask.setGraphic(icon);
        btn_addTask.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.PLUS);
        icon.setSize("2.5em");
        icon.setFill(Color.GRAY);
        btn_addNews.setGraphic(icon);
        btn_addNews.setText(null);

        icon = new MaterialDesignIconView(MaterialDesignIcon.CHECK);
        icon.setSize("2.5em");
        icon.setFill(Color.GRAY);
        btn_verifyCode.setGraphic(icon);
        btn_verifyCode.setText(null);
    }


    @FXML
    private void saveJoker() {
        System.out.println("Joker");
        if (!tf_reasonJoker.getText().isEmpty() && date_Joker.getValue() != null) {
            controller.addJoker(date_Joker.getValue(), tf_reasonJoker.getText());
            date_Joker.setValue(null);
            tf_reasonJoker.setText("");
        }
    }

    @FXML
    private void saveGesuch() {
        System.out.println("Gesuch");
        if (!tf_reasonGesuch.getText().isEmpty() && !tf_textGesuch.getText().isEmpty() && date_gesuch.getValue() != null) {
            controller.addGesuch(date_gesuch.getValue(), tf_reasonGesuch.getText(), tf_textGesuch.getText());
            date_gesuch.setValue(null);
            tf_reasonGesuch.setText("");
            tf_textGesuch.setText("");
        }
    }


    @FXML
    private void addTask() {
        popup_addTask.setInfo("", "", LocalDate.now(), LocalTime.now(), null, null, null);
        popup_addTask.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 50);
    }

    @FXML
    private void addNews() {
    }


    @FXML
    private void verifyCode() {
        if (!tf_code.getText().isEmpty()) {
            //do something
        }
    }

    @FXML
    private void save_absent() {
        if (cb_reason.getSelectionModel().getSelectedItem() != null && dp_startdate.getValue() != null
                && dp_starttime.getTime() != null && dp_enddate.getValue() != null && dp_endtime.getTime() != null
                && dp_startdate.getValue().isBefore(dp_enddate.getValue())) {
            LocalDateTime from = LocalDateTime.of(dp_startdate.getValue(), dp_starttime.getTime());
            LocalDateTime to = LocalDateTime.of(dp_enddate.getValue(), dp_endtime.getTime());
            controller.addAbsent(cb_reason.getSelectionModel().getSelectedItem(), from, to);
            cb_reason.getSelectionModel().clearSelection();

            dp_enddate.setValue(null);
            dp_startdate.setValue(null);

            try {
                dp_endtime.setTime(null);
                dp_starttime.setTime(null);
            } catch (Exception e) {
            }
            lbl_AbsentTotal.setText(controller.getTotalAbsentTime());

        }
    }

    private void editAppointment() {
        controller.getAgendaView().getAgenda().setEditAppointmentCallback(param -> {
            Appointment test = (Appointment) param;

            LocalDate date = test.getStartLocalDateTime().toLocalDate();
            LocalTime time = test.getStartLocalDateTime().toLocalTime();
            popup_addTask.setInfo(test.getSummary(), test.getDescription(), date, time, test.getSubject(), test.getTeacher(), test);
            popup_addTask.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 50);
            return null;
        });
    }

    @FXML
    private void switchView() {
        if (btn_schedule.isSelected()) {
            btn_schedule.setText("Agenda");
            pane_schedule.setCenter(controller.getAgendaView().getPanel());
        } else {
            btn_schedule.setText("Stundenplan");
            pane_schedule.setCenter(controller.getScheduleView().getPanel());
        }
    }
}
