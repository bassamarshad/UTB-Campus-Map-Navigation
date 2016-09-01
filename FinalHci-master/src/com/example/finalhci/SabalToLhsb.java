package com.example.finalhci;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;

import java.lang.Math;
import java.text.DecimalFormat;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SabalToLhsb extends Activity{
	
	TextView textviewsaballhsb_distance, currentposition, sabaltodevice, lhsbtodevice;
	LinearLayout directions1;
	Button btnSabalLhsb_back;
	double sabal_lat = 25.892845;
	
	final double  radius = 6371; // earth
	double sabal_lon = -97.487843;
	
	double lhsb_lat = 25.895953;
	double lhsb_lon = -97.486717;
	DecimalFormat formater = new DecimalFormat("#.##");
	DecimalFormat formaterCoords = new DecimalFormat("#.######");
	
	LocationManager manager;
	MediaPlayer mp;
	//////////////

	/////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sabal_to_lhsb);
		
		textviewsaballhsb_distance = (TextView) findViewById(R.id.textviewsaballhsb_distance);
		currentposition = (TextView) findViewById(R.id.currentposition);
		sabaltodevice = (TextView) findViewById(R.id.sabaltodevice);
		lhsbtodevice = (TextView) findViewById(R.id.lhsbtodevice);
		directions1 = (LinearLayout) findViewById(R.id.directions1);
		btnSabalLhsb_back = (Button) findViewById(R.id.btnSabalLhsb_back);
	    mp  = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);

		// calling button to go back on click
		goingBack();
		
		 double distance;

		 Location locationA = new Location("Sabal");

		 locationA.setLatitude(sabal_lat);

		 locationA.setLongitude(sabal_lon);

		 Location locationB = new Location("LHSB");

		 locationB.setLatitude(lhsb_lat);

		 locationB.setLongitude(lhsb_lon);

		 distance = locationA.distanceTo(locationB);
		 String msg = "The distance between SABAL and LHSB is approximately: " + formater.format(distance) +  " meters.";
		 textviewsaballhsb_distance.setText(msg);
		 
		 currentposition.setText("Getting Your Position Please Wait...");
		
		/////////////// GPS /////////////////
		//import android.location.LocationManager;		
		manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener listener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {

				
				// for testing
				currentposition.setText("Your Current Coords are: " + "\nLatitude: " + formaterCoords.format(location.getLatitude()) + "\nLongitude: " + formaterCoords.format(location.getLongitude()));
				/////////////
				
			
				/// THIS IS THE MAIN PART FOR GETTING DISTANCE
				 double distance, distance2;

				 Location location_sabal = new Location("Sabal");
				 location_sabal.setLatitude(sabal_lat);
				 location_sabal.setLongitude(sabal_lon);
				 Location location_lhsb = new Location("LHSB");
				 location_lhsb.setLatitude(lhsb_lat);
				 location_lhsb.setLongitude(lhsb_lon);   // getting lat and lon from both buildings hard coded
				 
				 Location locationB = new Location("US");

				 locationB.setLatitude(location.getLatitude());

				 locationB.setLongitude(location.getLongitude());

				 distance = location_sabal.distanceTo(locationB);
				 distance2 = location_lhsb.distanceTo(locationB);
				 /////////Displaying in textViews //////////
				 String msg2 = "The remaining distance to Sabal is: " + formater.format(distance) + " meters.";
				sabaltodevice.setText(msg2); // DEVICE TO SABAL
				
				String msg3 = "The remaining distance to LHSB is: " + formater.format(distance2) + " meters.";
				lhsbtodevice.setText(msg3); // DEVICE TO LHSB
				//////////////////////////////////////////////
	
				//addProximityAlert(25.897978,-97.487525);
//				25.897978
//				-97.487525
			//////////////////////////
				//directions1   TESTING IF IM INSIDE THE COORDS CHANGE THE COLOR LAYOUT FROM DIRECTION 1
				if(Double.parseDouble(formaterCoords.format(location.getLatitude()))>= 25.897800 &&
						Double.parseDouble(formaterCoords.format(location.getLatitude())) <= 25.897950 ){
					directions1.setBackgroundColor(Color.BLACK);
				}else{
					directions1.setBackgroundColor(Color.GREEN);
				}
			
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
		};
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER	, 0, 0, listener);
	
		
		
	}
	//going back
			public void goingBack(){
				btnSabalLhsb_back.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						mp.start();
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(i);
					}
				});
			}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sabal_to_lhsb, menu);
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
	
//private void addProximityAlert(double latitude, double longitude) {
//        
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//        
//        manager.addProximityAlert(
//        		latitude, // the latitude of the central point of the alert region
//        		longitude, // the longitude of the central point of the alert region
//            5, // the radius of the central point of the alert region, in meters
//            3000, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration 
//            proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
//       );
//        
////       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
////       registerReceiver(new ProximityIntentReceiver(), filter);
//       
//       Intent i = new Intent(Intent.ACTION_SEND);
//       i.setType("text/plain");
//       i.putExtra(Intent.EXTRA_EMAIL  , new String[] { "recipient@example.com" });
//       i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//       i.putExtra(Intent.EXTRA_TEXT   , "body of email");
//       try {
//           Toast.makeText(this, "You're inside the CIRCLE", Toast.LENGTH_LONG).show();
//
//       } catch (android.content.ActivityNotFoundException ex) {
//           Toast.makeText(this, "You're outside of the circle.", Toast.LENGTH_LONG).show();
//       }
//       
//       
//    }


	
	
}




/*
 
 
 */
