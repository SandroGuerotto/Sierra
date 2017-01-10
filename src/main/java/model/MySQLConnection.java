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
	 * @see Interfaces.DataSaver#insert_Request(data.Request)
	 */
	@Override
	public Integer insert_Request(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_Request(data.Request)
	 */
	@Override
	public void update_Request(Request request) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_Request(int)
	 */
	@Override
	public Object get_Request(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllRequests(int)
	 */
	@Override
	public ObservableList<Request> getAllRequests(int id_person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_Request(data.Request)
	 */
	@Override
	public void delete_Request(Request request) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_Gesuch(data.Gesuch)
	 */
	@Override
	public Integer insert_Gesuch(Gesuch gesuch) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_Gesuch(data.Gesuch)
	 */
	@Override
	public void update_Gesuch(Gesuch gesuch) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_Gesuch(int)
	 */
	@Override
	public Object get_Gesuch(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllGesuche(int)
	 */
	@Override
	public ObservableList<Gesuch> getAllGesuche(int id_person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_Gesuch(data.Gesuch)
	 */
	@Override
	public void delete_Gesuch(Gesuch gesuch) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_ItemEvent(view.ItemEvent)
	 */
	@Override
	public Integer insert_ItemEvent(ItemEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_ItemEvent(view.ItemEvent)
	 */
	@Override
	public void update_ItemEvent(ItemEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_ItemEvent(int)
	 */
	@Override
	public Object get_ItemEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllEvents()
	 */
	@Override
	public ObservableList<ItemEvent> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_ItemEvent(view.ItemEvent)
	 */
	@Override
	public void delete_ItemEvent(ItemEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_Mark(data.Mark)
	 */
	@Override
	public Integer insert_Mark(Mark mark) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_Mark(data.Mark)
	 */
	@Override
	public void update_Mark(Mark mark) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_Mark(int)
	 */
	@Override
	public Object get_Mark(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllMarks(int)
	 */
	@Override
	public ObservableList<Mark> getAllMarks(int id_person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_Mark(data.Mark)
	 */
	@Override
	public void delete_Mark(Mark mark) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_Schoolclass(data.Schoolclass)
	 */
	@Override
	public Integer insert_Schoolclass(Schoolclass schoolClass) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_Schoolclass(data.Schoolclass)
	 */
	@Override
	public void update_Schoolclass(Schoolclass schoolClass) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_Schoolclass(int)
	 */
	@Override
	public Object get_Schoolclass(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllSchoolclasses()
	 */
	@Override
	public ObservableList<Schoolclass> getAllSchoolclasses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_Schoolclass(data.Schoolclass)
	 */
	@Override
	public void delete_Schoolclass(Schoolclass schoolClass) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_ClassMember(view.ClassMember)
	 */
	@Override
	public Integer insert_ClassMember(ClassMember classMember) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_ClassMember(view.ClassMember)
	 */
	@Override
	public void update_ClassMember(ClassMember classMember) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_ClassMember(int)
	 */
	@Override
	public Object get_ClassMember(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllTeachers()
	 */
	@Override
	public ObservableList<ClassMember> getAllTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllPeople()
	 */
	@Override
	public ObservableList<ClassMember> getAllPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_ClassMember(view.ClassMember)
	 */
	@Override
	public void delete_ClassMember(ClassMember classMember) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#insert_Absent(data.Absent)
	 */
	@Override
	public Integer insert_Absent(Absent absent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#update_Absent(data.Absent)
	 */
	@Override
	public void update_Absent(Absent absent) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#get_Absent(int)
	 */
	@Override
	public Object get_Absent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#getAllAbsents(int)
	 */
	@Override
	public ObservableList<Absent> getAllAbsents(int id_person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Interfaces.DataSaver#delete_Absent(data.Absent)
	 */
	@Override
	public void delete_Absent(Absent absent) {
		// TODO Auto-generated method stub
		
	}


		
}//-class
