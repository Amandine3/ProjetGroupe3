package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.example.projetgroupe3.ListeRoomUtilisateur.MyAccesDB3;

import ClassesDB.MessageDB;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;

public class InterieurRoom extends ActionBarActivity{
	private ArrayList<String> messages;
	private Connection con;
	private ListView list=null;
	private Button envoi=null;
	Envoi en;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("AAA","AAA");
		setContentView(R.layout.activity_liste_roomut);
		Log.d("BBB","BBB");
		en = new Envoi(InterieurRoom.this);
		new Timer().schedule(new TimerTask(){
		public void run(){
			en.execute();
		}
		}, new Date(), 20000);
		envoi=(Button)findViewById(R.id.boutonenvoi);
		envoi.setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View v) {
						MessageDB mess=new MessageDB();
						mess.create();
						
					}
					
				});
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
			// TODO Auto-generated method stub
			return null;
		}
	}
	}
}
