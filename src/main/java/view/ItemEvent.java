package view;



import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ItemEvent extends VBox {

	private String title, date, time, descr, type;
	private int id;
	private BorderPane pane_title;
	private FlowPane pane_content;
	private TextArea ta_descr;
	private Label lbl_title, lbl_date, lbl_time;
	private String color;
	

	public ItemEvent(int id, String title, String date, String time, String descr, String type) {
		this.id = id;
		this.title = title;
		this.date  = date;
		this.time  = time;
		this.descr = descr;
		this.type = type;
		setId("item");
		create();
		
		background();
		
	}
	
	private void create(){
		this.setPrefWidth(300);
		this.setPrefHeight(130);
		this.setPadding(new Insets(10));
		
		pane_title = new BorderPane();
		lbl_title = new Label(this.title);
		lbl_title.setUnderline(true);
		lbl_title.setFont(new Font(14));
		lbl_title.setTextFill(Color.web("#000000"));

		
		pane_title.setLeft(lbl_title);
		
		pane_content = new FlowPane();
		pane_content.setHgap(10.00);
		lbl_date = new Label(this.date);
		lbl_time = new Label(this.time);
		lbl_date.setTextFill(Color.web("#000000"));
		lbl_time.setTextFill(Color.web("#000000"));
		pane_content.getChildren().addAll(lbl_date, lbl_time);
		
		ta_descr = new TextArea(this.descr);
		ta_descr.setEditable(false);
		
		getChildren().addAll(pane_title, pane_content, ta_descr);
		


	}
	
	private void background(){
		switch (this.type) {
		case "Test":
			this.color = "#FFBABA";
			break;
		case "News":
			this.color = "#BDE5F8";
			break;
		default:
			this.color = "#DFF2BF";
			break;
		}
		this.setStyle("-fx-background-color: " + getColor() + ";");
		ta_descr.setStyle("-fx-background-color: transparent;");
	}
	public String getColor(){
		return this.color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
