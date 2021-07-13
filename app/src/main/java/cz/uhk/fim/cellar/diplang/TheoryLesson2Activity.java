package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.UserTheory;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

public class TheoryLesson2Activity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private LinearLayout theoryTask1P1L2;
    private TextInputEditText theoryUser1P1L2;
    private Button btnSaveTheory1P1L2;
    private ImageButton btnBackFromTheory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_lesson2);
        user = FirebaseAuth.getInstance().getCurrentUser();

        theoryTask1P1L2 = (LinearLayout) findViewById(R.id.theoryTask1P1L2);

        theoryUser1P1L2 = (TextInputEditText) findViewById(R.id.theoryUser1P1L2);

        btnSaveTheory1P1L2 = (Button) findViewById(R.id.btnSaveTheory1P1L2);
        btnBackFromTheory = (ImageButton) findViewById(R.id.btnBackFromTheory2);

        btnSaveTheory1P1L2.setOnClickListener(this);
        btnBackFromTheory.setOnClickListener(this);


        loadData();
    }

    private void loadData() {

        DatabaseReference myRefTask1 = database.getReference("UserTheory").child(user.getUid()).child("Lesson2")
                .child("Page1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.child("TheoryTask1").getChildren()){
                    TextView tw= new TextView(TheoryLesson2Activity.this);

                    String value = dataSnapshot.getValue().toString();
                    value = value.replace(";", "\n");

                    tw.setText(value);
                    tw.setTextColor(Color.BLACK);
                    if(dataSnapshot.getKey().equals("info")){
                        tw.setTextSize(22);
                        tw.setTypeface(Typeface.DEFAULT_BOLD);
                    }else {
                        tw.setTextSize(18);
                    }
                    tw.setBackgroundResource(R.color.spinner_selected);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    tw.setLayoutParams(lp);
                    theoryTask1P1L2.addView(tw);
                }

                for(DataSnapshot dataSnapshot:snapshot.child("TheoryTaskUser1").getChildren()){
                    if(dataSnapshot!=null) {
                        String text = dataSnapshot.getValue().toString();
                        theoryUser1P1L2.setText(text);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnSaveTheory1P1L2):
                saveTheory1L1P2();
                break;
            case (R.id.btnBackFromTheory2):
                try {
                    startActivity(new Intent(TheoryLesson2Activity.this, NavigationActivity.class));
                } finally {
                    finish();
                }
                break;
        }
    }

    private void saveTheory1L1P2() {
        UserTheory userTheory = new UserTheory(theoryUser1P1L2.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page1")
                .child("TheoryTaskUser1")
                .setValue(userTheory);
    }


}