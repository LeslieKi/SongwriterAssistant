package lab4.android.wku.edu.songwriterassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage1;
import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage2;
import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage3;
import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage4;
import static lab4.android.wku.edu.songwriterassistant.R.id.chordImage5;

public class APianoChords extends AppCompatActivity {

    ImageView chordImagea;
    ImageView chordImageb;
    ImageView chordImagec;
    ImageView chordImaged;
    ImageView chordImagee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apianochords);
    }

    public void displayChord(View view) {
        chordImagea = (ImageView)findViewById(chordImage1);
        chordImageb = (ImageView)findViewById(chordImage2);
        chordImagec = (ImageView)findViewById(chordImage3);
        chordImaged = (ImageView)findViewById(chordImage4);
        chordImagee = (ImageView)findViewById(chordImage5);

        switch(view.getId()) {
            case R.id.maj:
                chordImagea.setImageResource(R.drawable.pianoamaj);
                break;
            case R.id.six:
                chordImagea.setImageResource(R.drawable.pianoa6);
                break;
            case R.id.seven:
                chordImagea.setImageResource(R.drawable.pianoa7);
                break;
            case R.id.nine:
                chordImagea.setImageResource(R.drawable.pianoa9);
                break;
            case R.id.thirteen:
                chordImagea.setImageResource(R.drawable.pianoa13);
                break;
            case R.id.majseven:
                chordImagea.setImageResource(R.drawable.pianoamaj7);
                break;
            case R.id.majnine:
                chordImagea.setImageResource(R.drawable.pianoamaj9);
                break;

            case R.id.minor:
                chordImageb.setImageResource(R.drawable.pianoamin);
                break;
            case R.id.minorseven:
                chordImageb.setImageResource(R.drawable.pianoam7);
                break;
            case R.id.minorsharp7:
                chordImageb.setImageResource(R.drawable.pianoam9);
                break;
            case R.id.minorsevenflatfive:
                chordImageb.setImageResource(R.drawable.pianoam7b5);
                break;
            case R.id.minorsix:
                chordImageb.setImageResource(R.drawable.pianoam6);
                break;
            case R.id.minornine:
                chordImageb.setImageResource(R.drawable.pianoam9);
                break;
            case R.id.minoreleven:
                chordImageb.setImageResource(R.drawable.pianoam11);
                break;

            case R.id.sustwo:
                chordImagec.setImageResource(R.drawable.pianoasus2);
                break;
            case R.id.susfour:
                chordImagec.setImageResource(R.drawable.pianoasus4);
                break;
            case R.id.sevensusfour:
                chordImagec.setImageResource(R.drawable.pianoa7sus4);
                break;
            case R.id.ninesusfour:
                chordImagec.setImageResource(R.drawable.pianoa9sus4);
                break;

            case R.id.aug:
                chordImaged.setImageResource(R.drawable.pianoaaug);
                break;

            case R.id.dim:
                chordImagee.setImageResource(R.drawable.pianoadim);
                break;
            case R.id.halfdim:
                chordImagee.setImageResource(R.drawable.pianoahalfdim);
                break;
            case R.id.dim7:
                chordImagee.setImageResource(R.drawable.pianoadim7);
                break;



        }
    }
}
