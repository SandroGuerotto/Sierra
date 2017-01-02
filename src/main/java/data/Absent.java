package data;


import javafx.beans.property.*;

public class Absent {

    private IntegerProperty id;
    private StringProperty datefrom;
    private StringProperty dateto;
    private StringProperty reason;
    private BooleanProperty isExcused;

    public Absent(int id, String datefrom, String dateto, String reason, boolean isExcused) {
        this.setID(id);
        this.setDatefrom(datefrom);
        this.setDateto(dateto);
        this.setReason(reason);
        this.setIsExcused(isExcused);
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

    //datefrom
    public void setDatefrom(String value) {
        this.datefromProperty().set(value);
    }

    public String getDatefrom() {
        return this.datefromProperty().get();
    }

    public StringProperty datefromProperty() {
        if (this.datefrom == null) {
            this.datefrom = new SimpleStringProperty(this, "datefrom");
        }
        return this.datefrom;
    }

    //dateto
    public void setDateto(String value) {
        this.datetoProperty().set(value);
    }

    public String getDateto() {
        return this.datetoProperty().get();
    }

    public StringProperty datetoProperty() {
        if (this.dateto == null) {
            this.dateto = new SimpleStringProperty(this, "dateto");
        }
        return this.dateto;
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

    //isExcused
    public void setIsExcused(boolean value) {
        this.isExcusedProperty().set(value);
    }

    public boolean getIsExcused() {
        return this.isExcusedProperty().get();
    }

    public BooleanProperty isExcusedProperty() {
        if (this.isExcused == null) {
            this.isExcused = new SimpleBooleanProperty(this, "isExcused");
        }
        return this.isExcused;
    }

}
