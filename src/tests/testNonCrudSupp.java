package tests;
/**
 * Classe testNonCrudSupp
 * @author Amandine Vandevoir & Aur�lien Vandaele
 */
import ClassesDB.*;
import myconnections.*;
import java.sql.Connection;
import java.util.ArrayList;

public class testNonCrudSupp{
	public static void main(String[] args){
	    DBConnection dbc =new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null)
        { 
            System.out.println("connexion impossible");
            System.exit(0);
        }
        
        RoomDB.setConnection(con);
		UtilisateurRoomDB.setConnection(con);
		try{
			System.out.println("Test deux m�thodes non crud positif");
			ArrayList<Integer> a=UtilisateurRoomDB.readRoom("Aurelien");
			ArrayList<MessageDB> b=RoomDB.getMessageRoom(31);
			for (int i=0;i<a.size();i++){
				System.out.println(a.get(i));
			}
			for(int j=0;j<b.size();j++){
				System.out.println(b.get(j));
			}
			System.out.println("OK");
		}
		catch(Exception e){
			System.out.println("BAD"+e.getMessage());
		}
		try{
			System.out.println("Test non fructueux");
			ArrayList<Integer> a=UtilisateurRoomDB.readRoom("inconnu");
			System.out.println("BAD");
			for (int i=0;i<a.size();i++){
				System.out.println(a.get(i));
			}
		}
		catch(Exception e){
			System.out.println("OK "+e.getMessage());
		}
		try{
			System.out.println("test non fructueux 2");
			ArrayList<MessageDB> b=RoomDB.getMessageRoom(5);
			System.out.println("BAD");
			for(int j=0;j<b.size();j++){
				System.out.println(b.get(j));
			}
		}
		catch(Exception e){
			System.out.println("OK "+ e.getMessage());
		}
	}
}


