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
import android.app.ProgressDialog;
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
	private ArrayList<String> liste=new ArrayList<String>();
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


		Log.d("execute r", "reception");
		Log.d("vald", "vald");
	    new Timer().schedule(new TimerTask()
		{
		public void run(){
			Log.d("run time", "run");
			if(r.getStatus()==AsyncTask.Status.FINISHED){
				r=new Reception(InterieurRoom.this);
				r.execute();
				Log.d("run + exec", "run +");
				Log.d("inv view", "dedans");
			}else
				Log.d("Tourne", "Tourne encore");
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
		private MessageDB mess;
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
		protected void onPreExecute()
		{
			Log.d("Dans pre", "ok");
            editText = (EditText) findViewById(R.id.editText1);
			mess=new MessageDB(editText.getText().toString(), idroom, pseudo);
			Log.d("Fin du pre", "ok jusque ici"+editText.getText().toString()+ " "+ pseudo+ " idroom "+idroom);

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

                    
            
            }
            MessageDB.setConnection(con);
            Log.d("Apres changement con", "Ok encore");
            try{
				mess.create();

			}
			catch(Exception e){
				e.printStackTrace();

				return false;
			}
			return true;
		}
		protected void onPostExecute(Boolean result){
			  super.onPostExecute(result);
			  if(result){
				  editText.setText("");
			  }
			  else{
					Toast.makeText(InterieurRoom.this, getString(R.string.prob) , Toast.LENGTH_SHORT).show();

			  }
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
            
            }
            RoomDB.setConnection(con);
			try{
				Log.d("Appel", "avant appel");
				mess=RoomDB.getMessageRoom(idroom);
			}
			catch(Exception e){
				return false;
			}
			Log.d("Findoin", "Fin");
			return true;
		}
		protected void onPostExecute(Boolean result){
			  super.onPostExecute(result);
			  if(result){
			Log.d("Dans le post", "ppopopop");
			String m;
			liste=new ArrayList<String>();
			for (int i=0;i<mess.size();i++){
				Log.d("Debut de la boucle", "debut");
				if(!mess.get(i).getPseudo().equals(pseudo)){
					Log.d("Dans la boucle", "cas 1dfghjkli!uytfdert");
					m=" "+mess.get(i).getPseudo()+getString(R.string.autre)+" "+mess.get(i).getContenu();
					Log.d("ZD", m);
					liste.add(m);
					Log.d("suite", "susdf");
				}
				else{
					Log.d("Dans la boucle", "cas 2");
					m=" "+getString(R.string.vous)+" "+mess.get(i).getContenu();
					liste.add(m);
				}
				liste.add("----------------");
				Log.d("Fin de la boucle", "fin");
			}
			if(mess.size()!=0){
				Log.d("Apres la boucle", "toujours bon");
				list = (ListView) findViewById(R.id.listMsg);
				Log.d("list", "list");
				ArrayAdapter<String> adapter = new  ArrayAdapter<String>(InterieurRoom.this,android.R.layout.simple_list_item_1,liste);
				Log.d("arrayAd","arrayAD");
				list.setAdapter(adapter);			Log.d("list adapt", "dedans");
				Log.d("execute r", "reception");

			}
			}
			  else{
					Toast.makeText(InterieurRoom.this, getString(R.string.prob2) , Toast.LENGTH_SHORT).show();
			  }
		}
	}
}
