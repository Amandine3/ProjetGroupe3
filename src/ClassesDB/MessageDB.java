package ClassesDB;

import java.sql.*;

/**
 * Classe MessageDB (classe de mappage poo-relationnel message)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see Message
 */

// ATTENTION Message & MessageDB A REVOIR !!!
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
    public MessageDB(int idmessage,String contenu, Date date, int idutilisateurroom)
    {
        super(idmessage,contenu,date,idutilisateurroom);
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
        
        public void create() throws Exception
        {
            CallableStatement cstmt=null;
            try
            {          
                 String query="call createMessage(?,?,?,?)"; 
    	     cstmt = dbConnect.prepareCall(query);
    	     /*CREATE OR REPLACE

    	     PROCEDURE createMessage (pseudoa varchar2, idrooma number, messagea varchar2) as

    	        idutilroom number;

    	       BEGIN

    	        SELECT idutilisateurroom INTO idutilroom FROM utilisateurroom WHERE pseudo=pseudo and idroom=idrooma;

    	        INSERT INTO message(datepost, contenu, idutilisateurroom) VALUES (current_date, messagea, idutilroom);

    	        COMMIT;

    	     END;*/
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
    	public void read(){
    		
    	}
    	public void update(){
    		
    	}
    	public void delete(){
    		
    	}
}
