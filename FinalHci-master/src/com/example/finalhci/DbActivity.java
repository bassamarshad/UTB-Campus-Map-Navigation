package com.example.finalhci;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DbActivity extends Activity 
{
	Button btnSignIn,btnSignUp;
	LoginDataBaseAdapter loginDataBaseAdapter;
	MediaPlayer mp;
	int backButtonCount = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.db_activity);
	     
	     // create a instance of SQLite Database
	     loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
	     
	     // Get The Reference Of Buttons
	     btnSignIn=(Button)findViewById(R.id.buttonSignIN);
	     btnSignUp=(Button)findViewById(R.id.buttonSignUP);
	     // sound on clicks
	    mp  = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);
	   
	    // Set OnClick Listener on SignUp button 
	    btnSignUp.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			//click sound
			mp.start();
			/// Create Intent for SignUpActivity  and Start The Activity
			Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
			startActivity(intentSignUP);
			}
		});
	}
	// Methods to handleClick Event of Sign In Button
	public void signIn(View V)
	   {
			mp.start();
			final Dialog dialog = new Dialog(DbActivity.this);
			dialog.setContentView(R.layout.login);
		    dialog.setTitle("Login");
		    
	
		    // get the Refferences of views
		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
		    final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
		    
			Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
				
			// Set On ClickListener
			btnSignIn.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// get The User name and Password
					String userName=editTextUserName.getText().toString();
					String password=editTextPassword.getText().toString();
					mp.start();
					// fetch the Password form database for respective user name
					String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
					
					// check if the Stored password matches with  Password entered by user
					if(password.equals(storedPassword))
					{
						Toast.makeText(DbActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
						dialog.dismiss();
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
						startActivity(i);
					}
					else
					{
						Toast.makeText(DbActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
					}
				}
			});
			
			dialog.show();
	}
	// ACTION BAR MENU WITH THE OPTION OF ACCOUNT TUTORIAL
	 @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu items for use in the action bar
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.tutorialaccount, menu);
	      return super.onCreateOptionsMenu(menu);
	  }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	      // Handle presses on the action bar items
	      switch (item.getItemId()) {
	          case R.id.action_settings2:
	              openTutorial();
	              return true;
	          default:
	              return super.onOptionsItemSelected(item);
	      }
	  }
	
	// RANDOM STUFF TO HAVE MORE FEATURES.... NEED TO ADD STUFF
	private void openTutorial() {
		final Dialog dialog = new Dialog(DbActivity.this);
		dialog.setContentView(R.layout.account_tutorial);
	    dialog.setTitle("Create Account Tutorial");
	    mp.start();
		// STEPS FOR CREATING ACCOUNT AND LOGIN..... OR USE IMAGES GRIDVIEW ETC....
	    
	Button btnGoBack = (Button)dialog.findViewById(R.id.buttonGoBack);
	btnGoBack.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			mp.start();
			dialog.cancel(); // go back to previous activity 

			
		}
	});
	    
	    dialog.show();
		
		
	}
	// SO THE USER CAN'T GO BACK TO THE INTRODUCTION ACTIVITY
	public void onBackPressed()
	{
		
	    if(backButtonCount >= 1)
	    {
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	    }
	    else
	    {
	        Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
	        backButtonCount = backButtonCount + 1;
	    }
	}
	
	
}




 