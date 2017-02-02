package exception;

/**
 * The class LoginException extends Exception that indicates conditions 
 * that a reasonable application might want to catch.
 * 
 * @author Sandro Guerotto
 * @since 17.12.2016
 * @version 0.1
 *
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException(){
        super("Benutzername oder Passwort falsch");
    }
}
