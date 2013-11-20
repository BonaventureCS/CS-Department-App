package com.example.sbucomputersciencev1_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import dbHelper.Helper;

public class DirectoryActivity extends Activity {

	private static Context context;
	private Helper dbHelper = null;
	private Cursor cursor = null;
	private LAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directory);
		DirectoryActivity.context = getApplicationContext();
		ListView listView = (ListView) findViewById(R.id.listView1);

		dbHelper = new Helper(DirectoryActivity.this);
		dbHelper.createDatabase();

		dbHelper.openDataBase();

		cursor = dbHelper.getDirectoryCursor();
		startManagingCursor(cursor);
		adapter = new LAdapter(cursor);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(onListClick);
	}

	public static Context getContext() {
		return DirectoryActivity.context;
	}

	private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Cursor c = dbHelper.getDirectoryDescription(String.valueOf(id));
			c.moveToFirst();
			String name = c.getString(0);
			String pic = c.getString(1);
			String phone = c.getString(2);
			String office = c.getString(3);
			int resID = getResources().getIdentifier(pic, "drawable",
					getPackageName());

			Intent i = new Intent(DirectoryActivity.this, DirectoryDesc.class);
			i.putExtra("id", resID);
			i.putExtra("office", office);
			i.putExtra("phone", phone);
			i.putExtra("name", name);
			startActivity(i);
		}
	};

	class LAdapter extends CursorAdapter {

		public LAdapter(Cursor c) {
			super(DirectoryActivity.this, c);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			StringHolder holder = (StringHolder) view.getTag();
			holder.populateFrom(cursor, dbHelper);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.my_row, parent, false);
			// calls stringholder to populate each row with data
			StringHolder holder = new StringHolder(row);
			row.setTag(holder);
			return (row);
		}

	}

	// class that is used to hold data in list
	static class StringHolder {

		private TextView name = null;

		private ImageView thumb = null;

		// private Resources res = getResources();
		StringHolder(View row) {
			name = (TextView) row.findViewById(R.id.alumName);
			thumb = (ImageView) row.findViewById(R.id.alumThumb);
		}

		void populateFrom(Cursor c, Helper r) {
			// grab context from activity
			Context cxt = DirectoryActivity.getContext();
			// get id of drawable image
			int resID = cxt.getResources().getIdentifier(
					r.getThumbPath(c).toString(), "drawable",
					cxt.getPackageName());
			Drawable drawable = cxt.getResources().getDrawable(resID);

			// populates rows with data
			name.setText(r.getName(c));

			thumb.setImageDrawable(drawable);

		}
	}
}
