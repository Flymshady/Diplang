package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
            // User is signed in
            // go to main page
            Toast.makeText(MainActivity.this, "Vítejte!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            try {
                startActivity(new Intent(MainActivity.this, NavigationActivity.class));
            } finally {
                finish();
            }
            } else {
            // No user is signed in
            // go to loging page
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