package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import myconnections.DBConnection;
import ClassesDB.RoomDB;
import ClassesDB.UtilisateurDB;
import ClassesDB.UtilisateurRoomDB;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.content.Intent;

public class CreerRoom2 extends ActionBarActivity {
	private ArrayList<UtilisateurDB> liste;
	private Connection con = null;
	private Button accepte=null;
	private RoomDB ro;
	private String pseudo; 
	private ListView list=null;
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
					 pgd.setMessage("Connexion en cours...");
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
                            UtilisateurDB.setConnection(con);
                            RoomDB.setConnection(con);
                            Log.d("UTILISATEURDB","utilisateurdb");
                    
                    }  
                    try{
                    	Log.d("DANS TRY","try");
                            liste= UtilisateurDB.getListUser();
                            Log.d("Uts :","liste ok");
                            for(int i=0; i<liste.size();i++)
                            {
                            	Log.d("ELEMENT","Elt"+i+"est: "+liste.get(i));
                            }
   						   // Intent i=new Intent();
				        	//pseudo=i.getStringExtra("PSEUDO");
                            pseudo="Aurelien"; //pseudo en dur pour l'instant
                            Log.d("ps","ps");
				        	ro=new RoomDB(pseudo);
				        	Log.d("iii","ii");
				        	ro.create();
				        	Log.d("Ro : ", "ro ok");
                    }
                    catch(Exception e) {
                    	Log.d("Dans catch","catch");
                            resultat = "Erreur" +e.getMessage();
                            return false;
                    }
                    return true; 
                  }
				
				/*Log.d("Dans DoInBackground","pff");
				   if(con==null)
				   {
					   	con = new DBConnection().getConnection();
				    	if(con==null)
				    	{
				    		resultat="ECHEC de la Connexion !";
				    		Log.d("PAS BON", resultat);
				    		return false;
					    }
					   UtilisateurDB.setConnection(con);
					   Log.d("apres UtDB","pas bon");
				   }
             
			        try{
			        	Intent i=new Intent();
			        	String pseudo=i.getStringExtra("PSEUDO");
			        	RoomDB ro=new RoomDB(pseudo);
			        	ro.create();
			        	Log.d("apres RO.CREATE()" , "cav");
			        	liste=UtilisateurDB.getListUser();
			        	ListView list=(ListView)findViewById(R.id.listView1);
			        	ArrayList<String> exemple=new ArrayList<String>();
			        	for (int j=0;j<liste.size();j++){
			        		if(!liste.get(j).getPseudo().equals(pseudo)){
			        			exemple.add(liste.get(j).getPseudo());
			        		}
			        	}
			        	Log.d("ok", "test 42"+liste+" numroom : "+ro.getIdRoom());
			        	ArrayAdapter<String> adapter = new  ArrayAdapter<String>(CreerRoom2.this,android.R.layout.simple_list_item_multiple_choice,exemple);
			    		list.setAdapter(adapter);

			        }
			        catch(Exception e){
			         resultat="erreur" +e.getMessage();
			         return false;

			         }


					return true;*/
				

				protected void onPostExecute(Boolean result){
					Log.d("DANS PostExecute","dans pE");
					
					 super.onPostExecute(result);
					  pgd.dismiss();
					  Log.d("Avant if result", "if result avant");
					 if(result)
					 {


				        	Log.d("apres RO.CREATE()" , "cav");
				        	list=(ListView)findViewById(R.id.listView1);
				        	Log.d("list minuscule","list minus");
				        	ArrayList<String> exemple=new ArrayList<String>();
				        	String aajou;
				        	Log.d("avant boucle", "avant");
				        	int i=0;
				        	for (int j=0;j<liste.size();j++){
				        		Log.d("Avant if boucle", "avant if bc"+liste.get(j).getPseudo());
				        		if(!liste.get(j).getPseudo().equals(pseudo)){
				        			aajou=liste.get(j).getPseudo();
				        			Log.d("IF BOUCLE", liste.get(j).getPseudo());
				        			Log.d("AVANT EXEMPLE ADD", "avant exemple add221212"+ aajou);
				        			exemple.add(aajou);
				        			Log.d("Essai", "1212");
				        			Log.d("Ajouté : ", "ici :" + exemple.get(i));
				        			i++;
				        		}
				        	}
				        	Log.d("ok", "test 42"+liste+" numroom : "+ro.getIdRoom());
				        	ArrayAdapter<String> adapter = new  ArrayAdapter<String>(CreerRoom2.this,android.R.layout.simple_list_item_multiple_choice, exemple);
				        	Log.d("ARRAYADAPT","arrayadapt");
				    		list.setAdapter(adapter);
				    		accepte=(Button)findViewById(R.id.button1);
				    		accepte.setOnClickListener(
				    				new OnClickListener(){

										@Override
										public void onClick(View v) {
				    						SparseBooleanArray elts=list.getCheckedItemPositions();
				    						UtilisateurRoomDB a;
				    						 for(int i=0;i<liste.size();i++){
				    							 if(elts.get(i)){
				    								 a=new UtilisateurRoomDB(ro.getIdRoom(), liste.get(i).getPseudo());
				    								 try{
				    									 a.create();
				    									 
				    								 }
				    								 catch(Exception e){
				    									 Log.d("Erreur creation utilisateur room n°"+i, e.getMessage());
				    								 }
				    								 
				    							 }
				    							 i++;
				    						 }									
											
										}

								
				    		});
				    		Log.d("FIN","fin");
					 }
					  

				}

			}
}
