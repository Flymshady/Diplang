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

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.PageTask;
import cz.uhk.fim.cellar.diplang.classes.UserTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;

import static android.view.View.GONE;

public class Page6Lesson3Fragment extends Fragment implements View.OnClickListener {

    private EditText ET1L3P6, ET2L3P6, ET3L3P6, ET4L3P6, ET5L3P6;
    private EditText ET6L3P6, ET7L3P6, ET8L3P6, ET9L3P6, ET10L3P6;
    private Button btnSaveL3P6, btnNextToP7;
    private String A1T1L3P6, A2T1L3P6, A3T1L3P6, A4T1L3P6, A5T1L3P6;
    private String A6T1L3P6, A7T1L3P6, A8T1L3P6, A9T1L3P6, A10T1L3P6;
    private String rightAnswerTextTask1L3P6, rightAnswer2TextTask1L3P6, rightAnswerTextTask2L3P6, rightAnswerTextTask3L3P6, rightAnswerTextTask4L3P6, rightAnswerTextTask5L3P6;
    private String rightAnswerTextTask6L3P6, rightAnswer2TextTask6L3P6, rightAnswerTextTask7L3P6, rightAnswerTextTask8L3P6, rightAnswer2TextTask8L3P6, rightAnswerTextTask9L3P6, rightAnswerTextTask10L3P6;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL3P6;
    private TextView finishTVL3P6, TVPointsL3P6;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L3P6, RightAnswer2L3P6, RightAnswer3L3P6, RightAnswer4L3P6, RightAnswer5L3P6;
    private TextView RightAnswer6L3P6, RightAnswer7L3P6, RightAnswer8L3P6, RightAnswer9L3P6, RightAnswer10L3P6;
    private TextView task1L3P6, task2L3P6, task3L3P6, task4L3P6, task5L3P6, infoPage6Lesson3;
    private TextView task6L3P6, task7L3P6, task8L3P6, task9L3P6, task10L3P6;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6, pointsT7, pointsT8, pointsT9, pointsT10;
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6, utask7, utask8, utask9, utask10;
    private int pagePoints;


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

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        ET1L3P6 = (EditText) v.findViewById(R.id.ET1L3P6);
        ET2L3P6 = (EditText) v.findViewById(R.id.ET2L3P6);
        ET3L3P6 = (EditText) v.findViewById(R.id.ET3L3P6);
        ET4L3P6 = (EditText) v.findViewById(R.id.ET4L3P6);
        ET5L3P6 = (EditText) v.findViewById(R.id.ET5L3P6);
        ET6L3P6 = (EditText) v.findViewById(R.id.ET6L3P6);
        ET7L3P6 = (EditText) v.findViewById(R.id.ET7L3P6);
        ET8L3P6 = (EditText) v.findViewById(R.id.ET8L3P6);
        ET9L3P6 = (EditText) v.findViewById(R.id.ET9L3P6);
        ET10L3P6 = (EditText) v.findViewById(R.id.ET10L3P6);
        btnSaveL3P6 = (Button) v.findViewById(R.id.btnSaveL3P6);
        finishL3P6 = (LinearLayout) v.findViewById(R.id.finishL3P6);
        finishTVL3P6 = (TextView) v.findViewById(R.id.finishTVL3P6);
        btnNextToP7 = (Button) v.findViewById(R.id.btnNextToP7L3);
        TVPointsL3P6 = (TextView) v.findViewById(R.id.TVPointsL3P6);
        TVPointsL3P6.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L3P6 = (TextView) v.findViewById(R.id.RightAnswer1L3P6);
        RightAnswer2L3P6 = (TextView) v.findViewById(R.id.RightAnswer2L3P6);
        RightAnswer3L3P6 = (TextView) v.findViewById(R.id.RightAnswer3L3P6);
        RightAnswer4L3P6 = (TextView) v.findViewById(R.id.RightAnswer4L3P6);
        RightAnswer5L3P6 = (TextView) v.findViewById(R.id.RightAnswer5L3P6);
        RightAnswer6L3P6 = (TextView) v.findViewById(R.id.RightAnswer6L3P6);
        RightAnswer7L3P6 = (TextView) v.findViewById(R.id.RightAnswer7L3P6);
        RightAnswer8L3P6 = (TextView) v.findViewById(R.id.RightAnswer8L3P6);
        RightAnswer9L3P6 = (TextView) v.findViewById(R.id.RightAnswer9L3P6);
        RightAnswer10L3P6 = (TextView) v.findViewById(R.id.RightAnswer10L3P6);
        task1L3P6 = (TextView) v.findViewById(R.id.task1L3P6);
        task2L3P6 = (TextView) v.findViewById(R.id.task2L3P6);
        task3L3P6 = (TextView) v.findViewById(R.id.task3L3P6);
        task4L3P6 = (TextView) v.findViewById(R.id.task4L3P6);
        task5L3P6 = (TextView) v.findViewById(R.id.task5L3P6);
        task6L3P6 = (TextView) v.findViewById(R.id.task6L3P6);
        task7L3P6 = (TextView) v.findViewById(R.id.task7L3P6);
        task8L3P6 = (TextView) v.findViewById(R.id.task8L3P6);
        task9L3P6 = (TextView) v.findViewById(R.id.task9L3P6);
        task10L3P6 = (TextView) v.findViewById(R.id.task10L3P6);
        infoPage6Lesson3 = (TextView) v.findViewById(R.id.infoPage6Lesson3);
        utask1 = new UserTask();
        utask2 = new UserTask();
        utask3 = new UserTask();
        utask4 = new UserTask();
        utask5 = new UserTask();
        utask6 = new UserTask();
        utask7 = new UserTask();
        utask8 = new UserTask();
        utask9 = new UserTask();
        utask10 = new UserTask();
        utask1.setCreated(LocalDateTime.now().toString());
        utask2.setCreated(LocalDateTime.now().toString());
        utask3.setCreated(LocalDateTime.now().toString());
        utask4.setCreated(LocalDateTime.now().toString());
        utask5.setCreated(LocalDateTime.now().toString());
        utask6.setCreated(LocalDateTime.now().toString());
        utask7.setCreated(LocalDateTime.now().toString());
        utask8.setCreated(LocalDateTime.now().toString());
        utask9.setCreated(LocalDateTime.now().toString());
        utask10.setCreated(LocalDateTime.now().toString());

        finishL3P6.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL3);

        loadData();

        btnSaveL3P6.setOnClickListener(this);
        btnNextToP7.setOnClickListener(this);

        return v;
    }


    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson3").child("Page6");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage6Lesson3.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson3").child("Page6").child("Task1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L3P6.setText(task1.getText());
                    task1L3P6.isTextSelectable();
                    rightAnswerTextTask1L3P6 = task1.getRightAnswer();
                    rightAnswer2TextTask1L3P6 = task1.getRightAnswer2();
                    RightAnswer1L3P6.setText("Right answer: "+rightAnswerTextTask1L3P6+" / " + rightAnswer2TextTask1L3P6);
                    RightAnswer1L3P6.isTextSelectable();
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L3P6.setText(task2.getText());
                    task2L3P6.isTextSelectable();
                    rightAnswerTextTask2L3P6 = task2.getRightAnswer();
                    RightAnswer2L3P6.setText("Right answer: "+rightAnswerTextTask2L3P6);
                    RightAnswer2L3P6.isTextSelectable();
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L3P6.setText(task3.getText());
                    task3L3P6.isTextSelectable();
                    rightAnswerTextTask3L3P6 = task3.getRightAnswer();
                    RightAnswer3L3P6.setText("Right answer: "+rightAnswerTextTask3L3P6);
                    RightAnswer3L3P6.isTextSelectable();
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L3P6.setText(task4.getText());
                    task4L3P6.isTextSelectable();
                    rightAnswerTextTask4L3P6 = task4.getRightAnswer();
                    RightAnswer4L3P6.setText("Right answer: "+rightAnswerTextTask4L3P6);
                    RightAnswer4L3P6.isTextSelectable();
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task5");
        myRefTask5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L3P6.setText(task5.getText());
                    task5L3P6.isTextSelectable();
                    rightAnswerTextTask5L3P6 = task5.getRightAnswer();
                    RightAnswer5L3P6.setText("Right answer: "+rightAnswerTextTask5L3P6);
                    RightAnswer5L3P6.isTextSelectable();
                    pointsT5 = task5.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask6 = database.getReference("Lessons").child("Lesson3").child("Page6").child("Task6");
        myRefTask6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task6 = dataSnapshot.getValue(PageTask.class);
                if(task6!=null){
                    task6L3P6.setText(task6.getText());
                    task6L3P6.isTextSelectable();
                    rightAnswerTextTask6L3P6 = task6.getRightAnswer();
                    rightAnswer2TextTask6L3P6 = task6.getRightAnswer2();
                    RightAnswer6L3P6.setText("Right answer: "+rightAnswerTextTask6L3P6+" / " + rightAnswer2TextTask6L3P6);
                    RightAnswer6L3P6.isTextSelectable();
                    pointsT6 = task6.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask7 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task7");
        myRefTask7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task7 = dataSnapshot.getValue(PageTask.class);
                if(task7!=null){
                    task7L3P6.setText(task7.getText());
                    task7L3P6.isTextSelectable();
                    rightAnswerTextTask7L3P6 = task7.getRightAnswer();
                    RightAnswer7L3P6.setText("Right answer: "+rightAnswerTextTask7L3P6);
                    RightAnswer7L3P6.isTextSelectable();
                    pointsT7 = task7.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask8 = database.getReference("Lessons").child("Lesson3").child("Page6").child("Task8");
        myRefTask8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task8 = dataSnapshot.getValue(PageTask.class);
                if(task8!=null){
                    task8L3P6.setText(task8.getText());
                    task8L3P6.isTextSelectable();
                    rightAnswerTextTask8L3P6 = task8.getRightAnswer();
                    rightAnswer2TextTask8L3P6 = task8.getRightAnswer2();
                    RightAnswer8L3P6.setText("Right answer: "+rightAnswerTextTask8L3P6+" / " + rightAnswer2TextTask8L3P6);
                    RightAnswer8L3P6.isTextSelectable();
                    pointsT8 = task8.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask9 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task9");
        myRefTask9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task9 = dataSnapshot.getValue(PageTask.class);
                if(task9!=null){
                    task9L3P6.setText(task9.getText());
                    task9L3P6.isTextSelectable();
                    rightAnswerTextTask9L3P6 = task9.getRightAnswer();
                    RightAnswer9L3P6.setText("Right answer: "+rightAnswerTextTask9L3P6);
                    RightAnswer9L3P6.isTextSelectable();
                    pointsT9 = task9.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask10 = database
                .getReference("Lessons").child("Lesson3").child("Page6").child("Task10");
        myRefTask10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task10 = dataSnapshot.getValue(PageTask.class);
                if(task10!=null){
                    task10L3P6.setText(task10.getText());
                    task10L3P6.isTextSelectable();
                    rightAnswerTextTask10L3P6 = task10.getRightAnswer();
                    RightAnswer10L3P6.setText("Right answer: "+rightAnswerTextTask10L3P6);
                    RightAnswer10L3P6.isTextSelectable();
                    pointsT10 = task10.getPoints();
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
            case (R.id.btnSaveL3P6):
                points = calculatePoint();
                finishL3P6.setVisibility(View.VISIBLE);
                finishTVL3P6.setText("Splněno! "+points +"dips!");
                btnSaveL3P6.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL3P6.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP7L3): viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task6")
                .setValue(utask6);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task7")
                .setValue(utask7);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task8")
                .setValue(utask8);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task9")
                .setValue(utask9);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page6")
                .child("Task10")
                .setValue(utask10);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL3P6 = (TextView) this.getView().findViewById(R.id.TVPointsL3P6);
        TVPointsL3P6.setText(viewModel.getDipPoints().getValue().toString());
    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L3P6 = ET1L3P6.getText().toString();
        A2T1L3P6 = ET2L3P6.getText().toString();
        A3T1L3P6 = ET3L3P6.getText().toString();
        A4T1L3P6 = ET4L3P6.getText().toString();
        A5T1L3P6 = ET5L3P6.getText().toString();
        A6T1L3P6 = ET6L3P6.getText().toString();
        A7T1L3P6 = ET7L3P6.getText().toString();
        A8T1L3P6 = ET8L3P6.getText().toString();
        A9T1L3P6 = ET9L3P6.getText().toString();
        A10T1L3P6 = ET10L3P6.getText().toString();
        ET1L3P6.setInputType(InputType.TYPE_NULL);
        ET2L3P6.setInputType(InputType.TYPE_NULL);
        ET3L3P6.setInputType(InputType.TYPE_NULL);
        ET4L3P6.setInputType(InputType.TYPE_NULL);
        ET5L3P6.setInputType(InputType.TYPE_NULL);
        ET6L3P6.setInputType(InputType.TYPE_NULL);
        ET7L3P6.setInputType(InputType.TYPE_NULL);
        ET8L3P6.setInputType(InputType.TYPE_NULL);
        ET9L3P6.setInputType(InputType.TYPE_NULL);
        ET10L3P6.setInputType(InputType.TYPE_NULL);
        utask1.setAnswer(A1T1L3P6);
        utask2.setAnswer(A2T1L3P6);
        utask3.setAnswer(A3T1L3P6);
        utask4.setAnswer(A4T1L3P6);
        utask5.setAnswer(A5T1L3P6);
        utask6.setAnswer(A6T1L3P6);
        utask7.setAnswer(A7T1L3P6);
        utask8.setAnswer(A8T1L3P6);
        utask9.setAnswer(A9T1L3P6);
        utask10.setAnswer(A10T1L3P6);
        if(A1T1L3P6.toLowerCase().equals(rightAnswerTextTask1L3P6.toLowerCase())) {
            ET1L3P6.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else if(A1T1L3P6.toLowerCase().equals(rightAnswer2TextTask1L3P6.toLowerCase())){
            ET1L3P6.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L3P6.setVisibility(View.VISIBLE);
            ET1L3P6.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L3P6.toLowerCase().equals(rightAnswerTextTask2L3P6.toLowerCase())) {
            ET2L3P6.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L3P6.setVisibility(View.VISIBLE);
            ET2L3P6.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L3P6.toLowerCase().equals(rightAnswerTextTask3L3P6.toLowerCase())) {
            ET3L3P6.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L3P6.setVisibility(View.VISIBLE);
            ET3L3P6.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L3P6.toLowerCase().equals(rightAnswerTextTask4L3P6.toLowerCase())) {
            ET4L3P6.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L3P6.setVisibility(View.VISIBLE);
            ET4L3P6.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L3P6.toLowerCase().equals(rightAnswerTextTask5L3P6.toLowerCase())) {
            ET5L3P6.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L3P6.setVisibility(View.VISIBLE);
            ET5L3P6.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        if(A6T1L3P6.toLowerCase().equals(rightAnswerTextTask6L3P6.toLowerCase())) {
            ET6L3P6.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else if(A6T1L3P6.toLowerCase().equals(rightAnswer2TextTask6L3P6.toLowerCase())){
            ET6L3P6.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else{
            RightAnswer6L3P6.setVisibility(View.VISIBLE);
            ET6L3P6.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }
        if(A7T1L3P6.toLowerCase().equals(rightAnswerTextTask7L3P6.toLowerCase())) {
            ET7L3P6.setBackgroundResource(R.color.green);
            utask7.setPoints(pointsT7);
            pointsCount+=pointsT7;
        }else {
            RightAnswer7L3P6.setVisibility(View.VISIBLE);
            ET7L3P6.setBackgroundResource(R.color.red);
            utask7.setPoints(0);
        }
        if(A8T1L3P6.toLowerCase().equals(rightAnswerTextTask8L3P6.toLowerCase())) {
            ET8L3P6.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else if(A8T1L3P6.toLowerCase().equals(rightAnswer2TextTask8L3P6.toLowerCase())){
            ET8L3P6.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else{
            RightAnswer8L3P6.setVisibility(View.VISIBLE);
            ET8L3P6.setBackgroundResource(R.color.red);
            utask8.setPoints(0);
        }
        if(A9T1L3P6.toLowerCase().equals(rightAnswerTextTask9L3P6.toLowerCase())) {
            ET9L3P6.setBackgroundResource(R.color.green);
            utask9.setPoints(pointsT9);
            pointsCount+=pointsT9;
        }else {
            RightAnswer9L3P6.setVisibility(View.VISIBLE);
            ET9L3P6.setBackgroundResource(R.color.red);
            utask9.setPoints(0);
        }
        if(A10T1L3P6.toLowerCase().equals(rightAnswerTextTask10L3P6.toLowerCase())) {
            ET10L3P6.setBackgroundResource(R.color.green);
            utask10.setPoints(pointsT10);
            pointsCount+=pointsT10;
        }else {
            RightAnswer10L3P6.setVisibility(View.VISIBLE);
            ET10L3P6.setBackgroundResource(R.color.red);
            utask10.setPoints(0);
        }
        return pointsCount;
    }
}