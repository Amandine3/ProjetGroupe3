package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import myconnections.DBConnection;
import ClassesDB.RoomDB;
import ClassesDB.UtilisateurDB;
import ClassesDB.UtilisateurRoomDB;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
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

public class CreerRoom2 extends ActionBarActivity
{
	private ArrayList<UtilisateurDB> liste;
	private Connection con = null;
	private Button accepte=null;
	private RoomDB ro;
	private String pseudo; 
	private ListView list=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_room2);
		
	      try{
          	
          	Intent in = getIntent();
          	pseudo= in.getStringExtra("pseudo");
          	Log.d(" ! pseudo CREEROOM 2 ! ", pseudo);
          }
                   	
          
          catch(Exception e)
          {
          	Log.d("recuperation du pseudo CREERROOM2", "ECHOUEEE");
          }

		 MyAccesDB adb = new MyAccesDB(CreerRoom2.this);
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
	
	class MyAccesDB extends AsyncTask<String,Integer,Boolean>
	{
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private String stringM = getString(R.string.message);

				public MyAccesDB(CreerRoom2 pActivity)
				{

					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(CreerRoom2 pActivity)
				{
					// TODO Auto-generated method stub


				}

				protected void onPreExecute()
				{
					 super.onPreExecute();
			         pgd=new ProgressDialog(CreerRoom2.this);
					 pgd.setMessage(stringM);
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
                    
                    }  
                    try
                    {

                            liste= UtilisateurDB.getListUser();
                            for(int i=0; i<liste.size();i++)
                            {
                            	Log.d("ELEMENT","Elt"+i+"est: "+liste.get(i));
                            }
/*Thomas 
 * try{
			Intent i=getIntent();			
			id = Integer.parseInt(i.getStringExtra("sendid2"));
			Log.d("MAdmin get i",""+id);
			list = i.getParcelableExtra(MainActivity.LISTDEP);
			Log.d("MAdmin get i","liste ok");
		}catch(Exception ex){
			Log.d("Test Intend MAdmin",""+ex.getMessage());
		}
 */
               
                            	Log.d("le pseudo c'est :"+pseudo, "OK");
                            	 //pseudo="Aurelien"; //pseudo en dur pour l'instant
    				        	ro=new RoomDB(pseudo);
    				        	ro.create();
                            	
                           
                            
                           
				        	
				        	

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
				        	ArrayList<String> exemple=new ArrayList<String>();
				        	String aajou;
				        	Iterator it=liste.iterator();
				        	int i=0;
				        	while(it.hasNext()){
				        		it.next();
				        		if(liste.get(i).getPseudo().equals(pseudo))
				        			it.remove();
				        		else{
				        			exemple.add(liste.get(i).getPseudo());
				        			i++;
				        		}
				        	}
				  
				        	ArrayAdapter<String> adapter = new  ArrayAdapter<String>(CreerRoom2.this,android.R.layout.simple_list_item_multiple_choice, exemple);
				    		list.setAdapter(adapter);
				    		accepte=(Button)findViewById(R.id.button1);
				    		accepte.setOnClickListener(
				    				new OnClickListener(){

										@Override
										public void onClick(View v) {
				    					     MyAccesDB2 dd=new MyAccesDB2(CreerRoom2.this);
				    					     dd.execute();
				    						
											
										}
				    		});
					 }
					  

				}

			}
	class MyAccesDB2 extends AsyncTask<String,Integer,Boolean>
	{
	    private String resultat;
	    private ProgressDialog pgd=null;
	   
	    private String mgSMS = getString(R.string.messms);
	    private String contenuSMS = getString (R.string.contsms);


				public MyAccesDB2(CreerRoom2 pActivity) {

					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(CreerRoom2 pActivity) {
					// TODO Auto-generated method stub


				}

				protected void onPreExecute(){
					 super.onPreExecute();
			         pgd=new ProgressDialog(CreerRoom2.this);
					 pgd.setMessage(mgSMS);
					 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		     		 pgd.show();

				}

				@Override
				protected Boolean doInBackground(String... params) {
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
					SparseBooleanArray elts=list.getCheckedItemPositions();
					SmsManager ma=SmsManager.getDefault(); 
					UtilisateurRoomDB a;
				    for(int i=0;i<liste.size();i++){
						 Log.d("LISTE => ELTS(get(i)) aff liste ", " element n° " + i +": : "+elts.get(i));
						 if(elts.get(i)){
							 Log.d("IF ELTS.get(i)","if elts"+ro.getIdRoom()+"   "+liste.get(i).getPseudo());
							 a=new UtilisateurRoomDB(ro.getIdRoom(), liste.get(i).getPseudo());
							 Log.d("a: ", a.toString());
							 try{
								 ma.sendTextMessage(liste.get(i).getNumgsm(), null, contenuSMS + ro.getIdRoom(), null, null);
								 Log.d("ENVOI DU SMS ", " FAIT A : "+liste.get(i).getNumgsm());
								 a.create();
								 Log.d("CREATION UT", "creer ok");
							 }
							 catch(Exception e){
								 Log.d("Erreur creation utilisateur room n°"+i, e.getMessage());
							 }
							 
						 }
					}
                            return null;
				}
				
				protected void onPostExecute(Boolean result){
					
					 super.onPostExecute(result);
					  pgd.dismiss();
					  Intent i = new Intent(CreerRoom2.this,InterieurRoom.class);
					  i.putExtra("pseudo",pseudo);
					  i.putExtra("idroom", ro.getIdRoom());
					  startActivity(i);
					  finish();
					  setContentView(R.layout.activity_creer_room3);
					  
				}
	}
}
