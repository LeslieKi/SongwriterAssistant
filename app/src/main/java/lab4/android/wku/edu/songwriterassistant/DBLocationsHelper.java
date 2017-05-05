package lab4.android.wku.edu.songwriterassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static lab4.android.wku.edu.songwriterassistant.DBNotesHelper.NOTES_COLUMN_TITLE;
import static lab4.android.wku.edu.songwriterassistant.DBNotesHelper.NOTES_TABLE_NAME;
import static lab4.android.wku.edu.songwriterassistant.R.string.street;

/**
 * Created by LesTK on 4/20/2017.
 */

public class DBLocationsHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "locations.db";
    public static final String LOCATIONS_TABLE_NAME = "locations";
    public static final String LOCATIONS_COLUMN_ID = "id";
    public static final String LOCATIONS_COLUMN_TITLE = "title";
    public static final String LOCATIONS_COLUMN_CITY = "city";
    public static final String LOCATIONS_COLUMN_STATE = "state";
    public static final String LOCATIONS_COLUMN_STREET = "street";
    public static final String LOCATIONS_COLUMN_ZIP = "zipcode";

    private HashMap hp;

    public DBLocationsHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table locations " +
                        "(id integer primary key, title text, street text, city text, state text, zipcode text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS locations");
        onCreate(db);
    }

    public boolean insertLocation (String title, String street, String city, String state, String zipcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("zipcode", zipcode);
        db.insert("locations", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from locations where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LOCATIONS_TABLE_NAME);
        return numRows;
    }

    public boolean updateLocation (Integer id, String title, String street, String city, String state, String zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("zipcode", zip);

        db.update("notes", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteLocation (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("locations",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllLocations() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from locations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(LOCATIONS_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllGeoAddresses() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from locations", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String street = (res.getString(res.getColumnIndex(LOCATIONS_COLUMN_STREET)));
            String city = (res.getString(res.getColumnIndex(LOCATIONS_COLUMN_CITY)));
            String state = (res.getString(res.getColumnIndex(LOCATIONS_COLUMN_STATE)));
            String address = (street+", "+city+", "+state).replace(" ", "+");

            array_list.add(address);

            //array_list.add(res.getString(res.getColumnIndex(LOCATIONS_COLUMN_STREET)).replace(" ", "+"));
            res.moveToNext();
        }
        return array_list;
    }


}
