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
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;


public class Page3Lesson2Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP4L2;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayout;
    private LessonViewModel viewModel;
    private TextView TVPointsL2P3;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Page3Lesson2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page3_lesson2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        linearLayout = (LinearLayout) v.findViewById(R.id.layoutP3L2);

        loadData();

        btnNextToP4L2 = (Button) v.findViewById(R.id.btnNextToP4L2);
        btnNextToP4L2.setOnClickListener(this);

        TVPointsL2P3 = (TextView) v.findViewById(R.id.TVPointsL2P3);
        TVPointsL2P3.setText(viewModel.getDipPoints().getValue().toString());

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        return v;
    }

    private void loadData() {

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page3").child("TextTask");
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
                    textView.isTextSelectable();
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
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL2P3 = (TextView) this.getView().findViewById(R.id.TVPointsL2P3);
        TVPointsL2P3.setText(viewModel.getDipPoints().getValue().toString());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP4L2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    }
}