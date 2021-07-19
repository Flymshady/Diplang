package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

/**
 * Aktivita pro seznam uložených cz-en frází
 */
public class PhrasesActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private LinearLayout phrasesLayout;
    private ImageButton btnRemove, btnBackToStudy;
    private TextView textViewCz, textViewEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        user = FirebaseAuth.getInstance().getCurrentUser();
        phrasesLayout = (LinearLayout) findViewById(R.id.phrasesLayout);
        loadUserData();

        btnBackToStudy = (ImageButton) findViewById(R.id.backToStudy);
        btnBackToStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(PhrasesActivity.this, NavigationActivity.class));
                } finally {
                    finish();
                }
            }
        });
    }

    /**
     * Načte data z databáze a aktualizuje layout
     */
    private void loadUserData() {
        DatabaseReference myRefTask1 = database.getReference("UserPhrases").child(user.getUid());
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();
                    LinearLayout ll = new LinearLayout(PhrasesActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(5,15,5,15);
                    ll.setLayoutParams(lp);
                    ll.setWeightSum(3.5f);
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                    ll.setTag("@+id/"+value);
                    phrasesLayout.addView(ll);
                    for(DataSnapshot child:dataSnapshot.getChildren()){
                        if(child.getKey().equals("englishPhrase")) {
                            textViewEn = new TextView(PhrasesActivity.this);
                            textViewEn.setText(child.getValue().toString());
                            textViewEn.setTextColor(Color.BLACK);
                            textViewEn.setTextSize(18);
                            textViewEn.setBackgroundResource(R.color.spinner_selected);
                            LinearLayout.LayoutParams lpTextEn = new LinearLayout.LayoutParams(0,
                                    LinearLayout.LayoutParams.WRAP_CONTENT, 1.6f);
                            lpTextEn.setMargins(0,0,5,0);
                            textViewEn.setLayoutParams(lpTextEn);
                            ll.addView(textViewEn);
                        }
                        if(child.getKey().equals("czechPhrase")) {
                            textViewCz = new TextView(PhrasesActivity.this);
                            textViewCz.setText(child.getValue().toString());
                            textViewCz.setTextColor(Color.BLACK);
                            textViewCz.setTextSize(18);
                            textViewCz.setBackgroundResource(R.color.spinner_selected);
                            LinearLayout.LayoutParams lpTextCz = new LinearLayout.LayoutParams(0,
                                    LinearLayout.LayoutParams.WRAP_CONTENT, 1.6f);
                            lpTextCz.setMargins(5,0,5,0);
                            textViewCz.setLayoutParams(lpTextCz);
                            ll.addView(textViewCz);
                        }
                        if(child.getKey().equals("created")) {
                            String created = child.getValue().toString();
                            btnRemove = new ImageButton(PhrasesActivity.this);
                            LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(0,
                                    LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f);
                            lpBtn.setMargins(5,0,5,0);
                            btnRemove.setLayoutParams(lpBtn);
                            btnRemove.setBackgroundResource(R.drawable.ic_baseline_delete_purple_24);
                            ll.addView(btnRemove);

                            btnRemove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    AlertDialog dialog = new AlertDialog.Builder(PhrasesActivity.this)
                                            .setTitle("Odstranění fráze")
                                            .setMessage("Skutečně chcete tuto frázi odstranit?")
                                            .setPositiveButton("Ano", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    FirebaseDatabase.getInstance().getReference("UserPhrases")
                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                                                            .child(created)
                                                            .removeValue();
                                                    phrasesLayout.removeView(ll);
                                                }
                                            })
                                            .setNegativeButton("Zrušit", null)
                                            .show();
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}