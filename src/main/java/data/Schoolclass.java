package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ClassMember;
/**
 * data class to hold a schoolclass an its members
 *
 * @author Sandro Guerotto
 * @since 14.01.2017
 * @version 0.1
 */
public class Schoolclass {

    @SuppressWarnings("unused")
	private int id;
    private int studentcount;

    private ObservableList<ClassMember> memebers = FXCollections.observableArrayList();
    @SuppressWarnings("unused")
	private ObservableList<Person> people = FXCollections.observableArrayList();

    private String classname;
	/**
	 * default constructor to create an schoolclass
	 * @param id unique integer key
	 * @param studentcount how many students are in the class
	 * @param classname name of the schoolclass
	 */
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
