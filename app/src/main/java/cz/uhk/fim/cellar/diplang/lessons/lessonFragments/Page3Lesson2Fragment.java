package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
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
import java.util.ArrayList;
import java.util.Locale;
import cz.uhk.fim.cellar.diplang.LocaleHelper;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.LessonPage;
import cz.uhk.fim.cellar.diplang.classes.PageTask;
import cz.uhk.fim.cellar.diplang.classes.UserTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment třetí stránky, druhé lekce, úrovně B2
 */
public class Page3Lesson2Fragment extends Fragment implements View.OnClickListener {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private EditText ET1L2P3, ET2L2P3, ET3L2P3, ET4L2P3, ET5L2P3, ET6L2P3;
    private Button btnSaveL2P3, btnNextToP4, btnCantSpeak;
    private ImageButton btnSpeak1, btnSpeak2, btnSpeak3, btnSpeak4, btnSpeak5, btnSpeak6;
    private String A1T1L2P3, A2T1L2P3, A3T1L2P3, A4T1L2P3, A5T1L2P3, A6T1L2P3;
    private String rightAnswerTextTask1L2P3, rightAnswerTextTask2L2P3, rightAnswerTextTask3L2P3, rightAnswerTextTask4L2P3, rightAnswerTextTask5L2P3;
    private String rightAnswerTextTask6L2P3;
    private String rightAnswer2TextTask1L2P3, rightAnswer2TextTask2L2P3, rightAnswer2TextTask3L2P3, rightAnswer2TextTask4L2P3, rightAnswer2TextTask5L2P3;
    private String rightAnswer2TextTask6L2P3;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL2P3;
    private TextView finishTVL2P3, TVPointsL2P3;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L2P3, RightAnswer2L2P3, RightAnswer3L2P3, RightAnswer4L2P3, RightAnswer5L2P3;
    private TextView RightAnswer6L2P3;
    private TextView task1L2P3, task2L2P3, task3L2P3, task4L2P3, task5L2P3, infoPage3Lesson2;
    private TextView task6L2P3;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private PageTask task1, task2, task3, task4, task5;
    private PageTask task6;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6;
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6;
    private int pagePoints;
    private Resources resources;
    private Context context;
    private boolean focusText1, focusText2, focusText3, focusText4, focusText5, focusText6;
    private boolean clickable;

    public Page3Lesson2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page3_lesson2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        context = LocaleHelper.setLocale(getActivity(), "en");
        resources = context.getResources();

        String CurrentLang = getResources().getConfiguration().locale.getLanguage();
        focusText1=false;
        focusText2=false;
        focusText3=false;
        focusText4=false;
        focusText5=false;
        focusText6=false;
        
        ET1L2P3 = (EditText) v.findViewById(R.id.ET1L2P3);
        ET2L2P3 = (EditText) v.findViewById(R.id.ET2L2P3);
        ET3L2P3 = (EditText) v.findViewById(R.id.ET3L2P3);
        ET4L2P3 = (EditText) v.findViewById(R.id.ET4L2P3);
        ET5L2P3 = (EditText) v.findViewById(R.id.ET5L2P3);
        ET6L2P3 = (EditText) v.findViewById(R.id.ET6L2P3);

        ET1L2P3.setFocusableInTouchMode(false);
        ET1L2P3.setFocusable(false);
        ET2L2P3.setFocusableInTouchMode(false);
        ET2L2P3.setFocusable(false);
        ET3L2P3.setFocusableInTouchMode(false);
        ET3L2P3.setFocusable(false);
        ET4L2P3.setFocusableInTouchMode(false);
        ET4L2P3.setFocusable(false);
        ET5L2P3.setFocusableInTouchMode(false);
        ET5L2P3.setFocusable(false);
        ET6L2P3.setFocusableInTouchMode(false);
        ET6L2P3.setFocusable(false);

        btnSaveL2P3 = (Button) v.findViewById(R.id.btnSaveL2P3);
        finishL2P3 = (LinearLayout) v.findViewById(R.id.finishL2P3);
        finishTVL2P3 = (TextView) v.findViewById(R.id.finishTVL2P3);
        btnNextToP4 = (Button) v.findViewById(R.id.btnNextToP4L2);
        TVPointsL2P3 = (TextView) v.findViewById(R.id.TVPointsL2P3);
        TVPointsL2P3.setText(viewModel.getDipPoints().getValue().toString());

        btnCantSpeak = (Button) v.findViewById(R.id.btnCantSpeak);
        btnSpeak1 = (ImageButton) v.findViewById(R.id.btnSpeak1);
        btnSpeak2 = (ImageButton) v.findViewById(R.id.btnSpeak2);
        btnSpeak3 = (ImageButton) v.findViewById(R.id.btnSpeak3);
        btnSpeak4 = (ImageButton) v.findViewById(R.id.btnSpeak4);
        btnSpeak5 = (ImageButton) v.findViewById(R.id.btnSpeak5);
        btnSpeak6 = (ImageButton) v.findViewById(R.id.btnSpeak6);

        RightAnswer1L2P3 = (TextView) v.findViewById(R.id.RightAnswer1L2P3);
        RightAnswer2L2P3 = (TextView) v.findViewById(R.id.RightAnswer2L2P3);
        RightAnswer3L2P3 = (TextView) v.findViewById(R.id.RightAnswer3L2P3);
        RightAnswer4L2P3 = (TextView) v.findViewById(R.id.RightAnswer4L2P3);
        RightAnswer5L2P3 = (TextView) v.findViewById(R.id.RightAnswer5L2P3);
        RightAnswer6L2P3 = (TextView) v.findViewById(R.id.RightAnswer6L2P3);

        task1L2P3 = (TextView) v.findViewById(R.id.task1L2P3);
        task2L2P3 = (TextView) v.findViewById(R.id.task2L2P3);
        task3L2P3 = (TextView) v.findViewById(R.id.task3L2P3);
        task4L2P3 = (TextView) v.findViewById(R.id.task4L2P3);
        task5L2P3 = (TextView) v.findViewById(R.id.task5L2P3);
        task6L2P3 = (TextView) v.findViewById(R.id.task6L2P3);

        infoPage3Lesson2 = (TextView) v.findViewById(R.id.infoPage3Lesson2);
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
        clickable = true;
        btnCantSpeak.setOnClickListener(this);
        btnSpeak1.setOnClickListener(this);
        btnSpeak2.setOnClickListener(this);
        btnSpeak3.setOnClickListener(this);
        btnSpeak4.setOnClickListener(this);
        btnSpeak5.setOnClickListener(this);
        btnSpeak6.setOnClickListener(this);


        finishL2P3.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        loadData();

        btnSaveL2P3.setOnClickListener(this);
        btnNextToP4.setOnClickListener(this);

        return v;
    }

    /**
     * Načtení dat
     */
    private void loadData() {
        /**Načtení informací o stránce**/
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson2").child("Page3").child("PageParams");
        myRefPage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage3Lesson2.setText(lessonPage.getInfo());
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
        DatabaseReference myRefTask1 = database.getReference("Lessons").child("Lesson2").child("Page3").child("Task1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task1 = dataSnapshot.getValue(PageTask.class);
                if(task1!=null){
                    task1L2P3.setText(task1.getText());
                    task1L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask1L2P3 = task1.getRightAnswer();
                    rightAnswer2TextTask1L2P3 = task1.getRightAnswer2();
                    RightAnswer1L2P3.setText("Right answer: "+rightAnswerTextTask1L2P3);
                    RightAnswer1L2P3.setTextIsSelectable(true);
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
                .getReference("Lessons").child("Lesson2").child("Page3").child("Task2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task2 = dataSnapshot.getValue(PageTask.class);
                if(task2!=null){
                    task2L2P3.setText(task2.getText());
                    task2L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask2L2P3 = task2.getRightAnswer();
                    rightAnswer2TextTask2L2P3 = task2.getRightAnswer2();
                    RightAnswer2L2P3.setText("Right answer: "+rightAnswerTextTask2L2P3);
                    RightAnswer2L2P3.setTextIsSelectable(true);
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
                .getReference("Lessons").child("Lesson2").child("Page3").child("Task3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task3 = dataSnapshot.getValue(PageTask.class);
                if(task3!=null){
                    task3L2P3.setText(task3.getText());
                    task3L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask3L2P3 = task3.getRightAnswer();
                    rightAnswer2TextTask3L2P3 = task3.getRightAnswer2();
                    RightAnswer3L2P3.setText("Right answer: "+rightAnswerTextTask3L2P3);
                    RightAnswer3L2P3.setTextIsSelectable(true);
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
                .getReference("Lessons").child("Lesson2").child("Page3").child("Task4");
        myRefTask4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task4 = dataSnapshot.getValue(PageTask.class);
                if(task4!=null){
                    task4L2P3.setText(task4.getText());
                    task4L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask4L2P3 = task4.getRightAnswer();
                    rightAnswer2TextTask4L2P3 = task4.getRightAnswer2();
                    RightAnswer4L2P3.setText("Right answer: "+rightAnswerTextTask4L2P3);
                    RightAnswer4L2P3.setTextIsSelectable(true);
                    pointsT4 = task4.getPoints();
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
        DatabaseReference myRefTask5 = database
                .getReference("Lessons").child("Lesson2").child("Page3").child("Task5");
        myRefTask5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task5 = dataSnapshot.getValue(PageTask.class);
                if(task5!=null){
                    task5L2P3.setText(task5.getText());
                    task5L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask5L2P3 = task5.getRightAnswer();
                    rightAnswer2TextTask5L2P3 = task5.getRightAnswer2();
                    RightAnswer5L2P3.setText("Right answer: "+rightAnswerTextTask5L2P3);
                    RightAnswer5L2P3.setTextIsSelectable(true);
                    pointsT5 = task5.getPoints();
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
        DatabaseReference myRefTask6 = database
                .getReference("Lessons").child("Lesson2").child("Page3").child("Task6");
        myRefTask6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                task6 = dataSnapshot.getValue(PageTask.class);
                if(task6!=null){
                    task6L2P3.setText(task6.getText());
                    task6L2P3.setTextIsSelectable(true);
                    rightAnswerTextTask6L2P3 = task6.getRightAnswer();
                    rightAnswer2TextTask6L2P3 = task6.getRightAnswer2();
                    RightAnswer6L2P3.setText("Right answer: "+rightAnswerTextTask6L2P3);
                    RightAnswer6L2P3.setTextIsSelectable(true);
                    pointsT6 = task6.getPoints();
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
            case (R.id.btnSaveL2P3):
                clickable=false;
                points = calculatePoint();
                finishL2P3.setVisibility(View.VISIBLE);
                finishTVL2P3.setText("Splněno! "+points +"dips!");
                btnSaveL2P3.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL2P3.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP4L2): viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case (R.id.btnSpeak1):
                switchFocus(1);
                speechToText();
                break;
            case (R.id.btnSpeak2):
                switchFocus(2);
                speechToText();
                break;
            case (R.id.btnSpeak3):
                switchFocus(3);
                speechToText();
                break;
            case (R.id.btnSpeak4):
                switchFocus(4);
                speechToText();
                break;
            case (R.id.btnSpeak5):
                switchFocus(5);
                speechToText();
                break;
            case (R.id.btnSpeak6):
                switchFocus(6);
                speechToText();
                break;
            case (R.id.btnCantSpeak):
                userCantSpeakNow();
                Toast.makeText(getContext(), "Nyní můžete odpovědi napsat do přiřazených polí.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void switchFocus(int i) {
        switch (i){
            case 1:
                focusText1=true;
                focusText2=false;
                focusText3=false;
                focusText4=false;
                focusText5=false;
                focusText6=false;
                break;
            case 2:
                focusText2=true;
                focusText1=false;
                focusText3=false;
                focusText4=false;
                focusText5=false;
                focusText6=false;
                break;
            case 3:
                focusText3=true;
                focusText2=false;
                focusText1=false;
                focusText4=false;
                focusText5=false;
                focusText6=false;
                break;
            case 4:
                focusText4=true;
                focusText2=false;
                focusText3=false;
                focusText1=false;
                focusText5=false;
                focusText6=false;
                break;
            case 5:
                focusText5=true;
                focusText2=false;
                focusText3=false;
                focusText4=false;
                focusText1=false;
                focusText6=false;
                break;
            case 6:
                focusText6=true;
                focusText2=false;
                focusText3=false;
                focusText4=false;
                focusText5=false;
                focusText1=false;
                break;
        }
    }

    private void userCantSpeakNow() {
        if(clickable) {
            ET1L2P3.setFocusableInTouchMode(true);
            ET1L2P3.setFocusable(true);
            ET2L2P3.setFocusableInTouchMode(true);
            ET2L2P3.setFocusable(true);
            ET3L2P3.setFocusableInTouchMode(true);
            ET3L2P3.setFocusable(true);
            ET4L2P3.setFocusableInTouchMode(true);
            ET4L2P3.setFocusable(true);
            ET5L2P3.setFocusableInTouchMode(true);
            ET5L2P3.setFocusable(true);
            ET6L2P3.setFocusableInTouchMode(true);
            ET6L2P3.setFocusable(true);
        }
    }

    private void speechToText() {
        Intent rintent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        rintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.US.toString());
        rintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, Locale.US.toString());
        rintent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE , true );
        rintent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        rintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text");
        try{
            startActivityForResult(rintent, REQ_CODE_SPEECH_INPUT);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE_SPEECH_INPUT){
            if(resultCode==RESULT_OK && data!=null){

                if(focusText1) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET1L2P3.setText(result.get(0));
                }
                if(focusText2) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET2L2P3.setText(result.get(0));
                }
                if(focusText3) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET3L2P3.setText(result.get(0));
                }
                if(focusText4) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET4L2P3.setText(result.get(0));
                }
                if(focusText5) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET5L2P3.setText(result.get(0));
                }
                if(focusText6) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ET6L2P3.setText(result.get(0));
                }
            }
        }
    }

    /** Uložení uživatelových odpovědí do db **/
    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page3")
                .child("Task6")
                .setValue(utask6);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL2P3 = (TextView) this.getView().findViewById(R.id.TVPointsL2P3);
        TVPointsL2P3.setText(viewModel.getDipPoints().getValue().toString());
    }

    /** Porovnání odpovědí, zobrazení správných řešení, kalkulace bodů **/
    private int calculatePoint() {
        int pointsCount=0;
        A1T1L2P3 = ET1L2P3.getText().toString();
        A2T1L2P3 = ET2L2P3.getText().toString();
        A3T1L2P3 = ET3L2P3.getText().toString();
        A4T1L2P3 = ET4L2P3.getText().toString();
        A5T1L2P3 = ET5L2P3.getText().toString();
        A6T1L2P3 = ET6L2P3.getText().toString();

        ET1L2P3.setFocusableInTouchMode(false);
        ET1L2P3.setFocusable(false);
        ET2L2P3.setFocusableInTouchMode(false);
        ET2L2P3.setFocusable(false);
        ET3L2P3.setFocusableInTouchMode(false);
        ET3L2P3.setFocusable(false);
        ET4L2P3.setFocusableInTouchMode(false);
        ET4L2P3.setFocusable(false);
        ET5L2P3.setFocusableInTouchMode(false);
        ET5L2P3.setFocusable(false);
        ET6L2P3.setFocusableInTouchMode(false);
        ET6L2P3.setFocusable(false);

        btnSpeak1.setEnabled(false);
        btnSpeak2.setEnabled(false);
        btnSpeak3.setEnabled(false);
        btnSpeak4.setEnabled(false);
        btnSpeak5.setEnabled(false);
        btnSpeak6.setEnabled(false);

        utask1.setAnswer(A1T1L2P3);
        utask2.setAnswer(A2T1L2P3);
        utask3.setAnswer(A3T1L2P3);
        utask4.setAnswer(A4T1L2P3);
        utask5.setAnswer(A5T1L2P3);
        utask6.setAnswer(A6T1L2P3);

        if(A1T1L2P3.toLowerCase().equals(rightAnswerTextTask1L2P3.toLowerCase()) || A1T1L2P3.toLowerCase().equals(rightAnswer2TextTask1L2P3.toLowerCase())) {
            ET1L2P3.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L2P3.setVisibility(View.VISIBLE);
            ET1L2P3.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L2P3.toLowerCase().equals(rightAnswerTextTask2L2P3.toLowerCase()) || A2T1L2P3.toLowerCase().equals(rightAnswer2TextTask2L2P3.toLowerCase())) {
            ET2L2P3.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L2P3.setVisibility(View.VISIBLE);
            ET2L2P3.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L2P3.toLowerCase().equals(rightAnswerTextTask3L2P3.toLowerCase()) || A3T1L2P3.toLowerCase().equals(rightAnswer2TextTask3L2P3.toLowerCase())) {
            ET3L2P3.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L2P3.setVisibility(View.VISIBLE);
            ET3L2P3.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L2P3.toLowerCase().equals(rightAnswerTextTask4L2P3.toLowerCase()) || A4T1L2P3.toLowerCase().equals(rightAnswer2TextTask4L2P3.toLowerCase())) {
            ET4L2P3.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L2P3.setVisibility(View.VISIBLE);
            ET4L2P3.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L2P3.toLowerCase().equals(rightAnswerTextTask5L2P3.toLowerCase()) || A5T1L2P3.toLowerCase().equals(rightAnswer2TextTask5L2P3.toLowerCase())) {
            ET5L2P3.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L2P3.setVisibility(View.VISIBLE);
            ET5L2P3.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }

        if(A6T1L2P3.toLowerCase().equals(rightAnswerTextTask6L2P3.toLowerCase()) || A6T1L2P3.toLowerCase().equals(rightAnswer2TextTask6L2P3.toLowerCase())) {
            ET6L2P3.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else {
            RightAnswer6L2P3.setVisibility(View.VISIBLE);
            ET6L2P3.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }
        return pointsCount;
    }
}