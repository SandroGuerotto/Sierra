package view;

import com.jfoenix.controls.*;
import controller.Controller;
import data.Appointment;
import data.Person;
import data.Subject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class PopupNewTask extends JFXPopup implements Initializable {

    @FXML
    private JFXButton btn_edit, btn_delete;
    @FXML
    private JFXComboBox<Person> cb_teacher;

    @FXML
    private JFXComboBox<Subject> cb_subject;

    @FXML
    private JFXTextField tf_title;

    @FXML
    private JFXDatePicker dp_date, dp_time;

    @FXML
    private JFXTextArea tf_descr;
    @FXML
    private Label lbl_error;


    private GridPane content;
    private Controller controller;
    private Appointment old = null;

    public PopupNewTask(Controller controller) {

        this.controller = controller;

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/popupTask.fxml"));
        loader.setController(this);
        try {
            content = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setContent(content);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButton();
        initLists();
    }

    private void initButton() {
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.PEN);
        icon.setSize("1.8em");
        btn_edit.setGraphic(icon);
        icon.setFill(Color.GRAY);
        icon = new MaterialDesignIconView(MaterialDesignIcon.DELETE);
        icon.setSize("1.8em");
        btn_delete.setGraphic(icon);
        icon.setFill(Color.GRAY);
    }

    private void initLists() {
        cb_teacher.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> p) {
                return new ListCell<Person>() {
                    @Override
                    protected void updateItem(Person t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getForename() + " " + t.getName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        cb_teacher.setItems(controller.getTeachers());
        cb_subject.setItems(controller.getSubjects());
    }

    @FXML
    private void save() {
        if (!tf_descr.getText().isEmpty() && dp_date.getValue() != null
                && dp_date.getValue() != null && !tf_title.getText().isEmpty()
                && cb_teacher.getSelectionModel().getSelectedItem() != null
                && cb_subject.getSelectionModel().getSelectedItem() != null) {
            try {
                Appointment appointment = new Appointment(LocalDateTime.of(dp_date.getValue(), dp_time.getTime()), LocalDateTime.of(dp_date.getValue(), dp_time.getTime().plusHours(1)),
                        tf_title.getText(), tf_descr.getText(), cb_subject.getSelectionModel().getSelectedItem(), cb_teacher.getSelectionModel().getSelectedItem());
//                appointment.setDescription(tf_descr.getText());
//                appointment.setStartLocalDateTime(LocalDateTime.of(dp_date.getValue(), dp_time.getTime()));
//                appointment.setEndLocalDateTime(LocalDateTime.of(dp_date.getValue(), dp_time.getTime().plusHours(1)));
//                appointment.setSummary(tf_title.getText());
                if (old != null) {
                    controller.deleteAppointment(old);
                    old = null;
                }
                controller.addAppointment(appointment);
                hideError();

                // popup erfolgreich
            } catch (Exception e) {
                showError("Es ist ein Fehler aufgetreten. Bitte später erneut probieren!");
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                    }
                    Platform.runLater(() -> hideError());
                }).start();
            } finally {
                tf_descr.clear();
                tf_title.clear();
                dp_date.setValue(null);
                try {
                    dp_time.setTime(null);
                } catch (Exception e) {
                }

                cb_subject.getSelectionModel().clearSelection();
                cb_teacher.getSelectionModel().clearSelection();
            }
        } else {
            showError("Bitte alle Felder ausfüllen!");
        }

    }

    private void showError(String text) {
        lbl_error.setText(text);
        lbl_error.setVisible(true);
        lbl_error.setDisable(false);
    }

    private void hideError() {
        lbl_error.setVisible(false);
        lbl_error.setDisable(true);
    }

    public void setInfo(String title, String descr, LocalDate start, LocalTime time, Subject subject, Person teacher, Appointment old) {
        tf_title.setText(title);
        tf_descr.setText(descr);
        dp_date.setValue(start);
        dp_time.setTime(time);
        cb_subject.getSelectionModel().select(subject);
        cb_teacher.getSelectionModel().select(teacher);
        this.old = old;
    }
}
