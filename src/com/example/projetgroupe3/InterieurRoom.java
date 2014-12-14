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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("AAA","AAA");
		setContentView(R.layout.activity_liste_roomut);
		Log.d("BBB","BBB");
		//récupérer les pseudo et idroom
		r = new Reception(InterieurRoom.this);
		envoi=(Button)findViewById(R.id.boutonenvoi);
		envoi.setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View v) {
						Envoi e=new Envoi(InterieurRoom.this);
						e.execute();
					}
					
				});
		new Timer().schedule(new TimerTask(){
		public void run(){
			r.execute();
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

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InterieurRoom pActivity)
		{
			// TODO Auto-generated method stub


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

                    MessageDB.setConnection(con);
            
            }
			MessageDB mess=new MessageDB("Ce qu'il y a dans le textfield", idroom, pseudo);
			try{
				mess.create();
			}
			catch(Exception e){
				//creer un toast disant qu'il y a eu un probleme lors de l'envoi du message
			}
			return null;
		}
	}
	class Reception extends AsyncTask<String,Integer,Boolean>{
		public Reception(InterieurRoom pActivity)
		{

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InterieurRoom pActivity)
		{
			// TODO Auto-generated method stub


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

                    RoomDB.setConnection(con);
            
            }
			try{
				mess=RoomDB.getMessageRoom(idroom);
				for (int i=0;i<mess.size();i++){
					//Faire une meilleure mise en page
					liste.add(mess.get(i).getPseudo()+mess.get(i).getContenu());
				}
			}
			catch(Exception e){
				//creer un toast disant qu'il y a eu un probleme lors de l'envoi du message
			}
			return null;
		}
		protected void onPostExecute(){
			list=(ListView)findViewById(R.id.listView1);
			//faire que la liste s'affiche depuis la fin -_- et faire qu'on peut pas cocher
			//Manque la redirection dans listeRoomUtilisateur et peut être changer les contenus des resultat afin de les traduire :'(
			ArrayAdapter<String> adapter = new  ArrayAdapter<String>(InterieurRoom.this,android.R.layout.simple_list_item_single_choice, liste);
			list.setAdapter(adapter);
		}
	}

}
