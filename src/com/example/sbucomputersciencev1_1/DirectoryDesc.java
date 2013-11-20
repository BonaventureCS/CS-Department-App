package com.example.sbucomputersciencev1_1;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

public class DirectoryDesc extends Activity {

	String passedOf = null;
	String passedName = null;
	String passedPhone = null;
	int passedID= 0;
	private TextView passedView=null;
	private TextView passedNam=null;
	private ImageView  passedImg= null;
	private TextView passedPhoneNum = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directory_desc);
		
		passedOf= getIntent().getStringExtra("office");
		passedName= getIntent().getStringExtra("name");
		passedID = getIntent().getIntExtra("id", 0);
		passedPhone = getIntent().getStringExtra("phone");

		passedView=(TextView)findViewById(R.id.office);
		passedView.setText(passedOf);
		
	
		
		passedPhoneNum=(TextView)findViewById(R.id.phone);
		passedPhoneNum.setText(passedPhone);

		Linkify.addLinks(passedPhoneNum, Linkify.PHONE_NUMBERS);
	//	passedPhoneNum.setAutoLinkMask(Linkify.PHONE_NUMBERS);
		
		passedNam=(TextView)findViewById(R.id.txtDirectoryName);
		passedNam.setText(passedName);
		
		passedImg = (ImageView)findViewById(R.id.directoryImage);
		drawImage();
		
		
	}

	public void drawImage(){
		Drawable drawable = getResources().getDrawable(passedID );
		passedImg.setImageDrawable(drawable);
	}



}
