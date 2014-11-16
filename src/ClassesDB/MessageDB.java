package ClassesDB;

import java.sql.*;

/**
 * Classe MessageDB (classe de mappage poo-relationnel message)
 * @author Amandine Vandevoir & Aur�lien Vandaele
 * @see Message
 */

// ATTENTION Message & MessageDB A REVOIR !!!
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
   */
    public MessageDB(int idmessage,String contenu, Date date, int idutilisateurroom)
    {
        super(idmessage,contenu,date,idutilisateurroom);
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
          super(0,contenu,date,idutilisateurroom);
      }
      
      /**
       * constructeur � utiliser lorsque le message est d�j� pr�sent en base de donn�es
       * mais qu'on ne conna�t que son identifiant, � utiliser avec read
       * @see #read()
       * @param idmessage identifiant du message
       */
        public MessageDB(int idmessage)
        {
            super(idmessage,"",null,0);
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
    	public void read(){
    		
    	}
    	public void update(){
    		
    	}
    	public void delete(){
    		
    	}
}
