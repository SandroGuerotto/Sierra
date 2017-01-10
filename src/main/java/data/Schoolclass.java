package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ClassMember;

public class Schoolclass {

    private int id;
    private int studentcount;

    private ObservableList<ClassMember> memebers = FXCollections.observableArrayList();
    private ObservableList<Person> people = FXCollections.observableArrayList();

    private String classname;

    public Schoolclass(int id, int studentcount, String classname) {
        this.id = id;
        this.studentcount = studentcount;
        this.classname = classname;
    }

    public void setMemebers(ObservableList<ClassMember> memebers){
        this.memebers = memebers;
    }

    public int getStudentcount() {
        return studentcount;
    }

    public ObservableList<ClassMember> getMemebers() {
        return memebers;
    }

    public String getClassname() {
        return classname;
    }
}
