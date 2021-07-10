package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;


public class Page9Lesson1Fragment extends Fragment implements View.OnClickListener {

    private ViewPager2 viewPager2;
    private Button btnNextToFinish, btnBackToL1P3;
    private TextView TVPointsL1P4;
    private LessonViewModel viewModel;

    public Page9Lesson1Fragment() {
        // Required empty public constructor
    }


    public static Page9Lesson1Fragment newInstance(String param1, String param2) {
        Page9Lesson1Fragment fragment = new Page9Lesson1Fragment();
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
        View v =  inflater.inflate(R.layout.fragment_page9_lesson1, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        btnBackToL1P3 = (Button) v.findViewById(R.id.btnBackToL1P3);
        TVPointsL1P4 = (TextView) v.findViewById(R.id.TVPointsL1P4);
        TVPointsL1P4.setText(viewModel.getDipPoints().getValue().toString());
        btnBackToL1P3.setOnClickListener(this);

        btnNextToFinish = (Button) v.findViewById(R.id.btnNextToFinish);
        btnNextToFinish.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        TVPointsL1P4 = (TextView) this.getView().findViewById(R.id.TVPointsL1P4);
        TVPointsL1P4.setText(viewModel.getDipPoints().getValue().toString());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToFinish:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                break;
            case R.id.btnBackToL1P3:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);

        }
    }

}