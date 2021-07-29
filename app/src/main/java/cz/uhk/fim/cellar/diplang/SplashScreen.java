package cz.uhk.fim.cellar.diplang;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.time.Duration;

import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

/**
 * @author Štěpán Cellar - FIM UHK
 * Aktivita, která je zobrazena po přihlášení uživatele a slouží k "zastítení" při načítání dat
 */
public class SplashScreen extends AppCompatActivity {

    private ImageView imageDip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageDip = (ImageView) findViewById(R.id.imageDip);
        imageDip.animate()
                .setDuration(3000).rotationYBy(360f).start();
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