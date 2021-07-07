package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.graphics.Color;
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

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import cz.uhk.fim.cellar.diplang.LessonViewModel;
import cz.uhk.fim.cellar.diplang.LoginActivity;
import cz.uhk.fim.cellar.diplang.R;

import static android.view.View.GONE;

public class PageFragment2 extends Fragment implements View.OnClickListener {

    private EditText ET1L1P2, ET2L1P2, ET3L1P2, ET4L1P2, ET5L1P2;
    private Button btnSaveL1P2, btnNextToP3, btnBackToL1P1;
    private String A1T1L1P2, A2T1L1P2, A3T1L1P2, A4T1L1P2, A5T1L1P2;
    private String RA1T1L1P2, RA2T1L1P2, RA3T1L1P2, RA4T1L1P2, RA5T1L1P2;
    private int points = 0;
    private ViewPager2 viewPager2;
    private LinearLayout finishL1P2;
    private TextView finishTVL1P2, TVPointsL1P2;
    private LessonViewModel viewModel;
    private TextView RightAnswer1L1P3, RightAnswer2L1P3,RightAnswer3L1P3, RightAnswer4L1P3, RightAnswer5L1P3;
    private TextView task1L1P2, task2L1P2, task3L1P2, task4L1P2, task5L1P2;


    public PageFragment2() {
        // Required empty public constructor
    }


    public static PageFragment2 newInstance(String param1, String param2) {
        PageFragment2 fragment = new PageFragment2();
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
        View v = inflater.inflate(R.layout.fragment_page2, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);


        ET1L1P2 = (EditText) v.findViewById(R.id.ET1L1P2);
        ET2L1P2 = (EditText) v.findViewById(R.id.ET2L1P2);
        ET3L1P2 = (EditText) v.findViewById(R.id.ET3L1P2);
        ET4L1P2 = (EditText) v.findViewById(R.id.ET4L1P2);
        ET5L1P2 = (EditText) v.findViewById(R.id.ET5L1P2);
        btnSaveL1P2 = (Button) v.findViewById(R.id.btnSaveL1P2);
        finishL1P2 = (LinearLayout) v.findViewById(R.id.finishL1P2);
        finishTVL1P2 = (TextView) v.findViewById(R.id.finishTVL1P2);
        btnNextToP3 = (Button) v.findViewById(R.id.btnNextToP3);
        btnBackToL1P1 = (Button) v.findViewById(R.id.btnBackToL1P1);
        TVPointsL1P2 = (TextView) v.findViewById(R.id.TVPointsL1P2);
        TVPointsL1P2.setText(viewModel.getDipPoints().getValue().toString());
        RightAnswer1L1P3 = (TextView) v.findViewById(R.id.RightAnswer1L1P3);
        RightAnswer2L1P3 = (TextView) v.findViewById(R.id.RightAnswer2L1P3);
        RightAnswer3L1P3 = (TextView) v.findViewById(R.id.RightAnswer3L1P3);
        RightAnswer4L1P3 = (TextView) v.findViewById(R.id.RightAnswer4L1P3);
        RightAnswer5L1P3 = (TextView) v.findViewById(R.id.RightAnswer5L1P3);
        task1L1P2 = (TextView) v.findViewById(R.id.task1L1P2);
        task2L1P2 = (TextView) v.findViewById(R.id.task2L1P2);
        task3L1P2 = (TextView) v.findViewById(R.id.task3L1P2);
        task4L1P2 = (TextView) v.findViewById(R.id.task4L1P2);
        task5L1P2 = (TextView) v.findViewById(R.id.task5L1P2);


        finishL1P2.setVisibility(GONE);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        loadData();



        btnSaveL1P2.setOnClickListener(this);
        btnNextToP3.setOnClickListener(this);
        btnBackToL1P1.setOnClickListener(this);

        return v;
    }

    private void loadData() {
        RA1T1L1P2 = "a";
        RA2T1L1P2 = "b";
        RA3T1L1P2 = "c";
        RA4T1L1P2 = "d";
        RA5T1L1P2 = "e";
/*
        task1L1P2.setText("");
        task2L1P2.setText("");
        task3L1P2.setText("");
        task4L1P2.setText("");
        task5L1P2.setText("");
  */
        RightAnswer1L1P3.setText(RA1T1L1P2);
        RightAnswer2L1P3.setText(RA2T1L1P2);
        RightAnswer3L1P3.setText(RA3T1L1P2);
        RightAnswer4L1P3.setText(RA4T1L1P2);
        RightAnswer5L1P3.setText(RA5T1L1P2);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveL1P2):
                points = calculatePoint();
                finishL1P2.setVisibility(View.VISIBLE);
                finishTVL1P2.setText("Splněno! "+points +"dips!");
                btnSaveL1P2.setVisibility(GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL1P2.setText(viewModel.getDipPoints().getValue().toString());
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
            case (R.id.btnNextToP3):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case(R.id.btnBackToL1P1):
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1P2 = (TextView) this.getView().findViewById(R.id.TVPointsL1P2);
        TVPointsL1P2.setText(viewModel.getDipPoints().getValue().toString());

    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L1P2 = ET1L1P2.getText().toString();
        A2T1L1P2 = ET2L1P2.getText().toString();
        A3T1L1P2 = ET3L1P2.getText().toString();
        A4T1L1P2 = ET4L1P2.getText().toString();
        A5T1L1P2 = ET5L1P2.getText().toString();
        ET1L1P2.setInputType(InputType.TYPE_NULL);
        ET2L1P2.setInputType(InputType.TYPE_NULL);
        ET3L1P2.setInputType(InputType.TYPE_NULL);
        ET4L1P2.setInputType(InputType.TYPE_NULL);
        ET5L1P2.setInputType(InputType.TYPE_NULL);
        if(A1T1L1P2.toLowerCase().equals(RA1T1L1P2)) {
            ET1L1P2.setBackgroundResource(R.color.green);
            pointsCount++;
        }else{
            RightAnswer1L1P3.setVisibility(View.VISIBLE);
            ET1L1P2.setBackgroundResource(R.color.red);
        }
        if(A2T1L1P2.toLowerCase().equals(RA2T1L1P2)) {
            ET2L1P2.setBackgroundResource(R.color.green);
            pointsCount++;
        }else {
            RightAnswer2L1P3.setVisibility(View.VISIBLE);
            ET2L1P2.setBackgroundResource(R.color.red);
        }
        if(A3T1L1P2.toLowerCase().equals(RA3T1L1P2)) {
            ET3L1P2.setBackgroundResource(R.color.green);
            pointsCount++;
        }else {
            RightAnswer3L1P3.setVisibility(View.VISIBLE);
            ET3L1P2.setBackgroundResource(R.color.red);
        }
        if(A4T1L1P2.toLowerCase().equals(RA4T1L1P2)) {
            ET4L1P2.setBackgroundResource(R.color.green);
            pointsCount++;
        }else {
            RightAnswer4L1P3.setVisibility(View.VISIBLE);
            ET4L1P2.setBackgroundResource(R.color.red);
        }
        if(A5T1L1P2.toLowerCase().equals(RA5T1L1P2)) {
            ET5L1P2.setBackgroundResource(R.color.green);
            pointsCount++;
        }else {
            RightAnswer5L1P3.setVisibility(View.VISIBLE);
            ET5L1P2.setBackgroundResource(R.color.red);
        }
        return pointsCount;
    }
}