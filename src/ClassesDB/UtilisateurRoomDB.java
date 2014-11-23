package ClassesDB;

import java.sql.*;
import java.util.ArrayList;


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
	 * Constructeur paramétré
	 * @param idUtilisateurRoom identifiant de la jonction entre la room et l'utilisateur
	 * @param idRoom identifiant de la room
	 * @param pseudo pseudo de l'utilisateur
	 */
	public UtilisateurRoomDB(int idUtilisateurRoom, int idRoom, String pseudo){
		super(idUtilisateurRoom, idRoom, pseudo);
	}
	
	
	public UtilisateurRoomDB(int idRoom, String pseudo){
		super(-1, idRoom, pseudo);
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
			 cstmt.setString(2, this.pseudo);
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
	* récupération des données d'un utilisateurRoom sur base de son identifiant
	* @throws Exception erreur lors de la lecture
	*/
	public void read() throws Exception{
		String query = "SELECT * FROM UtilisateurRoom WHERE idUtilisateurRoom=?";
		PreparedStatement cstmt=null;
		try
		{
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1,this.idUtilisateurRoom);
			ResultSet rs=cstmt.executeQuery();
			if(rs.isBeforeFirst())
        	{
				while(rs.next())
				{
					this.idRoom=rs.getInt("IDROOM");
					this.pseudo=rs.getString("PSEUDO");
				}
        	}
        	else
        		throw new Exception();
		
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
     * suppression de l'utilisateurroom sur base de l'identifiant de la room et du pseudo de l'utilisateur
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
	
    /**
    * méthode statique permettant de récupérer toutes les rooms auxquels un certain utilisateur a accès
    * @param pseudo pseudo de l'utilisateur
    * @return liste de room
    * @throws Exception Erreur Utilisateur inconnu
    */
	public static ArrayList<Integer> readRoom(String pseudo) throws Exception{
		PreparedStatement cstmt=null;
		ArrayList<Integer> resultat=new ArrayList<Integer>();
		try{
			String query="select idRoom from UtilisateurRoom where pseudo=?";
			cstmt=dbConnect.prepareCall(query);
			cstmt.setString(1, pseudo);
			ResultSet rs=cstmt.executeQuery();
			while(rs.next())
			{
				resultat.add(rs.getInt("IDROOM"));
			}
			return resultat;
		}
		catch(Exception e)
		{
			throw new Exception("Erreur Utilisateur inconnu"+e.getMessage());
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
	
	public void readPseudoRoom() throws Exception{
		String query = "SELECT * FROM UtilisateurRoom WHERE idRoom=? and pseudo=?";
		PreparedStatement cstmt=null;
		try
		{
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1,this.idRoom);
			cstmt.setString(2, this.pseudo);
			ResultSet rs=cstmt.executeQuery();
			if(rs.isBeforeFirst())
        	{
				while(rs.next())
				{
					this.idUtilisateurRoom=rs.getInt("IDUTILISATEURROOM");
				}
        	}
        	else
        		throw new Exception();
		
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
	
}