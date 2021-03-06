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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.R;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment páté stránky, třetí lekce, úrovně B2
 */
public class Page6Lesson3Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP7L3;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Page6Lesson3Fragment() {
        // Required empty public constructor
    }

    public static Page6Lesson3Fragment newInstance(String param1, String param2) {
        Page6Lesson3Fragment fragment = new Page6Lesson3Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page6_lesson3, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP6L3);

        loadData();
        btnNextToP7L3 = (Button) v.findViewById(R.id.btnNextToP7L3);
        btnNextToP7L3.setOnClickListener(this);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL3);

        return v;
    }
    /**
     * Načtení dat
     */
    private void loadData() {

        /**
         * Načtení textové úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson3").child("Page6").child("TextTask");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();

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

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    /** Nastavení buttonu **/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP7L3:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }
}