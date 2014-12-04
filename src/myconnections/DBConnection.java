package myconnections;

import java.sql.*;
import java.util.*;

import android.util.Log;
public class DBConnection 
{

  protected String serverName;
  protected String username;
  protected String password;
  protected String dbName;
  protected String dbPort;
 
  public DBConnection()
  {
	  PropertyResourceBundle properties = (PropertyResourceBundle)
      PropertyResourceBundle.getBundle("resources.application");
	  Log.e("Passe ici","passe");
	  serverName=properties.getString("cours.DB.server");
	  dbName =properties.getString("cours.DB.database");
	  username=properties.getString("cours.DB.login");
	  password=properties.getString("cours.DB.password");
	  dbPort=properties.getString("cours.DB.port");   
 
  }
  
  public DBConnection(String username,String password)
  {
      this();
      this.username=username;
      this.password=password;
   }

 public Connection getConnection()
 {
	 Log.e("Debut","debut");
	 try
	 {
	  
		Log.e("A","AA");
        Class.forName ("oracle.jdbc.OracleDriver");
        Log.e("B","BB");
        String url = "jdbc:oracle:thin:@//"+serverName+":"+dbPort+"/"+dbName;
        Log.e("C","CC");
        Connection  dbConnect = DriverManager.getConnection(url, username, password);
        Log.e("D","DD : "+dbConnect);
        Log.e("GETCO : ", "getco "+dbConnect);
        return dbConnect;
       
       
    }
    catch(Exception e) 
  	{
    	Log.e("Connection","PROBLEME De Connexion : "+e);
    	return null ;
    }
 }
 
}
 

