package cz.uhk.fim.cellar.diplang.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.authentication.LoginActivity;

/**
 * @author Štěpán Cellar - FIM UHK
 * Hlavní aktivita
 * Slouží pro zjištění zda je uživatel přihlášený -> převede jej na domovskou stránku
 * Pokud uživatel není přihlášený -> převede jej na stránku s přihlášením
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        if (user != null) {
            /**
             * Uživatel je přihlášen, je převeden na domovskou stránku
             */
            Toast.makeText(MainActivity.this, "Vítejte!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            try {
                startActivity(new Intent(MainActivity.this, NavigationActivity.class));
            } finally {
                finish();
            }
        } else {
            /**
             * Uživatel není přihlášen, musí se přihlásit
             */
            Toast.makeText(MainActivity.this, "Přihlaště se", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            try {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } finally {
                finish();
            }
        }
    }
}