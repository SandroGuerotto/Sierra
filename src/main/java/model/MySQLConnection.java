/**
 * @author :   Burim Cakolli
 * My Applications never have bugs. They just create new random features..
 * Created          :   07.01.2017
 * Project          :   Sierra
 * Package          :   model
 * @version 		:   1.0
 * LastUpdated      :   07.01.2017 / by Burim Cakolli
 * Description      :
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.Queries;
import view.ClassMember;
import view.ItemEvent;
import Interfaces.DataSaver;
import data.Absent;
import data.Gesuch;
import data.Mark;
import data.Person;
import data.Request;
import data.Schoolclass;
import javafx.collections.ObservableList;

public class MySQLConnection implements DataSaver{
	private static Connection conn = null; //Connection to DB
	private static String url = "jdbc:mysql://coiffeur-alan.ch.w012d764.kasserver.com:3306/d024b04f"; //Url zum DB-Server
	private static String user = "d024b04f"; //Benutzername
	private static String password = "root1234";// Datenbankpasswort
	 
	public MySQLConnection(){
		this.startConnection();
	}//-MySQLConnection
	
	private void startConnection(){
		try {
		    this.conn =
		       DriverManager.getConnection(url,user,password);
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}//-startConnection
	
	public ArrayList<String> getAllRollen(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();
		
		try {
		    stmt = this.conn.prepareStatement(Queries.getQuery("get_AllRollen"));
		    rs = stmt.executeQuery();
		    while(rs.next()){
		    	result.add(rs.getString(2));		
		    }//-while
			stmt.close();
			return result;
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insertPerson(data.Person)
	 */
	@Override
	public boolean insertPerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

		
}//-class
