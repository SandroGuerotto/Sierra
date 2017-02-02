
package data;

/**
 * data class for a person instance
 * @author Sandro Guerotto
 * @since 10.01.2017
 * @version 0.1
 */
public class Person {
	private int id;
	private String name;
	private String forename;
	private String birthdate;
	private String telnr;
	private String email;
	private String addText;
	private String password;
	private String username;
	private boolean isTeacher = false;  // teacher
	private boolean isRepresentative = false;  //Klassensprecher
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
		this.id = id;
		this.name = name;
		this.forename = forename;
		this.birthdate = birthdate;
		this.telnr = telnr;
		this.email = email;
		this.addText = addText;
		this.isRepresentative = isRepresentative;
		this.isTeacher	= isTeacher;
		this.password = password;
		this.username = username;
	}
	
	public boolean isTeacher() {
		return isTeacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

	public boolean isRepresentative() {
		return isRepresentative;
	}

	public void setRepresentative(boolean isRepresentative) {
		this.isRepresentative = isRepresentative;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getTelnr() {
		return telnr;
	}
	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddText() {
		return addText;
	}
	public void setAddText(String addText) {
		this.addText = addText;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return forename + " " + name;
	}
}
