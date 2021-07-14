package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.User;
import cz.uhk.fim.cellar.diplang.notifications.NotificationSettingsActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button buttonLogout, buttonSetNotification;
    private String userID;
    private SharedPreferences sp;
    private Context mContext;
    private Drawable topDrawable;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);

        sp = this.getActivity().getSharedPreferences("MyNotifications", Context.MODE_PRIVATE);
        buttonLogout = (Button) v.findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        if(sp.getBoolean("switcher", true)){
             topDrawable = AppCompatResources.getDrawable(mContext , R.drawable.ic_baseline_notifications_active_24);
        }else {
             topDrawable = AppCompatResources.getDrawable(mContext , R.drawable.ic_baseline_notifications_off_24);
        }
        buttonSetNotification = (Button) v.findViewById(R.id.buttonSetNotification);

        buttonSetNotification.setCompoundDrawablesWithIntrinsicBounds(null, topDrawable, null, null);
        buttonSetNotification.setOnClickListener(this);

        TextView textNameFill = (TextView) v.findViewById(R.id.textNameFill);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        /*
        reference = db.collection("users").document(userID);
        Task<DocumentSnapshot> documentSnapshot = reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                User userProfile = task.getResult().toObject(User.class);
                if(userProfile != null){
                    String name = userProfile.name;
                    textNameFill.setText(name);
                }else{
                    Toast.makeText(getActivity(), "Něco se pokazilo.", Toast.LENGTH_LONG).show();

                }
            }
        });
*/
        DatabaseReference myRef = database
                .getReference("Users").child(user.getUid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String name = userProfile.name;
                    String email = userProfile.getEmail();
                    textNameFill.setText(name+"\n"+email);
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

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.commit();
                startActivity(new Intent(getActivity(), NotificationSettingsActivity.class));

                break;

        }
    }
}