package cz.uhk.fim.cellar.diplang;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView textName, textPoints;
    private SharedPreferences sp;
    private Spinner spinnerLevel;

    private B2HomeFragment b2HomeFragment;
    private B2PlusHomeFragment b2PlusHomeFragment;
    private List<String> names;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
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
        View v=  inflater.inflate(R.layout.fragment_home, container, false);
        sp = this.getActivity().getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        textName = (TextView) v.findViewById(R.id.textName);
        textName.setText(sp.getString("name",""));

        spinnerLevel =(Spinner) v.findViewById(R.id.spinnerLevel);

        b2HomeFragment = new B2HomeFragment();
        b2PlusHomeFragment = new B2PlusHomeFragment();

        names = new ArrayList<>();
        names.add("B2");
        names.add("B2+");

        ArrayAdapter<String > arrayAdapter = new ArrayAdapter<>(v.getContext(), R.layout.item_level, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapter);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        selectFragment(b2HomeFragment);
                        break;
                    case 1:
                        selectFragment(b2PlusHomeFragment);
                        break;
                    default:
                        selectFragment(b2HomeFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}