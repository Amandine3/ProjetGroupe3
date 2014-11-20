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
	
	public UtilisateurRoomDB(int idUtilisateurRoom, int idRoom, String pseudo){
		super(idUtilisateurRoom, idRoom, pseudo);
	}
	
	/**
	* enregistrement d'un nouvel utilisateurRoom dans la base de données
	* @throws Exception erreur lors de la création
	*/
	public void create() throws Exception{
		CallableStatement cstmt=null;
		try
		{          
			 String query="call createUtilisateurRoom(?,?)"; 
			 cstmt = dbConnect.prepareCall(query);
			 cstmt.setInt(1, this.idRoom);
			 cstmt.setString(2,this.pseudo);
			cstmt.executeUpdate();
		}
		catch(Exception e)
		{
			throw new Exception("Erreur lors de la création"+e.getMessage());
		}
		finally
		{
			try
			{
				cstmt.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	/**
	* récupération des données d'un utilisateurRoom sur base de son 
	* @throws Exception code inconnu
	*/
	public void read() throws Exception{
		String query = "SELECT * FROM UtilisateurRoom WHERE idUtilisateurRoom=?";
		PreparedStatement cstmt=null;
		try
		{
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1,this.idUtilisateurRoom);
			ResultSet rs=cstmt.executeQuery(query);
			while(rs.next())
			{
				this.idRoom=rs.getInt("IDROOM");
				this.pseudo=rs.getString("PSEUDO");
			}
		
		}
		catch(Exception e)
		{
			throw new Exception("Erreur lors de la lecture"+e.getMessage());
		}
		finally
		{
			try
			{
			   cstmt.close();
			}
			catch (Exception e)
			{
				
			}
		}
	
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
	public void delete() throws Exception{
		CallableStatement cstmt=null;
		try
		{          
			 String query="call deleteUtilisateurRoom(?,?)"; 
			 cstmt = dbConnect.prepareCall(query);
			 cstmt.setInt(1, this.idRoom);
			 cstmt.setString(2,this.pseudo);
			cstmt.executeUpdate();
		}
		catch(Exception e)
		{
			throw new Exception("Erreur lors de la suppression"+e.getMessage());
		}
		finally
		{
			try
			{
				cstmt.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	public static ArrayList<int> readRoom(String pseudo) throws Exception{
		PreparedStatement cstmt=null;
		ArrayList<int> resultat=new ArrayList();
		try{
			string query="select idRoom from UtilisateurRoom where pseudo=?";
			cstmt=dbConnect.prepareCall(query);
			cstmt.setString(1, pseudo);
			ResultSet rs=cstmt.executeQuery(query);
			while(rs.next())
			{
				resultat.add(rs.getInt("IDROOM"));
			}
			return resultat;
		}
		catch(Exception e)
		{
			throw new Exception("Erreur lors de la lecture"+e.getMessage());
		}
		finally
		{
			try
			{
			   cstmt.close();
			}
			catch (Exception e)
			{
				
			}
		}
}