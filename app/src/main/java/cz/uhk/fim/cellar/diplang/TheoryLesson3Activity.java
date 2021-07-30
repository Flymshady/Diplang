package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.UserTheory;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;

/**
 * @author Štěpán Cellar - FIM UHK
 * Aktivita pro teoretické materiály z třetí lekce
 */
public class TheoryLesson3Activity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private LinearLayout theoryTask1P1L3, theoryTask2P1L3, theoryTask3P1L3, theoryTask1P2L3, theoryTask2P2L3, theoryTask3P2L3, theoryTask1P3L3, theoryTask2P3L3;
    private LinearLayout theoryTask1P4L3, theoryTask2P4L3, theoryTask3P4L3;
    private TextInputEditText theoryUser1P1L3, theoryUser2P1L3, theoryUser3P1L3, theoryUser1P2L3, theoryUser2P2L3, theoryUser3P2L3, theoryUser1P3L3, theoryUser2P3L3;
    private TextInputEditText theoryUser1P4L3, theoryUser2P4L3, theoryUser3P4L3;
    private Button btnSaveTheory1P1L3, btnSaveTheory2P1L3, btnSaveTheory3P1L3, btnSaveTheory1P2L3,btnSaveTheory2P2L3,btnSaveTheory3P2L3, btnSaveTheory1P3L3, btnSaveTheory2P3L3;
    private Button btnSaveTheory1P4L3, btnSaveTheory2P4L3, btnSaveTheory3P4L3;
    private TextView textInfoP1L3, textInfoP2L3, textInfoP3L3, textInfoP4L3;
    private ImageButton btnBackFromTheory3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_lesson3);
        user = FirebaseAuth.getInstance().getCurrentUser();

        theoryTask1P1L3 = (LinearLayout) findViewById(R.id.theoryTask1P1L3);
        theoryTask2P1L3 = (LinearLayout) findViewById(R.id.theoryTask2P1L3);
        theoryTask3P1L3 = (LinearLayout) findViewById(R.id.theoryTask3P1L3);
        theoryTask1P2L3 = (LinearLayout) findViewById(R.id.theoryTask1P2L3);
        theoryTask2P2L3 = (LinearLayout) findViewById(R.id.theoryTask2P2L3);
        theoryTask3P2L3 = (LinearLayout) findViewById(R.id.theoryTask3P2L3);
        theoryTask1P3L3 = (LinearLayout) findViewById(R.id.theoryTask1P3L3);
        theoryTask2P3L3 = (LinearLayout) findViewById(R.id.theoryTask2P3L3);
        theoryTask1P4L3 = (LinearLayout) findViewById(R.id.theoryTask1P4L3);
        theoryTask2P4L3 = (LinearLayout) findViewById(R.id.theoryTask2P4L3);
        theoryTask3P4L3 = (LinearLayout) findViewById(R.id.theoryTask3P4L3);
        theoryUser1P1L3 = (TextInputEditText) findViewById(R.id.theoryUser1P1L3);
        theoryUser2P1L3 = (TextInputEditText) findViewById(R.id.theoryUser2P1L3);
        theoryUser3P1L3 = (TextInputEditText) findViewById(R.id.theoryUser3P1L3);
        theoryUser1P2L3 = (TextInputEditText) findViewById(R.id.theoryUser1P2L3);
        theoryUser2P2L3 = (TextInputEditText) findViewById(R.id.theoryUser2P2L3);
        theoryUser3P2L3 = (TextInputEditText) findViewById(R.id.theoryUser3P2L3);
        theoryUser1P3L3 = (TextInputEditText) findViewById(R.id.theoryUser1P3L3);
        theoryUser2P3L3 = (TextInputEditText) findViewById(R.id.theoryUser2P3L3);
        theoryUser1P4L3 = (TextInputEditText) findViewById(R.id.theoryUser1P4L3);
        theoryUser2P4L3 = (TextInputEditText) findViewById(R.id.theoryUser2P4L3);
        theoryUser3P4L3 = (TextInputEditText) findViewById(R.id.theoryUser3P4L3);
        textInfoP1L3 = (TextView) findViewById(R.id.textInfoP1L3);
        textInfoP2L3 = (TextView) findViewById(R.id.textInfoP2L3);
        textInfoP3L3 = (TextView) findViewById(R.id.textInfoP3L3);
        textInfoP4L3 = (TextView) findViewById(R.id.textInfoP4L3);
        btnSaveTheory1P1L3 = (Button) findViewById(R.id.btnSaveTheory1P1L3);
        btnSaveTheory2P1L3 = (Button) findViewById(R.id.btnSaveTheory2P1L3);
        btnSaveTheory3P1L3 = (Button) findViewById(R.id.btnSaveTheory3P1L3);
        btnSaveTheory1P2L3 = (Button) findViewById(R.id.btnSaveTheory1P2L3);
        btnSaveTheory2P2L3 = (Button) findViewById(R.id.btnSaveTheory2P2L3);
        btnSaveTheory3P2L3 = (Button) findViewById(R.id.btnSaveTheory3P2L3);
        btnSaveTheory1P3L3 = (Button) findViewById(R.id.btnSaveTheory1P3L3);
        btnSaveTheory2P3L3 = (Button) findViewById(R.id.btnSaveTheory2P3L3);
        btnSaveTheory3P4L3 = (Button) findViewById(R.id.btnSaveTheory3P4L3);
        btnSaveTheory1P4L3 = (Button) findViewById(R.id.btnSaveTheory1P4L3);
        btnSaveTheory2P4L3 = (Button) findViewById(R.id.btnSaveTheory2P4L3);
        btnSaveTheory1P1L3.setOnClickListener(this);
        btnSaveTheory2P1L3.setOnClickListener(this);
        btnSaveTheory3P1L3.setOnClickListener(this);
        btnSaveTheory1P2L3.setOnClickListener(this);
        btnSaveTheory2P2L3.setOnClickListener(this);
        btnSaveTheory3P2L3.setOnClickListener(this);
        btnSaveTheory1P3L3.setOnClickListener(this);
        btnSaveTheory2P3L3.setOnClickListener(this);
        btnSaveTheory1P4L3.setOnClickListener(this);
        btnSaveTheory2P4L3.setOnClickListener(this);
        btnSaveTheory3P4L3.setOnClickListener(this);
        btnBackFromTheory3 = (ImageButton) findViewById(R.id.btnBackFromTheory3);
        btnBackFromTheory3.setOnClickListener(this);

        loadData();
    }

    /**
     * Načte data teoretických materiálů třetí lekce a uživatelovy poznámky z databáze a aktualizuje layout
     */
    private void loadData() {

        DatabaseReference myRefTask1 = database.getReference("UserTheory").child(user.getUid()).child("Lesson3")
                .child("Page1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                /**
                 * Načtení informací o stránce teorie
                 */
                LessonPage lessonPage = snapshot.child("PageParams").getValue(LessonPage.class);
                if (lessonPage != null) {
                    textInfoP1L3.setText(lessonPage.getInfo());
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask1").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask1P1L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask2").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask2P1L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask3").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask3P1L3.addView(tw);
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser1").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser1P1L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser2").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser2P1L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser3").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser3P1L3.setText(text);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        DatabaseReference myRefTask2 = database.getReference("UserTheory").child(user.getUid()).child("Lesson3")
                .child("Page2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                /**
                 * Načtení informací o stránce teorie
                 */
                LessonPage lessonPage = snapshot.child("PageParams").getValue(LessonPage.class);
                if(lessonPage!=null) {
                    textInfoP2L3.setText(lessonPage.getInfo());
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask1").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask1P2L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask2").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask2P2L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask3").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask3P2L3.addView(tw);
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser1").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser1P2L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser2").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser2P2L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser3").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser3P2L3.setText(text);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        DatabaseReference myRefTask3 = database.getReference("UserTheory").child(user.getUid()).child("Lesson3")
                .child("Page3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                /**
                 * Načtení informací o stránce teorie
                 */
                LessonPage lessonPage = snapshot.child("PageParams").getValue(LessonPage.class);
                if (lessonPage != null) {
                    textInfoP3L3.setText(lessonPage.getInfo());
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask1").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask1P3L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask2").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask2P3L3.addView(tw);
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser1").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser1P3L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser2").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser2P3L3.setText(text);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        DatabaseReference myRefTask4 = database.getReference("UserTheory").child(user.getUid()).child("Lesson3")
                .child("Page4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                /**
                 * Načtení informací o stránce teorie
                 */
                LessonPage lessonPage = snapshot.child("PageParams").getValue(LessonPage.class);
                if(lessonPage!=null) {
                    textInfoP4L3.setText(lessonPage.getInfo());
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask1").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask1P4L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask2").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask2P4L3.addView(tw);
                    }
                    /**
                     * Načtení teorie
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTask3").getChildren()) {
                        TextView tw = new TextView(TheoryLesson3Activity.this);
                        tw.setText(dataSnapshot.getValue().toString());
                        tw.setTextColor(Color.BLACK);
                        if (dataSnapshot.getKey().equals("info")) {
                            tw.setTextSize(22);
                            tw.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            tw.setTextSize(18);
                        }
                        tw.setBackgroundResource(R.color.spinner_selected);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        tw.setLayoutParams(lp);
                        theoryTask3P4L3.addView(tw);
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser1").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser1P4L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser2").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser2P4L3.setText(text);
                        }
                    }
                    /**
                     * Načtení uživatelových poznámek
                     */
                    for (DataSnapshot dataSnapshot : snapshot.child("TheoryTaskUser3").getChildren()) {
                        if (dataSnapshot != null) {
                            String text = dataSnapshot.getValue().toString();
                            theoryUser3P4L3.setText(text);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    /**
     * Nastavení buttonu
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * Tlačítka pro uložení příslušených poznámek pomocí volání metod
             */
            case (R.id.btnSaveTheory1P1L3):
                saveTheory1L3P1();
                break;
            case (R.id.btnSaveTheory2P1L3):
                saveTheory2L3P1();
                break;
            case (R.id.btnSaveTheory3P1L3):
                saveTheory3L3P1();
                break;
            case (R.id.btnSaveTheory1P2L3):
                saveTheory1L3P2();
                break;
            case (R.id.btnSaveTheory2P2L3):
                saveTheory2L3P2();
                break;
            case (R.id.btnSaveTheory3P2L3):
                saveTheory3L3P2();
                break;
            case (R.id.btnSaveTheory1P3L3):
                saveTheory1L3P3();
                break;
            case (R.id.btnSaveTheory2P3L3):
                saveTheory2L3P3();
                break;
            case (R.id.btnBackFromTheory3):
                try{
                    startActivity(new Intent(TheoryLesson3Activity.this, NavigationActivity.class));
                }finally {
                    finish();
                }
                break;
            case (R.id.btnSaveTheory1P4L3):
                saveTheory1L3P4();
                break;
            case (R.id.btnSaveTheory2P4L3):
                saveTheory2L3P4();
                break;
            case (R.id.btnSaveTheory3P4L3):
                saveTheory3L3P4();
                break;
        }
    }

    /**
     * Uložení uživatelových poznámek ke druhé části na první stránce ze třetí lekce
     */
    private void saveTheory2L3P1() {
        UserTheory userTheory = new UserTheory(theoryUser2P1L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTaskUser2")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek k první části na první stránce ze třetí lekce
     */
    private void saveTheory1L3P1() {
        UserTheory userTheory = new UserTheory(theoryUser1P1L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTaskUser1")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek ke třetí části na první stránce ze třetí lekce
     */
    private void saveTheory3L3P1() {
        UserTheory userTheory = new UserTheory(theoryUser3P1L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTaskUser3")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek k první části na druhé stránce ze třetí lekce
     */
    private void saveTheory1L3P2() {
        UserTheory userTheory = new UserTheory(theoryUser1P2L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page2")
                .child("TheoryTaskUser1")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek ke druhé části na druhé stránce ze třetí lekce
     */
    private void saveTheory2L3P2() {
        UserTheory userTheory = new UserTheory(theoryUser2P2L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page2")
                .child("TheoryTaskUser2")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek ke třetí části na druhé stránce ze třetí lekce
     */
    private void saveTheory3L3P2() {
        UserTheory userTheory = new UserTheory(theoryUser3P2L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page2")
                .child("TheoryTaskUser3")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }
    /**
     * Uložení uživatelových poznámek k první části na třetí stránce ze třetí lekce
     */
    private void saveTheory1L3P3() {
        UserTheory userTheory = new UserTheory(theoryUser1P3L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page3")
                .child("TheoryTaskUser1")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek ke druhé části na třetí stránce ze třetí lekce
     */
    private void saveTheory2L3P3() {
        UserTheory userTheory = new UserTheory(theoryUser2P3L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page3")
                .child("TheoryTaskUser2")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek k první části na čtvrté stránce ze třetí lekce
     */
    private void saveTheory1L3P4() {
        UserTheory userTheory = new UserTheory(theoryUser1P4L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page4")
                .child("TheoryTaskUser1")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }

    /**
     * Uložení uživatelových poznámek ke druhé části na čtvrté stránce ze třetí lekce
     */
    private void saveTheory2L3P4() {
        UserTheory userTheory = new UserTheory(theoryUser2P4L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page4")
                .child("TheoryTaskUser2")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
    }
    /**
     * Uložení uživatelových poznámek ke druhé části na čtvrté stránce ze třetí lekce
     */
    private void saveTheory3L3P4() {
        UserTheory userTheory = new UserTheory(theoryUser3P4L3.getText().toString());
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page4")
                .child("TheoryTaskUser3")
                .setValue(userTheory);
        Toast.makeText(TheoryLesson3Activity.this, "Uloženo", Toast.LENGTH_LONG).show();
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