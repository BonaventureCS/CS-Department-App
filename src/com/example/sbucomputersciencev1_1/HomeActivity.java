package com.example.sbucomputersciencev1_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		 configureAlumniButton();
	        configureDirectoryButton();
	        configureCareersButton();
	        configureVideoButton();
	        configureHomeButton();
	        configureNFCButton();
	        configureSeniorButton();
	        configureMapButton();
	}
	
	public void configureHomeButton(){
		Button b = (Button) findViewById(R.id.btnHome);
		b.setEnabled(false);
		//b.setOnClickListener(new OnClickListener() {
			//public void onClick(View view) {
			//	Intent intent = new Intent(view.getContext(), VideoListActivity.class);
			//	startActivity(intent);
		//	}
		//});	
	}
	
	public void configureVideoButton(){
		Button b = (Button) findViewById(R.id.btnVideos);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), VideoListActivity.class);
				
				startActivity(intent);
			}
		});	
	}
	public void configureNFCButton(){
		Button b = (Button) findViewById(R.id.btnNFC);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), NFCActivity.class);
				startActivity(intent);
			}
		});	
	}

	public void configureAlumniButton(){
		Button b = (Button) findViewById(R.id.alumni);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), AlumniActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void configureDirectoryButton(){
		Button b = (Button) findViewById(R.id.directory);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), DirectoryActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void configureCareersButton(){
		Button b = (Button) findViewById(R.id.careers);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), CareerActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void configureSeniorButton(){
		Button b = (Button) findViewById(R.id.senior);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), SeniorActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void configureMapButton(){
		Button b = (Button) findViewById(R.id.map);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), MapActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
}
