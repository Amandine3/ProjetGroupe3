package tests;

/**
 * Classe TestRoom
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

import java.sql.Connection;
import myconnections.DBConnection;
import ClassesDB.*;

public class TestRoom 
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
	        
	        RoomDB.setConnection(con);
	        RoomDB c1=null,c2=null;
	        try
	        {
	        	  System.out.println("Test création room fructueux");
	              c1=new RoomDB("Aurelien"); //Aurelien étant un utilisateur existant dans la DB
	              GestCRUD.create(c1);
	              int id=c1.getIdRoom();
	              c2=new RoomDB();
	              c2.setIdRoom(id);
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
	        	System.out.println("BAD exception de suppression"+e);
	        }
	    
	 
	        try
	        {
	        	System.out.println("Test création room infructueux");
		        c1=new RoomDB("Jenexistepas"); //'Jenexistepas' n'est pas un utilisateur existant
		        GestCRUD.create(c1);
		        int id=c1.getIdRoom();
		        c2=new RoomDB();
		        c2.setIdRoom(id);
		        GestCRUD.read(c2);
		        System.out.println("c2="+c2);
		        System.out.println("BAD ");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("OK exception normale d'ajout d'une room dont l'utilisateur n'existe pas  "+e);
	        }   
	        try
	        { 
	        	GestCRUD.delete(c1);
	        }
	        catch(Exception e)
	        {
	        	System.out.println("BAD exception de suppression"+e);
	        }
	 
     try
     {
         System.out.println("Test d'effacement fructueux");
         c1=new RoomDB("Aurelien");
         GestCRUD.create(c1);
         c2=new RoomDB();
         c2.setIdRoom(c1.getIdRoom());
         GestCRUD.delete(c1);
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