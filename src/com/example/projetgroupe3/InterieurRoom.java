package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import myconnections.DBConnection;

import com.example.projetgroupe3.ListeRoomUtilisateur.MyAccesDB3;

import ClassesDB.MessageDB;
import ClassesDB.RoomDB;
import ClassesDB.UtilisateurRoomDB;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class InterieurRoom extends ActionBarActivity{
	private ArrayList<String> messages;
	private Connection con;
	private ListView list=null;
	private Button envoi=null;
	private Envoi e;
	private Reception r;
	private ArrayList<MessageDB> mess;
	private String resultat;
	private ArrayList<String> liste;
	private String pseudo;
	private int idroom;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("AAA INTERIEUR ROOM ","AAA");
		setContentView(R.layout.activity_msg_room);
		Log.d("BBB INTERIEUR ROOM ","BBB");
		//récupérer les pseudo et idroom
		/*
		 * MainActivity intent de pseudo
		 * a transmettre de truc en truc... 
		 * quand besoin recuperer i.putExtra + faire passer apres
		 * 
		 * ou sont les méthodes post execute et pre execute ?
		 * 
		 */
      	Intent in = getIntent();
      	Log.d("Intent interieur Room", "intent");
      	pseudo= in.getStringExtra("pseudo");
     	Log.d("Pseudo Interieur Room ::", pseudo);
      	idroom=in.getIntExtra("idroom", -1);
     	Log.d("Idroom interieur Room", "la la "+ idroom);
		r = new Reception(InterieurRoom.this);
	 	Log.d("r interieur Room", "li li " + r);
		envoi=(Button)findViewById(R.id.boutonenvoi);
	 	Log.d("bouton envoie", "envoi");
		envoi.setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View v) {
						Log.d("bouton envoie 22", "envoi");
						e=new Envoi(InterieurRoom.this);
						Log.d("bouton envoie 33 " , "envoi");
						e.execute();
						Log.d("bouton envoie 44 exec ", "envoi");
					}
					
				});
		Log.d("R.exec", "r");
		r.execute();
		list = (ListView) findViewById(R.id.listMsg);
		Log.d("list", "list");
		ArrayAdapter<String> adapter = new  ArrayAdapter<String>(InterieurRoom.this,android.R.layout.simple_list_item_multiple_choice,liste);
		Log.d("arrayAd","arrayAD");
		list.setAdapter(adapter);
		Log.d("list adapt", "dedans");

		Log.d("execute r", "reception");
		list.invalidateViews();
		Log.d("vald", "vald");
		new Timer().schedule(new TimerTask()
		{
		public void run(){
			Log.d("run time", "run");
			r.execute();
			Log.d("run + exec", "run +");
			list.invalidateViews();
			Log.d("inv view", "dedans");
		}
		}, new Date(), 20000);

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
	class Envoi extends AsyncTask<String,Integer,Boolean>{
		public Envoi(InterieurRoom pActivity)
		{
			Log.d("Dans ENVOI IntROOm CONSTRUCTEUR", "dedans1");
			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InterieurRoom pActivity)
		{
			// TODO Auto-generated method stub
			Log.d("Dans LINK de IntROOm", "dedans2");


		}
		@Override
		protected Boolean doInBackground(String... params) {
			Log.d("Dans DOIN de IntROOm", "dedans3");
            if(con==null)
            {
                    con = new DBConnection().getConnection();
                    if(con==null)
                    {
                    	Log.d("Dans DOIN de IntROOm - if1", "dedans");
                            resultat = "ECHEC de la connexion";
                            Log.d("Con","Problème Connexion");
                            return false;
                    }
                    Log.d("Con","Connexion OK");

                    MessageDB.setConnection(con);
            
            }
            editText = (EditText) findViewById(R.id.editText1);
			MessageDB mess=new MessageDB(editText.getText().toString(), idroom, pseudo);
			try{
				mess.create();
			}
			catch(Exception e){

				Toast.makeText(InterieurRoom.this, R.string.prob , Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}
	class Reception extends AsyncTask<String,Integer,Boolean>{
		public Reception(InterieurRoom pActivity)
		{

			Log.d("Dans RECEPTION de IntROOm", "dedans");
			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InterieurRoom pActivity)
		{
			// TODO Auto-generated method stub
			Log.d("Reception link", "dedans");


		}
		@Override
		protected Boolean doInBackground(String... params) {
			Log.d("Dans DOIN de RECEPTION IntROOm", "dedans");
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

                    RoomDB.setConnection(con);
            
            }
			try{
				mess=RoomDB.getMessageRoom(idroom);
				for (int i=0;i<mess.size();i++){
					if(!mess.get(i).getPseudo().equals(pseudo)){
						liste.add(mess.get(i).getPseudo()+R.string.autre+mess.get(i).getContenu());
					}
					else{
						liste.add(mess.get(i).getPseudo()+R.string.vous+mess.get(i).getContenu());
					}
					liste.add("----------------");
				}
			}
			catch(Exception e){

				Toast.makeText(InterieurRoom.this, R.string.prob2 , Toast.LENGTH_SHORT).show();
				
			}
			return null;
		}
	}
}
