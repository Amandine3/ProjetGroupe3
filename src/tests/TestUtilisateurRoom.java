package tests;

/**
 * Classe TestUtilisateur
 * @author Amandine Vandevoir & Aurélien Vandaele
 */
import ClassesDB.*;
import myconnections.*;
import java.sql.Connection;

public class TestUtilisateurRoom
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
        
        UtilisateurRoomDB.setConnection(con);
        UtilisateurRoomDB c1=null,c2=null;  
        try
        {
        	  System.out.println("Test ajout utilisateurRoom");
        	  //pseudo, mp, nom, prenom, numgsm
<<<<<<< HEAD
              c1=new UtilisateurRoomDB(30, "Blabla");
              GestCRUD.create(c1);
			  c1.readPseudoRoom()
=======
              c1=new UtilisateurRoomDB(30, "Ametau");
              GestCRUD.create(c1);
			  c1.readPseudoRoom();
>>>>>>> bda423ca15dde0413f688f99e59f7f8908c9cb92
              int idUtilisateurRoom=c1.getIdUtilisateurRoom();
              c2=new UtilisateurRoomDB();
			  c2.setIdUtilisateurRoom(idUtilisateurRoom);
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
<<<<<<< HEAD
        	  System.out.println("Test ajout 2 utilisateurs avec le mÃªme pseudo dans la mÃªme room");
        	  //pseudo, mp, nom, prenom, numgsm
              c1=new UtilisateurRoomDB(30,"Blabla");
              GestCRUD.create(c1);
			  c1.readPseudoRoom():
              c2=new UtilisateurDB(30, "Blabla");
=======
        	  System.out.println("Test ajout 2 utilisateurs avec le même pseudo dans la même room");
        	  //pseudo, mp, nom, prenom, numgsm
              c1=new UtilisateurRoomDB(30,"Blabla");
              GestCRUD.create(c1);
			  c1.readPseudoRoom();
              c2=new UtilisateurRoomDB(30, "Blabla");
>>>>>>> bda423ca15dde0413f688f99e59f7f8908c9cb92
              GestCRUD.create(c2);
			  c2.readPseudoRoom();
              System.out.println("BAD ");
        }
        catch(Exception e)
        {
<<<<<<< HEAD
           System.out.println("OK exception normale d'ajout 2 ut. avec mÃªme pseudo dans la mÃªme room"+e);
=======
           System.out.println("OK exception normale d'ajout 2 ut. avec même pseudo dans la même room"+e);
>>>>>>> bda423ca15dde0413f688f99e59f7f8908c9cb92
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
        	  System.out.println("Test ajout infructueux dans room qui n'existe pas");
              c1=new UtilisateurRoomDB(28, "Blabla");
			  c1.readPseudoRoom();
              System.out.println("BAD ");
        }
        catch(Exception e)
        {
           System.out.println("OK exception normale d'ajout  ut. dans une room qui n'existe pas"+e);
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
        	  System.out.println("Test ajout  infructueux utilisateur qui n'existe pas");
              c1=new UtilisateurRoomDB(30, "azertyuio");
              c1.readPseudoRoom();
			  System.out.println("BAD ");
        }
        catch(Exception e)
        {
           System.out.println("OK exception normale d'ajout 2 ut. dans une room qui n'existe pas"+e);
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
            System.out.println("Test d'effacement fructueux");
<<<<<<< HEAD
            c1=new UtilisateurRoomDB(30, "blabla");
=======
            c1=new UtilisateurRoomDB(30, "Blabla");
>>>>>>> bda423ca15dde0413f688f99e59f7f8908c9cb92
            GestCRUD.create(c1);
			c1.readPseudoRoom();
			int idUr=c1.getIdUtilisateurRoom();
            GestCRUD.delete(c1);
<<<<<<< HEAD
            c2=new UtilisateurDB();
=======
            c2=new UtilisateurRoomDB();
>>>>>>> bda423ca15dde0413f688f99e59f7f8908c9cb92
			c2.setIdUtilisateurRoom(idUr);
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
        
        
        
    }
}
