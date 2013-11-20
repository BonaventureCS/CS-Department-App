package com.example.sbucomputersciencev1_1;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        Handler h = new Handler();
        h.postDelayed(new SplashHandler(), 2000);
    }
    
    public class SplashHandler implements Runnable{
    	public void run(){
    		startActivity(new Intent(getApplication(), HomeActivity.class));
    		SplashActivity.this.finish();
    	}
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_splash, menu);
//        return true;
//    }
    
}
