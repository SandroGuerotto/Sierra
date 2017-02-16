package data;


import javafx.beans.property.*;
/**
 * Data class to display content in TableView
 *
 * @author Sandro Guerotto
 * @since 02.01.2017
 * @version 0.1
 */
public class Absent {

    private IntegerProperty id;
    private StringProperty datefrom;
    private StringProperty dateto;
    private StringProperty reason;
    private BooleanProperty isExcused;
    private StringProperty secureCode;
	/**
	 * default constructor to create an absent
	 * @param id unique integer key
	 * @param datefrom  start date of absent
	 * @param dateto  end date of absent
	 * @param reason additional text for absent
	 * @param isExcused person is excused
	 */
    public Absent(int id, String datefrom, String dateto, String reason, boolean isExcused, String secureCode) {
        this.setID(id);
        this.setDatefrom(datefrom);
        this.setDateto(dateto);
        this.setReason(reason);
        this.setIsExcused(isExcused);
        this.setSecureCode(secureCode);
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
    
    //secureCode
    public void setSecureCode(String value) {
        this.secureCodeProperty().set(value);
    }

    public String getSecureCode() {
        return this.secureCodeProperty().get();
    }

    public StringProperty secureCodeProperty() {
        if (this.secureCode == null) {
            this.secureCode = new SimpleStringProperty(this, "secureCode");
        }
        return this.secureCode;
    }

}
