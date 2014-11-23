package tests;

/**
 * Classe TestMessage
 * @author Amandine Vandevoir & AurÃ©lien Vandaele
 */
import ClassesDB.*;
import myconnections.*;

import java.sql.Connection;

public class TestMessage
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
        
        MessageDB.setConnection(con);
        MessageDB c1=null,c2=null;  
        try
        {
        	  System.out.println("Test ajout Message par créateur");
              c1=new MessageDB("Bonjour a tous", 30, "Aurelien");
              GestCRUD.create(c1);
              int id=c1.getIdmessage();
              c2=new MessageDB(id);
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
           GestCRUD.delete(c1);
        }
        catch(Exception e)
        {
               
        }
     
        try
        {
        	  System.out.println("Test ajout message par une personne n'ayant pas créé la room");
              c1=new MessageDB("Bonjour a toi", 30, "Blabla");
              GestCRUD.create(c1);
              int idm=c1.getIdmessage();
              System.out.println("idmessage : " + idm);
              c2=new MessageDB();
              c2.setIdmessage(idm);

              GestCRUD.read(c2);
              System.out.println("c2="+c2);
              System.out.println("OK ");
        }
        catch(Exception e)
        {
           System.out.println("BAD "+e);
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
        	  System.out.println("Test ajout infructueux d'un message car utilisateur n'existe pas");
        	  c1=new MessageDB("Bonjour a toi", 30, "Nexistepas");
              GestCRUD.create(c1);
              int idm=c1.getIdmessage();
              c2.setIdmessage(idm);
              GestCRUD.read(c2);
              System.out.println("BAD ");
        }
        catch(Exception e)
        {
           System.out.println("OK exception normale d'ajout infructueux message"+e);
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
            System.out.println("Test d'effacement d'un message du créateur");
            c1=new MessageDB("Bonjour a toi", 30, "Aurelien");
            GestCRUD.create(c1);
			GestCRUD.read(c1);
			int idm=c1.getIdmessage();
            GestCRUD.delete(c1);
            c2=new MessageDB();
			c2.setIdmessage(idm);
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
            System.out.println("Test d'effacement d'un messages d'un simple utilisateur (pas le créateur de la room)");
            c1=new MessageDB("Bonjour a toi", 30, "Blabla");
            GestCRUD.create(c1);
			GestCRUD.read(c1);
			int idm=c1.getIdmessage();
            GestCRUD.delete(c1);
            c2=new MessageDB();
			c2.setIdmessage(idm);
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
