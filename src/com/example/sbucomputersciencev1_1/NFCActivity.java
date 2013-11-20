package com.example.sbucomputersciencev1_1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class NFCActivity extends ListActivity {

	String [] clues = {
			"Clue 1",
			"Clue 2",
			"Clue 3",
			
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc);
		 setListAdapter(new ArrayAdapter<String>(this,
	                R.layout.mytext, clues));
		
	        configureVideoButton();
	        configureHomeButton();
	        configureNFCButton();
	}
	
	public void configureHomeButton(){
		Button b = (Button) findViewById(R.id.btnHome);
		
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), HomeActivity.class);
				NFCActivity.this.finish();
				startActivity(intent);
			}
		});	
	}
	public void configureVideoButton(){
		Button b = (Button) findViewById(R.id.btnVideos);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), VideoListActivity.class);
				NFCActivity.this.finish();
				startActivity(intent);
			}
		});	
	}
	public void configureNFCButton(){
		Button b = (Button) findViewById(R.id.btnNFC);
		b.setEnabled(false);
//		b.setOnClickListener(new OnClickListener() {
//			public void onClick(View view) {
//				Intent intent = new Intent(view.getContext(), NFCActivity.class);
//				startActivity(intent);
//			}
//		});	
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Toast.makeText(this, "You have selected " + clues[position], Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nfc, menu);
		return true;
	}

}
