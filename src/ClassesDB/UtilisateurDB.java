package ClassesDB;
import java.util.*;
import java.sql.*;

/**
 * Classe UtilisateurDB (classe de mappage poo-relationnel utilisateur)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see Utilisateur
 */
public class UtilisateurDB extends Utilisateur implements CRUD {

    /**
    *Connexion à la base de données partagée entre toutes les instances(statique)
    */
    protected static Connection dbConnect=null;
    
   /**
   * constructeur par défaut
   */
    public UtilisateurDB()
    {
        
    }
    
    /**
   * constructeur paramétré à utiliser avant lorsque l'utilisateur est déjà présent
   * dans la base de données
   * @param pseudo identifiant de l'utilisateur
   * @param motdepasse mot de passe de l'utilisateur
   * @param nom nom de l'utilisateur
   * @param prenom prénom de l'utilisateur
   * @param numgsm numéro de gsm de l'utilisateur
   */
    public UtilisateurDB(String pseudo, String motdepasse,String nom, String prenom, String numgsm)
    {
        super(pseudo,motdepasse,nom,prenom,numgsm);
    }
    
   
   /**
   * constructeur à utiliser lorsque l'utilisateur est déjà présent en base de données
   * mais qu'on ne connaît que son pseudo, à utiliser avec read
   * @see #read()
   * @param pseudo
   */
    public UtilisateurDB(String pseudo)
    {
        super(pseudo,"","","","");
    }
    
   /**
   * méthode statique permettant de partager la connexion entre toutes les instances de
   * UtilisateurDB
   * @param nouvelledbConnect connexion à la base de données
   */
    public static void setConnection(Connection nouvelledbConnect)
    {
        dbConnect=nouvelledbConnect;
    }
    
   /**
   * enregistrement d'un nouvel utilisateur dans la base de données
   * @throws Exception erreur lors de la création
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
    * récupération des données d'un utilisateur sur base de son pseudo
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
    * mise à jour des données de l'utilisateur sur base de son pseudo
    * @throws Exception erreur lors de la mise à jour
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