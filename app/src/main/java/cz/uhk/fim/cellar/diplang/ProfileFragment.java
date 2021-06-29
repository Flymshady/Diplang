package cz.uhk.fim.cellar.diplang;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cz.uhk.fim.cellar.diplang.Classes.User;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button buttonLogout, buttonSetNotification;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        buttonLogout = (Button) v.findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        buttonSetNotification = (Button) v.findViewById(R.id.buttonSetNotification);

        buttonSetNotification.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView textNameFill = (TextView) v.findViewById(R.id.textNameFill);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String name = userProfile.name;
                    textNameFill.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Něco se pokazilo.", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogout:
                FirebaseAuth.getInstance().signOut();
                try {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } finally {
                    getActivity().finish();
                }


                break;
            case R.id.buttonSetNotification:
                startActivity(new Intent(getActivity(), NotificationSettingsActivity.class));

                break;

        }
    }
}