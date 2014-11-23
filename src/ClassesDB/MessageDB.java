package ClassesDB;

import java.sql.*;

/**
 * Classe MessageDB (classe de mappage poo-relationnel message)
 * @author Amandine Vandevoir & Aur�lien Vandaele
 * @see Message
 */

public class MessageDB extends Message implements CRUD
{
    /**
    *Connexion � la base de donn�es partag�e entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
   /**
   * constructeur par d�faut
   */
    public MessageDB()
    {
        
    }
    
    /**
   * constructeur param�tr� � utiliser avant lorsque le message est d�j� pr�sent
   * dans la base de donn�es
   * @param idmessage identifiant du message
   * @param contenu contenu du message
   * @param date date � laquelle le message a �t� post�
   * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
   * @param idRoom : identifiant de la room o� le message a �t� post�
   * @param pseudo : pseudo de l'utilisateur qui a post� le message
   */
    public MessageDB(int idmessage,String contenu, Date date, int idutilisateurroom, int idRoom, String pseudo)
    {
        super(idmessage,contenu,date,idutilisateurroom, idRoom, pseudo);
    }
    
    
    /**
     * constructeur param�tr� � utiliser avant lors de la cr�ation, l'idmessage
     * ne doit pas �tre pr�cis�,il sera affect� par la base de donn�es lors de la cr�ation
     * @see #create()
     * @param contenu contenu du message
     * @param date date � laquelle le message a �t� post�
     * @param idutilisateurroom identifiant de la jonction entre la room et l'utilisateur
     */
      public MessageDB(String contenu, Date date, int idutilisateurroom)
      {
          super(0,contenu,date,idutilisateurroom, 0, "");
      }
      
      /** constructeur param�tr� � utiliser afin de cr�er un message et de l'envoyer dans la base de donn�es
       * @param contenu contenu du message
       * @param idRoom identifiant de la room o� le message a �t� post�
       * @param pseudo pseudo de l'utilisateur qui a post� le message
       */
	  public MessageDB(String contenu, int idRoom, String pseudo){
		super(-1, contenu, null, -1, idRoom, pseudo);
	  }
	  
      /**
       * constructeur � utiliser lorsque le message est d�j� pr�sent en base de donn�es
       * mais qu'on ne conna�t que son identifiant, � utiliser avec read
       * @see #read()
       * @param idmessage identifiant du message
       */
        public MessageDB(int idmessage)
        {
            super(idmessage,"",null,0, 0, "");
        }
        
       /**
       * m�thode statique permettant de partager la connexion entre toutes les instances de
       * MessageDB
       * @param nouvelledbConnect connexion � la base de donn�es
       */
        public static void setConnection(Connection nouvelledbConnect)
        {
            dbConnect=nouvelledbConnect;
        }
        
        /**
         * enregistrement d'un nouveau message dans la base de donn�es
         * @throws Exception erreur lors de la cr�ation
         */
        public void create() throws Exception
        {
            CallableStatement cstmt=null;
            try
            {          
                 String query="call createMessage(?,?,?,?)"; 
                 cstmt = dbConnect.prepareCall(query);
				 cstmt.setString(1, pseudo);
				 cstmt.setInt(2,idRoom);
				 cstmt.setString(3, contenu);
				 cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
				 cstmt.executeUpdate();
			     this.idmessage=cstmt.getInt(4);
            }
            catch(Exception e)
            {
                throw new Exception("Erreur lors de la cr�ation"+e.getMessage());
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
         * r�cup�ration des donn�es d'un message sur base de son identifiant
         * @throws Exception erreur lors de la lecture
         */
    	public void read() throws Exception
    	{
    		String query = "SELECT * FROM MESSAGE WHERE IDMESSAGE=?";
            PreparedStatement cstmt=null;
            try
            {
            	cstmt = dbConnect.prepareStatement(query);
            	cstmt.setInt(1,this.idmessage);
            	ResultSet rs=cstmt.executeQuery();
            	if(rs.isBeforeFirst())
            	{
            		while(rs.next())
            		{
            			this.contenu=rs.getString("CONTENU");
            			this.date=rs.getDate("DATEPOST");
            			this.idutilisateurroom=rs.getInt("IDUTILISATEURROOM");
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
    	 * M�thode update non n�cessaire (mais ici car implements CRUD)
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
