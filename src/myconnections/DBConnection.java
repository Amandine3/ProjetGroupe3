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
	  
        Class.forName ("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@//"+serverName+":"+dbPort+"/"+dbName;
        Connection  dbConnect = DriverManager.getConnection(url, username, password);
        return dbConnect;
       
       
    }
    catch(Exception e) 
  	{
    	Log.e("Connection","PROBLEME De Connexion : "+e);
    	return null ;
    }
 }
 
}
 

