package Interfaces;

import data.Person;
/**
 * interface that defines all database method
 *
 * @author Burim Cakolli
 * @since 07.01.2017
 * @version 0.1
 */
public interface DataSaver {
	
	/**
	 * inserts a person into the database
	 * @param person insert object
	 * @return boolean was successful
	 */
	public boolean insertPerson(Person person);
	
}
