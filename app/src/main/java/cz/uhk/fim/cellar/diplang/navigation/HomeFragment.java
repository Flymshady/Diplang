package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.cellar.diplang.R;

public class HomeFragment extends Fragment {

    private TextView textName, textPoints;
    private SharedPreferences sp;
    private Spinner spinnerLevel;

    private B2HomeFragment b2HomeFragment;
    private B1HomeFragment b1HomeFragment;
    private List<String> names;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;

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
        user = FirebaseAuth.getInstance().getCurrentUser();
        spinnerLevel =(Spinner) v.findViewById(R.id.spinnerLevel);
        textPoints = (TextView) v.findViewById(R.id.textPoints);

        b2HomeFragment = new B2HomeFragment();
        b1HomeFragment = new B1HomeFragment();

        names = new ArrayList<>();
        names.add("B2");
        names.add("B1");

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
                        selectFragment(b1HomeFragment);
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

        if(isNetworkAvailable()) {
            loadUserData();
        }


        return v;
    }


    private void loadUserData() {
        DatabaseReference myRef = database
                .getReference("Users").child(user.getUid())
                .child("Points");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total=0;
                String dipsText="dips";
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    int value = ((Long)snapshot.getValue()).intValue();
                    total+=value;
                }
                if(total==1){
                    dipsText="dip";
                }
                textPoints.setText(total + " "+dipsText);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}