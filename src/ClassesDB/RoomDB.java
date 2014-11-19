package ClassesDB;

import java.sql.Connection;


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
	public RoomDB(String createur){
		super(createur);
	}
	public RoomDB(int idRoom, String createur){
		super(idRoom, createur);
	}
	public void create() throws Exception{
		CallableStatement cstmt =null;
		try{
			String query= "call createRoom1(?,?)";
			cstmt = dbConnect.prepareCall(query)
			cstmt.setString(1,createur);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.execute();
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
	public void read() throws Exception{
		string query="select createur from room where idroom="+this.idRoom;
		PreparedStatement cstmt=null;
		try{
            	cstmt = dbConnect.prepareStatement(query);
            	ResultSet rs=cstmt.executeQuery(query);
            	while(rs.next()){
            		this.createur=rs.getString("createur");
            	}
        }
        catch(Exception e){
            throw new Exception("Erreur lors de la lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch (Exception e){}
        }
	}
	public void update()
	{
		// pas utilisable dans le cadre de cette application
	}
	public void delete(){
		CallableStatement cstmt =null;
		try{
			String query= "deleteRoom(?)";
			cstmt = dbConnect.prepareCall(query)
			cstmt.setString(1,this.idRoom);
			cstmt.execute();
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
}
