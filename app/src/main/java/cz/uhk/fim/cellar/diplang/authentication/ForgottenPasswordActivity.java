package cz.uhk.fim.cellar.diplang.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import cz.uhk.fim.cellar.diplang.R;

/**
 * @author Štěpán Cellar - FIM UHK
 * Aktivita pro reset hesla uživatele
 */
public class ForgottenPasswordActivity extends AppCompatActivity {

    private EditText editEmailAddress;
    private Button buttonResetPassword, backToLoginFromForgotten;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        editEmailAddress = (EditText) findViewById(R.id.editEmailAddress);
        buttonResetPassword = (Button) findViewById(R.id.buttonResetPassword);
        backToLoginFromForgotten = (Button) findViewById(R.id.backToLoginFromForgotten);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        backToLoginFromForgotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(ForgottenPasswordActivity.this, LoginActivity.class));
                } finally {
                    finish();
                }
            }
        });
    }

    /**
     * Ověření emailu a odeslání požadavku na reset hesla
     */
    private void resetPassword() {
        String email = editEmailAddress.getText().toString().trim();

        if(email.isEmpty()){
            editEmailAddress.setError("Zadejte email");
            editEmailAddress.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                Toast.makeText(ForgottenPasswordActivity.this, "Zkontrolujte Vaši emailovou schránku pro změnu hesla.", Toast.LENGTH_LONG).show();
                }else{
                Toast.makeText(ForgottenPasswordActivity.this, "Došlo k chybě, zkuste to prosím znovu.", Toast.LENGTH_LONG).show();
                 }
                progressBar.setVisibility(View.GONE);
        }
        });
    }

    /** Skrytí klávesnice při "kliknutí" mimo editovatelné textové pole **/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}