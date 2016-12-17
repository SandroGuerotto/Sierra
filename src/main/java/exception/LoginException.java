package exception;


public class LoginException extends Exception {

    public LoginException(){
        super("Benutzername oder Passwort falsch");
    }
}
