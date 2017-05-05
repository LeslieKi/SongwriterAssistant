package lab4.android.wku.edu.songwriterassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChordLibraryMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_library_menu);
    }

    public void openPianoLibrary(View view) {
        Intent intent = new Intent(this, PianoChordLibrary.class);
        startActivity(intent);
    }

    public void openGuitarLibrary(View view) {
        Intent intent = new Intent(this, GuitarChordLibrary.class);
        startActivity(intent);
    }
}
