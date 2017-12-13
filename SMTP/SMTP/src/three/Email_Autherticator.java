package three;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/*
 * »œ÷§∆˜¿‡
 */
public class Email_Autherticator extends Authenticator {
	private String username;
	private String password;
	public Email_Autherticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(username, password);  
    }
}
