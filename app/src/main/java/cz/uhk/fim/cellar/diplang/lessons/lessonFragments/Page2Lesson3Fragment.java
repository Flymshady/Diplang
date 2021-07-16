package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.TheoryTask;

public class Page2Lesson3Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP3L3;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    private TextView infoPage2Lesson3;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TheoryTask theoryTask1, theoryTask2, theoryTask3;
    private LessonPage lessonPage;

    public Page2Lesson3Fragment() {
        // Required empty public constructor
    }


    public static Page2Lesson3Fragment newInstance(String param1, String param2) {
        Page2Lesson3Fragment fragment = new Page2Lesson3Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page2_lesson3, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP2L3);

        loadData();

        infoPage2Lesson3 = (TextView) v.findViewById(R.id.infoPage2Lesson3);
        btnNextToP3L3 = (Button) v.findViewById(R.id.btnNextToP3L3);
        btnNextToP3L3.setOnClickListener(this);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL3);

        return v;
    }

    private void loadData() {

        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson3").child("Page2");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage2Lesson3.setText(lessonPage.getInfo());

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson3").child("Page2").child("TheoryTask1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                theoryTask1 = snapshot.getValue(TheoryTask.class);
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();
                    value = value.replace(";", "\n");

                    TextView textView = new TextView(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER;
                    lp.setMargins(0, 5, 0, 0);

                    if(key.equals("info")){
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setTextSize(20);
                    }
                    textView.setText(value);
                    textView.setTextSize(18);
                    textView.setTextIsSelectable(true);
                    textView.setTextColor(Color.BLACK);
                    textView.setLayoutParams(lp);
                    linearLayout.addView(textView);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        DatabaseReference myRefTask2 = database.getReference("Lessons").child("Lesson3").child("Page2").child("TheoryTask2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                theoryTask2 = snapshot.getValue(TheoryTask.class);
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();
                    value = value.replace(";", "\n");

                    TextView textView = new TextView(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER;
                    lp.setMargins(0, 5, 0, 0);

                    if(key.equals("info")){
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                    textView.setText(value);
                    textView.setTextSize(20);
                    textView.setTextIsSelectable(true);
                    textView.setTextColor(Color.BLACK);
                    textView.setLayoutParams(lp);
                    linearLayout.addView(textView);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        DatabaseReference myRefTask3 = database.getReference("Lessons").child("Lesson3").child("Page2").child("TheoryTask3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                theoryTask3 = snapshot.getValue(TheoryTask.class);
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();
                    value = value.replace(";", "\n");

                    TextView textView = new TextView(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER;
                    lp.setMargins(0, 15, 0, 0);

                    if(key.equals("info")){
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                    textView.setText(value);
                    textView.setTextSize(20);
                    textView.setTextIsSelectable(true);
                    textView.setTextColor(Color.BLACK);
                    textView.setLayoutParams(lp);
                    linearLayout.addView(textView);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP3L3:
                saveUserTheory();
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    private void saveUserTheory() {
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .setValue(lessonPage);
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTask1")
                .setValue(theoryTask1);
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTask2")
                .setValue(theoryTask2);
        FirebaseDatabase.getInstance().getReference("UserTheory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page1")
                .child("TheoryTask3")
                .setValue(theoryTask3);
    }
}