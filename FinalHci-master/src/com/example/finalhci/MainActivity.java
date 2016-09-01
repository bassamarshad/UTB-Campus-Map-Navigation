package com.example.finalhci;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity implements OnClickListener {
  Spinner spnr;
  Spinner spnr2;
  Button button1;
  TextView title_saballhsb;
  MediaPlayer mp;
  
  String[] buildings = {
	  "Select a building",
      "LHSB",
      "BRHP",
      "MAIN",
      "SABAL"
  };
  String[] buildings2 = {
	      "Select a building",
		  "LHSB",
	      "BRHP",
	      "MAIN",
	      "SABAL"
	  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    spnr = (Spinner)findViewById(R.id.spinner1);
    spnr2 = (Spinner)findViewById(R.id.spinner2);
    button1 = (Button) findViewById(R.id.button1);
    mp  = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);
    title_saballhsb = (TextView) findViewById(R.id.title_saballhsb);
    
    button1.setOnClickListener(this);
    
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        this, R.layout.simple_spinner_item, buildings);
    
    spnr.setAdapter(adapter);
    
    spnr.setOnItemSelectedListener(
              new AdapterView.OnItemSelectedListener() {
            	  
                  @Override
                  public void onItemSelected(AdapterView<?> arg0, View arg1,
                          int arg2, long arg3) {
                    int position = spnr.getSelectedItemPosition();
                    if(spnr.getSelectedItem() == "LHSB" || spnr.getSelectedItem() == "BRHP" || spnr.getSelectedItem() == "MAIN" || spnr.getSelectedItem() == "SABAL")
                    Toast.makeText(getApplicationContext(),"You have selected "+ buildings[+position] + " as your first building",Toast.LENGTH_SHORT).show();
                      // TODO Auto-generated method stub
                  }
                  @Override
                  public void onNothingSelected(AdapterView<?> arg0) {
                      // TODO Auto-generated method stub
                  }
              }
          );
    
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
            this, R.layout.simple_spinner_item, buildings2);
    
    spnr2.setAdapter(adapter2);
    spnr2.setOnItemSelectedListener(  new AdapterView.OnItemSelectedListener() {
  	  
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                int arg2, long arg3) {
          int position2 = spnr2.getSelectedItemPosition();
          if(spnr2.getSelectedItem() == "LHSB" || spnr2.getSelectedItem() == "BRHP" || spnr2.getSelectedItem() == "MAIN" || spnr2.getSelectedItem() == "SABAL")

          Toast.makeText(getApplicationContext(),"You have selected "+ buildings2[+position2] + " as your second building",Toast.LENGTH_SHORT).show();
            // TODO Auto-generated method stub
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
);
    
    
    
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu items for use in the action bar
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu, menu);
      return super.onCreateOptionsMenu(menu);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      // Handle presses on the action bar items
      switch (item.getItemId()) {
          case R.id.action_settings:
        	  mp.start();
              openSettings();
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }
  }
  
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	mp.start();
	if(v.getId() == R.id.button1){
		if(spnr.getSelectedItem() == "SABAL" && spnr2.getSelectedItem() == "LHSB" || spnr.getSelectedItem() == "LHSB" && spnr2.getSelectedItem() == "SABAL" ){
			Intent i = new Intent(this, SabalToLhsb.class);
			startActivity(i);
		}
	if(spnr.getSelectedItem() == "Select a building" || spnr2.getSelectedItem() == "Select a building"){
		Toast.makeText(this, "Please enter 2 different buildings!", Toast.LENGTH_LONG).show();
			}
		
		}
	}

public void openSettings(){
	Toast.makeText(this, "Julian Espinosa \n" + " Bassam Arshad \n" + " Jaafar El Alamy", Toast.LENGTH_LONG).show();
}
}









