package com.example.sbucomputersciencev1_1;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class AlumniDesc extends Activity {

	String passedVar = null;
	String passedName = null;
	int passedID= 0;
	private TextView passedView=null;
	private TextView passedNam=null;
	private ImageView  passedImg= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumni_desc);
		
		passedVar= getIntent().getStringExtra("desc");
		passedName= getIntent().getStringExtra("name");
		passedID = getIntent().getIntExtra("id", 0);

		passedView=(TextView)findViewById(R.id.desc);
		passedView.setText(passedVar);
		
		passedNam=(TextView)findViewById(R.id.txtName);
		passedNam.setText(passedName);
		
		passedImg = (ImageView)findViewById(R.id.alumImage);
		
		drawImage();
		
		
	}

	public void drawImage(){
		Drawable drawable = getResources().getDrawable(passedID );
		passedImg.setImageDrawable(drawable);
	}


}
