package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import controller.Controller;
import data.Appointment;
import data.Person;
import data.Subject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import helper.Color;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * Popup object to create or edit an appointment view controller for
 * popupTask.fxml
 * 
 * @author Sandro Guerotto
 * @since 13.12.2016
 * @version 0.1
 */
public class PopupNewTask extends JFXPopup implements Initializable {

	@FXML
	private JFXButton btn_edit, btn_delete, btn_save;
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
	private Label lbl_error, lbl_titel;
	@FXML
	private GridPane popupTask;

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
			public ListCell<Person> call(ListView<Person> list) {
				return new ListCell<Person>() {
					@Override
					protected void updateItem(Person person, boolean bln) {
						super.updateItem(person, bln);
						if (person != null) {
							textProperty().bind(
									Bindings.createStringBinding(() -> person.getForename() + " " + person.getName()));
						} else {
							textProperty().unbind();
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
		if (!tf_descr.getText().isEmpty() && dp_date.getValue() != null && dp_date.getValue() != null
				&& !tf_title.getText().isEmpty() && cb_teacher.getSelectionModel().getSelectedItem() != null
				&& cb_subject.getSelectionModel().getSelectedItem() != null) {
			System.out.println("input fields checked");
			try {
				Appointment appointment = new Appointment(LocalDateTime.of(dp_date.getValue(), dp_time.getTime()),
						null, tf_title.getText(),
						tf_descr.getText(), cb_subject.getSelectionModel().getSelectedItem(),
						cb_teacher.getSelectionModel().getSelectedItem(), lbl_titel.getText());
				if (old!= null && old.getEndLocalDateTime() != null) {
					appointment.setEndLocalDateTime(old.getEndLocalDateTime());
				}else{
					appointment.setEndLocalDateTime(LocalDateTime.of(dp_date.getValue(), dp_time.getTime().plusMinutes(45)));
				}
				System.out.println("appointment erstellt");
				if (old != null && checkInput(old, appointment)) {
					System.out.println("update");
					controller.updateAppointment(old, appointment);
					old = null;
				} else if (old == null) {
					System.out.println("add");
					controller.addAppointment(appointment);
				}
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
					this.close();
					dp_time.setTime(LocalTime.of(0, 0));
					cb_subject.getSelectionModel().clearSelection();
					cb_teacher.getSelectionModel().clearSelection();
			}
		} else {
			showError("Bitte alle Felder ausfüllen!");
		}

	}

	@FXML
	private void edit() {
		lockinput(!tf_title.isDisable());
	}
	
	@FXML
	private void delete(){
		controller.deleteAppointment(old);
	}

	/**
	 * shows error label with given text
	 * 
	 * @param text displayed text
	 */
	private void showError(String text) {
		lbl_error.setText(text);
		lbl_error.setVisible(true);
		lbl_error.setDisable(false);
	}

	/**
	 * hides error label
	 */
	private void hideError() {
		lbl_error.setVisible(false);
		lbl_error.setDisable(true);
	}

	public void setInfo(String title, String descr, LocalDate start, LocalTime time, Subject subject, Person teacher,
			Appointment old, boolean lockinput, String type) {
		hideError(); // hide error message from previous
		if (old != null) {
			lbl_titel.setText(old.getType());
		} else {
			lbl_titel.setText(type);
			btn_delete.setDisable(true);
			btn_edit.setDisable(true);
		}
		tf_title.setText(title);
		tf_descr.setText(descr);
		dp_date.setValue(start);
		dp_time.setTime(time);
		cb_subject.getSelectionModel().select(subject);
		cb_teacher.getSelectionModel().select(teacher);
		this.old = old;
		lockinput(lockinput);
	}

	private void lockinput(boolean lockinput) {
		tf_descr.setDisable(lockinput);
		tf_title.setDisable(lockinput);
		dp_date.setDisable(lockinput);
		dp_time.setDisable(lockinput);
		cb_subject.setDisable(lockinput);
		cb_teacher.setDisable(lockinput);

		btn_save.setDisable(lockinput);
		if(old != null){
			btn_delete.setDisable(false);
			btn_edit.setDisable(false);
		}else{
			btn_delete.setDisable(true);
			btn_edit.setDisable(true);
		}
		
	}

	private boolean checkInput(Appointment old, Appointment appointment) {
		System.out.println("check inputa");
		if (old.getTeacher() == appointment.getTeacher() && old.getDescription().equals(appointment.getDescription())
				&& old.getLocation() == appointment.getLocation()
				&& old.getStartLocalDateTime().isEqual(appointment.getStartLocalDateTime())
				&& old.getEndLocalDateTime().isEqual(appointment.getEndLocalDateTime())
				&& old.getSummary().equals(appointment.getSummary()) && old.getSubject() == appointment.getSubject()
				&& old.getType() == appointment.getType()) {
			System.out.println("gleich");
			return false;
		} else {
			System.out.println("changed");
			return true;
		}
	}
}
