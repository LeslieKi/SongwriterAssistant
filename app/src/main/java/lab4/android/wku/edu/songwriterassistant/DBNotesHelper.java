package lab4.android.wku.edu.songwriterassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LesTK on 4/20/2017.
 */

public class DBNotesHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes.db";
    public static final String NOTES_TABLE_NAME = "notes";
    public static final String NOTES_COLUMN_ID = "id";
    public static final String NOTES_COLUMN_TITLE = "title";
    public static final String NOTES_COLUMN_CONTENT = "content";

    private HashMap hp;

    public DBNotesHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table notes " +
                        "(id integer primary key, title text, content text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public boolean insertNote (String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        db.insert("notes", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NOTES_TABLE_NAME);
        return numRows;
    }

    public boolean updateNote (Integer id, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        db.update("notes", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteNote (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("notes",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllNotes() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NOTES_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}
