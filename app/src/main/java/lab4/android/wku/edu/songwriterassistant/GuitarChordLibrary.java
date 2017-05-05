package lab4.android.wku.edu.songwriterassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuitarChordLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar_chord_library);
    }

    public void openAChords(View view) {
        Intent intent = new Intent(this, AGuitarChords.class);
        startActivity(intent);
    }
}
