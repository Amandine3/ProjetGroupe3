package ClassesDB;

/**
 * Classe Room
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

public class Room
{
	
	/**
	 * L'identifiant de la room
	 */
	private int idRoom;
	
	/**
	 * Le pseudo du créateur
	 */
	private String createur;
	
	
	/**
	 * Constructeur par défaut
	 */
	public Room(){
	}
	
	/**
	 * Constructeur parametré
	 * @param createur le pseudo du créateur
	 */
	 public Room(String createur)
	 {
		 this.createur=createur;
	 }
	
	 /**
	  * Second constructeur parametré
	  * @param createur le pseudo du créateur
	  * @param idroom l'identifiant de la room
	  */
	 public Room(int idRoom, String createur){
		 this.idRoom=idRoom;
		 this.createur=createur;
	 }
	 
	/**
	 * getter du createur
	 * @return le pseudo du créateur
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
	 * Setter du créateur
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
	* méthode toString
	* @return informations complètes de la room
	*/
	@Override
	public String toString() {
		return "Room [idRoom=" + idRoom + ", createur=" + createur + "]";
	}
	
	
}
