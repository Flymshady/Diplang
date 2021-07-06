package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.uhk.fim.cellar.diplang.R;

public class PageFragment1 extends Fragment {


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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page1, container, false);




        return rootView;
    }
}