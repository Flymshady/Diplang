package cz.uhk.fim.cellar.diplang.users;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.User;


public class ProfileChartFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private String userID;
    private LinearLayout layoutChart;
    private int points;
    private Context mContext;
    private ViewPager2 viewPagerProfile;



    public ProfileChartFragment() {
        // Required empty public constructor
    }


    public static ProfileChartFragment newInstance(String param1, String param2) {
        ProfileChartFragment fragment = new ProfileChartFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile_chart, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        viewPagerProfile = (ViewPager2) v.findViewById(R.id.viewPagerProfile);
        layoutChart = (LinearLayout) v.findViewById(R.id.layoutChart);
        loadChart();


        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadChart();
    }




    private void loadChart() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(userID).child("Social").child("Friends");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                layoutChart.removeAllViews();
                for(DataSnapshot ds: snapshot.getChildren()) {
                    User friend = ds.getValue(User.class);

                    CardView cardview = new CardView(mContext);
                    LinearLayout.LayoutParams lpCard = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    lpCard.setMargins(15, 15, 15, 0);
                    cardview.setLayoutParams(lpCard);
                    cardview.setRadius(8);

                    LinearLayout llText = new LinearLayout(mContext);
                    llText.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams llTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    llText.setLayoutParams(llTextParams);
                    llText.setWeightSum(2f);
                    llText.setBackgroundResource(R.drawable.gradient_teal_light);

                    LinearLayout llNameEmail = new LinearLayout(mContext);
                    llNameEmail.setOrientation(LinearLayout.VERTICAL);
                    llNameEmail.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f));

                    TextView textViewName = new TextView(mContext);
                    TextView textViewEmail = new TextView(mContext);

                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 5, 0, 5);

                    textViewName.setText(friend.getName());
                    textViewName.setTextSize(18);
                    textViewName.setTextColor(Color.BLACK);
                    textViewName.setLayoutParams(lp);
                    textViewEmail.setText(friend.getEmail());
                    textViewEmail.setTextSize(16);
                    textViewEmail.setTextColor(Color.BLACK);
                    textViewEmail.setLayoutParams(lp);

                    llNameEmail.addView(textViewName);
                    llNameEmail.addView(textViewEmail);

                    LinearLayout.LayoutParams lpPoints = new LinearLayout.LayoutParams(0,
                            ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f);
                    lpPoints.setMargins(0, 5, 0, 5);
                    lpPoints.gravity= Gravity.CENTER;

                    TextView textViewPoints = new TextView(mContext);


                    DatabaseReference myRef = database.getReference("Users").child(friend.getUid());
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int total=0;
                            for(DataSnapshot datasnapshot:snapshot.child("Points").getChildren()) {
                                int value = ((Long)datasnapshot.getValue()).intValue();
                                total+=value;
                            }
                            points=total;
                            textViewPoints.setText(String.valueOf(points));
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(mContext, "Něco se pokazilo.", Toast.LENGTH_LONG).show();
                        }
                    });

                    textViewPoints.setTextSize(20);
                    textViewPoints.setTypeface(Typeface.DEFAULT_BOLD);
                    textViewPoints.setTextColor(Color.BLACK);
                    textViewPoints.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    textViewPoints.setLayoutParams(lp);

                    llText.addView(llNameEmail);
                    llText.addView(textViewPoints);
                    cardview.addView(llText);
                    layoutChart.addView(cardview);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}