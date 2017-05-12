package lab4.android.wku.edu.songwriterassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }
}
