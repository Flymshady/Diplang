package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

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

import cz.uhk.fim.cellar.diplang.lessons.Lesson1Activity;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.Lesson;


public class B2HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textLessonName1,textLessonName2, textLessonName3, textLessonName4, textLessonName5, textLessonName6, textLessonName7;
    private TextView textLessonPoints1, textLessonPoints2, textLessonPoints3, textLessonPoints4, textLessonPoints5, textLessonPoints6, textLessonPoints7;
    private SharedPreferences sp;
    private Context mContext;
    private ImageView startLesson1, startLesson2, startLesson3, startLesson4, startLesson5, startLesson6, startLesson7;
    private ImageView downloadLesson1, downloadLesson2, downloadLesson3, downloadLesson4, downloadLesson5, downloadLesson6, downloadLesson7;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Lesson lesson1, resultLesson1;
    FirebaseUser user;

    public B2HomeFragment() {
        // Required empty public constructor
    }

    public static B2HomeFragment newInstance(String param1, String param2) {
        B2HomeFragment fragment = new B2HomeFragment();
        Bundle args = new Bundle();
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_b2_home, container, false);
        sp = this.getActivity().getSharedPreferences("MyUser", Context.MODE_PRIVATE);

        lesson1 = new Lesson();
        resultLesson1 = new Lesson();
        user = FirebaseAuth.getInstance().getCurrentUser();
        textLessonName1 = (TextView) v.findViewById(R.id.textLessonName1);
        textLessonName2 = (TextView) v.findViewById(R.id.textLessonName2);
        textLessonName3 = (TextView) v.findViewById(R.id.textLessonName3);
        textLessonName4 = (TextView) v.findViewById(R.id.textLessonName4);
        textLessonName5 = (TextView) v.findViewById(R.id.textLessonName5);
        textLessonName6 = (TextView) v.findViewById(R.id.textLessonName6);
        textLessonName7 = (TextView) v.findViewById(R.id.textLessonName7);
        textLessonPoints1 = (TextView) v.findViewById(R.id.textLessonPoints1);
        textLessonPoints2 = (TextView) v.findViewById(R.id.textLessonPoints2);
        textLessonPoints3 = (TextView) v.findViewById(R.id.textLessonPoints3);
        textLessonPoints4 = (TextView) v.findViewById(R.id.textLessonPoints4);
        textLessonPoints5 = (TextView) v.findViewById(R.id.textLessonPoints5);
        textLessonPoints6 = (TextView) v.findViewById(R.id.textLessonPoints6);
        textLessonPoints7 = (TextView) v.findViewById(R.id.textLessonPoints7);
        startLesson1 = (ImageView) v.findViewById(R.id.startLesson1);
        startLesson2 = (ImageView) v.findViewById(R.id.startLesson2);
        startLesson3 = (ImageView) v.findViewById(R.id.startLesson3);
        startLesson4 = (ImageView) v.findViewById(R.id.startLesson4);
        startLesson5 = (ImageView) v.findViewById(R.id.startLesson5);
        startLesson6 = (ImageView) v.findViewById(R.id.startLesson6);
        startLesson7 = (ImageView) v.findViewById(R.id.startLesson7);
        downloadLesson1 = (ImageView) v.findViewById(R.id.downloadLesson1);
        downloadLesson2 = (ImageView) v.findViewById(R.id.downloadLesson2);
        downloadLesson3 = (ImageView) v.findViewById(R.id.downloadLesson3);
        downloadLesson4 = (ImageView) v.findViewById(R.id.downloadLesson4);
        downloadLesson5 = (ImageView) v.findViewById(R.id.downloadLesson5);
        downloadLesson6 = (ImageView) v.findViewById(R.id.downloadLesson6);
        downloadLesson7 = (ImageView) v.findViewById(R.id.downloadLesson7);

        if(isNetworkAvailable()) {
            loadLessonsData();
        }
        startLesson1.setOnClickListener(this);
        
        

        return v;
    }

    private void loadLessonsData() {
        DatabaseReference myRefLesson1 = database
                .getReference("Lessons").child("Lesson1");
        myRefLesson1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lesson1 = dataSnapshot.getValue(Lesson.class);
                if(lesson1!=null){
                    textLessonName1.setText(lesson1.getName());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        DatabaseReference myRefUserLesson = database
                .getReference("UserTasks")
                .child(user.getUid()).child("Lesson1")
                .child("Results");
        myRefUserLesson.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                resultLesson1 = dataSnapshot.getValue(Lesson.class);
                if(resultLesson1!=null){
                    textLessonPoints1.setText(Integer.toString(resultLesson1.getDipsGained()));
                    if(resultLesson1.getDipsGained()==resultLesson1.getPointsTotal()){
                        startLesson1.setBackgroundResource(R.drawable.star_purple_full);
                    } else if (resultLesson1.getDipsGained()==0) {
                        startLesson1.setBackgroundResource(R.drawable.star_purple_border);
                    }else{
                        startLesson1.setBackgroundResource(R.drawable.star_purple_half);
                    }
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
        switch (view.getId()){
            case R.id.startLesson1:
                try {
                    startActivity(new Intent(this.getActivity(), Lesson1Activity.class)
                            .putExtra("level", "B2")
                            .putExtra("lesson", 1)
                            .putExtra("name", sp.getString("name",""))
                            .putExtra("pointsTotal", 10)

                    );
                }finally {
                    getActivity().finish();
                }

                break;
        }
    }
}