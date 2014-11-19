package ClassesDB;

import java.sql.*;

/**
 * Classe MessageDB (classe de mappage poo-relationnel message)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see Message
 */

// ATTENTION MessageDB A REVOIR !!! (CREATE)
public class MessageDB extends Message implements CRUD
{
    /**
    *Connexion à la base de données partagée entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
   /**
   * constructeur par défaut
   */
    public MessageDB()
    {
        
    }
    
    /**
   * constructeur paramétré à utiliser avant lorsque le message est déjà présent
   * dans la base de données
   * @param idmessage identifiant du message
   * @param contenu contenu du message
   * @param date date à laquelle le message a été posté
   * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
   */
    public MessageDB(int idmessage,String contenu, Date date, int idutilisateurroom, int idRoom, String pseudo)
    {
        super(idmessage,contenu,date,idutilisateurroom, idRoom, pseudo);
    }
    
    
    /**
     * constructeur paramétré à utiliser avant lors de la création, l'idmessage
     * ne doit pas être précisé,il sera affecté par la base de données lors de la création
     * @see #create()
     * @param contenu contenu du message
     * @param date date à laquelle le message a été posté
     * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
     */
      public MessageDB(String contenu, Date date, int idutilisateurroom)
      {
          super(0,contenu,date,idutilisateurroom);
      }
      
      /**
       * constructeur à utiliser lorsque le message est déjà présent en base de données
       * mais qu'on ne connaît que son identifiant, à utiliser avec read
       * @see #read()
       * @param idmessage identifiant du message
       */
        public MessageDB(int idmessage)
        {
            super(idmessage,"",null,0);
        }
        
       /**
       * méthode statique permettant de partager la connexion entre toutes les instances de
       * MessageDB
       * @param nouvelledbConnect connexion à la base de données
       */
        public static void setConnection(Connection nouvelledbConnect)
        {
            dbConnect=nouvelledbConnect;
        }
        
        /**
         * enregistrement d'un nouveau message dans la base de données
         * @throws Exception erreur lors de la création
         */
        public void create() throws Exception
        {
            CallableStatement cstmt=null;
            try
            {          
                 String query="call createMessage(?,?,?)"; 
                 cstmt = dbConnect.prepareCall(query);
				 cstmt.setString(1, pseudo);
				 cstmt.setInt(2,idRoom);
				 cstmt.setString(3, contenu);
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
         * récupération des données d'un message sur base de son identifiant
         * @throws Exception code inconnu
         */
    	public void read() throws Exception
    	{
    		String query = "SELECT * FROM MESSAGE WHERE IDMESSAGE=?";
            PreparedStatement cstmt=null;
            try
            {
            	cstmt = dbConnect.prepareStatement(query);
            	cstmt.setInt(1,this.idmessage);
            	ResultSet rs=cstmt.executeQuery(query);
            	while(rs.next())
            	{
            		this.contenu=rs.getString("contenu");
            		this.date=rs.getDate("date");
            		this.idutilisateurroom=rs.getInt("idutilisateurroom");
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
    		//pas utilisable dans le cadre de cette application
    	}
    	
        /**
         * suppression du message sur base de son identifiant
         * @throws Exception erreur lors de la suppression
         */
    	public void delete() throws Exception
    	{    		
            CallableStatement cstmt =null;
            try
            {
                String req = "call  DeleteMessage(?)";
                cstmt = dbConnect.prepareCall(req);
                cstmt.setInt(1,idmessage);
                cstmt.executeUpdate();
            }
            catch(Exception f)
            {
                throw new Exception("Erreur lors de la suppression"+f.getMessage());
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
