package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cz.uhk.fim.cellar.diplang.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;


public class PageFragment3 extends Fragment implements View.OnClickListener {

    private ViewPager2 viewPager2;
    private Button btnNextToP4, btnBackToL1P2, btnSaveL1P3;
    private TextView TVPointsL1P3, finishTVL1P3, RAL1P3;
    private LessonViewModel viewModel;
    private EditText ET1L1P3;
    private int points = 0;
    private LinearLayout finishL1P3;
    private String RAT1L1P3;
    //answer 1 text 1 lesson 1 page 3
    private String A1T1L1P3;


    public PageFragment3() {
        // Required empty public constructor
    }

    public static PageFragment3 newInstance(String param1, String param2) {
        PageFragment3 fragment = new PageFragment3();
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
        View v = inflater.inflate(R.layout.fragment_page3, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        btnBackToL1P2 = (Button) v.findViewById(R.id.btnBackToL1P2);
        TVPointsL1P3 = (TextView) v.findViewById(R.id.TVPointsL1P3);
        TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());
        btnBackToL1P2.setOnClickListener(this);

        finishTVL1P3 = (TextView) v.findViewById(R.id.finishTVL1P3);
        ET1L1P3 = (EditText) v.findViewById(R.id.ET1L1P3);
        finishL1P3 = (LinearLayout) v.findViewById(R.id.finishL1P3);
        finishL1P3.setVisibility(View.GONE);
        btnSaveL1P3 = (Button) v.findViewById(R.id.btnSaveL1P3);
        RAL1P3 = (TextView) v.findViewById(R.id.RAL1P3);

        loadData();

        btnSaveL1P3.setOnClickListener(this);
        btnNextToP4 = (Button) v.findViewById(R.id.btnNextToP4);
        btnNextToP4.setOnClickListener(this);


        return v;
    }

    private void loadData() {
        RAT1L1P3 = "a";
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1P3 = (TextView) this.getView().findViewById(R.id.TVPointsL1P3);
        TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP4:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case R.id.btnBackToL1P2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                break;
            case R.id.btnSaveL1P3:
                points = calculatePoints();
                finishL1P3.setVisibility(View.VISIBLE);
                finishTVL1P3.setText("Splněno! "+points+"dips!");
                btnSaveL1P3.setVisibility(View.GONE);
                viewModel.setDipPoints(viewModel.getDipPoints().getValue()+points);
                TVPointsL1P3.setText(viewModel.getDipPoints().getValue().toString());
                Toast.makeText(this.getActivity(), "Počet bodů: "+points, Toast.LENGTH_LONG).show();
                break;


        }
    }

    private int calculatePoints() {
        int pointsCount = 0;
        A1T1L1P3 = ET1L1P3.getText().toString();
        ET1L1P3.setInputType(InputType.TYPE_NULL);
        if(A1T1L1P3.toLowerCase().equals(RAT1L1P3)){
            ET1L1P3.setBackgroundResource(R.color.green);
            pointsCount++;
        }else{
            ET1L1P3.setBackgroundResource(R.color.red);
            RAL1P3.setVisibility(View.VISIBLE);
        }
        return pointsCount;
    }
}