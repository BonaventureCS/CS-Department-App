package com.example.sbucomputersciencev1_1;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Frag02 extends ListFragment{
	
	String [] clues = {
			"Clue 1",
			"Clue 2",
			"Clue 3",
			"Clue 4",
			"Clue 5",
			"Clue 6",
			"Clue 7",
			"Clue 8",
			"Clue 9",
			"Clue 10",
			"Clue 11",
	};

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.mytext, clues));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  View view = inflater.inflate(R.layout.activity_frag02, container, false);
		  return view;
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Toast.makeText(this.getActivity(), "You have selected " + clues[position], Toast.LENGTH_LONG).show();
	}
	
}
