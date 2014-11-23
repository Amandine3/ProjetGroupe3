package ClassesDB;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe RoomDB (classe de mappage poo-relationnel Room)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see Room
 */

public class RoomDB extends Room implements CRUD
{
	
    /**
    *Connexion à la base de données partagée entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
    
	/**
	* méthode statique permettant de partager la connexion entre toutes les instances de
	* RoomDB
	* @param nouvelledbConnect connexion à la base de données
	*/
	public static void setConnection(Connection nouvelledbConnect)
	{
	    dbConnect=nouvelledbConnect;
	}
	
	
   /**
   * constructeur par défaut
   */
	public RoomDB()
	{
		
	}
	
	/** 
	 * constructeur paramétré
	 * @param createur le créateur de la room
	 */
	public RoomDB(String createur){
		super(createur);
	}
	
	/**
	 * constructeur paramétré
	 * @param idRoom l'identifiant de la room
	 * @param createur le créateur de la room
	 */
	public RoomDB(int idRoom, String createur){
		super(idRoom, createur);
	}
	
    /**
     * enregistrement d'une nouvelle room dans la base de données
     * @throws Exception erreur lors de la création
     */
	public void create() throws Exception{
		CallableStatement cstmt =null;
		try{
			String query= "call createRoom1(?,?)";
			cstmt = dbConnect.prepareCall(query);
			cstmt.setString(1,createur);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			this.idRoom=cstmt.getInt(2);
		}
		catch(Exception e){
            throw new Exception("Erreur lors de la création"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }	
	}
	
    /**
     * récupération des données d'une room sur base de son identifiant
     * @throws Exception erreur lors de la lecture
     */
	public void read() throws Exception{
		String query="SELECT createur FROM room WHERE idroom = ?";
		PreparedStatement cstmt=null;
		try{
            	cstmt = dbConnect.prepareStatement(query);
            	cstmt.setInt(1,this.idRoom);
            	ResultSet rs=cstmt.executeQuery();
            	
            	if(rs.isBeforeFirst())
            	{
            	while(rs.next()){
            		this.createur=rs.getString("createur");
            	}
            	}
            	else throw new Exception();
        }
        catch(Exception e){
            throw new Exception("Erreur lors de la lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch (Exception e){}
        };
	}
	
	/**
	 * Méthode update non nécessaire (mais ici car implements CRUD)
	 */
	public void update()
	{
		// pas utilisable dans le cadre de cette application
	}
	
    /**
     * suppression d'une room sur base de son identifiant
     * @throws Exception erreur lors de la suppression
     */
	public void delete() throws Exception{
		CallableStatement cstmt =null;
		try{
			String query= "call deleteRoom(?)";
			cstmt = dbConnect.prepareCall(query);
			cstmt.setInt(1,this.idRoom);
			cstmt.executeUpdate();
		}
		catch(Exception e){
            throw new Exception("Erreur lors de la Suppression"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }		
	}
	
	
    /**
    * méthode statique permettant de récupérer tous les messages d'une certaine room
    * @param idroom identifiant de la room
    * @return liste de messages
    * @throws Exception Message inconnu
    */
	public static ArrayList<MessageDB> getMessageRoom(int idroom) throws Exception{
		ArrayList<MessageDB> resultat=new ArrayList<MessageDB>();
		MessageDB a;
		String query="select pseudo, contenu, datepost from jonctionmessage where idroom= ? order by datepost";
		PreparedStatement cstmt=null;
		try{
            	cstmt = dbConnect.prepareStatement(query);
            	cstmt.setInt(1,idroom);
            	ResultSet rs=cstmt.executeQuery(query);
				if(rs.isBeforeFirst()){					
					while(rs.next()){
						a=new MessageDB();
						a.setPseudo(rs.getString("PSEUDO"));
						a.setDate(rs.getDate("DATEPOST"));
						a.setContenu(rs.getString("CONTENU"));
					}
					return resultat;
				}
				else
				     throw new Exception("Message inconnu");
        }
        catch(Exception e){
            throw new Exception("Message inconnu"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
 
            }
            catch (Exception e){}
        }
		
	}	
}

