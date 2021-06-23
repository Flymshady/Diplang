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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button login, register, forgotten;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

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
                startActivity(new Intent(this, ForgottenPassword.class));
                break;

        }
    }

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
                    //emial verifikace
                   /*
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                   if(user.isEmailVerified()){
                       //redirect to user profile
                       startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                   }else {
                       user.sendEmailVerification();
                       Toast.makeText(MainActivity.this, "Nejprve potvrdtě registraci na Vašem emailu", Toast.LENGTH_LONG).show();
                   }

                    */
                   //redirect to user profile
                   startActivity(new Intent(MainActivity.this, ProfileActivity.class));

               }else{
                   Toast.makeText(MainActivity.this, "Došlo k chybě, zkuste to prosím znovu", Toast.LENGTH_LONG).show();
                   progressBar.setVisibility(View.GONE);
               }
            }
        });
    }


}