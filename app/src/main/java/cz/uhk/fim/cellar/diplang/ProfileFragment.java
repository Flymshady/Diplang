package cz.uhk.fim.cellar.diplang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private Button buttonLogout, buttonSetNotification;
    private FirebaseUser user;
    private String userID;
    private SharedPreferences sp;
    private Context mContext;
    private Drawable topDrawable;

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

        final TextView textNameFill = (TextView) v.findViewById(R.id.textNameFill);
        textNameFill.setText(this.getActivity().getSharedPreferences("MyUser", Context.MODE_PRIVATE).getString("name", ""));
/*
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
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
     /*
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


      */
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