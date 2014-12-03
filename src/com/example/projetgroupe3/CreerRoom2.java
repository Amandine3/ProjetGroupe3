package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;

import myconnections.DBConnection;
import ClassesDB.RoomDB;
import ClassesDB.UtilisateurDB;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.content.Intent;

public class CreerRoom2 extends ActionBarActivity {
	private ArrayList<UtilisateurDB> liste;
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


				public MyAccesDB(CreerRoom2 pActivity) {

					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(CreerRoom2 pActivity) {
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
			        	Intent i=new Intent();
			        	RoomDB ro=new RoomDB(i.getStringExtra("PSEUDO"));
			        	ro.create();
			        	liste=UtilisateurDB.getListUser();
			        	ListView list;
			        	ArrayList<String> exemple=new ArrayList<String>();
			        	for (int j=0;j<liste.size();j++){
			        		exemple.add(liste.get(j).getPseudo());
			        	}
			        	Log.d("ok", "test 42"+liste+" numroom : "+ro.getIdRoom());
			        	ArrayAdapter<String> adapter = new  ArrayAdapter<String>(CreerRoom2.this,android.R.layout.simple_list_item_multiple_choice,exemple);
			    		list.setAdapter(adapter);

			        }
			        catch(Exception e){
			         resultat="erreur" +e.getMessage();
			         return false;

			         }


					return true;
				}

				protected void onPostExecute(Boolean result){
					
					 super.onPostExecute(result);
					  pgd.dismiss();
					  

				}

			}
}
