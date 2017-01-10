/**
 * @author :   Burim Cakolli
 * Turns coffee & pizza into Software
 * Created          :   07.01.2017
 * Project          :   Sierra
 * Package          :   Interfaces
 * @version 		:   1.0
 * LastUpdated      :   07.01.2017 / by Burim Cakolli
 * Description      :
 * 
 */
package Interfaces;

import data.Absent;
import data.Gesuch;
import data.Mark;
import data.Person;
import data.Request;
import data.Schoolclass;
import javafx.collections.ObservableList;
import view.ClassMember;
import view.ItemEvent;

public interface DataSaver {
	/* Struktur:
	 * -Modelklasse
	 * --insert_class
	 * --update_class
	 * --get_class
	 * --getAll
	 * --delete_class
	 */
	
	//-class_Request
	public Integer insert_Request(Request request);
	public void update_Request(Request request);
	public Request get_Request(int id);
	public ObservableList<Request> getAllRequests(int id_person);
	public void delete_Request(Request request);
	//-End Request
	
	//-class_Gesuch
	public Integer insert_Gesuch(Gesuch gesuch);
	public void update_Gesuch(Gesuch gesuch);
	public Gesuch get_Gesuch(int id);
	public ObservableList<Gesuch> getAllGesuche(int id_person);
	public void delete_Gesuch(Gesuch gesuch);
	//-End Gesuch
	
	//-class_ItemEvent
	public Integer insert_ItemEvent(ItemEvent event);
	public void update_ItemEvent(ItemEvent event);
	public ItemEvent get_ItemEvent(int id);
	public ObservableList<ItemEvent> getAllEvents();
	public void delete_ItemEvent(ItemEvent event);
	//-End ItemEvent	
	
	//-class_Mark
	public Integer insert_Mark(Mark mark);
	public void update_Mark(Mark mark);
	public Mark get_Mark(int id);
	public ObservableList<Mark> getAllMarks(int id_person);
	public void delete_Mark(Mark mark);
	//-End Mark
	
	//-class_SchoolClass
	public Integer insert_Schoolclass(Schoolclass schoolClass);
	public void update_Schoolclass(Schoolclass schoolClass);
	public Schoolclass get_Schoolclass(int id);
	public ObservableList<Schoolclass> getAllSchoolclasses();
	public void delete_Schoolclass(Schoolclass schoolClass);
	//-End SchoolClass
	
	//-class_ClassMember
	public Integer insert_ClassMember(Person person, Schoolclass sclass);
	public void update_ClassMember(Person person, Schoolclass sclass);
	public ClassMember get_ClassMember(Person person, Schoolclass sclass);
	public ObservableList<ClassMember> getAllTeachers();
	public ObservableList<ClassMember> getAllPeople();	
	public void delete_ClassMember(Person person, Schoolclass sclass);
	//-End ClassMember
	
	//-class_Absent
	public Integer insert_Absent(Absent absent); 
	public void update_Absent(Absent absent);
	public Absent get_Absent(int id);
	public ObservableList<Absent> getAllAbsents(int id_person);
	public void delete_Absent(Absent absent);
	//-End Absent
			 
}//-class
