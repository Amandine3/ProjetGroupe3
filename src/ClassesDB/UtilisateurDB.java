package ClassesDB;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe UtilisateurDB (classe de mappage poo-relationnel utilisateur)
 * @author Amandine Vandevoir & Aurélien Vandaele
 * @see Utilisateur
 */
public class UtilisateurDB extends Utilisateur implements CRUD 
{

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
        System.out.println("SET CONNECTION : "+dbConnect);
        
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
    */
    public void read() throws Exception
    {
    	String query = "SELECT * FROM UTILISATEUR WHERE PSEUDO=?";
        PreparedStatement cstmt=null;
        try
        {
        	cstmt = dbConnect.prepareStatement(query);
        	cstmt.setString(1,this.pseudo);
        	ResultSet rs=cstmt.executeQuery();
        	if(rs.isBeforeFirst()){
	        	while(rs.next())
	        	{
	        		this.pseudo=rs.getString("PSEUDO");
	        		this.motdepasse=rs.getString("motdepasse");
	        		this.nom=rs.getString("nom");
	        		this.prenom=rs.getString("prenom");
	        		this.numgsm=rs.getString("numgsm");
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
              String req = "call deleteutilisateur(?)";
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
  
	public static ArrayList<UtilisateurDB> getListUser() throws Exception{
		System.out.println("1");
		ArrayList<UtilisateurDB> retour= new ArrayList<UtilisateurDB>();
		System.out.println("2");
		PreparedStatement cstmt=null;
		System.out.println("3");
		try{
			System.out.println("4");
			String query= "SELECT PSEUDO, NUMGSM FROM UTILISATEUR";
			System.out.println("5");
			cstmt = dbConnect.prepareStatement(query);
			System.out.println("CSTMt : " + cstmt);
			System.out.println("6");
        	ResultSet rs=cstmt.executeQuery();
        	System.out.println("7");
        	if(rs.isBeforeFirst()){
        		System.out.println("IF");
				UtilisateurDB a;
				System.out.println("8");
	        	while(rs.next())
	        	{
					a=new UtilisateurDB();
					System.out.println("9");
	        		a.setPseudo(rs.getString("PSEUDO"));
	        		System.out.println("10");
	        		a.setNumgsm(rs.getString("NUMGSM"));
	        		System.out.println("11");
					retour.add(a);
					System.out.println("12");
	        	}
        	}
        	else
        		throw new Exception();
        		System.out.println("ELSE");
			return retour;
        
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