package com.example.projetgroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CreerRoom extends ActionBarActivity
{

	Button boutonOk=null;
	Button bannuler = null;
	private String pseudo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_room);
		boutonOk=(Button)findViewById(R.id.button1);
		bannuler=(Button)findViewById(R.id.button2);
		
		boutonOk.setOnClickListener(
				new OnClickListener(){
					
					public void onClick(View v){
					     try{
					          	
					          	Intent in = getIntent();
					          	pseudo= in.getStringExtra("pseudo");
					          	Log.d("Pseudo de CREERROOM",pseudo);
					          }
					                   	
					          
					          catch(Exception e)
					          {
					          	Log.d("recuperation du pseudo CREERROOM ", "ECHOUEEE");
					          }

						
						Intent i = new Intent(CreerRoom.this,CreerRoom2.class);
						i.putExtra("pseudo",pseudo);
						startActivity(i);
						finish();
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
