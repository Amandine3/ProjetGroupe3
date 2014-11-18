package tests;

/**
 * Classe TestUtilisateur
 * @author Amandine Vandevoir & Aurélien Vandaele
 */
import ClassesDB.*;
import myconnections.*;
import java.sql.Connection;

public class TestUtilisateur
{
    public static void main(String[] args)
    {
        DBConnection dbc =new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null)
        { 
            System.out.println("connexion impossible");
            System.exit(0);
        }
        
        UtilisateurDB.setConnection(con);
        UtilisateurDB c1=null,c2=null;  
        try
        {
        	  System.out.println("Test ajout utilisateur");
        	  //pseudo, mp, nom, prenom, numgsm
              c1=new UtilisateurDB("Amandarine3","am3","Vandevoir","Amandine","0472/345789");
              c1.create();
              String pseu=c1.getPseudo();
              c2=new UtilisateurDB(pseu);
              c2.read();
              System.out.println("c2="+c2);
              System.out.println("OK");
        }
        catch(Exception e)
        {
           System.out.println("BAD exception d'ajout"+e);
        }   
        try
        { 
           c1.delete();
        }
        catch(Exception e)
        {
               
        }
     
        try
        {
            System.out.println("Test d'effacement fructueux");
            c1=new UtilisateurDB("Amandarine3","am3","Vandevoir","Amandine","0472/345789");
            c1.create();
			String pseu=c1.getPseudo();
            c1.delete();
            c2=new UtilisateurDB(pseu);
            c2.read();
            System.out.println("c2 ="+c2);
            System.out.println("BAD");
        }
        catch(Exception e)
        {
            System.out.println("OK exception normale d'effacement"+e);
        }
        try
        { 
            c1.delete();
        }
        catch(Exception e)
        {
             
        }
        
        try
        { 
            System.out.println("Test mise à jour");
            c1=new UtilisateurDB("Amandarine3","am3","Vandevoir","Amandine","0472/345789");
            c1.create();
			String pseu=c1.getPseudo();
			c1.setMotdepasse("amandine3");
            c1.setNom("nouvnom");
            c1.setPrenom("nouvprenom");
            c1.setNumgsm("0472/444444");
            c1.update();
            c2=new UtilisateurDB(pseu);
            c2.read();
            c1.setPseudo(pseu);
            c1.delete();
            System.out.println("OK");
        }
        catch(Exception e)
        {
            System.out.println("BAD exception de mise à jour "+e);
        }
        try
        { 
            c1.delete();
        }
        catch(Exception e)
        {
            
        }
        
    }
}

        
        /*
         try
         {
            System.out.println("test d'effacement infructueux(client impliqué dans réservation)");
            c1=new ClientDB(10);
            c1.delete();
            System.out.println("BAD");
         }
         catch(Exception e)
         {
             System.out.println("OK exception normale d'effacement"+e);
         }

                
        
        try
        {
            System.out.println("test de recherche fructueuse");
            ArrayList<ClientDB> liste=ClientDB.rechercheNom("Dupont");
            for(ClientDB pr:liste)
            {
              System.out.println(pr);
            }
            System.out.println("OK");
        }
        catch(Exception e)
        {
            System.out.println("exception de recherche "+e);
         }
        
        try
        {
            System.out.println("test de recherche infructueuse");
            ArrayList<ClientDB> liste=ClientDB.rechercheNom("ZZZZZZ");
            for(ClientDB pr:liste)
            {
              System.out.println(pr);
            }
            System.out.println("BAD");
        }
        catch(Exception e)
        {
            System.out.println("OK exception normale recherche "+e);
        }
        
      try
      {  
        con.close();
      }
      catch(Exception e)
      {
          
      }
   }*/ 
