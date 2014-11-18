package ClassesDB;

import java.sql.Connection;


/**
 * Classe UtilisateurRoomDB (classe de mappage poo-relationnel UtilisateurRoom)
 * @author Amandine Vandevoir & Aur�lien Vandaele
 * @see UtilisateurRoom
 */

public class UtilisateurRoomDB extends UtilisateurRoom implements CRUD{
	
    /**
    *Connexion � la base de donn�es partag�e entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;


	/**
	* m�thode statique permettant de partager la connexion entre toutes les instances de
	* UtilisateurRoomDB
	* @param nouvelledbConnect connexion � la base de donn�es
	*/
	public static void setConnection(Connection nouvelledbConnect)
	{
	    dbConnect=nouvelledbConnect;
	}
	
    
   /**
   * constructeur par d�faut
   */
	public UtilisateurRoomDB()
	{
		
	}
	
	/**
	* enregistrement d'un nouvel utilisateurRoom dans la base de donn�es
	* @throws Exception erreur lors de la cr�ation
	*/
	public void create(){
		
	}
	
	/**
	* r�cup�ration des donn�es d'un utilisateurRoom sur base de son 
	* @throws Exception code inconnu
	*/
	public void read(){
		
	}
	
	/**
	 * M�thode update non n�cessaire (mais ici car implements CRUD)
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