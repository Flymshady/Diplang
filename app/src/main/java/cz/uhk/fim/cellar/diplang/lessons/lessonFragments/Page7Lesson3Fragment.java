package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;
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

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment šesté stránky, třetí lekce, úrovně B2
 */
public class Page7Lesson3Fragment extends Fragment implements View.OnClickListener {

    private EditText ET1L3P7, ET2L3P7, ET3L3P7, ET4L3P7, ET5L3P7;
    private EditText ET6L3P7, ET7L3P7, ET8L3P7, ET9L3P7, ET10L3P7;
    private Button btnSaveL3P7, btnNextToP8;
    private String A1T1L3P7, A2T1L3P7, A3T1L3P7, A4T1L3P7, A5T1L3P7;
    private String A6T1L3P7, A7T1L3P7, A8T1L3P7, A9T1L3P7, A10T1L3P7;
    private String rightAnswerTextTask1L3P7, rightAnswer2TextTask1L3P7, rightAnswerTextTask2L3P7, rightAnswerTextTask3L3P7, rightAnswerTextTask4L3P7, rightAnswerTextTask5L3P7;
    private String rightAnswerTextTask6L3P7, rightAnswer2TextTask6L3P7, rightAnswerTextTask7L3P7, rightAnswerTextTask8L3P7, rightAnswer2TextTask8L3P7, rightAnswerTextTask9L3P7, rightAnswerTextTask10L3P7;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL3P7;
    private TextView finishTVL3P7, TVPointsL3P7;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L3P7, RightAnswer2L3P7, RightAnswer3L3P7, RightAnswer4L3P7, RightAnswer5L3P7;
    private TextView RightAnswer6L3P7, RightAnswer7L3P7, RightAnswer8L3P7, RightAnswer9L3P7, RightAnswer10L3P7;
    private TextView task1L3P7, task2L3P7, task3L3P7, task4L3P7, task5L3P7, infoPage7Lesson3;
    private TextView task6L3P7, task7L3P7, task8L3P7, task9L3P7, task10L3P7;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6, pointsT7, pointsT8, pointsT9, pointsT10;
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6, utask7, utask8, utask9, utask10;
    private int pagePoints;


    public Page7Lesson3Fragment() {
        // Required empty public constructor
    }


    public static Page7Lesson3Fragment newInstance(String param1, String param2) {
        Page7Lesson3Fragment fragment = new Page7Lesson3Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page7_lesson3, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        ET1L3P7 = (EditText) v.findViewById(R.id.ET1L3P7);
        ET2L3P7 = (EditText) v.findViewById(R.id.ET2L3P7);
        ET3L3P7 = (EditText) v.findViewById(R.id.ET3L3P7);
        ET4L3P7 = (EditText) v.findViewById(R.id.ET4L3P7);
        ET5L3P7 = (EditText) v.findViewById(R.id.ET5L3P7);
        ET6L3P7 = (EditText) v.findViewById(R.id.ET6L3P7);
        ET7L3P7 = (EditText) v.findViewById(R.id.ET7L3P7);
        ET8L3P7 = (EditText) v.findViewById(R.id.ET8L3P7);
        ET9L3P7 = (EditText) v.findViewById(R.id.ET9L3P7);
        ET10L3P7 = (EditText) v.findViewById(R.id.ET10L3P7);
        btnSaveL3P7 = (Button) v.findViewById(R.id.btnSaveL3P7);
        finishL3P7 = (LinearLayout) v.findViewById(R.id.finishL3P7);
        finishTVL3P7 = (TextView) v.findViewById(R.id.finishTVL3P7);
        btnNextToP8 = (Button) v.findViewById(R.id.btnNextToP8L3);
        TVPointsL3P7 = (TextView) v.findViewById(R.id.TVPointsL3P7);
        TVPointsL3P7.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L3P7 = (TextView) v.findViewById(R.id.RightAnswer1L3P7);
        RightAnswer2L3P7 = (TextView) v.findViewById(R.id.RightAnswer2L3P7);
        RightAnswer3L3P7 = (TextView) v.findViewById(R.id.RightAnswer3L3P7);
        RightAnswer4L3P7 = (TextView) v.findViewById(R.id.RightAnswer4L3P7);
        RightAnswer5L3P7 = (TextView) v.findViewById(R.id.RightAnswer5L3P7);
        RightAnswer6L3P7 = (TextView) v.findViewById(R.id.RightAnswer6L3P7);
        RightAnswer7L3P7 = (TextView) v.findViewById(R.id.RightAnswer7L3P7);
        RightAnswer8L3P7 = (TextView) v.findViewById(R.id.RightAnswer8L3P7);
        RightAnswer9L3P7 = (TextView) v.findViewById(R.id.RightAnswer9L3P7);
        RightAnswer10L3P7 = (TextView) v.findViewById(R.id.RightAnswer10L3P7);
        task1L3P7 = (TextView) v.findViewById(R.id.task1L3P7);
        task2L3P7 = (TextView) v.findViewById(R.id.task2L3P7);
        task3L3P7 = (TextView) v.findViewById(R.id.task3L3P7);
        task4L3P7 = (TextView) v.findViewById(R.id.task4L3P7);
        task5L3P7 = (TextView) v.findViewById(R.id.task5L3P7);
        task6L3P7 = (TextView) v.findViewById(R.id.task6L3P7);
        task7L3P7 = (TextView) v.findViewById(R.id.task7L3P7);
        task8L3P7 = (TextView) v.findViewById(R.id.task8L3P7);
        task9L3P7 = (TextView) v.findViewById(R.id.task9L3P7);
        task10L3P7 = (TextView) v.findViewById(R.id.task10L3P7);
        infoPage7Lesson3 = (TextView) v.findViewById(R.id.infoPage7Lesson3);
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

        finishL3P7.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL3);

        loadData();

        btnSaveL3P7.setOnClickListener(this);
        btnNextToP8.setOnClickListener(this);

        return v;
    }


    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson3").child("Page7").child("PageParams");
        myRefPage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage7Lesson3.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson3").child("Page7").child("Task1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L3P7.setText(task1.getText());
                    task1L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask1L3P7 = task1.getRightAnswer();
                    rightAnswer2TextTask1L3P7 = task1.getRightAnswer2();
                    RightAnswer1L3P7.setText("Right answer: "+rightAnswerTextTask1L3P7+" / " + rightAnswer2TextTask1L3P7);
                    RightAnswer1L3P7.setTextIsSelectable(true);
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L3P7.setText(task2.getText());
                    task2L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask2L3P7 = task2.getRightAnswer();
                    RightAnswer2L3P7.setText("Right answer: "+rightAnswerTextTask2L3P7);
                    RightAnswer2L3P7.setTextIsSelectable(true);
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L3P7.setText(task3.getText());
                    task3L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask3L3P7 = task3.getRightAnswer();
                    RightAnswer3L3P7.setText("Right answer: "+rightAnswerTextTask3L3P7);
                    RightAnswer3L3P7.setTextIsSelectable(true);
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task4");
        myRefTask4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L3P7.setText(task4.getText());
                    task4L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask4L3P7 = task4.getRightAnswer();
                    RightAnswer4L3P7.setText("Right answer: "+rightAnswerTextTask4L3P7);
                    RightAnswer4L3P7.setTextIsSelectable(true);
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task5");
        myRefTask5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L3P7.setText(task5.getText());
                    task5L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask5L3P7 = task5.getRightAnswer();
                    RightAnswer5L3P7.setText("Right answer: "+rightAnswerTextTask5L3P7);
                    RightAnswer5L3P7.setTextIsSelectable(true);
                    pointsT5 = task5.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask6 = database.getReference("Lessons").child("Lesson3").child("Page7").child("Task6");
        myRefTask6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task6 = dataSnapshot.getValue(PageTask.class);
                if(task6!=null){
                    task6L3P7.setText(task6.getText());
                    task6L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask6L3P7 = task6.getRightAnswer();
                    rightAnswer2TextTask6L3P7 = task6.getRightAnswer2();
                    RightAnswer6L3P7.setText("Right answer: "+rightAnswerTextTask6L3P7+" / " + rightAnswer2TextTask6L3P7);
                    RightAnswer6L3P7.setTextIsSelectable(true);
                    pointsT6 = task6.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask7 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task7");
        myRefTask7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task7 = dataSnapshot.getValue(PageTask.class);
                if(task7!=null){
                    task7L3P7.setText(task7.getText());
                    task7L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask7L3P7 = task7.getRightAnswer();
                    RightAnswer7L3P7.setText("Right answer: "+rightAnswerTextTask7L3P7);
                    RightAnswer7L3P7.setTextIsSelectable(true);
                    pointsT7 = task7.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask8 = database.getReference("Lessons").child("Lesson3").child("Page7").child("Task8");
        myRefTask8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task8 = dataSnapshot.getValue(PageTask.class);
                if(task8!=null){
                    task8L3P7.setText(task8.getText());
                    task8L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask8L3P7 = task8.getRightAnswer();
                    rightAnswer2TextTask8L3P7 = task8.getRightAnswer2();
                    RightAnswer8L3P7.setText("Right answer: "+rightAnswerTextTask8L3P7+" / " + rightAnswer2TextTask8L3P7);
                    RightAnswer8L3P7.setTextIsSelectable(true);
                    pointsT8 = task8.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask9 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task9");
        myRefTask9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task9 = dataSnapshot.getValue(PageTask.class);
                if(task9!=null){
                    task9L3P7.setText(task9.getText());
                    task9L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask9L3P7 = task9.getRightAnswer();
                    RightAnswer9L3P7.setText("Right answer: "+rightAnswerTextTask9L3P7);
                    RightAnswer9L3P7.setTextIsSelectable(true);
                    pointsT9 = task9.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask10 = database
                .getReference("Lessons").child("Lesson3").child("Page7").child("Task10");
        myRefTask10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task10 = dataSnapshot.getValue(PageTask.class);
                if(task10!=null){
                    task10L3P7.setText(task10.getText());
                    task10L3P7.setTextIsSelectable(true);
                    rightAnswerTextTask10L3P7 = task10.getRightAnswer();
                    RightAnswer10L3P7.setText("Right answer: "+rightAnswerTextTask10L3P7);
                    RightAnswer10L3P7.setTextIsSelectable(true);
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
            case (R.id.btnSaveL3P7):
                points = calculatePoint();
                finishL3P7.setVisibility(View.VISIBLE);
                finishTVL3P7.setText("Splněno! "+points +"dips!");
                btnSaveL3P7.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL3P7.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP8L3): viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task6")
                .setValue(utask6);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task7")
                .setValue(utask7);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task8")
                .setValue(utask8);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task9")
                .setValue(utask9);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson3")
                .child("Page7")
                .child("Task10")
                .setValue(utask10);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL3P7 = (TextView) this.getView().findViewById(R.id.TVPointsL3P7);
        TVPointsL3P7.setText(viewModel.getDipPoints().getValue().toString());
    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L3P7 = ET1L3P7.getText().toString();
        A2T1L3P7 = ET2L3P7.getText().toString();
        A3T1L3P7 = ET3L3P7.getText().toString();
        A4T1L3P7 = ET4L3P7.getText().toString();
        A5T1L3P7 = ET5L3P7.getText().toString();
        A6T1L3P7 = ET6L3P7.getText().toString();
        A7T1L3P7 = ET7L3P7.getText().toString();
        A8T1L3P7 = ET8L3P7.getText().toString();
        A9T1L3P7 = ET9L3P7.getText().toString();
        A10T1L3P7 = ET10L3P7.getText().toString();
        ET1L3P7.setFocusableInTouchMode(false);
        ET2L3P7.setFocusableInTouchMode(false);
        ET3L3P7.setFocusableInTouchMode(false);
        ET4L3P7.setFocusableInTouchMode(false);
        ET5L3P7.setFocusableInTouchMode(false);
        ET6L3P7.setFocusableInTouchMode(false);
        ET7L3P7.setFocusableInTouchMode(false);
        ET8L3P7.setFocusableInTouchMode(false);
        ET9L3P7.setFocusableInTouchMode(false);
        ET10L3P7.setFocusableInTouchMode(false);
        ET1L3P7.setFocusable(false);
        ET2L3P7.setFocusable(false);
        ET3L3P7.setFocusable(false);
        ET4L3P7.setFocusable(false);
        ET5L3P7.setFocusable(false);
        ET6L3P7.setFocusable(false);
        ET7L3P7.setFocusable(false);
        ET8L3P7.setFocusable(false);
        ET9L3P7.setFocusable(false);
        ET10L3P7.setFocusable(false);
        utask1.setAnswer(A1T1L3P7);
        utask2.setAnswer(A2T1L3P7);
        utask3.setAnswer(A3T1L3P7);
        utask4.setAnswer(A4T1L3P7);
        utask5.setAnswer(A5T1L3P7);
        utask6.setAnswer(A6T1L3P7);
        utask7.setAnswer(A7T1L3P7);
        utask8.setAnswer(A8T1L3P7);
        utask9.setAnswer(A9T1L3P7);
        utask10.setAnswer(A10T1L3P7);
        if(A1T1L3P7.toLowerCase().equals(rightAnswerTextTask1L3P7.toLowerCase())) {
            ET1L3P7.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else if(A1T1L3P7.toLowerCase().equals(rightAnswer2TextTask1L3P7.toLowerCase())){
            ET1L3P7.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L3P7.setVisibility(View.VISIBLE);
            ET1L3P7.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L3P7.toLowerCase().equals(rightAnswerTextTask2L3P7.toLowerCase())) {
            ET2L3P7.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L3P7.setVisibility(View.VISIBLE);
            ET2L3P7.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L3P7.toLowerCase().equals(rightAnswerTextTask3L3P7.toLowerCase())) {
            ET3L3P7.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L3P7.setVisibility(View.VISIBLE);
            ET3L3P7.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L3P7.toLowerCase().equals(rightAnswerTextTask4L3P7.toLowerCase())) {
            ET4L3P7.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L3P7.setVisibility(View.VISIBLE);
            ET4L3P7.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L3P7.toLowerCase().equals(rightAnswerTextTask5L3P7.toLowerCase())) {
            ET5L3P7.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L3P7.setVisibility(View.VISIBLE);
            ET5L3P7.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        if(A6T1L3P7.toLowerCase().equals(rightAnswerTextTask6L3P7.toLowerCase())) {
            ET6L3P7.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else if(A6T1L3P7.toLowerCase().equals(rightAnswer2TextTask6L3P7.toLowerCase())){
            ET6L3P7.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else{
            RightAnswer6L3P7.setVisibility(View.VISIBLE);
            ET6L3P7.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }
        if(A7T1L3P7.toLowerCase().equals(rightAnswerTextTask7L3P7.toLowerCase())) {
            ET7L3P7.setBackgroundResource(R.color.green);
            utask7.setPoints(pointsT7);
            pointsCount+=pointsT7;
        }else {
            RightAnswer7L3P7.setVisibility(View.VISIBLE);
            ET7L3P7.setBackgroundResource(R.color.red);
            utask7.setPoints(0);
        }
        if(A8T1L3P7.toLowerCase().equals(rightAnswerTextTask8L3P7.toLowerCase())) {
            ET8L3P7.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else if(A8T1L3P7.toLowerCase().equals(rightAnswer2TextTask8L3P7.toLowerCase())){
            ET8L3P7.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else{
            RightAnswer8L3P7.setVisibility(View.VISIBLE);
            ET8L3P7.setBackgroundResource(R.color.red);
            utask8.setPoints(0);
        }
        if(A9T1L3P7.toLowerCase().equals(rightAnswerTextTask9L3P7.toLowerCase())) {
            ET9L3P7.setBackgroundResource(R.color.green);
            utask9.setPoints(pointsT9);
            pointsCount+=pointsT9;
        }else {
            RightAnswer9L3P7.setVisibility(View.VISIBLE);
            ET9L3P7.setBackgroundResource(R.color.red);
            utask9.setPoints(0);
        }
        if(A10T1L3P7.toLowerCase().equals(rightAnswerTextTask10L3P7.toLowerCase())) {
            ET10L3P7.setBackgroundResource(R.color.green);
            utask10.setPoints(pointsT10);
            pointsCount+=pointsT10;
        }else {
            RightAnswer10L3P7.setVisibility(View.VISIBLE);
            ET10L3P7.setBackgroundResource(R.color.red);
            utask10.setPoints(0);
        }
        return pointsCount;
    }
}