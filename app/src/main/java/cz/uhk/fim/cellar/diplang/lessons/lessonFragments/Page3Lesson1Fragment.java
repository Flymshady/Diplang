package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.PageTask;
import cz.uhk.fim.cellar.diplang.classes.UserTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;

import static android.view.View.GONE;


public class Page3Lesson1Fragment extends Fragment implements View.OnClickListener {

    private EditText ET1L1P3, ET2L1P3, ET3L1P3, ET4L1P3, ET5L1P3, ET6L1P3;
    private Button btnSaveL1P3, btnNextToP4;
    private String A1T1L1P3, A2T1L1P3, A3T1L1P3, A4T1L1P3, A5T1L1P3, A6T1L1P3 ="";
    private String rightAnswerTextTask1L1P3, rightAnswerTextTask2L1P3, rightAnswerTextTask3L1P3, rightAnswerTextTask4L1P3, rightAnswerTextTask5L1P3, rightAnswerTextTask6L1P3;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL1P3;
    private TextView finishTVL1P3, TVPointsL1P3;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L1P3, RightAnswer2L1P3, RightAnswer3L1P3, RightAnswer4L1P3, RightAnswer5L1P3, RightAnswer6L1P3;
    private TextView task1L1P3, task2L1P3, task3L1P3, task4L1P3, task5L1P3, task6L1P3, infoPage3Lesson1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5, task6;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6;
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6;
    private int pagePoints;

    public Page3Lesson1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page3_lesson1, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);


        ET1L1P3 = (EditText) v.findViewById(R.id.ET1L1P3);
        ET2L1P3 = (EditText) v.findViewById(R.id.ET2L1P3);
        ET3L1P3 = (EditText) v.findViewById(R.id.ET3L1P3);
        ET4L1P3 = (EditText) v.findViewById(R.id.ET4L1P3);
        ET5L1P3 = (EditText) v.findViewById(R.id.ET5L1P3);
        ET6L1P3 = (EditText) v.findViewById(R.id.ET6L1P3);
        btnSaveL1P3 = (Button) v.findViewById(R.id.btnSaveL1P3);
        finishL1P3 = (LinearLayout) v.findViewById(R.id.finishL1P3);
        finishTVL1P3 = (TextView) v.findViewById(R.id.finishTVL1P3);
        btnNextToP4 = (Button) v.findViewById(R.id.btnNextToP4);
        TVPointsL1P3 = (TextView) v.findViewById(R.id.TVPointsL1P3);
        TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L1P3 = (TextView) v.findViewById(R.id.RightAnswer1L1P3);
        RightAnswer2L1P3 = (TextView) v.findViewById(R.id.RightAnswer2L1P3);
        RightAnswer3L1P3 = (TextView) v.findViewById(R.id.RightAnswer3L1P3);
        RightAnswer4L1P3 = (TextView) v.findViewById(R.id.RightAnswer4L1P3);
        RightAnswer5L1P3 = (TextView) v.findViewById(R.id.RightAnswer5L1P3);
        RightAnswer6L1P3 = (TextView) v.findViewById(R.id.RightAnswer6L1P3);
        task1L1P3 = (TextView) v.findViewById(R.id.task1L1P3);
        task2L1P3 = (TextView) v.findViewById(R.id.task2L1P3);
        task3L1P3 = (TextView) v.findViewById(R.id.task3L1P3);
        task4L1P3 = (TextView) v.findViewById(R.id.task4L1P3);
        task5L1P3 = (TextView) v.findViewById(R.id.task5L1P3);
        task6L1P3 = (TextView) v.findViewById(R.id.task6L1P3);
        infoPage3Lesson1 = (TextView) v.findViewById(R.id.infoPage3Lesson1);
        utask1 = new UserTask();
        utask2 = new UserTask();
        utask3 = new UserTask();
        utask4 = new UserTask();
        utask5 = new UserTask();
        utask6 = new UserTask();
        utask1.setCreated(LocalDateTime.now().toString());
        utask2.setCreated(LocalDateTime.now().toString());
        utask3.setCreated(LocalDateTime.now().toString());
        utask4.setCreated(LocalDateTime.now().toString());
        utask5.setCreated(LocalDateTime.now().toString());
        utask6.setCreated(LocalDateTime.now().toString());

        finishL1P3.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        loadData();

        btnSaveL1P3.setOnClickListener(this);
        btnNextToP4.setOnClickListener(this);

        return v;
    }

    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson1").child("Page3");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage3Lesson1.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson1").child("Page3").child("Task1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L1P3.setText(task1.getText());
                    rightAnswerTextTask1L1P3 = task1.getRightAnswer();
                    RightAnswer1L1P3.setText("Right answer: "+rightAnswerTextTask1L1P3);
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson1").child("Page3").child("Task2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L1P3.setText(task2.getText());
                    rightAnswerTextTask2L1P3 = task2.getRightAnswer();
                    RightAnswer2L1P3.setText("Right answer: "+rightAnswerTextTask2L1P3);
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson1").child("Page3").child("Task3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L1P3.setText(task3.getText());
                    rightAnswerTextTask3L1P3 = task3.getRightAnswer();
                    RightAnswer3L1P3.setText("Right answer: "+rightAnswerTextTask3L1P3);
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson1").child("Page3").child("Task4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L1P3.setText(task4.getText());
                    rightAnswerTextTask4L1P3 = task4.getRightAnswer();
                    RightAnswer4L1P3.setText("Right answer: "+rightAnswerTextTask4L1P3);
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson1").child("Page3").child("Task5");
        myRefTask5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L1P3.setText(task5.getText());
                    rightAnswerTextTask5L1P3 = task5.getRightAnswer();
                    RightAnswer5L1P3.setText("Right answer: "+rightAnswerTextTask5L1P3);
                    pointsT5 = task5.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask6 = database
                .getReference("Lessons").child("Lesson1").child("Page3").child("Task6");
        myRefTask6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task6 = dataSnapshot.getValue(PageTask.class);
                if(task6!=null){
                    task6L1P3.setText(task6.getText());
                    rightAnswerTextTask6L1P3 = task6.getRightAnswer();
                    RightAnswer6L1P3.setText("Right answer: "+rightAnswerTextTask6L1P3);
                    pointsT6 = task6.getPoints();
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
            case (R.id.btnSaveL1P3):
                points = calculatePoint();
                finishL1P3.setVisibility(View.VISIBLE);
                finishTVL1P3.setText("Splněno! "+points +"dips!");
                btnSaveL1P3.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP4):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page3")
                .child("Task6")
                .setValue(utask6);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1P3 = (TextView) this.getView().findViewById(R.id.TVPointsL1P3);
        TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());

    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L1P3 = ET1L1P3.getText().toString();
        A2T1L1P3 = ET2L1P3.getText().toString();
        A3T1L1P3 = ET3L1P3.getText().toString();
        A4T1L1P3 = ET4L1P3.getText().toString();
        A5T1L1P3 = ET5L1P3.getText().toString();
        A6T1L1P3 = ET6L1P3.getText().toString();
        ET1L1P3.setInputType(InputType.TYPE_NULL);
        ET2L1P3.setInputType(InputType.TYPE_NULL);
        ET3L1P3.setInputType(InputType.TYPE_NULL);
        ET4L1P3.setInputType(InputType.TYPE_NULL);
        ET5L1P3.setInputType(InputType.TYPE_NULL);
        ET6L1P3.setInputType(InputType.TYPE_NULL);
        utask1.setAnswer(A1T1L1P3);
        utask2.setAnswer(A2T1L1P3);
        utask3.setAnswer(A3T1L1P3);
        utask4.setAnswer(A4T1L1P3);
        utask5.setAnswer(A5T1L1P3);
        utask6.setAnswer(A6T1L1P3);
        if(A1T1L1P3.toLowerCase().equals(rightAnswerTextTask1L1P3.toLowerCase())) {
            ET1L1P3.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L1P3.setVisibility(View.VISIBLE);
            ET1L1P3.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L1P3.toLowerCase().equals(rightAnswerTextTask2L1P3.toLowerCase())) {
            ET2L1P3.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L1P3.setVisibility(View.VISIBLE);
            ET2L1P3.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L1P3.toLowerCase().equals(rightAnswerTextTask3L1P3.toLowerCase())) {
            ET3L1P3.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L1P3.setVisibility(View.VISIBLE);
            ET3L1P3.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L1P3.toLowerCase().equals(rightAnswerTextTask4L1P3.toLowerCase())) {
            ET4L1P3.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L1P3.setVisibility(View.VISIBLE);
            ET4L1P3.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L1P3.toLowerCase().equals(rightAnswerTextTask5L1P3.toLowerCase())) {
            ET5L1P3.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L1P3.setVisibility(View.VISIBLE);
            ET5L1P3.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        if(A6T1L1P3.toLowerCase().equals(rightAnswerTextTask6L1P3.toLowerCase())) {
            ET6L1P3.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else {
            RightAnswer6L1P3.setVisibility(View.VISIBLE);
            ET6L1P3.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }
        return pointsCount;
    }
}