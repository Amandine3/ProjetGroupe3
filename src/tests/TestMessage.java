package tests;

/**
 * Classe TestUtilisateur
 * @author Amandine Vandevoir & Aurélien Vandaele
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
              int id=c1.getIdMessage();
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
           GestCRUD.delete(c1);;
        }
        catch(Exception e)
        {
               
        }
     
        try
        {
        	  System.out.println("Test ajout message par une personne n'ayant pas créé la room");
              c1=new MessageDB("Bonjour a toi", 30, "Blabla");
              GestCRUD.create(c1);
              c2=new MessageDB(c1.getIdMessage());
              GestCRUD.create(c2);
              System.out.println("c2="+c2);
              System.out.println("OK ");
        }
        catch(Exception e)
        {
           System.out.println("BAD exception normale d'ajout 2 ut. avec même pseudo"+e);
        }   
        try
        { 
        	GestCRUD.delete(c1);
        }
        catch(Exception e)
        {
               
        }
        
}
 
