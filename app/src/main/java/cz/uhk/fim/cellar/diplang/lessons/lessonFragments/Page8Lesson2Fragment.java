package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Locale;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.PageTask;
import cz.uhk.fim.cellar.diplang.classes.UserTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;

import static android.view.View.GONE;

public class Page8Lesson2Fragment extends Fragment implements View.OnClickListener {

    private EditText ET1L2P8, ET2L2P8, ET3L2P8, ET4L2P8, ET5L2P8;
    private Button btnSaveL2P8, btnNextToP9;
    private String A1T1L2P8, A2T1L2P8, A3T1L2P8, A4T1L2P8, A5T1L2P8;
    private String rightAnswerTextTask1L2P8, rightAnswerTextTask2L2P8, rightAnswerTextTask3L2P8, rightAnswerTextTask4L2P8, rightAnswerTextTask5L2P8;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL2P8;
    private TextView finishTVL2P8, TVPointsL2P8;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L2P8, RightAnswer2L2P8, RightAnswer3L2P8, RightAnswer4L2P8, RightAnswer5L2P8;
    private TextView task1L2P8, task2L2P8, task3L2P8, task4L2P8, task5L2P8, infoPage8Lesson2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5;
    private UserTask utask1, utask2, utask3, utask4, utask5;
    private int pagePoints;


    public Page8Lesson2Fragment() {
        // Required empty public constructor
    }


    public static Page8Lesson2Fragment newInstance(String param1, String param2) {
        Page8Lesson2Fragment fragment = new Page8Lesson2Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page8_lesson2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        ET1L2P8 = (EditText) v.findViewById(R.id.ET1L2P8);
        ET2L2P8 = (EditText) v.findViewById(R.id.ET2L2P8);
        ET3L2P8 = (EditText) v.findViewById(R.id.ET3L2P8);
        ET4L2P8 = (EditText) v.findViewById(R.id.ET4L2P8);
        ET5L2P8 = (EditText) v.findViewById(R.id.ET5L2P8);
        btnSaveL2P8 = (Button) v.findViewById(R.id.btnSaveL2P8);
        finishL2P8 = (LinearLayout) v.findViewById(R.id.finishL2P8);
        finishTVL2P8 = (TextView) v.findViewById(R.id.finishTVL2P8);
        btnNextToP9 = (Button) v.findViewById(R.id.btnNextToP9L2);
        TVPointsL2P8 = (TextView) v.findViewById(R.id.TVPointsL2P8);
        TVPointsL2P8.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L2P8 = (TextView) v.findViewById(R.id.RightAnswer1L2P8);
        RightAnswer2L2P8 = (TextView) v.findViewById(R.id.RightAnswer2L2P8);
        RightAnswer3L2P8 = (TextView) v.findViewById(R.id.RightAnswer3L2P8);
        RightAnswer4L2P8 = (TextView) v.findViewById(R.id.RightAnswer4L2P8);
        RightAnswer5L2P8 = (TextView) v.findViewById(R.id.RightAnswer5L2P8);
        task1L2P8 = (TextView) v.findViewById(R.id.task1L2P8);
        task2L2P8 = (TextView) v.findViewById(R.id.task2L2P8);
        task3L2P8 = (TextView) v.findViewById(R.id.task3L2P8);
        task4L2P8 = (TextView) v.findViewById(R.id.task4L2P8);
        task5L2P8 = (TextView) v.findViewById(R.id.task5L2P8);
        infoPage8Lesson2 = (TextView) v.findViewById(R.id.infoPage8Lesson2);
        utask1 = new UserTask();
        utask2 = new UserTask();
        utask3 = new UserTask();
        utask4 = new UserTask();
        utask5 = new UserTask();
        utask1.setCreated(LocalDateTime.now().toString());
        utask2.setCreated(LocalDateTime.now().toString());
        utask3.setCreated(LocalDateTime.now().toString());
        utask4.setCreated(LocalDateTime.now().toString());
        utask5.setCreated(LocalDateTime.now().toString());

        finishL2P8.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        loadData();

        btnSaveL2P8.setOnClickListener(this);
        btnNextToP9.setOnClickListener(this);

        return v;
    }


    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson2").child("Page8");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage8Lesson2.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page8").child("Task1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L2P8.setText(task1.getText());
                    task1L2P8.isTextSelectable();
                    rightAnswerTextTask1L2P8 = task1.getRightAnswer();
                    RightAnswer1L2P8.setText("Right answer: "+rightAnswerTextTask1L2P8);
                    RightAnswer1L2P8.isTextSelectable();
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson2").child("Page8").child("Task2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L2P8.setText(task2.getText());
                    task2L2P8.isTextSelectable();
                    rightAnswerTextTask2L2P8 = task2.getRightAnswer();
                    RightAnswer2L2P8.setText("Right answer: "+rightAnswerTextTask2L2P8);
                    RightAnswer2L2P8.isTextSelectable();
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson2").child("Page8").child("Task3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L2P8.setText(task3.getText());
                    task3L2P8.isTextSelectable();
                    rightAnswerTextTask3L2P8 = task3.getRightAnswer();
                    RightAnswer3L2P8.setText("Right answer: "+rightAnswerTextTask3L2P8);
                    RightAnswer3L2P8.isTextSelectable();
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson2").child("Page8").child("Task4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L2P8.setText(task4.getText());
                    task4L2P8.isTextSelectable();
                    rightAnswerTextTask4L2P8 = task4.getRightAnswer();
                    RightAnswer4L2P8.setText("Right answer: "+rightAnswerTextTask4L2P8);
                    RightAnswer4L2P8.isTextSelectable();
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson2").child("Page8").child("Task5");
        myRefTask5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L2P8.setText(task5.getText());
                    task5L2P8.isTextSelectable();
                    rightAnswerTextTask5L2P8 = task5.getRightAnswer();
                    RightAnswer5L2P8.setText("Right answer: "+rightAnswerTextTask5L2P8);
                    RightAnswer5L2P8.isTextSelectable();
                    pointsT5 = task5.getPoints();
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
            case (R.id.btnSaveL2P8):
                points = calculatePoint();
                finishL2P8.setVisibility(View.VISIBLE);
                finishTVL2P8.setText("Splněno! "+points +"dips!");
                btnSaveL2P8.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL2P8.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP9L2): viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page8")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page8")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page8")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page8")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page8")
                .child("Task5")
                .setValue(utask5);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL2P8 = (TextView) this.getView().findViewById(R.id.TVPointsL2P8);
        TVPointsL2P8.setText(viewModel.getDipPoints().getValue().toString());
    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L2P8 = ET1L2P8.getText().toString();
        A2T1L2P8 = ET2L2P8.getText().toString();
        A3T1L2P8 = ET3L2P8.getText().toString();
        A4T1L2P8 = ET4L2P8.getText().toString();
        A5T1L2P8 = ET5L2P8.getText().toString();
        ET1L2P8.setInputType(InputType.TYPE_NULL);
        ET2L2P8.setInputType(InputType.TYPE_NULL);
        ET3L2P8.setInputType(InputType.TYPE_NULL);
        ET4L2P8.setInputType(InputType.TYPE_NULL);
        ET5L2P8.setInputType(InputType.TYPE_NULL);
        utask1.setAnswer(A1T1L2P8);
        utask2.setAnswer(A2T1L2P8);
        utask3.setAnswer(A3T1L2P8);
        utask4.setAnswer(A4T1L2P8);
        utask5.setAnswer(A5T1L2P8);
        if(A1T1L2P8.toLowerCase().equals(rightAnswerTextTask1L2P8.toLowerCase())) {
            ET1L2P8.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L2P8.setVisibility(View.VISIBLE);
            ET1L2P8.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L2P8.toLowerCase().equals(rightAnswerTextTask2L2P8.toLowerCase())) {
            ET2L2P8.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L2P8.setVisibility(View.VISIBLE);
            ET2L2P8.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L2P8.toLowerCase().equals(rightAnswerTextTask3L2P8.toLowerCase())) {
            ET3L2P8.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L2P8.setVisibility(View.VISIBLE);
            ET3L2P8.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L2P8.toLowerCase().equals(rightAnswerTextTask4L2P8.toLowerCase())) {
            ET4L2P8.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L2P8.setVisibility(View.VISIBLE);
            ET4L2P8.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L2P8.toLowerCase().equals(rightAnswerTextTask5L2P8.toLowerCase())) {
            ET5L2P8.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L2P8.setVisibility(View.VISIBLE);
            ET5L2P8.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        return pointsCount;
    }
}