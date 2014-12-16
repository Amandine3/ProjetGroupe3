package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import com.example.projetgroupe3.CreerRoom2.MyAccesDB2;

import myconnections.DBConnection;

/*import com.example.projetgroupe3.CreerRoom2.MyAccesDB;
import com.example.projetgroupe3.CreerRoom2.MyAccesDB2;*/

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
//import ClassesDB.RoomDB;
//import ClassesDB.UtilisateurDB;
import ClassesDB.UtilisateurRoomDB;

public class ListeRoomUtilisateur extends ActionBarActivity{
	private ArrayList<Integer> liste;
	private Connection con;
	private ListView list=null;
	private Button accepte=null;
	private String pseudo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_roomut);
		Intent in = getIntent();
      	pseudo= in.getStringExtra("pseudo");
     	

		MyAccesDB3 adb = new MyAccesDB3(ListeRoomUtilisateur.this);
        adb.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_room2, menu); 
		return true;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		 try
		 {
	          con.close();
	          con=null;
	          Log.d("connexion","deconnexion OK");
	     }
	     catch (Exception e)
		 {}
		 Log.d("connexion","deconnexion OK");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class MyAccesDB3 extends AsyncTask<String,Integer,Boolean>
	{
		
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private String stringMSp = getString(R.string.spin);
	    
		public MyAccesDB3(ListeRoomUtilisateur pActivity)
		{

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(ListeRoomUtilisateur pActivity)
		{
			// TODO Auto-generated method stub


		}
		protected void onPreExecute()
		{
			 super.onPreExecute();
	         pgd=new ProgressDialog(ListeRoomUtilisateur.this);
			 pgd.setMessage(stringMSp);
			 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
     		 pgd.show();

		}

		
		 @Override
				protected Boolean doInBackground(String... arg0)
				{			        
                    if(con==null)
                    {
                            con = new DBConnection().getConnection();
                            if(con==null)
                            {
                                    resultat = "ECHEC de la connexion";
                                    Log.d("Con","Problème Connexion");
                                    return false;
                            }
                            Log.d("Con","Connexion OK");

                            UtilisateurRoomDB.setConnection(con);
                    
                    }  
                    try
                    {
                		liste= UtilisateurRoomDB.readRoom(pseudo);
                        for(int i=0; i<liste.size();i++)
                        {
                        	Log.d("ELEMENT","Elt"+i+"est: "+liste.get(i));
                        }


                    }
                    catch(Exception e)
                    {

                            resultat = "Erreur" +e.getMessage();
                            return false;
                    }
                    return true; 
                  }
				
				
				protected void onPostExecute(Boolean result)
				{
					
					  super.onPostExecute(result);
					  pgd.dismiss();

					 if(result)
					 {
				        	list=(ListView)findViewById(R.id.listView1);
				        	ArrayAdapter<Integer> adapter = new  ArrayAdapter<Integer>(ListeRoomUtilisateur.this,android.R.layout.simple_list_item_single_choice, liste);
				    		list.setAdapter(adapter);
				    		accepte=(Button)findViewById(R.id.boutonVal);
				    		accepte.setOnClickListener(
				    				new OnClickListener(){

										@Override
										public void onClick(View v) {
											  int ro=-1;
											  SparseBooleanArray elts=list.getCheckedItemPositions();

											  for(int i=0;i<liste.size();i++){

												  if(elts.get(i)){

													  ro=liste.get(i);
													  break;
		
												  }
											  }
											  if(ro>0){

												  Intent it = new Intent(ListeRoomUtilisateur.this,InterieurRoom.class);
												  it.putExtra("pseudo",pseudo);
												  it.putExtra("idroom", ro);
												  startActivity(it);
												  //finish();
												  setContentView(R.layout.activity_msg_room);
											  }
											  
											  else{
												  Toast.makeText(ListeRoomUtilisateur.this, R.string.err , Toast.LENGTH_SHORT).show();
											  }
										}
				    		});
					 }

			}
		 

	}
}
