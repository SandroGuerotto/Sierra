package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.Queries;
import Interfaces.DataSaver;

import data.Person;
/**
 * MySQL connector that connects applationtion with our database server. 
 * implements DataSaver
 *
 * @author Burim Cakolli
 * @since 07.01.2017
 * @version 0.1
 */
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
		    MySQLConnection.conn =
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
		    stmt = MySQLConnection.conn.prepareStatement(Queries.getQuery("get_AllRollen"));
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
	@SuppressWarnings("unused")
	@Override
	public boolean insertPerson(Person person) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		
		
		return result;
	}

	
		
}//-class
