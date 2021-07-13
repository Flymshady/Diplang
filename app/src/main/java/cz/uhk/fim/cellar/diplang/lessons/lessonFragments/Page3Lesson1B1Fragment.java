package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import cz.uhk.fim.cellar.diplang.classes.OptionsTask;
import cz.uhk.fim.cellar.diplang.classes.UserTask;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;

import static android.view.View.GONE;

public class Page3Lesson1B1Fragment extends Fragment implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6, utask7, utask8, utask9, utask10;
    private String A1T1L1B1P3, A2T1L1B1P3, A3T1L1B1P3, A4T1L1B1P3, A5T1L1B1P3, A6T1L1B1P3, A7T1L1B1P3, A8T1L1B1P3, A9T1L1B1P3, A10T1L1B1P3;
    private int pagePoints;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private LinearLayout finishL1B1P3;
    private Button btnSaveL1B1P3, btnNextToP4;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6, pointsT7, pointsT8, pointsT9, pointsT10;
    private String rightAnswerTextTask1L1B1P3, rightAnswerTextTask2L1B1P3, rightAnswerTextTask3L1B1P3, rightAnswerTextTask4L1B1P3, rightAnswerTextTask5L1B1P3;
    private String rightAnswerTextTask6L1B1P3, rightAnswerTextTask7L1B1P3, rightAnswerTextTask8L1B1P3, rightAnswerTextTask9L1B1P3, rightAnswerTextTask10L1B1P3;
    private TextView finishTVL1B1P3, TVPointsL1B1P3;
    private TextView RightAnswer1L1B1P3, RightAnswer2L1B1P3, RightAnswer3L1B1P3, RightAnswer4L1B1P3, RightAnswer5L1B1P3;
    private TextView RightAnswer6L1B1P3, RightAnswer7L1B1P3, RightAnswer8L1B1P3, RightAnswer9L1B1P3, RightAnswer10L1B1P3;
    private TextView task1L1B1P3, task2L1B1P3, task3L1B1P3, task4L1B1P3, task5L1B1P3, infoPage3Lesson1B1;
    private TextView task6L1B1P3, task7L1B1P3, task8L1B1P3, task9L1B1P3, task10L1B1P3;
    private OptionsTask optionsTask1, optionsTask2, optionsTask3, optionsTask4, optionsTask5;
    private OptionsTask optionsTask6, optionsTask7, optionsTask8, optionsTask9, optionsTask10;
    private Spinner spinnerT1L1B1P3, spinnerT2L1B1P3, spinnerT3L1B1P3, spinnerT4L1B1P3, spinnerT5L1B1P3;
    private Spinner spinnerT6L1B1P3, spinnerT7L1B1P3, spinnerT8L1B1P3, spinnerT9L1B1P3, spinnerT10L1B1P3;
    private String[] spinnerT1L1B1P3Array, spinnerT2L1B1P3Array, spinnerT3L1B1P3Array, spinnerT4L1B1P3Array, spinnerT5L1B1P3Array;
    private String[] spinnerT6L1B1P3Array, spinnerT7L1B1P3Array, spinnerT8L1B1P3Array, spinnerT9L1B1P3Array, spinnerT10L1B1P3Array;
    private String hint1L1B1P3, hint2L1B1P3, hint3L1B1P3, hint4L1B1P3, hint5L1B1P3;
    private String hint6L1B1P3, hint7L1B1P3, hint8L1B1P3, hint9L1B1P3, hint10L1B1P3;
    private ImageButton btnHint1L1B1P3, btnHint2L1B1P3, btnHint3L1B1P3, btnHint4L1B1P3, btnHint5L1B1P3;
    private ImageButton btnHint6L1B1P3, btnHint7L1B1P3, btnHint8L1B1P3, btnHint9L1B1P3, btnHint10L1B1P3;


    public Page3Lesson1B1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page3_lesson1b1, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        A1T1L1B1P3 = "";
        A2T1L1B1P3 = "";
        A3T1L1B1P3 = "";
        A4T1L1B1P3 = "";
        A5T1L1B1P3 = "";
        A6T1L1B1P3 = "";
        A7T1L1B1P3 = "";
        A8T1L1B1P3 = "";
        A9T1L1B1P3 = "";
        A10T1L1B1P3 = "";
        spinnerT1L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT2L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT3L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT4L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT5L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT6L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT7L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT8L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT9L1B1P3Array = new String[]{"A","B","C","D"};
        spinnerT10L1B1P3Array = new String[]{"A","B","C","D"};

        spinnerT1L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT1L1B1P3);
        spinnerT2L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT2L1B1P3);
        spinnerT3L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT3L1B1P3);
        spinnerT4L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT4L1B1P3);
        spinnerT5L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT5L1B1P3);
        spinnerT6L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT6L1B1P3);
        spinnerT7L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT7L1B1P3);
        spinnerT8L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT8L1B1P3);
        spinnerT9L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT9L1B1P3);
        spinnerT10L1B1P3 = (Spinner) v.findViewById(R.id.spinnerT10L1B1P3);

        btnHint1L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint1L1B1P3);
        btnHint2L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint2L1B1P3);
        btnHint3L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint3L1B1P3);
        btnHint4L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint4L1B1P3);
        btnHint5L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint5L1B1P3);
        btnHint6L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint6L1B1P3);
        btnHint7L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint7L1B1P3);
        btnHint8L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint8L1B1P3);
        btnHint9L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint9L1B1P3);
        btnHint10L1B1P3 = (ImageButton) v.findViewById(R.id.btnHint10L1B1P3);
        btnHint1L1B1P3.setOnClickListener(this);
        btnHint2L1B1P3.setOnClickListener(this);
        btnHint3L1B1P3.setOnClickListener(this);
        btnHint4L1B1P3.setOnClickListener(this);
        btnHint5L1B1P3.setOnClickListener(this);
        btnHint6L1B1P3.setOnClickListener(this);
        btnHint7L1B1P3.setOnClickListener(this);
        btnHint8L1B1P3.setOnClickListener(this);
        btnHint9L1B1P3.setOnClickListener(this);
        btnHint10L1B1P3.setOnClickListener(this);


        btnSaveL1B1P3 = (Button) v.findViewById(R.id.btnSaveL1B1P3);
        finishL1B1P3 = (LinearLayout) v.findViewById(R.id.finishL1B1P3);
        finishTVL1B1P3 = (TextView) v.findViewById(R.id.finishTVL1B1P3);
        btnNextToP4 = (Button) v.findViewById(R.id.btnNextToP4L1B1);
        TVPointsL1B1P3 = (TextView) v.findViewById(R.id.TVPointsL1B1P3);
        TVPointsL1B1P3.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer1L1B1P3);
        RightAnswer2L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer2L1B1P3);
        RightAnswer3L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer3L1B1P3);
        RightAnswer4L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer4L1B1P3);
        RightAnswer5L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer5L1B1P3);
        RightAnswer6L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer6L1B1P3);
        RightAnswer7L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer7L1B1P3);
        RightAnswer8L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer8L1B1P3);
        RightAnswer9L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer9L1B1P3);
        RightAnswer10L1B1P3 = (TextView) v.findViewById(R.id.RightAnswer10L1B1P3);
        task1L1B1P3 = (TextView) v.findViewById(R.id.task1L1B1P3);
        task2L1B1P3 = (TextView) v.findViewById(R.id.task2L1B1P3);
        task3L1B1P3 = (TextView) v.findViewById(R.id.task3L1B1P3);
        task4L1B1P3 = (TextView) v.findViewById(R.id.task4L1B1P3);
        task5L1B1P3 = (TextView) v.findViewById(R.id.task5L1B1P3);
        task6L1B1P3 = (TextView) v.findViewById(R.id.task6L1B1P3);
        task7L1B1P3 = (TextView) v.findViewById(R.id.task7L1B1P3);
        task8L1B1P3 = (TextView) v.findViewById(R.id.task8L1B1P3);
        task9L1B1P3 = (TextView) v.findViewById(R.id.task9L1B1P3);
        task10L1B1P3 = (TextView) v.findViewById(R.id.task10L1B1P3);
        infoPage3Lesson1B1 = (TextView) v.findViewById(R.id.infoPage3Lesson1B1);
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

        finishL1B1P3.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL1B1);

        loadData();

        btnSaveL1B1P3.setOnClickListener(this);
        btnNextToP4.setOnClickListener(this);
        
        return v;
    
    
    }

    private void loadData() {
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson1B1").child("Page3");
        myRefPage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage3Lesson1B1.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask1 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask1");
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask1 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask1!=null){
                    task1L1B1P3.setText(optionsTask1.getText());
                    task1L1B1P3.isTextSelectable();
                    hint1L1B1P3=optionsTask1.getHint();
                    rightAnswerTextTask1L1B1P3 = optionsTask1.getRightAnswer();
                    RightAnswer1L1B1P3.setText("Right answer: "+rightAnswerTextTask1L1B1P3);
                    RightAnswer1L1B1P3.isTextSelectable();
                    pointsT1 = optionsTask1.getPoints();

                    spinnerT1L1B1P3Array[0]=optionsTask1.getOptionA();
                    spinnerT1L1B1P3Array[1]=optionsTask1.getOptionB();
                    spinnerT1L1B1P3Array[2]=optionsTask1.getOptionC();
                    spinnerT1L1B1P3Array[3]=optionsTask1.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT1 = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item, spinnerT1L1B1P3Array);
                    spinnerArrayAdapterT1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT1L1B1P3.setAdapter(spinnerArrayAdapterT1);
                    spinnerT1L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A1T1L1B1P3 = spinnerT1L1B1P3Array[i];
                            spinnerT1L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask2 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask2");
        myRefTask2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask2 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask2!=null){
                    task2L1B1P3.setText(optionsTask2.getText());
                    task2L1B1P3.isTextSelectable();
                    hint2L1B1P3=optionsTask2.getHint();
                    rightAnswerTextTask2L1B1P3 = optionsTask2.getRightAnswer();
                    RightAnswer2L1B1P3.setText("Right answer: "+rightAnswerTextTask2L1B1P3);
                    RightAnswer2L1B1P3.isTextSelectable();
                    pointsT2 = optionsTask2.getPoints();

                    spinnerT2L1B1P3Array[0]=optionsTask2.getOptionA();
                    spinnerT2L1B1P3Array[1]=optionsTask2.getOptionB();
                    spinnerT2L1B1P3Array[2]=optionsTask2.getOptionC();
                    spinnerT2L1B1P3Array[3]=optionsTask2.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT2 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT2L1B1P3Array);
                    spinnerArrayAdapterT2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT2L1B1P3.setAdapter(spinnerArrayAdapterT2);
                    spinnerT2L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A2T1L1B1P3 = spinnerT2L1B1P3Array[i];
                            spinnerT2L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask3 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask3");
        myRefTask3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask3 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask3!=null){
                    task3L1B1P3.setText(optionsTask3.getText());
                    task3L1B1P3.isTextSelectable();
                    hint3L1B1P3=optionsTask3.getHint();
                    rightAnswerTextTask3L1B1P3 = optionsTask3.getRightAnswer();
                    RightAnswer3L1B1P3.setText("Right answer: "+rightAnswerTextTask3L1B1P3);
                    RightAnswer3L1B1P3.isTextSelectable();
                    pointsT3 = optionsTask3.getPoints();

                    spinnerT3L1B1P3Array[0]=optionsTask3.getOptionA();
                    spinnerT3L1B1P3Array[1]=optionsTask3.getOptionB();
                    spinnerT3L1B1P3Array[2]=optionsTask3.getOptionC();
                    spinnerT3L1B1P3Array[3]=optionsTask3.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT3 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT3L1B1P3Array);
                    spinnerArrayAdapterT3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT3L1B1P3.setAdapter(spinnerArrayAdapterT3);
                    spinnerT3L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A3T1L1B1P3 = spinnerT3L1B1P3Array[i];
                            spinnerT3L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask4 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask4");
        myRefTask4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask4 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask4!=null) {
                    task4L1B1P3.setText(optionsTask4.getText());
                    task4L1B1P3.isTextSelectable();
                    hint4L1B1P3=optionsTask4.getHint();
                    rightAnswerTextTask4L1B1P3 = optionsTask4.getRightAnswer();
                    RightAnswer4L1B1P3.setText("Right answer: " + rightAnswerTextTask4L1B1P3);
                    RightAnswer4L1B1P3.isTextSelectable();
                    pointsT4 = optionsTask4.getPoints();

                    spinnerT4L1B1P3Array[0] = optionsTask4.getOptionA();
                    spinnerT4L1B1P3Array[1] = optionsTask4.getOptionB();
                    spinnerT4L1B1P3Array[2] = optionsTask4.getOptionC();
                    spinnerT4L1B1P3Array[3] = optionsTask4.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT4 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT4L1B1P3Array);
                    spinnerArrayAdapterT4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT4L1B1P3.setAdapter(spinnerArrayAdapterT4);
                    spinnerT4L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A4T1L1B1P3 = spinnerT4L1B1P3Array[i];
                            spinnerT4L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



        DatabaseReference myRefTask5 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask5");
        myRefTask5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask5 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask5!=null){
                    task5L1B1P3.setText(optionsTask5.getText());
                    task5L1B1P3.isTextSelectable();
                    hint5L1B1P3=optionsTask5.getHint();
                    rightAnswerTextTask5L1B1P3 = optionsTask5.getRightAnswer();
                    RightAnswer5L1B1P3.setText("Right answer: "+rightAnswerTextTask5L1B1P3);
                    RightAnswer5L1B1P3.isTextSelectable();
                    pointsT5 = optionsTask5.getPoints();

                    spinnerT5L1B1P3Array[0]=optionsTask5.getOptionA();
                    spinnerT5L1B1P3Array[1]=optionsTask5.getOptionB();
                    spinnerT5L1B1P3Array[2]=optionsTask5.getOptionC();
                    spinnerT5L1B1P3Array[3]=optionsTask5.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT5 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT5L1B1P3Array);
                    spinnerArrayAdapterT5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT5L1B1P3.setAdapter(spinnerArrayAdapterT5);
                    spinnerT5L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A5T1L1B1P3 = spinnerT5L1B1P3Array[i];
                            spinnerT5L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        DatabaseReference myRefTask6 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask6");
        myRefTask6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask6 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask6!=null){
                    task6L1B1P3.setText(optionsTask6.getText());
                    task6L1B1P3.isTextSelectable();
                    hint6L1B1P3=optionsTask6.getHint();
                    rightAnswerTextTask6L1B1P3 = optionsTask6.getRightAnswer();
                    RightAnswer6L1B1P3.setText("Right answer: "+rightAnswerTextTask6L1B1P3);
                    RightAnswer6L1B1P3.isTextSelectable();
                    pointsT6 = optionsTask6.getPoints();

                    spinnerT6L1B1P3Array[0]=optionsTask6.getOptionA();
                    spinnerT6L1B1P3Array[1]=optionsTask6.getOptionB();
                    spinnerT6L1B1P3Array[2]=optionsTask6.getOptionC();
                    spinnerT6L1B1P3Array[3]=optionsTask6.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT6 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT6L1B1P3Array);
                    spinnerArrayAdapterT6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT6L1B1P3.setAdapter(spinnerArrayAdapterT6);
                    spinnerT6L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A6T1L1B1P3 = spinnerT6L1B1P3Array[i];
                            spinnerT6L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        DatabaseReference myRefTask7 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask7");
        myRefTask7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask7 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask7!=null){
                    task7L1B1P3.setText(optionsTask7.getText());
                    task7L1B1P3.isTextSelectable();
                    hint7L1B1P3=optionsTask7.getHint();
                    rightAnswerTextTask7L1B1P3 = optionsTask7.getRightAnswer();
                    RightAnswer7L1B1P3.setText("Right answer: "+rightAnswerTextTask7L1B1P3);
                    RightAnswer7L1B1P3.isTextSelectable();
                    pointsT7 = optionsTask7.getPoints();

                    spinnerT7L1B1P3Array[0]=optionsTask7.getOptionA();
                    spinnerT7L1B1P3Array[1]=optionsTask7.getOptionB();
                    spinnerT7L1B1P3Array[2]=optionsTask7.getOptionC();
                    spinnerT7L1B1P3Array[3]=optionsTask7.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT7 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT7L1B1P3Array);
                    spinnerArrayAdapterT7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT7L1B1P3.setAdapter(spinnerArrayAdapterT7);
                    spinnerT7L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A7T1L1B1P3 = spinnerT7L1B1P3Array[i];
                            spinnerT7L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        DatabaseReference myRefTask8 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask8");
        myRefTask8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask8 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask8!=null){
                    task8L1B1P3.setText(optionsTask8.getText());
                    task8L1B1P3.isTextSelectable();
                    hint8L1B1P3=optionsTask8.getHint();
                    rightAnswerTextTask8L1B1P3 = optionsTask8.getRightAnswer();
                    RightAnswer8L1B1P3.setText("Right answer: "+rightAnswerTextTask8L1B1P3);
                    RightAnswer8L1B1P3.isTextSelectable();
                    pointsT8 = optionsTask8.getPoints();

                    spinnerT8L1B1P3Array[0]=optionsTask8.getOptionA();
                    spinnerT8L1B1P3Array[1]=optionsTask8.getOptionB();
                    spinnerT8L1B1P3Array[2]=optionsTask8.getOptionC();
                    spinnerT8L1B1P3Array[3]=optionsTask8.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT8 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT8L1B1P3Array);
                    spinnerArrayAdapterT8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT8L1B1P3.setAdapter(spinnerArrayAdapterT8);
                    spinnerT8L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A8T1L1B1P3 = spinnerT8L1B1P3Array[i];
                            spinnerT8L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask9 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask9");
        myRefTask9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask9 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask9!=null){
                    task9L1B1P3.setText(optionsTask9.getText());
                    task9L1B1P3.isTextSelectable();
                    hint9L1B1P3=optionsTask9.getHint();
                    rightAnswerTextTask9L1B1P3 = optionsTask9.getRightAnswer();
                    RightAnswer9L1B1P3.setText("Right answer: "+rightAnswerTextTask9L1B1P3);
                    RightAnswer9L1B1P3.isTextSelectable();
                    pointsT9 = optionsTask9.getPoints();

                    spinnerT9L1B1P3Array[0]=optionsTask9.getOptionA();
                    spinnerT9L1B1P3Array[1]=optionsTask9.getOptionB();
                    spinnerT9L1B1P3Array[2]=optionsTask9.getOptionC();
                    spinnerT9L1B1P3Array[3]=optionsTask9.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT9 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT9L1B1P3Array);
                    spinnerArrayAdapterT9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT9L1B1P3.setAdapter(spinnerArrayAdapterT9);
                    spinnerT9L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A9T1L1B1P3 = spinnerT9L1B1P3Array[i];
                            spinnerT9L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefTask10 = database
                .getReference("Lessons")
                .child("Lesson1B1").child("Page3").child("OptionsTask10");
        myRefTask10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask10 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask10!=null){
                    task10L1B1P3.setText(optionsTask10.getText());
                    task10L1B1P3.isTextSelectable();
                    hint10L1B1P3=optionsTask10.getHint();
                    rightAnswerTextTask10L1B1P3 = optionsTask10.getRightAnswer();
                    RightAnswer10L1B1P3.setText("Right answer: "+rightAnswerTextTask10L1B1P3);
                    RightAnswer10L1B1P3.isTextSelectable();
                    pointsT10 = optionsTask10.getPoints();

                    spinnerT10L1B1P3Array[0]=optionsTask10.getOptionA();
                    spinnerT10L1B1P3Array[1]=optionsTask10.getOptionB();
                    spinnerT10L1B1P3Array[2]=optionsTask10.getOptionC();
                    spinnerT10L1B1P3Array[3]=optionsTask10.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT10 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT10L1B1P3Array);
                    spinnerArrayAdapterT10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT10L1B1P3.setAdapter(spinnerArrayAdapterT10);
                    spinnerT10L1B1P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A10T1L1B1P3 = spinnerT10L1B1P3Array[i];
                            spinnerT10L1B1P3.setBackgroundResource(R.color.spinner_selected);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1B1P3 = (TextView) this.getView().findViewById(R.id.TVPointsL1B1P3);
        TVPointsL1B1P3.setText(viewModel.getDipPoints().getValue().toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL1B1P3):
                points = calculatePoints();
                finishL1B1P3.setVisibility(View.VISIBLE);
                finishTVL1B1P3.setText("Splněno! "+points +"dips!");
                btnSaveL1B1P3.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL1B1P3.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP4L1B1):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case (R.id.btnHint1L1B1P3):
                Toast.makeText(this.getActivity(), hint1L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint2L1B1P3):
                Toast.makeText(this.getActivity(), hint2L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint3L1B1P3):
                Toast.makeText(this.getActivity(), hint3L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint4L1B1P3):
                Toast.makeText(this.getActivity(), hint4L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint5L1B1P3):
                Toast.makeText(this.getActivity(), hint5L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint6L1B1P3):
                Toast.makeText(this.getActivity(), hint6L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint7L1B1P3):
                Toast.makeText(this.getActivity(), hint7L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint8L1B1P3):
                Toast.makeText(this.getActivity(), hint8L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint9L1B1P3):
                Toast.makeText(this.getActivity(), hint9L1B1P3, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnHint10L1B1P3):
                Toast.makeText(this.getActivity(), hint10L1B1P3, Toast.LENGTH_LONG).show();
                break;


        }
    }

    private int calculatePoints() {
        int pointsCount=0;
        A1T1L1B1P3 = spinnerT1L1B1P3.getSelectedItem().toString();
        A2T1L1B1P3 = spinnerT2L1B1P3.getSelectedItem().toString();
        A3T1L1B1P3 = spinnerT3L1B1P3.getSelectedItem().toString();
        A4T1L1B1P3 = spinnerT4L1B1P3.getSelectedItem().toString();
        A5T1L1B1P3 = spinnerT5L1B1P3.getSelectedItem().toString();
        A6T1L1B1P3 = spinnerT6L1B1P3.getSelectedItem().toString();
        A7T1L1B1P3 = spinnerT7L1B1P3.getSelectedItem().toString();
        A8T1L1B1P3 = spinnerT8L1B1P3.getSelectedItem().toString();
        A9T1L1B1P3 = spinnerT9L1B1P3.getSelectedItem().toString();
        A10T1L1B1P3 = spinnerT10L1B1P3.getSelectedItem().toString();

        utask1.setAnswer(A1T1L1B1P3);
        utask2.setAnswer(A2T1L1B1P3);
        utask3.setAnswer(A3T1L1B1P3);
        utask4.setAnswer(A4T1L1B1P3);
        utask5.setAnswer(A5T1L1B1P3);
        utask6.setAnswer(A6T1L1B1P3);
        utask7.setAnswer(A7T1L1B1P3);
        utask8.setAnswer(A8T1L1B1P3);
        utask9.setAnswer(A9T1L1B1P3);
        utask10.setAnswer(A10T1L1B1P3);
        spinnerT1L1B1P3.setEnabled(false);
        spinnerT2L1B1P3.setEnabled(false);
        spinnerT3L1B1P3.setEnabled(false);
        spinnerT4L1B1P3.setEnabled(false);
        spinnerT5L1B1P3.setEnabled(false);
        spinnerT6L1B1P3.setEnabled(false);
        spinnerT7L1B1P3.setEnabled(false);
        spinnerT8L1B1P3.setEnabled(false);
        spinnerT9L1B1P3.setEnabled(false);
        spinnerT10L1B1P3.setEnabled(false);

        if(A1T1L1B1P3.equals(rightAnswerTextTask1L1B1P3)) {
            spinnerT1L1B1P3.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L1B1P3.setVisibility(View.VISIBLE);
            spinnerT1L1B1P3.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L1B1P3.equals(rightAnswerTextTask2L1B1P3)) {
            spinnerT2L1B1P3.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L1B1P3.setVisibility(View.VISIBLE);
            spinnerT2L1B1P3.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L1B1P3.equals(rightAnswerTextTask3L1B1P3)) {
            spinnerT3L1B1P3.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L1B1P3.setVisibility(View.VISIBLE);
            spinnerT3L1B1P3.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L1B1P3.equals(rightAnswerTextTask4L1B1P3)) {
            spinnerT4L1B1P3.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L1B1P3.setVisibility(View.VISIBLE);
            spinnerT4L1B1P3.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L1B1P3.equals(rightAnswerTextTask5L1B1P3)) {
            spinnerT5L1B1P3.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L1B1P3.setVisibility(View.VISIBLE);
            spinnerT5L1B1P3.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        if(A6T1L1B1P3.equals(rightAnswerTextTask6L1B1P3)) {
            spinnerT6L1B1P3.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else {
            RightAnswer6L1B1P3.setVisibility(View.VISIBLE);
            spinnerT6L1B1P3.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }
        if(A7T1L1B1P3.equals(rightAnswerTextTask7L1B1P3)) {
            spinnerT7L1B1P3.setBackgroundResource(R.color.green);
            utask7.setPoints(pointsT7);
            pointsCount+=pointsT7;
        }else {
            RightAnswer7L1B1P3.setVisibility(View.VISIBLE);
            spinnerT7L1B1P3.setBackgroundResource(R.color.red);
            utask7.setPoints(0);
        }
        if(A8T1L1B1P3.equals(rightAnswerTextTask8L1B1P3)) {
            spinnerT8L1B1P3.setBackgroundResource(R.color.green);
            utask8.setPoints(pointsT8);
            pointsCount+=pointsT8;
        }else {
            RightAnswer8L1B1P3.setVisibility(View.VISIBLE);
            spinnerT8L1B1P3.setBackgroundResource(R.color.red);
            utask8.setPoints(0);
        }
        if(A9T1L1B1P3.equals(rightAnswerTextTask9L1B1P3)) {
            spinnerT9L1B1P3.setBackgroundResource(R.color.green);
            utask9.setPoints(pointsT9);
            pointsCount+=pointsT9;
        }else {
            RightAnswer9L1B1P3.setVisibility(View.VISIBLE);
            spinnerT9L1B1P3.setBackgroundResource(R.color.red);
            utask9.setPoints(0);
        }
        if(A10T1L1B1P3.equals(rightAnswerTextTask10L1B1P3)) {
            spinnerT10L1B1P3.setBackgroundResource(R.color.green);
            utask10.setPoints(pointsT10);
            pointsCount+=pointsT10;
        }else {
            RightAnswer10L1B1P3.setVisibility(View.VISIBLE);
            spinnerT10L1B1P3.setBackgroundResource(R.color.red);
            utask10.setPoints(0);
        }

        return pointsCount;
    }

    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task1")
                .setValue(utask1);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task2")
                .setValue(utask2);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task3")
                .setValue(utask3);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task6")
                .setValue(utask6);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task7")
                .setValue(utask7);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task8")
                .setValue(utask8);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task9")
                .setValue(utask9);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1B1")
                .child("Page3")
                .child("Task10")
                .setValue(utask10);
    }
}