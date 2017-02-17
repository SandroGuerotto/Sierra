package handler;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.effects.JFXDepthManager;

import controller.Controller;
import data.Absent;
import data.Appointment;
import data.Gesuch;
import data.Request;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import helper.Color;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jfxtras.scene.control.agenda.Agenda;
import view.ClassMember;
import view.ItemEvent;
import view.PopupNewTask;

/**
 * view controller for home.fxml handles all events on home screen
 * 
 * @author Sandro Guerotto
 * @since 27.12.2016
 * @version 0.1
 */
public class HomeHandler implements Initializable {

	@FXML
	private JFXButton btn_addTask, btn_addNews, btnSaveJoker, btnSaveGesuch, btn_prevweek, btn_nextweek;

	@FXML
	private GridPane pane_home, pane_container;

	@FXML
	private JFXListView<ItemEvent> lv_tasks;

	@FXML
	private JFXListView<ItemEvent> lv_news;

	@FXML
	private BorderPane pane_schedule, pane_nav;

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
	private JFXDatePicker dp_startdate, dp_starttime, dp_navGoTo;

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
	private Label lbl_AbsentTotal, lbl_teacher, lbl_telnrTeacher, lbl_absenterror, lbl_currentWeek;

	@FXML
	private StackPane pane_main;

	private PopupNewTask popup_addTask, popup_addNews;

	private Controller controller;

	private Agenda agenda;

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

		table_joker.setItems(controller.getRequests());
		table_gesuche.setItems(controller.getGesuche());
		table_absent.setItems(controller.getAbsents());

		editAppointment();

		initAbsentView();

		this.agenda = controller.getAgendaView().getAgenda();

		initNavBar();

	}

	private void initNavBar() {
		pane_nav.visibleProperty().bind(btn_schedule.selectedProperty());
		lbl_currentWeek.textProperty().bind(controller.getAgendaView().displayedWeekNumberProperty());
		dp_navGoTo.valueProperty().bindBidirectional(controller.getAgendaView().displayedLocalDateProperty());
	}

	/**
	 * initialize absent box
	 */
	private void initAbsentView() {
		// Todo:
		ObservableList<String> reasons = FXCollections.observableArrayList();
		reasons.addAll("Arzt", "Krank", "Unfall", "Sonstiges");
		cb_reason.setItems(reasons);

		JFXDepthManager.setDepth(box_absent, 1);
		ClassMember teacher = controller.getMemebers().stream().filter(ClassMember::isTeacher)
				.collect(Collectors.toList()).get(0);
		lbl_teacher.setText(teacher.getForename() + " " + teacher.getName());
		lbl_telnrTeacher.setText(teacher.getTelnr());
		lbl_AbsentTotal.textProperty().bind(controller.getTotalAbsentTime());

		lbl_absenterror.setVisible(false);
	}

	private void initPopup() {
		popup_addTask = new PopupNewTask(controller);
		popup_addTask.setPopupContainer(controller.getPane_main());
		popup_addTask.setSource(btn_addTask);

		popup_addNews = new PopupNewTask(controller);
		popup_addNews.setPopupContainer(controller.getPane_main());
		popup_addNews.setSource(btn_addNews);
	}

	/**
	 * cellfactory of tableview. create a custom cell for status column
	 */
	private void initCol() {

		col_jokerDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		col_jokerReason.setCellValueFactory(cellData -> cellData.getValue().reasonProperty());
		col_jokerStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

		col_gesuchDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		col_gesuchReason.setCellValueFactory(cellData -> cellData.getValue().reasonProperty());
		col_gesuchStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

		col_AbsentFrom.setCellValueFactory(cellData -> cellData.getValue().datefromProperty());
		col_AbsentTo.setCellValueFactory(cellData -> cellData.getValue().datetoProperty());
		col_absentReason.setCellValueFactory(cellData -> cellData.getValue().reasonProperty());
		col_absentExcuse.setCellValueFactory(cellData -> cellData.getValue().isExcusedProperty());

		col_absentExcuse.setCellFactory(param -> new TableCell<Absent, Boolean>() {
			@Override
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);
				MaterialDesignIconView icon = null;
				if (!empty && item) {
					icon = new MaterialDesignIconView(MaterialDesignIcon.CHECK);
					;
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
		// Set fixed column widths that resize automatically
		// Values are weighted to be a fraction of a total of 41 (arbitrary)
		col_jokerDate.prefWidthProperty().bind(table_joker.widthProperty().multiply(4.0 / 41.0));
		col_jokerReason.prefWidthProperty().bind(table_joker.widthProperty().multiply(34.8 / 41.0));
		col_jokerStatus.prefWidthProperty().bind(table_joker.widthProperty().multiply(2.0 / 41.0));

		col_gesuchDate.prefWidthProperty().bind(table_gesuche.widthProperty().multiply(4.0 / 41.0));
		col_gesuchReason.prefWidthProperty().bind(table_gesuche.widthProperty().multiply(34.8 / 41.0));
		col_gesuchStatus.prefWidthProperty().bind(table_gesuche.widthProperty().multiply(2.0 / 41.0));

		col_AbsentFrom.prefWidthProperty().bind(table_absent.widthProperty().multiply(9.0 / 41.0));
		col_AbsentTo.prefWidthProperty().bind(table_absent.widthProperty().multiply(9.0 / 41.0));
		col_absentReason.prefWidthProperty().bind(table_absent.widthProperty().multiply(16.7 / 41.0));
		col_absentExcuse.prefWidthProperty().bind(table_absent.widthProperty().multiply(6.0 / 41.0));

		setTooltip();
	}

	/**
	 * add tooltip to absent table when absent is excused tooltip contains
	 * securecode
	 */
	private void setTooltip() {
		table_absent.setRowFactory(tv -> new TableRow<Absent>() {
			private Tooltip tooltip = new Tooltip();

			@Override
			public void updateItem(Absent absent, boolean empty) {
				super.updateItem(absent, empty);
				if (absent == null) {
					setTooltip(null);
				} else if (absent.isExcused()) {
					tooltip.textProperty().bind(absent.secureCodeProperty());
					setTooltip(tooltip);
				}
			}
		});
	}

	/**
	 * "add" button factory
	 */
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

		icon = new MaterialDesignIconView(MaterialDesignIcon.MENU_LEFT);
		icon.setSize("2.5em");
		icon.setFill(Color.GRAY);
		btn_prevweek.setGraphic(icon);
		btn_prevweek.setText(null);

		icon = new MaterialDesignIconView(MaterialDesignIcon.MENU_RIGHT);
		icon.setSize("2.5em");
		icon.setFill(Color.GRAY);
		btn_nextweek.setGraphic(icon);
		btn_nextweek.setText(null);
		//setup listener to switch to agenda when clicked on add news or task
		btn_schedule.selectedProperty().addListener((ChangeListener<Boolean>) ((arg, oldVal, newVal) -> this.switchView()));
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
		if (!tf_reasonGesuch.getText().isEmpty() && !tf_textGesuch.getText().isEmpty()
				&& date_gesuch.getValue() != null) {
			controller.addGesuch(date_gesuch.getValue(), tf_reasonGesuch.getText(), tf_textGesuch.getText());
			date_gesuch.setValue(null);
			tf_reasonGesuch.setText("");
			tf_textGesuch.setText("");
		}
	}

	@FXML
	private void addTask() {
		btn_schedule.setSelected(true);
		popup_addTask.setInfo("", "", LocalDate.now(), LocalTime.now(), null, null, null, false, "Aufgabe");
		popup_addTask.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 50);
	}

	@FXML
	private void addNews() {
		btn_schedule.setSelected(true);
		popup_addNews.setInfo("", "", LocalDate.now(), LocalTime.now(), null, null, null, false, "Information");
		popup_addNews.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 50);
	}

	@FXML
	private void verifyCode() {
		if (!tf_code.getText().isEmpty()) {
			if (controller.verifyCode(tf_code.getText())) {
				tf_code.setText("");
			} else {
				showError("Bestätigungscode ungültig!");
			}

		}
	}

	private void showError(String msg) {
		lbl_absenterror.setText(msg);
		lbl_absenterror.setVisible(true);
		new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
			}
			Platform.runLater(() -> lbl_absenterror.setVisible(false));
		}).start();
	}

	@FXML
	private void save_absent() {
		if (cb_reason.getSelectionModel().getSelectedItem() != null && dp_startdate.getValue() != null
				&& dp_starttime.getTime() != null && dp_enddate.getValue() != null && dp_endtime.getTime() != null
				&& (dp_startdate.getValue().isBefore(dp_enddate.getValue())
						|| dp_startdate.getValue().isEqual(dp_enddate.getValue()))) {
			LocalDateTime from = LocalDateTime.of(dp_startdate.getValue(), dp_starttime.getTime());
			LocalDateTime to = LocalDateTime.of(dp_enddate.getValue(), dp_endtime.getTime());
			controller.addAbsent(cb_reason.getSelectionModel().getSelectedItem(), from, to);
			cb_reason.getSelectionModel().clearSelection();

			dp_enddate.setValue(null);
			dp_startdate.setValue(null);
			dp_endtime.setTime(LocalTime.of(0, 0));
			dp_starttime.setTime(LocalTime.of(0, 0));
		} else {
			showError("Bitte alles ausfüllen!");
		}
	}

	/**
	 * set new callback to custom popup to edit appointment
	 */
	private void editAppointment() {
		controller.getAgendaView().getAgenda().setEditAppointmentCallback(param -> {
			Appointment newA = (Appointment) param;

			LocalDate date = newA.getStartLocalDateTime().toLocalDate();
			LocalTime time = newA.getStartLocalDateTime().toLocalTime();
			popup_addTask.setInfo(newA.getSummary(), newA.getDescription(), date, time, newA.getSubject(),
					newA.getTeacher(), newA, true, "");
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

	@FXML
	private void prevweek() {
		agenda.setDisplayedLocalDateTime(agenda.displayedLocalDateTime().get().minusWeeks(1));
	}

	@FXML
	private void nextweek() {
		agenda.setDisplayedLocalDateTime(agenda.displayedLocalDateTime().get().plusWeeks(1));
	}

	@FXML
	private void gotoDate() {
		agenda.setDisplayedLocalDateTime(LocalDateTime.of(dp_navGoTo.getValue(), LocalTime.now()));
	}

}
