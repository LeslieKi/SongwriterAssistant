package lab4.android.wku.edu.songwriterassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayLocation extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBLocationsHelper mydb ;

    TextView title;
    TextView street;
    TextView city;
    TextView state;
    TextView zipcode;

    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        title = (TextView) findViewById(R.id.editTextTitle1);
        street = (TextView) findViewById(R.id.editTextStreet);
        city = (TextView) findViewById(R.id.editTextCity);
        state = (TextView) findViewById(R.id.editTextState);
        zipcode = (TextView) findViewById(R.id.editTextZip);

        mydb = new DBLocationsHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String titl = rs.getString(rs.getColumnIndex(DBLocationsHelper.LOCATIONS_COLUMN_TITLE));
                String stree = rs.getString(rs.getColumnIndex(DBLocationsHelper.LOCATIONS_COLUMN_STREET));
                String cit = rs.getString(rs.getColumnIndex(DBLocationsHelper.LOCATIONS_COLUMN_CITY));
                String stat = rs.getString(rs.getColumnIndex(DBLocationsHelper.LOCATIONS_COLUMN_STATE));
                String zi = rs.getString(rs.getColumnIndex(DBLocationsHelper.LOCATIONS_COLUMN_ZIP));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button2);
                b.setVisibility(View.INVISIBLE);

                title.setText(titl);
                title.setFocusable(false);
                title.setClickable(false);

                city.setText(cit);
                city.setFocusable(false);
                city.setClickable(false);

                street.setText(stree);
                street.setFocusable(false);
                street.setClickable(false);

                state.setText(stat);
                state.setFocusable(false);
                state.setClickable(false);

                zipcode.setText(zi);
                zipcode.setFocusable(false);
                zipcode.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_location, menu);
            } else{
                getMenuInflater().inflate(R.menu.locations_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id.button2);
                b.setVisibility(View.VISIBLE);
                title.setEnabled(true);
                title.setFocusableInTouchMode(true);
                title.setClickable(true);

                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);

                city.setEnabled(true);
                city.setFocusableInTouchMode(true);
                city.setClickable(true);

                state.setEnabled(true);
                state.setFocusableInTouchMode(true);
                state.setClickable(true);

                zipcode.setEnabled(true);
                zipcode.setFocusableInTouchMode(true);
                zipcode.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteLocation(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AddLocation.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            case R.id.Main_Menu:
                Intent intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                return true;
            case R.id.Documents_Menu:
                Intent intent1 = new Intent(this, AddLocation.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateLocation(id_To_Update, title.getText().toString(),
                        street.getText().toString(),
                        city.getText().toString(),
                        state.getText().toString(),
                        zipcode.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AddLocation.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertLocation(title.getText().toString(),
                        street.getText().toString(),
                        city.getText().toString(),
                        state.getText().toString(),
                        zipcode.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),AddLocation.class);
                startActivity(intent);
            }
        }
    }
}
