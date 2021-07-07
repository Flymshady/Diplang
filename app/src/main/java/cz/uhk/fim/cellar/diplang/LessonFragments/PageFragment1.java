package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cz.uhk.fim.cellar.diplang.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;

public class PageFragment1 extends Fragment implements View.OnClickListener {

    private Button btnNextToP2;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private TextView task1L1P1, task2L1P1;

    public PageFragment1() {
        // Required empty public constructor
    }


    public static PageFragment1 newInstance(String param1, String param2) {
        PageFragment1 fragment = new PageFragment1();
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
        View v = inflater.inflate(R.layout.fragment_page1, container, false);

        task1L1P1 = (TextView) v.findViewById(R.id.task1L1P1);
        task2L1P1 = (TextView) v.findViewById(R.id.task2L1P1);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        loadData();

        btnNextToP2 = (Button) v.findViewById(R.id.btnNextToP2);
        btnNextToP2.setOnClickListener(this);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        return v;
    }

    private void loadData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    }
}