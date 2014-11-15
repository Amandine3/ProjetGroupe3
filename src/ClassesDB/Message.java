package ClassesDB;
import java.sql.Date;
/**
 * Classe Message
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

public class Message
{
	/**
	 * idmessage : identifiant du message
	 * idutilisateurroom : identifiant de la jonction entre la room et l'utilisateur
	 * contenu : contenu du message
	 * date : date à laquelle le message a été posté
	 */
	protected int idmessage, idutilisateurroom;
	protected String contenu;
	protected Date date;
	
	/**
	 * constructeur par défaut
	*/
	public Message()
	{
		
	}
	//=>dans MsgDB fonction arraylist
}
