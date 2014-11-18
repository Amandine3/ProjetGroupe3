package ClassesDB;

import java.sql.Connection;


/**
 * Classe UtilisateurRoomDB (classe de mappage poo-relationnel UtilisateurRoom)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see UtilisateurRoom
 */

public class UtilisateurRoomDB extends UtilisateurRoom implements CRUD{
	
    /**
    *Connexion à la base de données partagée entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;


	/**
	* méthode statique permettant de partager la connexion entre toutes les instances de
	* UtilisateurRoomDB
	* @param nouvelledbConnect connexion à la base de données
	*/
	public static void setConnection(Connection nouvelledbConnect)
	{
	    dbConnect=nouvelledbConnect;
	}
	
    
   /**
   * constructeur par défaut
   */
	public UtilisateurRoomDB()
	{
		
	}
	
	/**
	* enregistrement d'un nouvel utilisateurRoom dans la base de données
	* @throws Exception erreur lors de la création
	*/
	public void create(){
		
	}
	
	/**
	* récupération des données d'un utilisateurRoom sur base de son 
	* @throws Exception code inconnu
	*/
	public void read(){
		
	}
	
	/**
	 * Méthode update non nécessaire (mais ici car implements CRUD)
	 */
	public void update()
	{
		// pas utilisable dans le cadre de cette application
	}
	
    /**
     * suppression de l'utilisateurroom sur base de son 
     * @throws Exception erreur lors de la suppression
     */
	public void delete(){
		
	}
}