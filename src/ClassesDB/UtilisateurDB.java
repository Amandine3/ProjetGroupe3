package ClassesDB;
import java.util.*;
import java.sql.*;

/**
 * Classe UtilisateurDB (classe de mappage poo-relationnel utilisateur)
 * @author Amandine Vandevoir & Aur�lien Vandaele
 * @see Utilisateur
 */
public class UtilisateurDB extends Utilisateur implements CRUD {

    /**
    *Connexion � la base de donn�es partag�e entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
   /**
   * constructeur par d�faut
   */
    public UtilisateurDB()
    {
        
    }
    
    /**
   * constructeur param�tr� � utiliser avant lorsque l'utilisateur est d�j� pr�sent
   * dans la base de donn�es
   * @param pseudo identifiant de l'utilisateur
   * @param motdepasse mot de passe de l'utilisateur
   * @param nom nom de l'utilisateur
   * @param prenom pr�nom de l'utilisateur
   * @param numgsm num�ro de gsm de l'utilisateur
   */
    public UtilisateurDB(String pseudo, String motdepasse,String nom, String prenom, String numgsm)
    {
        super(pseudo,motdepasse,nom,prenom,numgsm);
    }
    
   
   /**
   * constructeur � utiliser lorsque l'utilisateur est d�j� pr�sent en base de donn�es
   * mais qu'on ne conna�t que son pseudo, � utiliser avec read
   * @see #read()
   * @param pseudo
   */
    public UtilisateurDB(String pseudo)
    {
        super(pseudo,"","","","");
    }
    
   /**
   * m�thode statique permettant de partager la connexion entre toutes les instances de
   * UtilisateurDB
   * @param nouvelledbConnect connexion � la base de donn�es
   */
    public static void setConnection(Connection nouvelledbConnect)
    {
        dbConnect=nouvelledbConnect;
    }
    
   /**
   * enregistrement d'un nouvel utilisateur dans la base de donn�es
   * @throws Exception erreur lors de la cr�ation
   */
    public void create() throws Exception
    {
        CallableStatement cstmt=null;
        try
        {          
             String query="call CreateUtilisateur(?,?,?,?,?)"; 
             cstmt = dbConnect.prepareCall(query);
             cstmt.setString(1,pseudo);
             cstmt.setString(2,motdepasse);
             cstmt.setString(3,nom);
             cstmt.setString(4,prenom);
             cstmt.setString(5,numgsm);
             cstmt.executeUpdate();
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
    * r�cup�ration des donn�es d'un utilisateur sur base de son pseudo
    * @throws Exception code inconnu
    
    public void read() throws Exception
    {
        String query = "SELECT * FROM UTILISATEUR WHERE PSEUDO=?";
        CallableStatement cstmt=null;
        try
        {
             cstmt = dbConnect.prepareCall(query);
             cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
             cstmt.setInt(2,id_client);
	         cstmt.executeQuery();
             ResultSet rs=(ResultSet)cstmt.getObject(1);
             if(rs.next())
             {
	     	this.nom=rs.getString("NOM");
	     	this.prenom=rs.getString("PRENOM");
	     	this.adresse=rs.getString("ADRESSE");
                this.npostal=rs.getString("NPOSTAL");
	     	this.localite=rs.getString("LOCALITE");
             }
	     else
             { 
	         throw new Exception("Code inconnu");
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
    */
    
    /**
    * mise � jour des donn�es de l'utilisateur sur base de son pseudo
    * @throws Exception erreur lors de la mise � jour
    */
     public void update() throws Exception
     {
         CallableStatement cstmt=null;
          try
          {
            
             String query = "call updateUtilisateur(?,?,?,?,?)";
             cstmt=dbConnect.prepareCall(query);
             PreparedStatement pstm = dbConnect.prepareStatement(query);
             cstmt.setString(1,pseudo);
             cstmt.setString(2,motdepasse);
             cstmt.setString(3,nom);
             cstmt.setString(4,prenom);
             cstmt.setString(5,numgsm);
             cstmt.executeUpdate();
          }
          catch(Exception e)
          {
             throw new Exception("Erreur lors de la mise a jour"+e.getMessage());
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
     * suppression de l'utilisateur sur base de son pseudo
     * @throws Exception erreur lors de la suppression
     */
     public void delete() throws Exception
     {
          CallableStatement cstmt =null;
          try
          {
              String req = "call  DeleteUtilisateur(?)";
              cstmt = dbConnect.prepareCall(req);
              cstmt.setString(1,pseudo);
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
              catch (Exception e)
              {

              }
           }
     }

   
}