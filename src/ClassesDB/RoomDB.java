package ClassesDB;

import java.sql.Connection;


/**
 * Classe RoomDB (classe de mappage poo-relationnel Room)
 * @author Amandine Vandevoir & Aur�lien Vandaele
 * @see Room
 */

public class RoomDB extends Room implements CRUD
{
	
    /**
    *Connexion � la base de donn�es partag�e entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
    
	/**
	* m�thode statique permettant de partager la connexion entre toutes les instances de
	* RoomDB
	* @param nouvelledbConnect connexion � la base de donn�es
	*/
	public static void setConnection(Connection nouvelledbConnect)
	{
	    dbConnect=nouvelledbConnect;
	}
	
	
   /**
   * constructeur par d�faut
   */
	public RoomDB()
	{
		
	}
	public RoomDB(String createur){
		super(createur);
	}
	public RoomDB(int idRoom, String createur){
		super(idRoom, createur);
	}
	public void create(){
		String query= ";      
	}
	public void read(){
		
	}
	public void update()
	{
		// pas utilisable dans le cadre de cette application
	}
	public void delete(){
		
	}
}
