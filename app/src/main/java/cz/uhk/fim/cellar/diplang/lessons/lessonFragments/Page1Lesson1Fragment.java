package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.R;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment první stránky, první lekce, úrovně B2
 */
public class Page1Lesson1Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP2;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private boolean loaded;

    public Page1Lesson1Fragment() {
        // Required empty public constructor
    }

    public static Page1Lesson1Fragment newInstance(String param1, String param2) {
        Page1Lesson1Fragment fragment = new Page1Lesson1Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page1_lesson1, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP1L1);
        loadData();
        btnNextToP2 = (Button) v.findViewById(R.id.btnNextToP2);
        btnNextToP2.setOnClickListener(this);
        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        return v;
    }

    /**
     * Načtení dat
     */
    private void loadData() {
        /**
         * Načtení textové úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson1").child("Page1").child("TextTask1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot!=null) {
                        loaded=true;
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
            case R.id.btnNextToP2:
                /**
                 * Přechod na další stránku
                 */
                if(loaded) {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else{
                    Toast.makeText(getContext(), "Data lekce nebyly načteny. Připojte se k síti a opakujte pokus.", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}