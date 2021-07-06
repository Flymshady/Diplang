package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cz.uhk.fim.cellar.diplang.R;

public class PageFragment2 extends Fragment {


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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page2, container, false);




        return rootView;
    }
}