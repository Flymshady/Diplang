package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.Lesson;
import cz.uhk.fim.cellar.diplang.lessons.Lesson1Activity;
import cz.uhk.fim.cellar.diplang.lessons.Lesson1B1Activity;


public class B1HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textLessonName1B1,textLessonName2B1, textLessonName3B1, textLessonName4B1, textLessonName5B1, textLessonName6B1, textLessonName7B1;
    private TextView textLessonPoints1B1, textLessonPoints2B1, textLessonPoints3B1, textLessonPoints4B1, textLessonPoints5B1, textLessonPoints6B1, textLessonPoints7B1;
    private SharedPreferences sp;
    private CardView startLesson1B1, startLesson2B1, startLesson3B1, startLesson4B1, startLesson5B1, startLesson6B1, startLesson7B1;
    private Context mContext;
    private Lesson lesson1, resultLesson1;
    private ImageView starL1B1;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int lesson1Results;
    
    public B1HomeFragment() {
        // Required empty public constructor
    }

    public static B1HomeFragment newInstance(String param1, String param2) {
        B1HomeFragment fragment = new B1HomeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
        View v = inflater.inflate(R.layout.fragment_b1_home, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        sp = this.getActivity().getSharedPreferences("MyUser", Context.MODE_PRIVATE);

        lesson1 = new Lesson();
        resultLesson1 = new Lesson();


        
        textLessonName1B1 = (TextView) v.findViewById(R.id.textLessonName1B1);
        textLessonName2B1 = (TextView) v.findViewById(R.id.textLessonName2B1);
        textLessonName3B1 = (TextView) v.findViewById(R.id.textLessonName3B1);
        textLessonName4B1 = (TextView) v.findViewById(R.id.textLessonName4B1);
        textLessonName5B1 = (TextView) v.findViewById(R.id.textLessonName5B1);
        textLessonName6B1 = (TextView) v.findViewById(R.id.textLessonName6B1);
        textLessonName7B1 = (TextView) v.findViewById(R.id.textLessonName7B1);
        textLessonPoints1B1 = (TextView) v.findViewById(R.id.textLessonPoints1B1);
        textLessonPoints2B1 = (TextView) v.findViewById(R.id.textLessonPoints2B1);
        textLessonPoints3B1 = (TextView) v.findViewById(R.id.textLessonPoints3B1);
        textLessonPoints4B1 = (TextView) v.findViewById(R.id.textLessonPoints4B1);
        textLessonPoints5B1 = (TextView) v.findViewById(R.id.textLessonPoints5B1);
        textLessonPoints6B1 = (TextView) v.findViewById(R.id.textLessonPoints6B1);
        textLessonPoints7B1 = (TextView) v.findViewById(R.id.textLessonPoints7B1);
        startLesson1B1 = (CardView) v.findViewById(R.id.startLesson1B1);
        startLesson2B1 = (CardView) v.findViewById(R.id.startLesson2B1);
        startLesson3B1 = (CardView) v.findViewById(R.id.startLesson3B1);
        startLesson4B1 = (CardView) v.findViewById(R.id.startLesson4B1);
        startLesson5B1 = (CardView) v.findViewById(R.id.startLesson5B1);
        startLesson6B1 = (CardView) v.findViewById(R.id.startLesson6B1);
        startLesson7B1 = (CardView) v.findViewById(R.id.startLesson7B1);
        starL1B1 = (ImageView) v.findViewById(R.id.starL1B1);

        /*
        if(isNetworkAvailable()) {

        }
         */
        loadLessonsData();

        startLesson1B1.setOnClickListener(this);

        return v;
    }

    private void loadLessonsData() {
        DatabaseReference myRefLesson1 = database
                .getReference("Lessons").child("Lesson1B1").child("LessonParams");
        myRefLesson1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lesson1 = dataSnapshot.getValue(Lesson.class);
                if(lesson1!=null){
                    textLessonName1B1.setText(lesson1.getName());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefUserLesson = database
                .getReference("UserTasks")
                .child(user.getUid()).child("Lesson1B1")
                .child("Results");
        myRefUserLesson.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                resultLesson1 = dataSnapshot.getValue(Lesson.class);
                if(resultLesson1!=null){
                    textLessonPoints1B1.setText(Integer.toString(resultLesson1.getDipsGained())+" / " + resultLesson1.getPointsTotal());
                    if(resultLesson1.getDipsGained()==resultLesson1.getPointsTotal()){
                        starL1B1.setImageResource(R.drawable.star_purple_full);
                    } else if (resultLesson1.getDipsGained()==0) {
                        starL1B1.setImageResource(R.drawable.star_purple_border);
                    }else{
                        starL1B1.setImageResource(R.drawable.star_purple_half);
                    }
                    lesson1Results = resultLesson1.getDipsGained();
                }
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startLesson1B1:
                try {
                    startActivity(new Intent(this.getActivity(), Lesson1B1Activity.class)
                            .putExtra("level", lesson1.getLevel())
                            .putExtra("number", lesson1.getNumber())
                            .putExtra("name", lesson1.getName())
                            .putExtra("pointsTotal", lesson1.getPointsTotal())
                            .putExtra("lessonResults", lesson1Results)

                    );
                } finally {
                    getActivity().finish();
                }
                break;
        }
    }
}