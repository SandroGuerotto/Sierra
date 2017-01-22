package data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Data class to display content in TableView
 * @author Sandro Guerotto
 * @since 01.01.2017
 * @version 0.1
 */
public class Mark {
    private IntegerProperty id;
    private StringProperty date;
    private StringProperty subject;
    private StringProperty theme;
    private StringProperty semester;
    private StringProperty mark;
    private StringProperty avgClass;

	/**
	 * 
	 * @param id unique key
	 * @param date date of exam
	 * @param subject in which lesson 
	 * @param theme title of exam
	 * @param semester in which semester
	 * @param mark actual mark
	 * @param avgClass average mark in class
	 */
    public Mark(int id, String date, String subject, String theme, String semester, String mark, String avgClass) {
        this.setID(id);
        this.setDate(date);
        this.setSubject(subject);
        this.setTheme(theme);
        this.setSemester(semester);
        this.setMark(mark);
        this.setAvgClass(avgClass);
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

    //subject
    public void setSubject(String value) {
        this.subjectProperty().set(value);
    }

    public String getSubject() {
        return this.subjectProperty().get();
    }

    public StringProperty subjectProperty() {
        if (this.subject == null) {
            this.subject = new SimpleStringProperty(this, "subject");
        }
        return this.subject;
    }

    //theme
    public void setTheme(String value) {
        this.themeProperty().set(value);
    }

    public String getTheme() {
        return this.themeProperty().get();
    }

    public StringProperty themeProperty() {
        if (this.theme == null) {
            this.theme = new SimpleStringProperty(this, "theme");
        }
        return this.theme;
    }

    //semester
    public void setSemester(String value) {
        this.semesterProperty().set(value);
    }

    public String getSemester() {
        return this.semesterProperty().get();
    }

    public StringProperty semesterProperty() {
        if (this.semester == null) {
            this.semester = new SimpleStringProperty(this, "semester");
        }
        return this.semester;
    }

    //mark
    public void setMark(String value) {
        this.markProperty().set(value);
    }

    public String getMark() {
        return this.markProperty().get();
    }

    public StringProperty markProperty() {
        if (this.mark == null) {
            this.mark = new SimpleStringProperty(this, "mark");
        }
        return this.mark;
    }

    //avgClass
    public void setAvgClass(String value) {
        this.avgClassProperty().set(value);
    }

    public String getAvgClass() {
        return this.avgClassProperty().get();
    }

    public StringProperty avgClassProperty() {
        if (this.avgClass == null) {
            this.avgClass = new SimpleStringProperty(this, "avgClass");
        }
        return this.avgClass;
    }

}
