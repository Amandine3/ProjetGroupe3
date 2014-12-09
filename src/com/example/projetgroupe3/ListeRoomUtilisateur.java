package com.example.projetgroupe3;

import java.sql.Connection;
import java.util.ArrayList;

import com.example.projetgroupe3.CreerRoom2.MyAccesDB;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import ClassesDB.UtilisateurRoomDB;

public class ListeRoomUtilisateur extends ActionBarActivity{
	private ArrayList<UtilisateurRoomDB> liste;
	private Connection con;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_room2);
		 MyAccesDB adb = new MyAccesDB(ListeRoomUtilisateur.this);
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
		public MyAccesDB(ListeRoomUtilisateur pActivity)
		{

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(ListeRoomUtilisateur pActivity)
		{
			// TODO Auto-generated method stub


		}

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}}
	

}
