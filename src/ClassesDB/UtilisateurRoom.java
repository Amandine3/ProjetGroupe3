package ClassesDB;

/**
 * Classe UtilisateurRoom
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

public class UtilisateurRoom
{

	/**
	 * idUtilisateurRoom identifiant de la jonction entre la room et l'utilisateur
	 * idRoom identifiant de la room
	 */
	protected int idUtilisateurRoom, idRoom;
	 
	/**
	 * pseudo pseudo de l'utilisateur
	 */
	protected String pseudo;
	
	/**
	* constructeur par défaut
	*/
	public UtilisateurRoom(){
	}
	
	/**
	 * Constructeur paramétré
	 * @param idUtilisateurRoom identifiant de la jonction entre la room et l'utilisateur
	 * @param idRoom identifiant de la room
	 * @param pseudo pseudo de l'utilisateur
	 */
	public UtilisateurRoom(int idUtilisateurRoom, int idRoom, String pseudo){
		this.idUtilisateurRoom=idUtilisateurRoom;
		this.idRoom=idRoom;
		this.pseudo=pseudo;
	}
	
	
    /**
     * getter pseudo
     * @return pseudo de l'utilisateur
     */
	public String getPseudo(){
		return this.pseudo;
	}
	
    /**
     * getter idUtilisateurRoom
     * @return identifiant de la jonction entre la room et l'utilisateur
     */
	public int getIdUtilisateurRoom(){
		return this.idUtilisateurRoom;
	}
	
    /**
     * getter idRoom
     * @return identifiant de la room 
     */
	public int getIdRoom(){
		return this.idRoom;
	}
	
    /**
     * setter pseudo
     * @param pseudo pseudo de l'utilisateur
     */
	public void setPseudo(String pseudo){
		this.pseudo=pseudo;
	}
	
    /**
     * setter idUtilisateurRoom
     * @param idUtilisateurRoom identifiant de la jonction entre la room et l'utilisateur
     */
	public void setIdUtilisateurRoom(int idUtilisateurRoom){
		this.idUtilisateurRoom=idUtilisateurRoom;
	}
	
    /**
     * setter idRoom
     * @param idRoom identifiant de la room 
     */
	public void setIdRoom(int idRoom){
		this.idRoom=idRoom;
	}

	/**
	* méthode toString
	* @return informations complètes de utilisateurRoom
	*/
	@Override
	public String toString() {
		return "UtilisateurRoom [idUtilisateurRoom=" + idUtilisateurRoom
				+ ", idRoom=" + idRoom + ", pseudo=" + pseudo + "]";
	}

	
}
