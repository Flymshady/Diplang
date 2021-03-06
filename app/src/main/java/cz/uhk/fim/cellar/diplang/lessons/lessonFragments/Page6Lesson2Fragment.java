package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;
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

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment šesté stránky, druhé lekce, úrovně B2
 */
public class Page6Lesson2Fragment extends Fragment implements View.OnClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private UserTask utask1, utask2, utask3, utask4, utask5, utask6;
    private String A1T1L2P6, A2T1L2P6, A3T1L2P6, A4T1L2P6, A5T1L2P6, A6T1L2P6;
    private int pagePoints;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private LinearLayout finishL2P6;
    private Button btnSaveL2P6, btnNextToP7;
    private int pointsT1, pointsT2, pointsT3, pointsT4, pointsT5, pointsT6;
    private String rightAnswerTextTask1L2P6, rightAnswerTextTask2L2P6, rightAnswerTextTask3L2P6, rightAnswerTextTask4L2P6, rightAnswerTextTask5L2P6, rightAnswerTextTask6L2P6;
    private TextView finishTVL2P6, TVPointsL2P6;
    private TextView RightAnswer1L2P6, RightAnswer2L2P6, RightAnswer3L2P6, RightAnswer4L2P6, RightAnswer5L2P6, RightAnswer6L2P6;
    private TextView task1L2P6, task2L2P6, task3L2P6, task4L2P6, task5L2P6, task6L2P6, task7L2P6, infoPage6Lesson2;
    private OptionsTask optionsTask1, optionsTask2, optionsTask3, optionsTask4, optionsTask5, optionsTask6, optionsTask7;
    private Spinner spinnerT1L2P6, spinnerT2L2P6, spinnerT3L2P6, spinnerT4L2P6, spinnerT5L2P6, spinnerT6L2P6;
    private String[] spinnerT1L2P6Array, spinnerT2L2P6Array, spinnerT3L2P6Array, spinnerT4L2P6Array,spinnerT5L2P6Array, spinnerT6L2P6Array;

    public Page6Lesson2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page6_lesson2, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        A1T1L2P6 = "";
        A2T1L2P6 = "";
        A3T1L2P6 = "";
        A4T1L2P6 = "";
        A5T1L2P6 = "";
        A6T1L2P6 = "";
        spinnerT1L2P6Array = new String[]{"A","B","C","D"};
        spinnerT2L2P6Array = new String[]{"A","B","C","D"};
        spinnerT3L2P6Array = new String[]{"A","B","C","D"};
        spinnerT4L2P6Array = new String[]{"A","B","C","D"};
        spinnerT5L2P6Array = new String[]{"A","B","C","D"};
        spinnerT6L2P6Array = new String[]{"A","B","C","D"};

        spinnerT1L2P6 = (Spinner) v.findViewById(R.id.spinnerT1L2P6);
        spinnerT2L2P6 = (Spinner) v.findViewById(R.id.spinnerT2L2P6);
        spinnerT3L2P6 = (Spinner) v.findViewById(R.id.spinnerT3L2P6);
        spinnerT4L2P6 = (Spinner) v.findViewById(R.id.spinnerT4L2P6);
        spinnerT5L2P6 = (Spinner) v.findViewById(R.id.spinnerT5L2P6);
        spinnerT6L2P6 = (Spinner) v.findViewById(R.id.spinnerT6L2P6);

        btnSaveL2P6 = (Button) v.findViewById(R.id.btnSaveL2P6);
        finishL2P6 = (LinearLayout) v.findViewById(R.id.finishL2P6);
        finishTVL2P6 = (TextView) v.findViewById(R.id.finishTVL2P6);
        btnNextToP7 = (Button) v.findViewById(R.id.btnNextToP7L2);
        TVPointsL2P6 = (TextView) v.findViewById(R.id.TVPointsL2P6);
        TVPointsL2P6.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L2P6 = (TextView) v.findViewById(R.id.RightAnswer1L2P6);
        RightAnswer2L2P6 = (TextView) v.findViewById(R.id.RightAnswer2L2P6);
        RightAnswer3L2P6 = (TextView) v.findViewById(R.id.RightAnswer3L2P6);
        RightAnswer4L2P6 = (TextView) v.findViewById(R.id.RightAnswer4L2P6);
        RightAnswer5L2P6 = (TextView) v.findViewById(R.id.RightAnswer5L2P6);
        RightAnswer6L2P6 = (TextView) v.findViewById(R.id.RightAnswer6L2P6);
        task1L2P6 = (TextView) v.findViewById(R.id.task1L2P6);
        task2L2P6 = (TextView) v.findViewById(R.id.task2L2P6);
        task3L2P6 = (TextView) v.findViewById(R.id.task3L2P6);
        task4L2P6 = (TextView) v.findViewById(R.id.task4L2P6);
        task5L2P6 = (TextView) v.findViewById(R.id.task5L2P6);
        task6L2P6 = (TextView) v.findViewById(R.id.task6L2P6);
        task7L2P6 = (TextView) v.findViewById(R.id.task7L2P6);
        infoPage6Lesson2 = (TextView) v.findViewById(R.id.infoPage6Lesson2);
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

        finishL2P6.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPagerL2);

        loadData();

        btnSaveL2P6.setOnClickListener(this);
        btnNextToP7.setOnClickListener(this);
        
        return v;

    }
    /**
     * Načtení dat
     */
    private void loadData() {
        /** Načtení informací o stránce **/
        DatabaseReference myRefPage = database.getReference("Lessons").child("Lesson2").child("Page6").child("PageParams");
        myRefPage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                LessonPage lessonPage = dataSnapshot.getValue(LessonPage.class);
                if(lessonPage!=null){
                    infoPage6Lesson2.setText(lessonPage.getInfo());
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
                .child("Lesson2").child("Page6").child("OptionsTask1");
        myRefTask1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask1 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask1!=null){
                    task1L2P6.setText(optionsTask1.getText());
                    task1L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask1L2P6 = optionsTask1.getRightAnswer();
                    RightAnswer1L2P6.setText("Right answer: "+rightAnswerTextTask1L2P6);
                    RightAnswer1L2P6.setTextIsSelectable(true);
                    pointsT1 = optionsTask1.getPoints();

                    spinnerT1L2P6Array[0]=optionsTask1.getOptionA();
                    spinnerT1L2P6Array[1]=optionsTask1.getOptionB();
                    spinnerT1L2P6Array[2]=optionsTask1.getOptionC();
                    spinnerT1L2P6Array[3]=optionsTask1.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT1 = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item, spinnerT1L2P6Array);
                    spinnerArrayAdapterT1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT1L2P6.setAdapter(spinnerArrayAdapterT1);
                    spinnerT1L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A1T1L2P6 = spinnerT1L2P6Array[i];
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
                .child("Lesson2").child("Page6").child("OptionsTask2");
        myRefTask2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask2 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask2!=null){
                    task2L2P6.setText(optionsTask2.getText());
                    task2L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask2L2P6 = optionsTask2.getRightAnswer();
                    RightAnswer2L2P6.setText("Right answer: "+rightAnswerTextTask2L2P6);
                    RightAnswer2L2P6.setTextIsSelectable(true);
                    pointsT2 = optionsTask2.getPoints();

                    spinnerT2L2P6Array[0]=optionsTask2.getOptionA();
                    spinnerT2L2P6Array[1]=optionsTask2.getOptionB();
                    spinnerT2L2P6Array[2]=optionsTask2.getOptionC();
                    spinnerT2L2P6Array[3]=optionsTask2.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT2 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT2L2P6Array);
                    spinnerArrayAdapterT2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT2L2P6.setAdapter(spinnerArrayAdapterT2);
                    spinnerT2L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A2T1L2P6 = spinnerT2L2P6Array[i];
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
                .child("Lesson2").child("Page6").child("OptionsTask3");
        myRefTask3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask3 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask3!=null){
                    task3L2P6.setText(optionsTask3.getText());
                    task3L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask3L2P6 = optionsTask3.getRightAnswer();
                    RightAnswer3L2P6.setText("Right answer: "+rightAnswerTextTask3L2P6);
                    RightAnswer3L2P6.setTextIsSelectable(true);
                    pointsT3 = optionsTask3.getPoints();

                    spinnerT3L2P6Array[0]=optionsTask3.getOptionA();
                    spinnerT3L2P6Array[1]=optionsTask3.getOptionB();
                    spinnerT3L2P6Array[2]=optionsTask3.getOptionC();
                    spinnerT3L2P6Array[3]=optionsTask3.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT3 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT3L2P6Array);
                    spinnerArrayAdapterT3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT3L2P6.setAdapter(spinnerArrayAdapterT3);
                    spinnerT3L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A3T1L2P6 = spinnerT3L2P6Array[i];
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
                .child("Lesson2").child("Page6").child("OptionsTask4");
        myRefTask4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask4 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask4!=null) {
                    task4L2P6.setText(optionsTask4.getText());
                    task4L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask4L2P6 = optionsTask4.getRightAnswer();
                    RightAnswer4L2P6.setText("Right answer: " + rightAnswerTextTask4L2P6);
                    RightAnswer4L2P6.setTextIsSelectable(true);
                    pointsT4 = optionsTask4.getPoints();

                    spinnerT4L2P6Array[0] = optionsTask4.getOptionA();
                    spinnerT4L2P6Array[1] = optionsTask4.getOptionB();
                    spinnerT4L2P6Array[2] = optionsTask4.getOptionC();
                    spinnerT4L2P6Array[3] = optionsTask4.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT4 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT4L2P6Array);
                    spinnerArrayAdapterT4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT4L2P6.setAdapter(spinnerArrayAdapterT4);
                    spinnerT4L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A4T1L2P6 = spinnerT4L2P6Array[i];
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
        DatabaseReference myRefTask5 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page6").child("OptionsTask5");
        myRefTask5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask5 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask5!=null) {
                    task5L2P6.setText(optionsTask5.getText());
                    task5L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask5L2P6 = optionsTask5.getRightAnswer();
                    RightAnswer5L2P6.setText("Right answer: " + rightAnswerTextTask5L2P6);
                    RightAnswer5L2P6.setTextIsSelectable(true);
                    pointsT5 = optionsTask5.getPoints();

                    spinnerT5L2P6Array[0] = optionsTask5.getOptionA();
                    spinnerT5L2P6Array[1] = optionsTask5.getOptionB();
                    spinnerT5L2P6Array[2] = optionsTask5.getOptionC();
                    spinnerT5L2P6Array[3] = optionsTask5.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT5 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT5L2P6Array);
                    spinnerArrayAdapterT5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT5L2P6.setAdapter(spinnerArrayAdapterT5);
                    spinnerT5L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A5T1L2P6 = spinnerT5L2P6Array[i];
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
        DatabaseReference myRefTask6 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page6").child("OptionsTask6");
        myRefTask6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask6 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask6!=null) {
                    task6L2P6.setText(optionsTask6.getText());
                    task6L2P6.setTextIsSelectable(true);
                    rightAnswerTextTask6L2P6 = optionsTask6.getRightAnswer();
                    RightAnswer6L2P6.setText("Right answer: " + rightAnswerTextTask6L2P6);
                    RightAnswer6L2P6.setTextIsSelectable(true);
                    pointsT6 = optionsTask6.getPoints();

                    spinnerT6L2P6Array[0] = optionsTask6.getOptionA();
                    spinnerT6L2P6Array[1] = optionsTask6.getOptionB();
                    spinnerT6L2P6Array[2] = optionsTask6.getOptionC();
                    spinnerT6L2P6Array[3] = optionsTask6.getOptionD();
                    ArrayAdapter<String> spinnerArrayAdapterT6 =
                            new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerT6L2P6Array);
                    spinnerArrayAdapterT6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinnerT6L2P6.setAdapter(spinnerArrayAdapterT6);
                    spinnerT6L2P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            A6T1L2P6 = spinnerT6L2P6Array[i];
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
        DatabaseReference myRefTask7 = database
                .getReference("Lessons")
                .child("Lesson2").child("Page6").child("OptionsTask7");
        myRefTask7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                optionsTask7 = dataSnapshot.getValue(OptionsTask.class);
                if(optionsTask7!=null) {
                    task7L2P6.setText(optionsTask7.getText());
                    task7L2P6.setTextIsSelectable(true);
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
        TVPointsL2P6 = (TextView) this.getView().findViewById(R.id.TVPointsL2P6);
        TVPointsL2P6.setText(viewModel.getDipPoints().getValue().toString());
    }

    /** Nastavení buttonů **/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL2P6):
                points = calculatePoints();
                finishL2P6.setVisibility(View.VISIBLE);
                finishTVL2P6.setText("Splněno! "+points +"dips!");
                btnSaveL2P6.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL2P6.setText(viewModel.getDipPoints().getValue().toString());
                saveUserTask();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP7L2):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;

        }
    }

    /** Porovnání odpovědí, zobrazení správných řešení, kalkulace bodů **/
    private int calculatePoints() {
        int pointsCount=0;
        A1T1L2P6 = spinnerT1L2P6.getSelectedItem().toString();
        A2T1L2P6 = spinnerT2L2P6.getSelectedItem().toString();
        A3T1L2P6 = spinnerT3L2P6.getSelectedItem().toString();
        A4T1L2P6 = spinnerT4L2P6.getSelectedItem().toString();
        A5T1L2P6 = spinnerT5L2P6.getSelectedItem().toString();
        A6T1L2P6 = spinnerT6L2P6.getSelectedItem().toString();

        utask1.setAnswer(A1T1L2P6);
        utask2.setAnswer(A2T1L2P6);
        utask3.setAnswer(A3T1L2P6);
        utask4.setAnswer(A4T1L2P6);
        utask5.setAnswer(A5T1L2P6);
        utask6.setAnswer(A6T1L2P6);
        spinnerT1L2P6.setEnabled(false);
        spinnerT2L2P6.setEnabled(false);
        spinnerT3L2P6.setEnabled(false);
        spinnerT4L2P6.setEnabled(false);
        spinnerT5L2P6.setEnabled(false);
        spinnerT6L2P6.setEnabled(false);

        if(A1T1L2P6.equals(rightAnswerTextTask1L2P6)) {
            spinnerT1L2P6.setBackgroundResource(R.color.green);
            utask1.setPoints(pointsT1);
            pointsCount+=pointsT1;
        }else{
            RightAnswer1L2P6.setVisibility(View.VISIBLE);
            spinnerT1L2P6.setBackgroundResource(R.color.red);
            utask1.setPoints(0);
        }
        if(A2T1L2P6.equals(rightAnswerTextTask2L2P6)) {
            spinnerT2L2P6.setBackgroundResource(R.color.green);
            utask2.setPoints(pointsT2);
            pointsCount+=pointsT2;
        }else {
            RightAnswer2L2P6.setVisibility(View.VISIBLE);
            spinnerT2L2P6.setBackgroundResource(R.color.red);
            utask2.setPoints(0);
        }
        if(A3T1L2P6.equals(rightAnswerTextTask3L2P6)) {
            spinnerT3L2P6.setBackgroundResource(R.color.green);
            utask3.setPoints(pointsT3);
            pointsCount+=pointsT3;
        }else {
            RightAnswer3L2P6.setVisibility(View.VISIBLE);
            spinnerT3L2P6.setBackgroundResource(R.color.red);
            utask3.setPoints(0);
        }
        if(A4T1L2P6.equals(rightAnswerTextTask4L2P6)) {
            spinnerT4L2P6.setBackgroundResource(R.color.green);
            utask4.setPoints(pointsT4);
            pointsCount+=pointsT4;
        }else {
            RightAnswer4L2P6.setVisibility(View.VISIBLE);
            spinnerT4L2P6.setBackgroundResource(R.color.red);
            utask4.setPoints(0);
        }
        if(A5T1L2P6.equals(rightAnswerTextTask5L2P6)) {
            spinnerT5L2P6.setBackgroundResource(R.color.green);
            utask5.setPoints(pointsT5);
            pointsCount+=pointsT5;
        }else {
            RightAnswer5L2P6.setVisibility(View.VISIBLE);
            spinnerT5L2P6.setBackgroundResource(R.color.red);
            utask5.setPoints(0);
        }
        if(A6T1L2P6.equals(rightAnswerTextTask6L2P6)) {
            spinnerT6L2P6.setBackgroundResource(R.color.green);
            utask6.setPoints(pointsT6);
            pointsCount+=pointsT6;
        }else {
            RightAnswer6L2P6.setVisibility(View.VISIBLE);
            spinnerT6L2P6.setBackgroundResource(R.color.red);
            utask6.setPoints(0);
        }

        return pointsCount;
    }

    /** Uložení uživatelových odpovědí do db **/
    private void saveUserTask() {
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task1")
                .setValue(utask1);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task2")
                .setValue(utask2);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task3")
                .setValue(utask3);

        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task4")
                .setValue(utask4);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task5")
                .setValue(utask5);
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson2")
                .child("Page6")
                .child("Task6")
                .setValue(utask6);
    }
}