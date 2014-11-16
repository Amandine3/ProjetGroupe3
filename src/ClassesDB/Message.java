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
	
	   /**
     * constructeur param�tr�
     * @param idmessage identifiant du message affect� par la base de donn�es
     * @param contenu contenu du message
     * @param date date � laquelle le message a �t� post�
     * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
     */
    public Message(int idmessage, String contenu, Date date, int idutilisateurroom)
    {
        this.idmessage=idmessage;
        this.contenu=contenu;
        this.date=date;
        this.idutilisateurroom=idutilisateurroom;
    }

    /**
     * getter idmessage
     * @return identifiant du message
     */
	public int getIdmessage() {
		return idmessage;
	}

    /**
     * setter idmessage
     * @param idmessage identifiant du message
     */
	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}

	 /**
     * getter idutilisateurroom
     * @return identifiant de la jonction entre la room et l'utilisateur
     */
	public int getIdutilisateurroom() {
		return idutilisateurroom;
	}
	
    /**
     * setter idutilisateurroom
     * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
     */
	public void setIdutilisateurroom(int idutilisateurroom) {
		this.idutilisateurroom = idutilisateurroom;
	}

	 /**
     * getter contenu
     * @return contenu du message
     */
	public String getContenu() {
		return contenu;
	}

    /**
     * setter contenu
     * @param contenu contenu du message
     */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	 /**
     * getter date
     * @return date � laquelle le message a �t� post�
     */
	public Date getDate() {
		return date;
	}

    /**
     * setter date
     * @param date date � laquelle le message a �t� post�
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	* m�thode toString
	* @return informations compl�tes du message
	*/
	@Override
	public String toString() {
		return "Message [idmessage=" + idmessage + ", idutilisateurroom="
				+ idutilisateurroom + ", contenu=" + contenu + ", date=" + date
				+ "]";
	}
}
