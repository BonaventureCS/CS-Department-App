package com.example.sbucomputersciencev1_1;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dbHelper.Helper;

public class NFCActivity extends Activity {
	private NfcAdapter mNfcAdapter;
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String TAG = "NfcDemo";
	private TextView mTextView;
	private Helper dbHelper = null;
	private Cursor cursor = null;
	private LAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc);
		
		mTextView = (TextView) findViewById(R.id.text);
		configureVideoButton();
		configureHomeButton();
		configureNFCButton();

		ListView listView = (ListView) findViewById(R.id.clueList);

		dbHelper = new Helper(NFCActivity.this);
		dbHelper.createDatabase();

		dbHelper.openDataBase();

		cursor = dbHelper.getClueCursor();
		startManagingCursor(cursor);
		adapter = new LAdapter(cursor);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(onListClick);

		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		handleIntent(getIntent());
	}
	
	private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			Cursor c = dbHelper.getSeniorDescription(String.valueOf(id));
			c.moveToFirst();
			String name = c.getString(0);
			String pic = c.getString(1);
			String info = c.getString(2);
			
			int resID = getResources().getIdentifier(pic , "drawable",getPackageName());
			
			Intent i = new Intent(NFCActivity.this, ClueActivity.class);
			i.putExtra("id", resID);
			i.putExtra("info",info);
			i.putExtra("name" ,name);
			startActivity(i);
		}
	};
	class LAdapter extends CursorAdapter {

		public LAdapter(Cursor c) {
			super(NFCActivity.this, c);	
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			StringHolder holder = (StringHolder)view.getTag();
			holder.populateFrom(cursor, dbHelper);
		}
		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.my_row, parent, false);
			//calls stringholder to populate each row with data
			StringHolder holder = new StringHolder(row);
			row.setTag(holder);	
			return (row);
		}
		
		@Override
		public boolean isEnabled(int position) {
			int value = cursor.getInt(2);
			
			System.out.println(value);
		    if(value == 0){
		        return false;
		    }
		    return true;
		}

		
	}
	//class that is used to hold data in list
	static class StringHolder {
		
		private TextView name = null;
		
		//private ImageView thumb = null;
		//private Resources res = getResources();
		StringHolder(View row){
			name = (TextView)row.findViewById(R.id.alumName);
			//thumb = (ImageView)row.findViewById(R.id.alumThumb);
		}
		
		void populateFrom(Cursor c, Helper r){
			//grab context from activity
			
			//get id of drawable image
			//int resID = cxt.getResources().getIdentifier(r.getSeniorThumbPath(c).toString() , "drawable", cxt.getPackageName());
			//Drawable drawable = cxt.getResources().getDrawable(resID );
			
			//populates rows with data
			name.setText(c.getString(1));
			
			//thumb.setImageDrawable(drawable);
			
			
		}
	}

	public void configureHomeButton() {
		Button b = (Button) findViewById(R.id.btnHome);

		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),
						HomeActivity.class);
				NFCActivity.this.finish();
				startActivity(intent);
			}
		});
	}

	public void configureVideoButton() {
		Button b = (Button) findViewById(R.id.btnVideos);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),
						VideoListActivity.class);
				NFCActivity.this.finish();
				startActivity(intent);
			}
		});
	}

	public void configureNFCButton() {
		Button b = (Button) findViewById(R.id.btnNFC);
		b.setEnabled(false);
		// b.setOnClickListener(new OnClickListener() {
		// public void onClick(View view) {
		// Intent intent = new Intent(view.getContext(), NFCActivity.class);
		// startActivity(intent);
		// }
		// });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nfc, menu);
		return true;
	}

	private void handleIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			String type = intent.getType();
			if (MIME_TEXT_PLAIN.equals(type)) {
				Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
				new NdefReaderTask().execute(tag);
			} else {
				Log.d(TAG, "Wrong mime type: " + type);
			}
		} else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			// In case we would still use the Tech Discovered Intent
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			String[] techList = tag.getTechList();
			String searchedTech = Ndef.class.getName();
			for (String tech : techList) {
				if (searchedTech.equals(tech)) {
					new NdefReaderTask().execute(tag);
					break;
				}
			}
		}
	}

	private class NdefReaderTask extends AsyncTask<Tag, Void, String> {
		@Override
		protected String doInBackground(Tag... params) {
			Tag tag = params[0];
			Ndef ndef = Ndef.get(tag);
			if (ndef == null) {
				// NDEF is not supported by this Tag.
				return null;
			}
			NdefMessage ndefMessage = ndef.getCachedNdefMessage();
			NdefRecord[] records = ndefMessage.getRecords();
			for (NdefRecord ndefRecord : records) {
				if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN
						&& Arrays.equals(ndefRecord.getType(),
								NdefRecord.RTD_TEXT)) {
					try {
						return readText(ndefRecord);
					} catch (UnsupportedEncodingException e) {
						Log.e(TAG, "Unsupported Encoding", e);
					}
				}
			}
			return null;
		}

		private String readText(NdefRecord record)
				throws UnsupportedEncodingException {
			/*
			 * See NFC forum specification for "Text Record Type Definition" at
			 * 3.2.1
			 * 
			 * http://www.nfc-forum.org/specs/
			 * 
			 * bit_7 defines encoding bit_6 reserved for future use, must be 0
			 * bit_5..0 length of IANA language code
			 */
			byte[] payload = record.getPayload();
			// Get the Text Encoding
			String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8"
					: "UTF-16";
			// Get the Language Code
			int languageCodeLength = payload[0] & 0063;
			// String languageCode = new String(payload, 1, languageCodeLength,
			// "US-ASCII");
			// e.g. "en"
			// Get the Text
			return new String(payload, languageCodeLength + 1, payload.length
					- languageCodeLength - 1, textEncoding);
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {

				Intent intent = new Intent(NFCActivity.this, ClueActivity.class);
				intent.putExtra("id", result);
				startActivity(intent);
				// mTextView.setText("Read content: " + result);
			}
		}
	}

}
