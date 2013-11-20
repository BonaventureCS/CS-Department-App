package com.example.sbucomputersciencev1_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Frag01 extends ListFragment {
	
	String videos[] = {
			"Computer Science",
			"Programming Contest",
			"Robot Demonstration",
			"Android Tutorial",
			"NFC Tag Hunt",
			"Tour of Walsh",
			"C.S. Promo Vid"
	};

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.mytext, videos));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_frag01, container, false);
	}

	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Intent intent = new Intent(v.getContext(), EventActivity.class);
		Frag01.this.startActivity(intent);
	}
}
