package com.example.sbucomputersciencev1_1;

import youtubeHelper.DeveloperKey;
import youtubeHelper.YouTubeFailureRecoveryActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.t;

public class CareerActivity extends YouTubeFailureRecoveryActivity {

	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.playerview_demo);

	    YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
	    youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
	    YouTubeThumbnailView t = new YouTubeThumbnailView(getApplicationContext());
	    t.setTag("oxYZ5_u6Z7A");
	  }

	  @Override
	  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
	      boolean wasRestored) {
	    if (!wasRestored) {
	     
	      player.cueVideo("oxYZ5_u6Z7A");
	     
	    }
	  }

	  @Override
	  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	    return (YouTubePlayerView) findViewById(R.id.youtube_view);
	  }

}
