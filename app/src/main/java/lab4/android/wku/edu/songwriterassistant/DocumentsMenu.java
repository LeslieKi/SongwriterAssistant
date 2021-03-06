package lab4.android.wku.edu.songwriterassistant;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DocumentsMenu extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBNotesHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_menu);

        mydb = new DBNotesHelper(this);
        ArrayList array_list = mydb.getAllNotes();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),DisplayNote.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.documents_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.item1:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(this ,DisplayNote.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            case R.id.Main_Menu:
                Intent intent1 = new Intent(this, MainMenu.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

