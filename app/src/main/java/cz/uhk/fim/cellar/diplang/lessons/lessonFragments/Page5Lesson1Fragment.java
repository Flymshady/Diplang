package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment páté stránky, první lekce, úrovně B2
 */
public class Page5Lesson1Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP6;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    private LessonViewModel viewModel;
    private TextView TVPointsL1P5;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Page5Lesson1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page5_lesson1, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP5L1);

        loadData();

        btnNextToP6 = (Button) v.findViewById(R.id.btnNextToP6);
        btnNextToP6.setOnClickListener(this);

        TVPointsL1P5 = (TextView) v.findViewById(R.id.TVPointsL1P5);
        TVPointsL1P5.setText(viewModel.getDipPoints().getValue().toString());

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
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson1").child("Page5").child("TextTask1");
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
            case R.id.btnNextToP6:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }
}