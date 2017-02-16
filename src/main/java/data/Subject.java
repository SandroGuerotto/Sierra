package data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
/**
 * Data class for all subject
 *
 * @author Burim Cakolli
 * @since 10.01.2017
 * @version 0.1
 */
public class Subject extends Label {

	private IntegerProperty id1;
	private StringProperty name;
	private StringProperty color_hex;
	
	public Subject(int id, String name, String colour_hex){
		this.setId1(id);
		this.setName(name);
		this.setColor_hex(colour_hex);
		this.textProperty().bind(this.nameProperty());
	}
	
	// id
	public int getId1() {
		return this.id1Property().get();
	}
	public void setId1(int id) {
		this.id1Property().set(id);;
	}
	public IntegerProperty id1Property(){
		if (this.id1 == null) {
            this.id1 = new SimpleIntegerProperty(this, "id1");
        }
        return this.id1;
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
	
	// color_hex
	public String getColor_hex() {
		return this.color_hexProperty().get();
	}
	public void setColor_hex(String hex) {
		this.color_hexProperty().set(hex);
	}
	public StringProperty color_hexProperty(){
		if (this.color_hex == null) {
            this.color_hex = new SimpleStringProperty(this, "color_hex");
        }
        return this.color_hex;
	}
	
}
