package com.example.sbucomputersciencev1_1;

import dbHelper.Helper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class ClueActivity extends Activity {
	String id = null;
	private TextView passedId=null;
	private TextView name =null;
	private static Context context;
	private Helper dbHelper = null;
	private Cursor cursor = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clue);
		id = getIntent().getStringExtra("id");
		passedId = (TextView) findViewById(R.id.desc);
		name = (TextView) findViewById(R.id.clueName);
		
		
		
		dbHelper = new Helper(ClueActivity.this);
		dbHelper.createDatabase();
		
		dbHelper.openDataBase();
		cursor = dbHelper.getClueDescription(id);
		System.out.println(cursor.toString());
		if(cursor!= null && cursor.getCount()>0){
			passedId.setText(cursor.getString(1));
			name.setText(cursor.getString(0));
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_clue, menu);
		return true;
	}

}
