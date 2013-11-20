package com.example.sbucomputersciencev1_1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class EventActivity extends Activity {

	private static final String MOVIE = "http://youtu.be/oxYZ5_u6Z7A";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events);
		
		VideoView vid = (VideoView) findViewById(R.id.video);
	//	vid.setVideoPath("android.resource://" + getPackageName() + "/" 
		//		+ R.raw.comp);
		vid.setMediaController(new MediaController(this));
	//	Uri video = Uri.parse(MOVIE);
		//vid.setVideoURI(video);
		vid.start();
		vid.requestFocus();
	
	}

	
	
	
	
}
