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
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.TranslatorActivity;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;


public class Page7Lesson2Fragment extends Fragment implements View.OnClickListener {

    private Button btnNextToP8L2, btnHintP7L2;
    private ViewPager2 viewPager2;
    private LinearLayout linearLayoutA, linearLayoutB, linearLayout;
    private LessonViewModel viewModel;
    private TextView TVPointsL2P7, textA1P7L2, textA2P7L2, textA3P7L2, textA4P7L2, textA5P7L2;
    private TextView textB1P7L2, textB2P7L2, textB3P7L2, textB4P7L2, textB5P7L2, textInfoP7L2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Page7Lesson2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page7_lesson2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        textA1P7L2 = (TextView) v.findViewById(R.id.textA1P7L2);
        textA2P7L2 = (TextView) v.findViewById(R.id.textA2P7L2);
        textA3P7L2 = (TextView) v.findViewById(R.id.textA3P7L2);
        textA4P7L2 = (TextView) v.findViewById(R.id.textA4P7L2);
        textA5P7L2 = (TextView) v.findViewById(R.id.textA5P7L2);
        textB1P7L2 = (TextView) v.findViewById(R.id.textB1P7L2);
        textB2P7L2 = (TextView) v.findViewById(R.id.textB2P7L2);
        textB3P7L2 = (TextView) v.findViewById(R.id.textB3P7L2);
        textB4P7L2 = (TextView) v.findViewById(R.id.textB4P7L2);
        textB5P7L2 = (TextView) v.findViewById(R.id.textB5P7L2);
        textInfoP7L2 = (TextView) v.findViewById(R.id.textInfoP7L2);

        loadData();

        btnNextToP8L2 = (Button) v.findViewById(R.id.btnNextToP8L2);
        btnNextToP8L2.setOnClickListener(this);

        btnHintP7L2 = (Button) v.findViewById(R.id.btnHintP7L2);
        btnHintP7L2.setOnClickListener(this);

        TVPointsL2P7 = (TextView) v.findViewById(R.id.TVPointsL2P7);
        TVPointsL2P7.setText(viewModel.getDipPoints().getValue().toString());

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        return v;
    }

    private void loadData() {

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page7").child("TheoryTask");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String key = dataSnapshot.getKey();
                    String value = dataSnapshot.getValue().toString();
                    if(key.equals("info")){
                        textInfoP7L2.setText(value);
                        textInfoP7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textA1")){
                        textA1P7L2.setText(value);
                        textA1P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textA2")){
                        textA2P7L2.setText(value);
                        textA2P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textA3")){
                        textA3P7L2.setText(value);
                        textA3P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textA4")){
                        textA4P7L2.setText(value);
                        textA4P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textA4")){
                        textA5P7L2.setText(value);
                        textA5P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textB1")){
                        textB1P7L2.setText(value);
                        textB1P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textB2")){
                        textB2P7L2.setText(value);
                        textB2P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textB3")){
                        textB3P7L2.setText(value);
                        textB3P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textB4")){
                        textB4P7L2.setText(value);
                        textB4P7L2.setTextIsSelectable(true);
                    }
                    if(key.equals("textB4")) {
                        textB5P7L2.setText(value);
                        textB5P7L2.setTextIsSelectable(true);
                    }

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
        TVPointsL2P7 = (TextView) this.getView().findViewById(R.id.TVPointsL2P7);
        TVPointsL2P7.setText(viewModel.getDipPoints().getValue().toString());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP8L2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case R.id.btnHintP7L2:
                hintQuestion();
                break;
        }
    }

    private void hintQuestion() {
        textB1P7L2.setBackgroundResource(R.color.right_answer_color);
        textB2P7L2.setBackgroundResource(R.color.right_answer_color);
        textB3P7L2.setBackgroundResource(R.color.right_answer_color);
        textB4P7L2.setBackgroundResource(R.color.right_answer_color);
        textB5P7L2.setBackgroundResource(R.color.right_answer_color);
        Toast.makeText(getActivity(), "More diplomatic sentences", Toast.LENGTH_LONG).show();

    }
}