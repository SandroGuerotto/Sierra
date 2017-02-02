package data;

import javafx.scene.control.Label;
/**
 * Data class for all subject
 *
 * @author Burim Cakolli
 * @since 10.01.2017
 * @version 0.1
 */
public class Subject extends Label {

	private int id1;
	private String name;
	private String colour_hex;
	
	public Subject(int id, String name, String colour_hex){
		this.id1 = id;
		this.name = name;
		this.colour_hex = colour_hex;
		this.setText(name);
	}
	
	public int getId1() {
		return id1;
	}
	public void setId1(int id) {
		this.id1 = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColour_hex() {
		return colour_hex;
	}
	public void setColour_hex(String colour_hex) {
		this.colour_hex = colour_hex;
	}
	
}
