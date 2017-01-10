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

public class Roll {
	private int id;
	private String name;
	
	public Roll(int id, String name){
		this.id = id;
		this.name = name;
	}//-Roll
	
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
	
}//-class
