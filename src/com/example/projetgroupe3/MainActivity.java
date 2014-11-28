package com.example.projetgroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	Button boutonOk=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_room);
		boutonOk=(Button)findViewById(R.id.button1);

		
		boutonOk.setOnClickListener(
				new OnClickListener(){
					
					public void onClick(View v){

						
						Intent i = new Intent(MainActivity.this,CreerRoom2.class);
					  	//pour une arrayList de personnes :i.putParcelableArrayListExtra(ID_LIST, listePersonnes);
						startActivity(i);
					
					}//fin m�thode onClick
				  }//fin classe onClickLister
				);//fin param�trage m�thode setOnClickListener
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
