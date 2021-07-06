package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cz.uhk.fim.cellar.diplang.R;

public class PageFragment3 extends Fragment {


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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page3, container, false);




        return rootView;
    }
}