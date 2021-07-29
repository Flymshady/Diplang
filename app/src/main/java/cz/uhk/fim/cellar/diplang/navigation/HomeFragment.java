package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.SharedPreferences;
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
import cz.uhk.fim.cellar.diplang.classes.User;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment domovské stránky s lekcemi v NavigationActivity
 */
public class HomeFragment extends Fragment {

    private TextView textName, textPoints;
    private SharedPreferences sp;
    private Spinner spinnerLevel;
    private String userLevel;
    private B2HomeFragment b2HomeFragment;
    private B1HomeFragment b1HomeFragment;
    private List<String> names;
    private int spinnerValue;
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        spinnerLevel =(Spinner) v.findViewById(R.id.spinnerLevel);
        textPoints = (TextView) v.findViewById(R.id.textPoints);

        b2HomeFragment = new B2HomeFragment();
        b1HomeFragment = new B1HomeFragment();

        names = new ArrayList<>();
        names.add("Upper Intermediate");
        names.add("Intermediate");

        /**
         * Automatický přechod na poslední nastavenou položku spinneru
         */
        spinnerValue = sp.getInt("userLevel",-1);
        if(spinnerValue != -1){
            spinnerLevel.setSelection(spinnerValue);
        }
        ArrayAdapter<String > arrayAdapter = new ArrayAdapter<>(v.getContext(), R.layout.item_level, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapter);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sp.edit();
                switch(i){
                    case 0:
                        editor.putInt("userLevel", 0);
                        editor.commit();
                        selectFragment(b2HomeFragment);
                        break;
                    case 1:
                        editor.putInt("userLevel", 1);
                        editor.commit();
                        selectFragment(b1HomeFragment);
                        break;
                    default:
                        editor.putInt("userLevel", 0);
                        editor.commit();
                        selectFragment(b2HomeFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerValue = sp.getInt("userLevel",-1);
        if(spinnerValue != -1){
            spinnerLevel.setSelection(spinnerValue);
        }

        /**
         * Uložení zvoleného stavu na spinneru
         */
        int userLevel = spinnerLevel.getSelectedItemPosition();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("userLevel", userLevel);
        editor.commit();

        spinnerValue = sp.getInt("userLevel",-1);
        if(spinnerValue != -1){
            spinnerLevel.setSelection(spinnerValue);
        }

        loadUserData();

        return v;
    }

    /**
     * Přechod na posledni nastavenou položku na spinneru
     */
    @Override
    public void onResume() {
        super.onResume();
        spinnerValue = sp.getInt("userLevel",-1);
        if(spinnerValue != -1){
            spinnerLevel.setSelection(spinnerValue);
        }

    }

    /**
     * Načtení součtu uživatelových bodů z lekcí (dips)
     */
    private void loadUserData() {
        DatabaseReference myRef = database
                .getReference("Users").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total=0;
                String dipsText="dips";
                for(DataSnapshot snapshot:dataSnapshot.child("Points").getChildren()) {
                    String key = snapshot.getKey();
                    int value = ((Long)snapshot.getValue()).intValue();
                    total+=value;
                }
                if(total==1){
                    dipsText="dip";
                }
                textPoints.setText(total + " "+dipsText);
                User u = dataSnapshot.child("UserParams").getValue(User.class);
                textName.setText(u.getName());

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    /**
     * Přechod mezi fragmenty B1HomeFragment a B2HomeFragment pro různé úrovně jazyka
     * @param fragment Fragment
     */
    private void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}