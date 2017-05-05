package lab4.android.wku.edu.songwriterassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PianoChordLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_chord_library);
    }

    public void openAChords(View view) {
        Intent intent = new Intent(this, APianoChords.class);
        startActivity(intent);
    }

    public void openBChords(View view) {
        Intent intent = new Intent(this, BPianoChords.class);
        startActivity(intent);
    }
}
