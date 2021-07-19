package cz.uhk.fim.cellar.diplang;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

/**
 * Aktivita, která je zobrazena po přihlášení uživatele a slouží k "zastítení" při načítání dat
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, NavigationActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}