package dbHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class Helper extends SQLiteOpenHelper {
	
	private static final String DATABASE_PATH = "/data/data/com.example.sbucomputersciencev1_1/databases/";
	private static final String DATABASE_NAME = "cs_app";
	private static final  int SCHEMA_VERSION = 1;
	public static final String TABLE_NAME = "alumni";
	public static final String COLUMN_ID= "_id";
	public static final String COLUMN_TITLE= "name";
	public static final String COLUMN_DESC="description";
	public static final String COLUMN_CLASS = "class";
	public static final String COLUMN_IMG_PATH = "thumb";
	public SQLiteDatabase dbSqlite;
	
	private final Context myContext;
	
	public Helper(Context context){
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		
	}
	
	@Override	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}
	
	public void createDatabase(){
		createDB();
	}
	
	private void createDB(){
		boolean dbExist = DBExists();
		SQLiteDatabase db = null;
		if(!dbExist){
			db = this.getReadableDatabase();
			
			if (db.isOpen()){
			db.close();
			}
			copyDBFromResource();
			
		}
	}
	
	private boolean DBExists(){
		SQLiteDatabase db = null;
		
		try{
			String databasePath = DATABASE_PATH + DATABASE_NAME;
			db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
			db.setLocale(Locale.getDefault());
			db.setLockingEnabled(true);
			db.setVersion(1);
		}catch(SQLiteException e){
			Log.e("SQLHelper", "database not found");
		}
		
		if(db!=null){
			db.close();
		}
		
		return db != null ? true : false;
	}
	
	
	
	private void copyDBFromResource(){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String dbFilePath = DATABASE_PATH + DATABASE_NAME;
		
		try{
			inputStream = myContext.getAssets().open(DATABASE_NAME);
			outputStream = new FileOutputStream(dbFilePath);
			
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = inputStream.read(buffer))>0){
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		}catch(IOException e){
			//e.toString();
			throw new Error("Problem copying db from resource file");
		}
	}
	
	
	public void openDataBase(){
		String myPath = DATABASE_PATH + DATABASE_NAME;
		dbSqlite = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	@Override
	public synchronized void close(){
		if(dbSqlite!=null){
			dbSqlite.close();
		}
		super.close();
	}
	public Cursor getCursor(){
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(TABLE_NAME);
		String[] asColumnsToReturn = new String[] {COLUMN_ID, COLUMN_TITLE, COLUMN_DESC, COLUMN_CLASS, COLUMN_IMG_PATH};
		Cursor mCursor = queryBuilder.query(dbSqlite, asColumnsToReturn, null, null, null, null, null);
		return mCursor;
		}
	public Cursor getDirectoryCursor(){
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables("directory");
		String[] asColumnsToReturn = new String[] {COLUMN_ID, COLUMN_TITLE,"phone", "office", COLUMN_IMG_PATH};
		Cursor mCursor = queryBuilder.query(dbSqlite, asColumnsToReturn, null, null, null, null, null);
		return mCursor;
		}
	public Cursor getSeniorCursor(){
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables("seniors");
		String[] asColumnsToReturn = new String[] {COLUMN_ID, COLUMN_TITLE,"info", COLUMN_IMG_PATH};
		Cursor mCursor = queryBuilder.query(dbSqlite, asColumnsToReturn, null, null, null, null, null);
		return mCursor;
		}
	
	public String getName(Cursor c){
		return c.getString((1));
	}
	public String getDesc1(Cursor c){
		return c.getString((2));
	}
	public String getClass(Cursor c){
		return c.getString((3));
	}
	public String getThumbPath(Cursor c){
		return c.getString((4));
	}
	public String getSeniorThumbPath(Cursor c){
		return c.getString((3));
	}
	
	public Cursor getAlumniDescription(String id){
		String[] args={id};
		return (getReadableDatabase()
				.rawQuery("SELECT description, name, imagePath FROM alumni WHERE _id=?", args));
	}
	public Cursor getDirectoryDescription(String id){
		String[] args={id};
		return (getReadableDatabase()
				.rawQuery("SELECT name, pic, phone, office FROM directory WHERE _id=?", args));
	}
	public Cursor getSeniorDescription(String id){
		String[] args={id};
		return (getReadableDatabase()
				.rawQuery("SELECT name, pic, info FROM seniors WHERE _id=?", args));
	}
	
}
