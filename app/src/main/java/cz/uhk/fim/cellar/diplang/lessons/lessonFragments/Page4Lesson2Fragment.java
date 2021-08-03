package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
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

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment čtvrté stránky, druhé lekce, úrovně B2
 */
public class Page4Lesson2Fragment extends Fragment implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private UserTask utask1, utask2, utask3, utask4;
    private String A1T1L2P4, A2T1L2P4, A3T1L2P4, A4T1L2P4;
    private int pagePoints;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private LinearLayout finishL2P4;
    private Button btnSaveL2P4, btnNextToP5;
    private int pointsT1, pointsT2, pointsT3, pointsT4;
    private String rightAnswerTextTask1L2P4, rightAnswerTextTask2L2P4, rightAnswerTextTask3L2P4, rightAnswerTextTask4L2P4;
    private TextView finishTVL2P4, TVPointsL2P4;
    private TextView RightAnswer1L2P4, RightAnswer2L2P4, RightAnswer3L2P4, RightAnswer4L2P4;
    private TextView task1L2P4, task2L2P4, task3L2P4, task4L2P4, infoPage4Lesson2;
    private OptionsTask optionsTask1, optionsTask2, optionsTask3, optionsTask4;
    private Spinner spinnerT1L2P4, spinnerT2L2P4, spinnerT3L2P4, spinnerT4L2P4;
    private String[] spinnerT1L2P4Array, spinnerT2L2P4Array, spinnerT3L2P4Array, spinnerT4L2P4Array;

    public Page4Lesson2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page4_lesson2, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        A1T1L2P4 = "";
        A2T1L2P4 = "";
        A3T1L2P4 = "";
        A4T1L2P4 = "";
        spinnerT1L2P4Array = new String[]{"A","B","C","D"};
        spinnerT2L2P4Array = new String[]{"A","B","C","D"};
        spinnerT3L2P4Array = new String[]{"A","B","C","D"};
        spinnerT4L2P4Array = new String[]{"A","B","C","D"};

        spinnerT1L2P4 = (Spinner) v.findViewById(R.id.spinnerT1L2P4);
        spinnerT2L2P4 = (Spinner) v.findViewById(R.id.spinnerT2L2P4);
        spinnerT3L2P4 = (Spinner) v.findViewById(R.id.spinnerT3L2P4);
        spinnerT4L2P4 = (Spinner) v.findViewById(R.id.spinnerT4L2P4);

        btnSaveL2P4 = (Button) v.findViewById(R.id.btnSaveL2P4);
        finishL2P4 = (LinearLayout) v.findViewById(R.id.finishL2P4);
        finishTVL2P4 = (TextView) v.findViewById(R.id.finishTVL2P4);
        btnNextToP5 = (Button) v.findViewById(R.id.btnNextToP5L2);
        TVPointsL2P4 = (TextView) v.findViewById(R.id.TVPointsL2P4);
        TVPointsL2P4.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L2P4 = (TextView) v.findViewById(R.id.RightAnswer1L2P4);
        RightAnswer2L2P4 = (TextView) v.findViewById(R.id.RightAnswer2L2P4);
        RightAnswer3L2P4 = (TextView) v.findViewById(R.id.RightAnswer3L2P4);
        RightAnswer4L2P4 = (TextView) v.findViewById(R.id.RightAnswer4L2P4);
        task1L2P4 = (TextView) v.findViewById(R.id.task1L2P4);
        task2L2P4 = (TextView) v.findViewById(R.id.task2L2P4);
        task3L2P4 = (TextView) v.findViewById(R.id.task3L2P4);
        task4L2P4 = (TextView) v.findViewById(R.id.task4L2P4);
        infoPage4Lesson2 = (TextView) v.findViewById(R.id.infoPage4Lesson2);
        utask1 = new UserTask();
        utask2 = new UserTask();
        utask3 = new UserTask();
        utask4 = new UserTask();
        utask1.setCreated(LocalDateTime.now().toString());
        utask2.setCreated(LocalDateTime.now().toString());
        utask3.setCreated(LocalDateTime.now().toString());
        utask4.setCreated(LocalDateTime.now().toString());

        finishL2P4.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        loadData();

        btnSaveL2P4.setOnClickListener(this);
        btnNextToP5.setOnClickListener(this);
        
        return v;
    }
    /**
     * Načtení dat
     */
    private void loadData() {
        /** Načtení informací o stránce **/
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson2").child("Page4").child("PageParams");
        myRefPage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage4Lesson2.setText(lessonPage.getInfo());
                    pagePoints = lessonPage.getPagePoints();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        /**
         * Načtení úlohy s možnostmi a její přidání do layoutu
         */
        DatabaseReference myRefTask1 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page4").child("OptionsTask1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask1 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask1!=null){
                    task1L2P4.setText(optionsTask1.getText());
                    task1L2P4.setTextIsSelectable(true);
                    rightAnswerTextTask1L2P4 = optionsTask1.getRightAnswer();
                    RightAnswer1L2P4.setText("Right answer: "+rightAnswerTextTask1L2P4);
                    RightAnswer1L2P4.setTextIsSelectable(true);
                    pointsT1 = optionsTask1.getPoints();

                    spinnerT1L2P4Array[0]=optionsTask1.getOptionA();
                    spinnerT1L2P4Array[1]=optionsTask1.getOptionB();
                    spinnerT1L2P4Array[2]=optionsTask1.getOptionC();
                    spinnerT1L2P4Array[3]=optionsTask1.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT1 = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item, spinnerT1L2P4Array);
                    spinnerArrayAdapterT1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT1L2P4.setAdapter(spinnerArrayAdapterT1);
                    spinnerT1L2P4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A1T1L2P4 = spinnerT1L2P4Array[i];
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

        /**
         * Načtení úlohy s možnostmi a její přidání do layoutu
         */
        DatabaseReference myRefTask2 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page4").child("OptionsTask2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask2 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask2!=null){
                    task2L2P4.setText(optionsTask2.getText());
                    task2L2P4.setTextIsSelectable(true);
                    rightAnswerTextTask2L2P4 = optionsTask2.getRightAnswer();
                    RightAnswer2L2P4.setText("Right answer: "+rightAnswerTextTask2L2P4);
                    RightAnswer2L2P4.setTextIsSelectable(true);
                    pointsT2 = optionsTask2.getPoints();

                    spinnerT2L2P4Array[0]=optionsTask2.getOptionA();
                    spinnerT2L2P4Array[1]=optionsTask2.getOptionB();
                    spinnerT2L2P4Array[2]=optionsTask2.getOptionC();
                    spinnerT2L2P4Array[3]=optionsTask2.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT2 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT2L2P4Array);
                    spinnerArrayAdapterT2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT2L2P4.setAdapter(spinnerArrayAdapterT2);
                    spinnerT2L2P4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A2T1L2P4 = spinnerT2L2P4Array[i];
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

        /**
         * Načtení úlohy s možnostmi a její přidání do layoutu
         */
        DatabaseReference myRefTask3 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page4").child("OptionsTask3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask3 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask3!=null){
                    task3L2P4.setText(optionsTask3.getText());
                    task3L2P4.setTextIsSelectable(true);
                    rightAnswerTextTask3L2P4 = optionsTask3.getRightAnswer();
                    RightAnswer3L2P4.setText("Right answer: "+rightAnswerTextTask3L2P4);
                    RightAnswer3L2P4.setTextIsSelectable(true);
                    pointsT3 = optionsTask3.getPoints();

                    spinnerT3L2P4Array[0]=optionsTask3.getOptionA();
                    spinnerT3L2P4Array[1]=optionsTask3.getOptionB();
                    spinnerT3L2P4Array[2]=optionsTask3.getOptionC();
                    spinnerT3L2P4Array[3]=optionsTask3.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT3 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT3L2P4Array);
                    spinnerArrayAdapterT3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT3L2P4.setAdapter(spinnerArrayAdapterT3);
                    spinnerT3L2P4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A3T1L2P4 = spinnerT3L2P4Array[i];
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

        /**
         * Načtení úlohy s možnostmi a její přidání do layoutu
         */
        DatabaseReference myRefTask4 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page4").child("OptionsTask4");
        myRefTask4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask4 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask4!=null) {
                    task4L2P4.setText(optionsTask4.getText());
                    task4L2P4.setTextIsSelectable(true);
                    rightAnswerTextTask4L2P4 = optionsTask4.getRightAnswer();
                    RightAnswer4L2P4.setText("Right answer: " + rightAnswerTextTask4L2P4);
                    RightAnswer4L2P4.setTextIsSelectable(true);
                    pointsT4 = optionsTask4.getPoints();

                    spinnerT4L2P4Array[0] = optionsTask4.getOptionA();
                    spinnerT4L2P4Array[1] = optionsTask4.getOptionB();
                    spinnerT4L2P4Array[2] = optionsTask4.getOptionC();
                    spinnerT4L2P4Array[3] = optionsTask4.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT4 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT4L2P4Array);
                    spinnerArrayAdapterT4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT4L2P4.setAdapter(spinnerArrayAdapterT4);
                    spinnerT4L2P4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A4T1L2P4 = spinnerT4L2P4Array[i];
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
        TVPointsL2P4 = (TextView) this.getView().findViewById(R.id.TVPointsL2P4);
        TVPointsL2P4.setText(viewModel.getDipPoints().getValue().toString());
    }

    /** Nastavení buttonů **/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL2P4):
                points = calculatePoints();
                finishL2P4.setVisibility(View.VISIBLE);
                finishTVL2P4.setText("Splněno! "+points +"dips!");
                btnSaveL2P4.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL2P4.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP5L2):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    /** Porovnání odpovědí, zobrazení správných řešení, kalkulace bodů **/
    private int calculatePoints() {
        int pointsCount=0;
        A1T1L2P4 = spinnerT1L2P4.getSelectedItem().toString();
        A2T1L2P4 = spinnerT2L2P4.getSelectedItem().toString();
        A3T1L2P4 = spinnerT3L2P4.getSelectedItem().toString();
        A4T1L2P4 = spinnerT4L2P4.getSelectedItem().toString();

        utask1.setAnswer(A1T1L2P4);
        utask2.setAnswer(A2T1L2P4);
        utask3.setAnswer(A3T1L2P4);
        utask4.setAnswer(A4T1L2P4);
        spinnerT1L2P4.setEnabled(false);
        spinnerT2L2P4.setEnabled(false);
        spinnerT3L2P4.setEnabled(false);
        spinnerT4L2P4.setEnabled(false);

        if(A1T1L2P4.equals(rightAnswerTextTask1L2P4)) {
            spinnerT1L2P4.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L2P4.setVisibility(View.VISIBLE);
            spinnerT1L2P4.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L2P4.equals(rightAnswerTextTask2L2P4)) {
            spinnerT2L2P4.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L2P4.setVisibility(View.VISIBLE);
            spinnerT2L2P4.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L2P4.equals(rightAnswerTextTask3L2P4)) {
            spinnerT3L2P4.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L2P4.setVisibility(View.VISIBLE);
            spinnerT3L2P4.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L2P4.equals(rightAnswerTextTask4L2P4)) {
            spinnerT4L2P4.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L2P4.setVisibility(View.VISIBLE);
            spinnerT4L2P4.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }

        return pointsCount;
    }

    /** Uložení uživatelových odpovědí do db **/
    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page4")
                .child("Task1")
                .setValue(utask1);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page4")
                .child("Task2")
                .setValue(utask2);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page4")
                .child("Task3")
                .setValue(utask3);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page4")
                .child("Task4")
                .setValue(utask4);
    }
}