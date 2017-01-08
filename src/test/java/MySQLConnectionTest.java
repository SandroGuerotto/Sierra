import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import model.MySQLConnection;

/**
 * @author :   Burim Cakolli
 * Turns coffee & pizza into Software
 * Created          :   07.01.2017
 * Project          :   Sierra
 * Package          :   
 * @version 		:   1.0
 * LastUpdated      :   07.01.2017 / by Burim Cakolli
 * Description      :
 * 
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
