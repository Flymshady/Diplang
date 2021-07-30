package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.TheoryTask;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment první stránky, druhé lekce, úrovně B2
 */
public class Page1Lesson2Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP2L2;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TheoryTask theoryTask;
    private boolean loaded;

    public Page1Lesson2Fragment() {
        // Required empty public constructor
    }

    public static Page1Lesson2Fragment newInstance(String param1, String param2) {
        Page1Lesson2Fragment fragment = new Page1Lesson2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page1_lesson2, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP1L2);
        loadData();
        btnNextToP2L2 = (Button) v.findViewById(R.id.btnNextToP2L2);
        btnNextToP2L2.setOnClickListener(this);
        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        return v;
    }

    /**
     * Načtení dat
     */
    private void loadData() {
        /**
         * Načtení textové úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page1").child("TextTask1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot!=null) {
                        loaded=true;
                        String key = dataSnapshot.getKey();
                        String value = dataSnapshot.getValue().toString();
                        value = value.replace(";", "\n");

                        TextView textView = new TextView(getActivity());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        lp.setMargins(0, 20, 0, 0);

                        textView.setText(value);
                        textView.setTextSize(20);
                        textView.setTextIsSelectable(true);
                        textView.setTextColor(Color.BLACK);
                        textView.setLayoutParams(lp);

                        linearLayout.addView(textView);
                    }else{
                        loaded=false;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                loaded=false;
            }
        });

        /**
         * Načtení teoretické úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask2 = database.getReference("Lessons").child("Lesson2").child("Page1").child("TheoryTask1");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                theoryTask = snapshot.getValue(TheoryTask.class);
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot!=null) {
                        loaded=true;
                        String key = dataSnapshot.getKey();
                        String value = dataSnapshot.getValue().toString();
                        value = value.replace(";", "\n");
                        TextView textView = new TextView(getActivity());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;
                        lp.setMargins(0, 20, 0, 0);
                        textView.setText(value);
                        textView.setTextSize(20);
                        textView.setTextIsSelectable(true);
                        textView.setTextColor(Color.BLACK);
                        textView.setLayoutParams(lp);

                        linearLayout.addView(textView);
                    }else{
                        loaded=false;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                loaded=false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP2L2:
                /**
                 * Zavolání metody pro uložení teoretické úlohy
                 * a přechod na další stránku
                 */
                if(loaded) {
                    saveUserTheory();
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else {
                    Toast.makeText(getContext(), "Data lekce nebyly načteny. Připojte se k síti a opakujte pokus.", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    /**
     * Uložení teoretické úlohy a její zpřístupnění pro uživatele
     */
    private void saveUserTheory() {
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page1")
                .child("TheoryTask1")
                .setValue(theoryTask);
    }
}