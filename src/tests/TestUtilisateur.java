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
              c1=new UtilisateurDB("Amandarine3","am","Vandevoir","Amandine","04567899");
              GestCRUD.create(c1);
              String pseu=c1.getPseudo();
              c2=new UtilisateurDB(pseu);
              GestCRUD.read(c2);
              System.out.println("c2="+c2);
              System.out.println("OK");
        }
        catch(Exception e)
        {
           System.out.println("BAD exception d'ajout"+e);
        }   
        try
        { 
           GestCRUD.delete(c1);;
        }
        catch(Exception e)
        {
               
        }
     
        try
        {
        	  System.out.println("Test ajout 2 utilisateurs avec le même pseudo");
              c1=new UtilisateurDB("Amandarine3","am3","Vandevoir","Amandine","0472/345789");
              GestCRUD.create(c1);
              c2=new UtilisateurDB("Amandarine3","ammm","Line","Amande","0472/0909989");
              GestCRUD.create(c2);
              System.out.println("BAD ");
        }
        catch(Exception e)
        {
           System.out.println("OK exception normale d'ajout 2 ut. avec même pseudo"+e);
        }   
        try
        { 
        	GestCRUD.delete(c1);
        	GestCRUD.delete(c2);
        }
        catch(Exception e)
        {
               
        }
        
        try
        {
        	  System.out.println("Test ajout 2 utilisateurs avec le même numéro");
              c1=new UtilisateurDB("Alala3","am3","Vandevoir","Amandine","0472/343434");
              GestCRUD.create(c1);
              c2=new UtilisateurDB("KitKat","ammm","Line","Amande","0472/343434");
              GestCRUD.create(c2);
              System.out.println("BAD ");
        }
        catch(Exception e)
        {
           System.out.println("OK exception normale d'ajout 2 ut. avec même numéro"+e);
        }   
        try
        { 
        	GestCRUD.delete(c1);
        	GestCRUD.delete(c2);
        }
        catch(Exception e)
        {
               
        }
        
        
        try
        {
            System.out.println("Test d'effacement fructueux");
            c1=new UtilisateurDB("Chateau","bla3","BLASPHEME","BATSITE","04727789");
            GestCRUD.create(c1);
			String pseu=c1.getPseudo();
            GestCRUD.delete(c1);
            c2=new UtilisateurDB(pseu);
            GestCRUD.read(c2);
            System.out.println("c2 ="+c2);
            System.out.println("BAD");
        }
        catch(Exception e)
        {
            System.out.println("OK exception normale d'effacement"+e);
        }
        try
        { 
            GestCRUD.delete(c1);
        }
        catch(Exception e)
        {
             
        }
        
        try
        { 
            System.out.println("Test mise à jour");
            c1=new UtilisateurDB("Amandarine3","am3","Vandevoir","Amandine","0472/345789");
            GestCRUD.create(c1);
			String pseu=c1.getPseudo();
			c1.setMotdepasse("amandine3");
            c1.setNom("nouvnom");
            c1.setPrenom("nouvprenom");
            c1.setNumgsm("0472/444444");
            GestCRUD.update(c1);
            c2=new UtilisateurDB(pseu);
            GestCRUD.read(c2);
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
 
