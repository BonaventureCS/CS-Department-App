package com.example.sbucomputersciencev1_1;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

public class SeniorDesc extends Activity {


	String passedName = null;
	String passedinfo = null;
	int passedID= 0;

	private TextView passedNam=null;
	private ImageView  passedImg= null;
	private TextView passedInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_senior_desc);
		
		
		passedName= getIntent().getStringExtra("name");
		passedID = getIntent().getIntExtra("id", 0);
		passedinfo= getIntent().getStringExtra("info");

		passedInfo=(TextView)findViewById(R.id.info);
		passedInfo.setText(passedinfo);
		
		
		passedNam=(TextView)findViewById(R.id.txtName);
		passedNam.setText(passedName);
		
		passedImg = (ImageView)findViewById(R.id.seniorImage);
		drawImage();
		
		
	}

	public void drawImage(){
		Drawable drawable = getResources().getDrawable(passedID );
		passedImg.setImageDrawable(drawable);
	}



}
