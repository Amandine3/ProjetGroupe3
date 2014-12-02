package com.example.projetgroupe3;

import java.sql.Connection;
import myconnections.DBConnection;
import ClassesDB.UtilisateurDB;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.os.AsyncTask;
import android.app.ProgressDialog;

public class CreerRoom2 extends ActionBarActivity {

	private Connection con = null;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_room2);
		 MyAccesDB adb = new MyAccesDB(CreerRoom2.this);
        adb.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_room2, menu);
		return true;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		 try {
	          con.close();
	          con=null;
	          Log.d("connexion","deconnexion OK");
	          }
	          catch (Exception e) {
	          }
		 Log.d("connexion","deconnexion OK");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class MyAccesDB extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;


				public MyAccesDB(MainActivity pActivity) {

					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(MainActivity pActivity) {
					// TODO Auto-generated method stub


				}

				protected void onPreExecute(){
					 super.onPreExecute();
			         pgd=new ProgressDialog(CreerRoom2.this);
					 pgd.setMessage("Connexion en cours");
					 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		     		 pgd.show();

				}

				@Override
				protected Boolean doInBackground(String... arg0) {

				   if(con==null)
				   {
					   	con = new DBConnection().getConnection();
				    	if(con==null)
				    	{
				    		resultat="ECHEC de la Connexion !";
				    		return false;
					    }
					   UtilisateurDB.setConnection(con);
				   }
             
			        try{

			        	UtilisateurDB.getListUser();

			        }
			        catch(Exception e){
			         resultat="erreur" +e.getMessage();
			         return false;

			         }


					return true;
				}

				protected void onPostExecute(Boolean result){
					// ce qui se passe après l'accès à la DB se met dans le OnPostExecute
					 super.onPostExecute(result);
					  pgd.dismiss();
					  //mettre le résultat dans la list view

				}

			}
}
