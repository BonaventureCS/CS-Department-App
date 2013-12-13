package com.example.sbucomputersciencev1_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {

	// Get a handle to the Map Fragment
	private GoogleMap map1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();

		map1 = map;

		// location of st.bonaventure
		LatLng bonas = new LatLng(42.0781, -78.4813);

		// location of walsh building
		LatLng walsh = new LatLng(42.081991, -78.484422);

		map1.setMyLocationEnabled(true);
		map1.moveCamera(CameraUpdateFactory.newLatLngZoom(bonas, 15));

		// place marker over walsh
		map1.addMarker(new MarkerOptions().title("Walsh")
				.snippet("The finest building at Bonas.").position(walsh));
		

	}

	public void satelliteView(View v) {
		map1.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}

	public void normalView(View v) {
		map1.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
}
