package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cz.uhk.fim.cellar.diplang.classes.PageTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;

public class PageFragment1 extends Fragment implements View.OnClickListener {

    private Button btnNextToP2;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private TextView task1L1P1, task2L1P1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public PageFragment1() {
        // Required empty public constructor
    }


    public static PageFragment1 newInstance(String param1, String param2) {
        PageFragment1 fragment = new PageFragment1();
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
        View v = inflater.inflate(R.layout.fragment_page1, container, false);

        task1L1P1 = (TextView) v.findViewById(R.id.task1L1P1);
        task2L1P1 = (TextView) v.findViewById(R.id.task2L1P1);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        loadData();

        btnNextToP2 = (Button) v.findViewById(R.id.btnNextToP2);
        btnNextToP2.setOnClickListener(this);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        return v;
    }

    private void loadData() {
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson1").child("Page1").child("Task1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L1P1.setText(task1.getText());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        DatabaseReference myRefTask2 = database.getReference("Lessons").child("Lesson1").child("Page1").child("Task2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L1P1.setText(task2.getText());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    }
}