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

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment čtvrté stránky, první lekce, úrovně B2
 */
public class Page4Lesson1Fragment extends Fragment implements View.OnClickListener {

    private EditText ET1L1P4, ET2L1P4, ET3L1P4, ET4L1P4;
    private Button btnSaveL1P4, btnNextToP5;
    private String A1T1L1P4, A2T1L1P4, A3T1L1P4, A4T1L1P4 ="";
    private String rightAnswerTextTask1L1P4, rightAnswerTextTask2L1P4, rightAnswerTextTask3L1P4, rightAnswerTextTask4L1P4;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL1P4;
    private TextView finishTVL1P4, TVPointsL1P4;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L1P4, RightAnswer2L1P4, RightAnswer3L1P4, RightAnswer4L1P4;
    private TextView task1L1P4, task2L1P4, task3L1P4, task4L1P4, infoPage4Lesson1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5;
    private int pointsT1, pointsT2, pointsT3, pointsT4;
    private UserTask utask1, utask2, utask3, utask4;
    private int pagePoints;

    public Page4Lesson1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page4_lesson1, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        ET1L1P4 = (EditText) v.findViewById(R.id.ET1L1P4);
        ET2L1P4 = (EditText) v.findViewById(R.id.ET2L1P4);
        ET3L1P4 = (EditText) v.findViewById(R.id.ET3L1P4);
        ET4L1P4 = (EditText) v.findViewById(R.id.ET4L1P4);
        btnSaveL1P4 = (Button) v.findViewById(R.id.btnSaveL1P4);
        finishL1P4 = (LinearLayout) v.findViewById(R.id.finishL1P4);
        finishTVL1P4 = (TextView) v.findViewById(R.id.finishTVL1P4);
        btnNextToP5 = (Button) v.findViewById(R.id.btnNextToP5);
        TVPointsL1P4 = (TextView) v.findViewById(R.id.TVPointsL1P4);
        TVPointsL1P4.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L1P4 = (TextView) v.findViewById(R.id.RightAnswer1L1P4);
        RightAnswer2L1P4 = (TextView) v.findViewById(R.id.RightAnswer2L1P4);
        RightAnswer3L1P4 = (TextView) v.findViewById(R.id.RightAnswer3L1P4);
        RightAnswer4L1P4 = (TextView) v.findViewById(R.id.RightAnswer4L1P4);
        task1L1P4 = (TextView) v.findViewById(R.id.task1L1P4);
        task2L1P4 = (TextView) v.findViewById(R.id.task2L1P4);
        task3L1P4 = (TextView) v.findViewById(R.id.task3L1P4);
        task4L1P4 = (TextView) v.findViewById(R.id.task4L1P4);
        infoPage4Lesson1 = (TextView) v.findViewById(R.id.infoPage4Lesson1);
        utask1 = new UserTask();
        utask2 = new UserTask();
        utask3 = new UserTask();
        utask4 = new UserTask();
        utask1.setCreated(LocalDateTime.now().toString());
        utask2.setCreated(LocalDateTime.now().toString());
        utask3.setCreated(LocalDateTime.now().toString());
        utask4.setCreated(LocalDateTime.now().toString());

        finishL1P4.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        loadData();

        btnSaveL1P4.setOnClickListener(this);
        btnNextToP5.setOnClickListener(this);

        return v;
    }
    /**
     * Načtení dat
     */
    private void loadData() {
        /** Načtení informací o stránce **/
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson1").child("Page4").child("PageParams");
        myRefPage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage4Lesson1.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        /**
         * Načtení úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson1").child("Page4").child("Task1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L1P4.setText(task1.getText());
                    rightAnswerTextTask1L1P4 = task1.getRightAnswer();
                    RightAnswer1L1P4.setText("Right answer: "+rightAnswerTextTask1L1P4);
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        /**
         * Načtení úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson1").child("Page4").child("Task2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L1P4.setText(task2.getText());
                    rightAnswerTextTask2L1P4 = task2.getRightAnswer();
                    RightAnswer2L1P4.setText("Right answer: "+rightAnswerTextTask2L1P4);
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        /**
         * Načtení úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson1").child("Page4").child("Task3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L1P4.setText(task3.getText());
                    rightAnswerTextTask3L1P4 = task3.getRightAnswer();
                    RightAnswer3L1P4.setText("Right answer: "+rightAnswerTextTask3L1P4);
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        /**
         * Načtení úlohy a její přidání do layoutu
         */
        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson1").child("Page4").child("Task4");
        myRefTask4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                PageTask task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L1P4.setText(task4.getText());
                    rightAnswerTextTask4L1P4 = task4.getRightAnswer();
                    RightAnswer4L1P4.setText("Right answer: "+rightAnswerTextTask4L1P4);
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    /** Nastavení buttonů **/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL1P4):
                points = calculatePoint();
                finishL1P4.setVisibility(View.VISIBLE);
                finishTVL1P4.setText("Splněno! "+points +"dips!");
                btnSaveL1P4.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL1P4.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP5):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    /** Uložení uživatelových odpovědí do db **/
    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page4")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page4")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page4")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1")
                .child("Page4")
                .child("Task4")
                .setValue(utask4);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1P4 = (TextView) this.getView().findViewById(R.id.TVPointsL1P4);
        TVPointsL1P4.setText(viewModel.getDipPoints().getValue().toString());

    }

    /** Porovnání odpovědí, zobrazení správných řešení, kalkulace bodů **/
    private int calculatePoint() {
        int pointsCount=0;
        A1T1L1P4 = ET1L1P4.getText().toString();
        A2T1L1P4 = ET2L1P4.getText().toString();
        A3T1L1P4 = ET3L1P4.getText().toString();
        A4T1L1P4 = ET4L1P4.getText().toString();
        ET1L1P4.setFocusableInTouchMode(false);
        ET2L1P4.setFocusableInTouchMode(false);
        ET3L1P4.setFocusableInTouchMode(false);
        ET4L1P4.setFocusableInTouchMode(false);
        ET1L1P4.setFocusable(false);
        ET2L1P4.setFocusable(false);
        ET3L1P4.setFocusable(false);
        ET4L1P4.setFocusable(false);
        utask1.setAnswer(A1T1L1P4);
        utask2.setAnswer(A2T1L1P4);
        utask3.setAnswer(A3T1L1P4);
        utask4.setAnswer(A4T1L1P4);
        if(A1T1L1P4.toLowerCase().equals(rightAnswerTextTask1L1P4.toLowerCase())) {
            ET1L1P4.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L1P4.setVisibility(View.VISIBLE);
            ET1L1P4.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L1P4.toLowerCase().equals(rightAnswerTextTask2L1P4.toLowerCase())) {
            ET2L1P4.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L1P4.setVisibility(View.VISIBLE);
            ET2L1P4.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L1P4.toLowerCase().equals(rightAnswerTextTask3L1P4.toLowerCase())) {
            ET3L1P4.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L1P4.setVisibility(View.VISIBLE);
            ET3L1P4.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L1P4.toLowerCase().equals(rightAnswerTextTask4L1P4.toLowerCase())) {
            ET4L1P4.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L1P4.setVisibility(View.VISIBLE);
            ET4L1P4.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        return pointsCount;
    }
        
        
}