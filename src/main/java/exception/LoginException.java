package exception;

/**
 * Created by Sandro on 13.12.2016.
 */
public class LoginException extends Exception {

    public LoginException(){
        super("Benutzername oder Passwort falsch");
    }
}
