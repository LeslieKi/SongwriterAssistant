package lab4.android.wku.edu.songwriterassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage;

public class AGuitarChords extends AppCompatActivity {

    ImageView chordImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aguitar_chords);
    }

    public void displayChord(View view) {
        chordImage = (ImageView)findViewById(R.id.chordImage);

        switch(view.getId()) {
            case R.id.majChord:
                chordImage.setImageResource(R.drawable.guitaramajor);
                break;
            case R.id.fiveChord:
                chordImage.setImageResource(R.drawable.guitara5);
                break;
            case R.id.sixChord:
                chordImage.setImageResource(R.drawable.guitara6);
                break;
            case R.id.sevenChord:
                chordImage.setImageResource(R.drawable.guitara7);
                break;
            case R.id.nineChord:
                chordImage.setImageResource(R.drawable.guitara9);
                break;
            case R.id.thirteenChord:
                chordImage.setImageResource(R.drawable.guitara13);
                break;
            case R.id.maj7chord:
                chordImage.setImageResource(R.drawable.guitaramaj7);
                break;
            case R.id.maj9chord:
                chordImage.setImageResource(R.drawable.guitaramaj9);
                break;
        }
    }
}
