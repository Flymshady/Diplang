package cz.uhk.fim.cellar.diplang.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.SplashScreen;
import cz.uhk.fim.cellar.diplang.classes.User;

/**
 * @author Štěpán Cellar - FIM UHK
 * Aktivita pro přihlášení uživatele
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button login, register, forgotten;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseUser user;
    private SharedPreferences sp;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        forgotten = (Button) findViewById(R.id.forgotten);
        forgotten.setOnClickListener(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.emailTitle);
        password = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            // go to main page
            try {
                startActivity(new Intent(LoginActivity.this, SplashScreen.class));
            } finally {
                finish();
            }
        }
    }

    /**
     * Aktivace button
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.forgotten:
                startActivity(new Intent(this, ForgottenPasswordActivity.class));
                break;

        }
    }
    /**
     * Validace emailu a hesla, zaslání požadavku o přihlášení a přesměrování uživatele na SplashScreen
     */
    private void userLogin() {
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();

        if (emailString.isEmpty()) {
            email.setError("Email nesmí být prázdný");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("Prosím zadejte validní email");
            email.requestFocus();
            return;
        }

        if (passwordString.isEmpty()) {
            password.setError("Heslo nesmí být prázdné");
            password.requestFocus();
            return;
        }
        if (passwordString.length() < 6) {
            password.setError("Heslo musí obsahovat alespoň 6 znaků");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //email verifikace
                   /*
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                   if(user.isEmailVerified()){
                       //redirect to user profile
                       startActivity(new Intent(MainActivity.this, MenuActivity.class));
                   }else {
                       user.sendEmailVerification();
                       Toast.makeText(MainActivity.this, "Nejprve potvrdtě registraci na Vašem emailu", Toast.LENGTH_LONG).show();
                   }

                    */
                    //přesměrování na navigační stránku

                    try {
                        startActivity(new Intent(LoginActivity.this, SplashScreen.class));
                    } finally {
                        finish();

                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Došlo k chybě, zkuste to prosím znovu", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}