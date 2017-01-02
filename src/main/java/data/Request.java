package data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Request {
    private IntegerProperty id;
    private StringProperty date;
    private StringProperty reason;
    private StringProperty status;

    public Request(int id, String date, String reason, String status) {
        this.setID(id);
        this.setDate(date);
        this.setReason(reason);
        this.setStatus(status);
    }

    //id
    public void setID(int value) {
        this.idProperty().set(value);
    }

    public int getID() {
        return this.idProperty().get();
    }

    public IntegerProperty idProperty() {
        if (this.id == null) {
            this.id = new SimpleIntegerProperty(this, "id");
        }
        return this.id;
    }

    //date
    public void setDate(String value) {
        this.dateProperty().set(value);
    }

    public String getDate() {
        return this.dateProperty().get();
    }

    public StringProperty dateProperty() {
        if (this.date == null) {
            this.date = new SimpleStringProperty(this, "date");
        }
        return this.date;
    }

    //reason
    public void setReason(String value) {
        this.reasonProperty().set(value);
    }

    public String getReason() {
        return this.reasonProperty().get();
    }

    public StringProperty reasonProperty() {
        if (this.reason == null) {
            this.reason = new SimpleStringProperty(this, "reason");
        }
        return this.reason;
    }

    //status
    public void setStatus(String value) {
        this.statusProperty().set(value);
    }

    public String getStatus() {
        return this.statusProperty().get();
    }

    public StringProperty statusProperty() {
        if (this.status == null) {
            this.status = new SimpleStringProperty(this, "status");
        }
        return this.status;
    }
}
