package ClassesDB;

/**
 * Classe Room
 * @author Amandine Vandevoir & Aur�lien Vandaele
 */

public class Room
{
	
	/**
	 * L'identifiant de la room
	 */
	private int idRoom;
	
	/**
	 * Le pseudo du cr�ateur
	 */
	private String createur;
	
	
	/**
	 * Constructeur par d�faut
	 */
	public Room(){
	}
	
	/**
	 * Constructeur parametr�
	 * @param createur le pseudo du cr�ateur
	 */
	 public Room(String createur)
	 {
		 this.createur=createur;
	 }
	
	 /**
	  * Second constructeur parametr�
	  * @param createur le pseudo du cr�ateur
	  * @param idroom l'identifiant de la room
	  */
	 public Room(int idRoom, String createur){
		 this.idRoom=idRoom;
		 this.createur=createur;
	 }
	 
	/**
	 * getter du createur
	 * @return le pseudo du cr�ateur
	 */
	public String getCreateur(){
		return this.createur;
	}
	
	/**
	 * getter de l'idroom
	 * @return l'identifiant de la room
	 */
	public int getIdRoom(){
		return this.idRoom;
	}

	/**
	 * Setter du cr�ateur
	 * @param createur le nouveau createur
	 */
	public void setCreateur(String createur){
		this.createur=createur;
	}
	
	/**
	 * Setter de l'idRoom
	 * @param identifiant le nouvel idRoom
	 */
	public void setIdRoom(int idRoom){
		this.idRoom=idRoom;
	}

	/**
	* m�thode toString
	* @return informations compl�tes de la room
	*/
	@Override
	public String toString() {
		return "Room [idRoom=" + idRoom + ", createur=" + createur + "]";
	}
	
	
}
