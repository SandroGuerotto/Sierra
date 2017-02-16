
package data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * data class for a person instance
 * @author Sandro Guerotto
 * @since 10.01.2017
 * @version 0.1
 */
public class Person {
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty forename;
	private StringProperty birthdate;
	private StringProperty telnr;
	private StringProperty email;
	private StringProperty addText;
	private StringProperty password;
	private StringProperty username;
	private BooleanProperty teacher;  
	private BooleanProperty representative;  
	/**
	 * standard constructor to create a person object
	 * @param id unique integer key
	 * @param name lastname of the person
	 * @param forename firstname of the person
	 * @param birthdate birthdate of the person format dd.mm.yyyy
	 * @param telnr phone number of the person format 000 000 00 00
	 * @param email e-mail address must be valid xyz@provider.domain
	 * @param addText additional text optional
	 * @param isTeacher define person is a teacher
	 * @param isRepresentative define person is a representative. A person can not be Teacher and Representative
	 * @param password custom user password / initial password 
	 * @param username username to log into the application format firstname.lastname
	 */
	public Person(int id, String name, String forename, String birthdate, String telnr, String email, String addText,
			boolean isTeacher,boolean isRepresentative, String password, String username) {
		this.setId(id);;
		this.setName(name);
		this.setForename(forename);
		this.setBirthdate(birthdate);
		this.setTelnr(telnr);
		this.setEmail(email);
		this.setAddText(addText);
		this.setRepresentative(isRepresentative);
		this.setTeacher(isTeacher);
		this.setPassword(password);
		this.setUsername(username);

	}

	// id
	public int getId() {
		return this.idProperty().get();
	}
	public void setId(int id) {
		this.idProperty().set(id);;
	}
	public IntegerProperty idProperty(){
		if (this.id == null) {
            this.id = new SimpleIntegerProperty(this, "id");
        }
        return this.id;
	}
	
	// name
	public String getName() {
		return this.nameProperty().get();
	}
	public void setName(String name) {
		this.nameProperty().set(name);
	}
	public StringProperty nameProperty(){
		if (this.name == null) {
            this.name = new SimpleStringProperty(this, "name");
        }
        return this.name;
	}
	
	//forename
	public String getForename() {
		return this.forenameProperty().get();
	}
	public void setForename(String forename) {
		this.forenameProperty().set(forename);;
	}
	public StringProperty forenameProperty(){
		if (this.forename == null) {
            this.forename = new SimpleStringProperty(this, "forename");
        }
        return this.forename;
	}
	
	//birthdate
	public String getBirthdate() {
		return this.birthdateProperty().get();
	}
	public void setBirthdate(String birthdate) {
		this.birthdateProperty().set(birthdate);
	}
	public StringProperty birthdateProperty(){
		if (this.birthdate == null) {
            this.birthdate = new SimpleStringProperty(this, "birthdate");
        }
        return this.birthdate;
	}
	
	//telnr
	public String getTelnr() {
		return this.telnrProperty().get();
	}
	public void setTelnr(String telnr) {
		this.telnrProperty().set(telnr);
	}
	public StringProperty telnrProperty(){
		if (this.telnr == null) {
            this.telnr = new SimpleStringProperty(this, "telnr");
        }
        return this.telnr;
	}
	
	//Email
	public String getEmail() {
		return this.emailProperty().get();
	}
	public void setEmail(String email) {
		this.emailProperty().set(email);
	}
	public StringProperty emailProperty(){
		if (this.email == null) {
            this.email = new SimpleStringProperty(this, "email");
        }
        return this.email;
	}
	
	//additional Text
	public String getAddText() {
		return this.addTextProperty().get();
	}
	public void setAddText(String addText) {
		this.addTextProperty().set(addText);
	}
	public StringProperty addTextProperty(){
		if (this.addText == null) {
            this.addText = new SimpleStringProperty(this, "addText");
        }
        return this.addText;
	}
	
	//Passwort
	public String getPassword() {
		return this.passwordProperty().get();
	}
	public void setPassword(String password) {
		this.passwordProperty().set(password);
	}
	public StringProperty passwordProperty(){
		if (this.password == null) {
            this.password = new SimpleStringProperty(this, "password");
        }
        return this.password;
	}
	
	//Username
	public String getUsername() {
		return this.usernameProperty().get();
	}
	public void setUsername(String username) {
		this.usernameProperty().set(username);
	}
	public StringProperty usernameProperty(){
		if (this.username == null) {
            this.username = new SimpleStringProperty(this, "username");
        }
        return this.username;
	}
	
	//Teacher
	public boolean isTeacher() {
		return this.teacherProperty().get();
	}
	public void setTeacher(boolean isTeacher) {
		this.teacherProperty().set(isTeacher);
	}
	public BooleanProperty teacherProperty(){
		if (this.teacher == null) {
            this.teacher = new SimpleBooleanProperty(this, "teacher");
        }
        return this.teacher;
	}
	
	//Teacher
	public boolean isRepresentative() {
		return this.representativeProperty().get();
	}
	public void setRepresentative(boolean isRepresentative) {
		this.representativeProperty().set(isRepresentative);
	}
	public BooleanProperty representativeProperty(){
		if (this.representative == null) {
            this.representative = new SimpleBooleanProperty(this, "representative");
        }
        return this.representative;
	}

	@Override
	public String toString() {
		return forename.get() + " " + name.get();
	}
}