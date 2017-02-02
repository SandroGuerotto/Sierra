package mysqltests;
import org.junit.Test;
import model.MySQLConnection;


/**
 * unit test class to test queries
 *
 * @author Burim Cakolli
 * @since 07.01.2017
 * @version 0.1
 */

public class MySQLConnectionTest {
	//-Zu gebrauchende Instanzen für das Testing
	private MySQLConnection mysql = new MySQLConnection();
	//-End Instanzen
	
	@Test
	public void getRollen() {
		System.out.println("Ergebnis:");
		System.out.println(this.mysql.getAllRollen());
	}
}
