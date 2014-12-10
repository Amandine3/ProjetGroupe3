package com.example.projetgroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
{

	Button bouton1=null;
	Button bouton2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choisir_rejoucreer_room);
		bouton1=(Button)findViewById(R.id.boutonA);//rejoindre room
		bouton2=(Button)findViewById(R.id.boutonB);//creer room
		
		bouton1.setOnClickListener(
				new OnClickListener(){
					
					public void onClick(View v){

						
						Intent i = new Intent(MainActivity.this,ListeRoomUtilisateur.class);
						startActivity(i);
					
					}
				  }
				);
	
		bouton2.setOnClickListener(
				new OnClickListener(){
					
					public void onClick(View v){

						
						Intent i = new Intent(MainActivity.this,CreerRoom.class);
						startActivity(i);
					
					}
				  }
				);

	}
	public void bannuler(View v)
	{
		Toast.makeText(this, R.string.pasEnCours , Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_room, menu);
		return true;      
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
}
