package cz.uhk.fim.cellar.diplang.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.uhk.fim.cellar.diplang.R;


public class ProfileFriendsFragment extends Fragment {



    public ProfileFriendsFragment() {
        // Required empty public constructor
    }

    public static ProfileFriendsFragment newInstance(String param1, String param2) {
        ProfileFriendsFragment fragment = new ProfileFriendsFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile_friends, container, false);



        return v;
    }
}