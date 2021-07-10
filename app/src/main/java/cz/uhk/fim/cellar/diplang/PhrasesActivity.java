package cz.uhk.fim.cellar.diplang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import cz.uhk.fim.cellar.diplang.classes.Lesson;

public class PhrasesActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(isNetworkAvailable()) {
            loadUserData();
        }


    }

    private void loadUserData() {
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)PhrasesActivity.this
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}