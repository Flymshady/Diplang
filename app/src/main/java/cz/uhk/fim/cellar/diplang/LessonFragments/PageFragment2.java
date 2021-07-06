package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import cz.uhk.fim.cellar.diplang.LoginActivity;
import cz.uhk.fim.cellar.diplang.R;

public class PageFragment2 extends Fragment implements View.OnClickListener {

    private EditText ET1L1, ET2L1, ET3L1, ET4L1, ET5L1;
    private Button btnSaveP1L1;
    private String A1T1L1, A2T1L1, A3T1L1, A4T1L1, A5T1L1;
    private String RA1T1L1, RA2T1L1, RA3T1L1, RA4T1L1, RA5T1L1;
    private int points = 0;


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

        ET1L1 = (EditText) v.findViewById(R.id.ET1L1);
        ET2L1 = (EditText) v.findViewById(R.id.ET2L1);
        ET3L1 = (EditText) v.findViewById(R.id.ET3L1);
        ET4L1 = (EditText) v.findViewById(R.id.ET4L1);
        ET5L1 = (EditText) v.findViewById(R.id.ET5L1);
        btnSaveP1L1 = (Button) v.findViewById(R.id.btnSaveP1L1);

        RA1T1L1 = "a";
        RA2T1L1 = "b";
        RA3T1L1 = "c";
        RA4T1L1 = "d";
        RA5T1L1 = "e";


        btnSaveP1L1.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnSaveP1L1):
                points = calculatePoint();
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;
        }
    }

    private int calculatePoint() {
        int pointsCount=0;
        A1T1L1 = ET1L1.getText().toString();
        A2T1L1 = ET2L1.getText().toString();
        A3T1L1 = ET3L1.getText().toString();
        A4T1L1 = ET4L1.getText().toString();
        A5T1L1 = ET5L1.getText().toString();
        if(A1T1L1.equals(RA1T1L1)) {pointsCount++;}
        if(A2T1L1.equals(RA2T1L1)) {pointsCount++;}
        if(A3T1L1.equals(RA3T1L1)) {pointsCount++;}
        if(A4T1L1.equals(RA4T1L1)) {pointsCount++;}
        if(A5T1L1.equals(RA5T1L1)) {pointsCount++;}
        return pointsCount;
    }
}