package com.example.finalhci;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class InitialWindow extends Activity {
	TextView welcomedevelopers;
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_window);
		
		welcomedevelopers = (TextView) findViewById(R.id.welcomeDevelopers);
		String message = "  Developep by: \n" + "Julian Espinosa \n" + "Bassam Arshad \n" + "Jaafar El Alamy";
		welcomedevelopers.setText(message);
	    mp  = MediaPlayer.create(getApplicationContext(), R.raw.magic1);
	    mp.start();
		 new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {

                 Intent i = new Intent(getApplicationContext(),DbActivity.class);
                 startActivity(i);
             }
         }, 5000);
		 
		 
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.initial_window, menu);
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
