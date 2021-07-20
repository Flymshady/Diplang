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

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment druhé stránky, druhé lekce, úrovně B2
 */
public class Page2Lesson2Fragment extends Fragment implements View.OnClickListener {

    private YouTubePlayerView youTubePlayerView;
    private String videoLink="";
    private ImageButton btnListening;
    private TextToSpeech mTTS;
    private String listeningText;

    private EditText ET1L2P2, ET2L2P2, ET3L2P2, ET4L2P2, ET5L2P2, ET6L2P2, ET7L2P2, ET8L2P2, ET9L2P2, ET10L2P2;
    private Button btnSaveL2P2, btnNextToP3;
    private String A1T1L2P2, A2T1L2P2, A3T1L2P2, A4T1L2P2, A5T1L2P2, A6T1L2P2, A7T1L2P2, A8T1L2P2, A9T1L2P2, A10T1L2P2;
    private String rightAnswerTextTask1L2P2, rightAnswerTextTask2L2P2, rightAnswerTextTask3L2P2, rightAnswerTextTask4L2P2, rightAnswerTextTask5L2P2;
    private String rightAnswerTextTask6L2P2, rightAnswerTextTask7L2P2, rightAnswerTextTask8L2P2, rightAnswerTextTask9L2P2, rightAnswerTextTask10L2P2;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL2P2;
    private TextView finishTVL2P2, TVPointsL2P2;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L2P2, RightAnswer2L2P2, RightAnswer3L2P2, RightAnswer4L2P2, RightAnswer5L2P2;
    private TextView RightAnswer6L2P2, RightAnswer7L2P2, RightAnswer8L2P2, RightAnswer9L2P2, RightAnswer10L2P2;
    private TextView task1L2P2, task2L2P2, task3L2P2, task4L2P2, task5L2P2, infoPage2Lesson2;
    private TextView task6L2P2, task7L2P2, task8L2P2, task9L2P2, task10L2P2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5;
    private PageTask task6, task7, task8, task9, task10, videoTask;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6, pointsT7, pointsT8, pointsT9, pointsT10;
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6, utask7, utask8, utask9, utask10;
    private int pagePoints;


    public Page2Lesson2Fragment() {
        // Required empty public constructor
    }


    public static Page2Lesson2Fragment newInstance(String param1, String param2) {
        Page2Lesson2Fragment fragment = new Page2Lesson2Fragment();
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
        View v = inflater.inflate(R.layout.fragment_page2_lesson2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        youTubePlayerView = v.findViewById(R.id.ytbVideo);
        getLifecycle().addObserver(youTubePlayerView);
        btnListening = v.findViewById(R.id.listeningSound);
        btnListening.setOnClickListener(this);

        ET1L2P2 = (EditText) v.findViewById(R.id.ET1L2P2);
        ET2L2P2 = (EditText) v.findViewById(R.id.ET2L2P2);
        ET3L2P2 = (EditText) v.findViewById(R.id.ET3L2P2);
        ET4L2P2 = (EditText) v.findViewById(R.id.ET4L2P2);
        ET5L2P2 = (EditText) v.findViewById(R.id.ET5L2P2);
        ET6L2P2 = (EditText) v.findViewById(R.id.ET6L2P2);
        ET7L2P2 = (EditText) v.findViewById(R.id.ET7L2P2);
        ET8L2P2 = (EditText) v.findViewById(R.id.ET8L2P2);
        ET9L2P2 = (EditText) v.findViewById(R.id.ET9L2P2);
        ET10L2P2 = (EditText) v.findViewById(R.id.ET10L2P2);
        btnSaveL2P2 = (Button) v.findViewById(R.id.btnSaveL2P2);
        finishL2P2 = (LinearLayout) v.findViewById(R.id.finishL2P2);
        finishTVL2P2 = (TextView) v.findViewById(R.id.finishTVL2P2);
        btnNextToP3 = (Button) v.findViewById(R.id.btnNextToP3L2);
        TVPointsL2P2 = (TextView) v.findViewById(R.id.TVPointsL2P2);
        TVPointsL2P2.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L2P2 = (TextView) v.findViewById(R.id.RightAnswer1L2P2);
        RightAnswer2L2P2 = (TextView) v.findViewById(R.id.RightAnswer2L2P2);
        RightAnswer3L2P2 = (TextView) v.findViewById(R.id.RightAnswer3L2P2);
        RightAnswer4L2P2 = (TextView) v.findViewById(R.id.RightAnswer4L2P2);
        RightAnswer5L2P2 = (TextView) v.findViewById(R.id.RightAnswer5L2P2);
        RightAnswer6L2P2 = (TextView) v.findViewById(R.id.RightAnswer6L2P2);
        RightAnswer7L2P2 = (TextView) v.findViewById(R.id.RightAnswer7L2P2);
        RightAnswer8L2P2 = (TextView) v.findViewById(R.id.RightAnswer8L2P2);
        RightAnswer9L2P2 = (TextView) v.findViewById(R.id.RightAnswer9L2P2);
        RightAnswer10L2P2 = (TextView) v.findViewById(R.id.RightAnswer10L2P2);
        task1L2P2 = (TextView) v.findViewById(R.id.task1L2P2);
        task2L2P2 = (TextView) v.findViewById(R.id.task2L2P2);
        task3L2P2 = (TextView) v.findViewById(R.id.task3L2P2);
        task4L2P2 = (TextView) v.findViewById(R.id.task4L2P2);
        task5L2P2 = (TextView) v.findViewById(R.id.task5L2P2);
        task6L2P2 = (TextView) v.findViewById(R.id.task6L2P2);
        task7L2P2 = (TextView) v.findViewById(R.id.task7L2P2);
        task8L2P2 = (TextView) v.findViewById(R.id.task8L2P2);
        task9L2P2 = (TextView) v.findViewById(R.id.task9L2P2);
        task10L2P2 = (TextView) v.findViewById(R.id.task10L2P2);
        infoPage2Lesson2 = (TextView) v.findViewById(R.id.infoPage2Lesson2);
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

        finishL2P2.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        loadData();
        setListeningFormat();

        btnSaveL2P2.setOnClickListener(this);
        btnNextToP3.setOnClickListener(this);

        return v;
    }

    private void setListeningFormat() {
        youTubePlayerView.setVisibility(View.VISIBLE);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String videoId = videoLink;
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        btnListening.setVisibility(View.VISIBLE);
        mTTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");
                    }else {
                        btnListening.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson2").child("Page2").child("PageParams");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage2Lesson2.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTaskVideo = database.getReference("Lessons").child("Lesson2").child("Page2").child("VideoTask");
        myRefTaskVideo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                videoTask = dataSnapshot.getValue(PageTask.class);
                if(videoTask!=null){
                    videoLink = videoTask.getLink();
                    System.out.println(videoTask.getText());
                    listeningText = videoTask.getText();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page2").child("Task1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L2P2.setText(task1.getText());
                    task1L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask1L2P2 = task1.getRightAnswer();
                    RightAnswer1L2P2.setText("Right answer: "+rightAnswerTextTask1L2P2);
                    RightAnswer1L2P2.setTextIsSelectable(true);
                    pointsT1 = task1.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L2P2.setText(task2.getText());
                    task2L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask2L2P2 = task2.getRightAnswer();
                    RightAnswer2L2P2.setText("Right answer: "+rightAnswerTextTask2L2P2);
                    RightAnswer2L2P2.setTextIsSelectable(true);
                    pointsT2 = task2.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L2P2.setText(task3.getText());
                    task3L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask3L2P2 = task3.getRightAnswer();
                    RightAnswer3L2P2.setText("Right answer: "+rightAnswerTextTask3L2P2);
                    RightAnswer3L2P2.setTextIsSelectable(true);
                    pointsT3 = task3.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L2P2.setText(task4.getText());
                    task4L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask4L2P2 = task4.getRightAnswer();
                    RightAnswer4L2P2.setText("Right answer: "+rightAnswerTextTask4L2P2);
                    RightAnswer4L2P2.setTextIsSelectable(true);
                    pointsT4 = task4.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task5");
        myRefTask5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L2P2.setText(task5.getText());
                    task5L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask5L2P2 = task5.getRightAnswer();
                    RightAnswer5L2P2.setText("Right answer: "+rightAnswerTextTask5L2P2);
                    RightAnswer5L2P2.setTextIsSelectable(true);
                    pointsT5 = task5.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask6 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task6");
        myRefTask6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task6 = dataSnapshot.getValue(PageTask.class);
                if(task6!=null){
                    task6L2P2.setText(task6.getText());
                    task6L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask6L2P2 = task6.getRightAnswer();
                    RightAnswer6L2P2.setText("Right answer: "+rightAnswerTextTask6L2P2);
                    RightAnswer6L2P2.setTextIsSelectable(true);
                    pointsT6 = task6.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask7 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task7");
        myRefTask7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task7 = dataSnapshot.getValue(PageTask.class);
                if(task7!=null){
                    task7L2P2.setText(task7.getText());
                    task7L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask7L2P2 = task7.getRightAnswer();
                    RightAnswer7L2P2.setText("Right answer: "+rightAnswerTextTask7L2P2);
                    RightAnswer7L2P2.setTextIsSelectable(true);
                    pointsT7 = task7.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask8 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task8");
        myRefTask8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task8 = dataSnapshot.getValue(PageTask.class);
                if(task8!=null){
                    task8L2P2.setText(task8.getText());
                    task8L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask8L2P2 = task8.getRightAnswer();
                    RightAnswer8L2P2.setText("Right answer: "+rightAnswerTextTask8L2P2);
                    RightAnswer8L2P2.setTextIsSelectable(true);
                    pointsT8 = task8.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask9 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task9");
        myRefTask9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task9 = dataSnapshot.getValue(PageTask.class);
                if(task9!=null){
                    task9L2P2.setText(task9.getText());
                    task9L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask9L2P2 = task9.getRightAnswer();
                    RightAnswer9L2P2.setText("Right answer: "+rightAnswerTextTask9L2P2);
                    RightAnswer9L2P2.setTextIsSelectable(true);
                    pointsT9 = task9.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask10 = database
                .getReference("Lessons").child("Lesson2").child("Page2").child("Task10");
        myRefTask10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task10 = dataSnapshot.getValue(PageTask.class);
                if(task10!=null){
                    task10L2P2.setText(task10.getText());
                    task10L2P2.setTextIsSelectable(true);
                    rightAnswerTextTask10L2P2 = task10.getRightAnswer();
                    RightAnswer10L2P2.setText("Right answer: "+rightAnswerTextTask10L2P2);
                    RightAnswer10L2P2.setTextIsSelectable(true);
                    pointsT10 = task10.getPoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void speak() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTTS.speak(listeningText,TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            mTTS.speak(listeningText, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL2P2):
                points = calculatePoint();
                finishL2P2.setVisibility(View.VISIBLE);
                finishTVL2P2.setText("Splněno! "+points +"dips!");
                btnSaveL2P2.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL2P2.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP3L2): viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case (R.id.listeningSound):
                System.out.println(listeningText);
                System.out.println(videoTask.getText());
                if(mTTS.isSpeaking()){
                    mTTS.stop();
                }else{
                    speak();
                }
                break;

        }
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task6")
                .setValue(utask6);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task7")
                .setValue(utask7);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task8")
                .setValue(utask8);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task9")
                .setValue(utask9);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page2")
                .child("Task10")
                .setValue(utask10);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL2P2 = (TextView) this.getView().findViewById(R.id.TVPointsL2P2);
        TVPointsL2P2.setText(viewModel.getDipPoints().getValue().toString());
    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L2P2 = ET1L2P2.getText().toString();
        A2T1L2P2 = ET2L2P2.getText().toString();
        A3T1L2P2 = ET3L2P2.getText().toString();
        A4T1L2P2 = ET4L2P2.getText().toString();
        A5T1L2P2 = ET5L2P2.getText().toString();
        A6T1L2P2 = ET6L2P2.getText().toString();
        A7T1L2P2 = ET7L2P2.getText().toString();
        A8T1L2P2 = ET8L2P2.getText().toString();
        A9T1L2P2 = ET9L2P2.getText().toString();
        A10T1L2P2 = ET10L2P2.getText().toString();
        ET1L2P2.setFocusableInTouchMode(false);
        ET2L2P2.setFocusableInTouchMode(false);
        ET3L2P2.setFocusableInTouchMode(false);
        ET4L2P2.setFocusableInTouchMode(false);
        ET5L2P2.setFocusableInTouchMode(false);
        ET6L2P2.setFocusableInTouchMode(false);
        ET7L2P2.setFocusableInTouchMode(false);
        ET8L2P2.setFocusableInTouchMode(false);
        ET9L2P2.setFocusableInTouchMode(false);
        ET10L2P2.setFocusableInTouchMode(false);
        ET1L2P2.setFocusable(false);
        ET2L2P2.setFocusable(false);
        ET3L2P2.setFocusable(false);
        ET4L2P2.setFocusable(false);
        ET5L2P2.setFocusable(false);
        ET6L2P2.setFocusable(false);
        ET7L2P2.setFocusable(false);
        ET8L2P2.setFocusable(false);
        ET9L2P2.setFocusable(false);
        ET10L2P2.setFocusable(false);
        utask1.setAnswer(A1T1L2P2);
        utask2.setAnswer(A2T1L2P2);
        utask3.setAnswer(A3T1L2P2);
        utask4.setAnswer(A4T1L2P2);
        utask5.setAnswer(A5T1L2P2);
        utask6.setAnswer(A6T1L2P2);
        utask7.setAnswer(A7T1L2P2);
        utask8.setAnswer(A8T1L2P2);
        utask9.setAnswer(A9T1L2P2);
        utask10.setAnswer(A10T1L2P2);
        if(A1T1L2P2.toLowerCase().equals(rightAnswerTextTask1L2P2.toLowerCase())) {
            ET1L2P2.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L2P2.setVisibility(View.VISIBLE);
            ET1L2P2.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L2P2.toLowerCase().equals(rightAnswerTextTask2L2P2.toLowerCase())) {
            ET2L2P2.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L2P2.setVisibility(View.VISIBLE);
            ET2L2P2.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L2P2.toLowerCase().equals(rightAnswerTextTask3L2P2.toLowerCase())) {
            ET3L2P2.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L2P2.setVisibility(View.VISIBLE);
            ET3L2P2.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L2P2.toLowerCase().equals(rightAnswerTextTask4L2P2.toLowerCase())) {
            ET4L2P2.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L2P2.setVisibility(View.VISIBLE);
            ET4L2P2.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L2P2.toLowerCase().equals(rightAnswerTextTask5L2P2.toLowerCase())) {
            ET5L2P2.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L2P2.setVisibility(View.VISIBLE);
            ET5L2P2.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }

        if(A6T1L2P2.toLowerCase().equals(rightAnswerTextTask6L2P2.toLowerCase())) {
            ET6L2P2.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else {
            RightAnswer6L2P2.setVisibility(View.VISIBLE);
            ET6L2P2.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }

        if(A7T1L2P2.toLowerCase().equals(rightAnswerTextTask7L2P2.toLowerCase())) {
            ET7L2P2.setBackgroundResource(R.color.green);
            utask7.setPoints(pointsT7);
            pointsCount+=pointsT7;
        }else {
            RightAnswer7L2P2.setVisibility(View.VISIBLE);
            ET7L2P2.setBackgroundResource(R.color.red);
            utask7.setPoints(0);
        }

        if(A8T1L2P2.toLowerCase().equals(rightAnswerTextTask8L2P2.toLowerCase())) {
            ET8L2P2.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else {
            RightAnswer8L2P2.setVisibility(View.VISIBLE);
            ET8L2P2.setBackgroundResource(R.color.red);
            utask8.setPoints(0);
        }

        if(A9T1L2P2.toLowerCase().equals(rightAnswerTextTask9L2P2.toLowerCase())) {
            ET9L2P2.setBackgroundResource(R.color.green);
            utask9.setPoints(pointsT9);
            pointsCount+=pointsT5;
        }else {
            RightAnswer9L2P2.setVisibility(View.VISIBLE);
            ET9L2P2.setBackgroundResource(R.color.red);
            utask9.setPoints(0);
        }

        if(A10T1L2P2.toLowerCase().equals(rightAnswerTextTask10L2P2.toLowerCase())) {
            ET10L2P2.setBackgroundResource(R.color.green);
            utask10.setPoints(pointsT10);
            pointsCount+=pointsT10;
        }else {
            RightAnswer10L2P2.setVisibility(View.VISIBLE);
            ET10L2P2.setBackgroundResource(R.color.red);
            utask10.setPoints(0);
        }
        return pointsCount;
    }
}