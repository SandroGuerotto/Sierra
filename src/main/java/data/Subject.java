/**
 * @author :   Burim Cakolli
 * Turns coffee & pizza into Software
 * Created          :   10.01.2017
 * Project          :   Sierra
 * Package          :   data
 * @version 		:   1.0
 * LastUpdated      :   10.01.2017 / by Burim Cakolli
 * Description      :
 * 
 */
package data;

public class Subject {
	private int id;
	private String name;
	private String colour_hex;
	
	public Subject(int id, String name, String colour_hex){
		this.id = id;
		this.name = name;
		this.colour_hex = colour_hex;
	}//-Fach
	
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
	public String getColour_hex() {
		return colour_hex;
	}
	public void setColour_hex(String colour_hex) {
		this.colour_hex = colour_hex;
	}
	
}
