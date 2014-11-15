package ClassesDB;
import java.sql.Date;
/**
 * Classe Message
 * @author Amandine Vandevoir & Aur�lien Vandaele
 */

public class Message
{
	/**
	 * idmessage : identifiant du message
	 * idutilisateurroom : identifiant de la jonction entre la room et l'utilisateur
	 * contenu : contenu du message
	 * date : date � laquelle le message a �t� post�
	 */
	protected int idmessage, idutilisateurroom;
	protected String contenu;
	protected Date date;
	
	/**
	 * constructeur par d�faut
	*/
	public Message()
	{
		
	}
	//=>dans MsgDB fonction arraylist
}
