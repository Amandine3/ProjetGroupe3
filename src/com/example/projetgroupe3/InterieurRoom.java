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
		setContentView(R.layout.activity_msg_room);

      	Intent in = getIntent();
      	pseudo= in.getStringExtra("pseudo");
      	idroom=in.getIntExtra("idroom", -1);

		r = new Reception(InterieurRoom.this);
		envoi=(Button)findViewById(R.id.boutonenvoi);

		envoi.setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View v) {
						e=new Envoi(InterieurRoom.this);
						e.execute();

					}
					
				});
		r.execute();


	    new Timer().schedule(new TimerTask()
		{
		public void run(){

			if(r.getStatus()==AsyncTask.Status.FINISHED)
			{
				r=new Reception(InterieurRoom.this);
				r.execute();

			}
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
			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InterieurRoom pActivity)
		{
			// TODO Auto-generated method stub


		}
		protected void onPreExecute()
		{
            editText = (EditText) findViewById(R.id.editText1);
			mess=new MessageDB(editText.getText().toString(), idroom, pseudo);

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

                    
            
            }
            MessageDB.setConnection(con);

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
            
            }
            RoomDB.setConnection(con);
			try{

				mess=RoomDB.getMessageRoom(idroom);
			}
			catch(Exception e){
				return false;
			}

			return true;
		}
		protected void onPostExecute(Boolean result){
			  super.onPostExecute(result);
			  if(result)
			  {

				  String m;
				  liste=new ArrayList<String>();
				  for (int i=0;i<mess.size();i++)
				  {

					  if(!mess.get(i).getPseudo().equals(pseudo)){
						  m=" "+mess.get(i).getPseudo()+getString(R.string.autre)+" "+mess.get(i).getContenu();
						  liste.add(m);
					  }
					  else
					  {
					
						  m=" "+getString(R.string.vous)+" "+mess.get(i).getContenu();
						  liste.add(m);
					  }
					  liste.add("----------------");
				
				  }
				  if(mess.size()!=0)
				  {

					  list = (ListView) findViewById(R.id.listMsg);
					  ArrayAdapter<String> adapter = new  ArrayAdapter<String>(InterieurRoom.this,android.R.layout.simple_list_item_1,liste);
					  list.setAdapter(adapter);			

				  }
			}
			else
			{
					Toast.makeText(InterieurRoom.this, getString(R.string.prob2) , Toast.LENGTH_SHORT).show();
			}
		}
	}
}
