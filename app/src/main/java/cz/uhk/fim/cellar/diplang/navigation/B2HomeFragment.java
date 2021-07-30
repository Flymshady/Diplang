package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import cz.uhk.fim.cellar.diplang.lessons.Lesson2Activity;
import cz.uhk.fim.cellar.diplang.lessons.Lesson3Activity;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment v HomeFragment pro lekce úrovně B2
 */
public class B2HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textLessonName1,textLessonName2, textLessonName3, textLessonName4, textLessonName5, textLessonName6, textLessonName7;
    private TextView textLessonPoints1, textLessonPoints2, textLessonPoints3, textLessonPoints4, textLessonPoints5, textLessonPoints6, textLessonPoints7;
    private SharedPreferences sp;
    private Context mContext;
    private CardView startLesson1, startLesson2, startLesson3, startLesson4, startLesson5, startLesson6, startLesson7;
    private ImageView starLesson1, starLesson2, starLesson3, starLesson4, starLesson5, starLesson6, starLesson7;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Lesson lesson1, resultLesson1, lesson2, resultLesson2, lesson3, resultLesson3;
    FirebaseUser user;
    private int lesson1Results, lesson2Results, lesson3Results, lesson1HighScoreResults, lesson2HighScoreResults, lesson3HighScoreResults;
    private boolean loadedL1, loadedL2, loadedL3;

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
        lesson2 = new Lesson();
        resultLesson2 = new Lesson();
        lesson3 = new Lesson();
        resultLesson3 = new Lesson();
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
        startLesson1 = (CardView) v.findViewById(R.id.startLesson1);
        startLesson2 = (CardView) v.findViewById(R.id.startLesson2);
        startLesson3 = (CardView) v.findViewById(R.id.startLesson3);
        startLesson4 = (CardView) v.findViewById(R.id.startLesson4);
        startLesson5 = (CardView) v.findViewById(R.id.startLesson5);
        startLesson6 = (CardView) v.findViewById(R.id.startLesson6);
        startLesson7 = (CardView) v.findViewById(R.id.startLesson7);
        starLesson1 = (ImageView) v.findViewById(R.id.starLesson1);
        starLesson2 = (ImageView) v.findViewById(R.id.starLesson2);
        starLesson3 = (ImageView) v.findViewById(R.id.starLesson3);
        starLesson4 = (ImageView) v.findViewById(R.id.starLesson4);
        starLesson5 = (ImageView) v.findViewById(R.id.starLesson5);
        starLesson6 = (ImageView) v.findViewById(R.id.starLesson6);
        starLesson7 = (ImageView) v.findViewById(R.id.starLesson7);

        loadLessonsData();

        startLesson1.setOnClickListener(this);
        startLesson2.setOnClickListener(this);
        startLesson3.setOnClickListener(this);
        startLesson4.setOnClickListener(this);
        startLesson5.setOnClickListener(this);
        startLesson6.setOnClickListener(this);
        startLesson7.setOnClickListener(this);

        return v;
    }

    /**
     * Načtení dat z databáze
     */
    private void loadLessonsData() {
        /**
         * Načtení parametrů o lekcích
         */
        DatabaseReference myRefLesson1 = database
                .getReference("Lessons");
        myRefLesson1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                lesson1 = dataSnapshot.child("Lesson1").child("LessonParams").getValue(Lesson.class);
                if(lesson1!=null){
                    textLessonName1.setText(lesson1.getName());
                    loadedL1=true;
                }else{
                    loadedL1=false;
                }
                lesson2 = dataSnapshot.child("Lesson2").child("LessonParams").getValue(Lesson.class);
                if(lesson2!=null){
                    textLessonName2.setText(lesson2.getName());
                    loadedL2=true;
                }else{
                    loadedL2=false;
                }
                lesson3 = dataSnapshot.child("Lesson3").child("LessonParams").getValue(Lesson.class);
                if(lesson3!=null){
                    textLessonName3.setText(lesson3.getName());
                    loadedL3=true;
                }else{
                    loadedL3=false;
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                loadedL1=false;
                loadedL2=false;
                loadedL3=false;
            }
        });

        DatabaseReference myRefUserLesson = database
                .getReference("UserTasks")
                .child(user.getUid());
        myRefUserLesson.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /**
                 * Načtení uživatelových výsledků první lekce
                 */
                resultLesson1 = dataSnapshot.child("Lesson1")
                        .child("Results").getValue(Lesson.class);
                if(resultLesson1!=null){
                    textLessonPoints1.setText(Integer.toString(resultLesson1.getDipsGained())+" / " + resultLesson1.getPointsTotal()
                    + ", Nejvyšší skóre: "+ resultLesson1.getHighScore());
                    if(resultLesson1.getDipsGained()==resultLesson1.getPointsTotal()){
                        starLesson1.setImageResource(R.drawable.star_purple_full);
                    } else if (resultLesson1.getDipsGained()==0) {
                        starLesson1.setImageResource(R.drawable.star_purple_border);
                    }else{
                        starLesson1.setImageResource(R.drawable.star_purple_half);
                    }
                    lesson1Results=(resultLesson1.getDipsGained());
                    lesson1HighScoreResults = resultLesson1.getHighScore();
                }
                /**
                 * Načtení uživatelových výsledků druhé lekce
                 */
                resultLesson2 = dataSnapshot.child("Lesson2")
                        .child("Results").getValue(Lesson.class);
                if(resultLesson2!=null){
                    textLessonPoints2.setText(Integer.toString(resultLesson2.getDipsGained())+" / " + resultLesson2.getPointsTotal()
                            + ", Nejvyšší skóre: "+ resultLesson2.getHighScore());
                    if(resultLesson2.getDipsGained()==resultLesson2.getPointsTotal()){
                        starLesson2.setImageResource(R.drawable.star_purple_full);
                    } else if (resultLesson2.getDipsGained()==0) {
                        starLesson2.setImageResource(R.drawable.star_purple_border);
                    }else{
                        starLesson2.setImageResource(R.drawable.star_purple_half);
                    }
                    lesson2Results=(resultLesson2.getDipsGained());
                    lesson2HighScoreResults = resultLesson2.getHighScore();
                }

                /**
                 * Načtení uživatelových výsledků třetí lekce
                 */
                resultLesson3= dataSnapshot.child("Lesson3")
                        .child("Results").getValue(Lesson.class);
                if(resultLesson3!=null){
                    textLessonPoints3.setText(Integer.toString(resultLesson3.getDipsGained())+" / " + resultLesson3.getPointsTotal()
                            + ", Nejvyšší skóre: "+ resultLesson3.getHighScore());
                    if(resultLesson3.getDipsGained()==resultLesson3.getPointsTotal()){
                        starLesson3.setImageResource(R.drawable.star_purple_full);
                    } else if (resultLesson3.getDipsGained()==0) {
                        starLesson3.setImageResource(R.drawable.star_purple_border);
                    }else{
                        starLesson3.setImageResource(R.drawable.star_purple_half);
                    }
                    lesson3Results=(resultLesson3.getDipsGained());
                    lesson3HighScoreResults = resultLesson3.getHighScore();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                loadedL1=false;
                loadedL2=false;
                loadedL3=false;
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startLesson1:
                /**
                 * Nastavení parametrů první lekce
                 */
                if(loadedL1) {
                    try {
                        startActivity(new Intent(this.getActivity(), Lesson1Activity.class)
                                .putExtra("level", lesson1.getLevel())
                                .putExtra("number", lesson1.getNumber())
                                .putExtra("name", lesson1.getName())
                                .putExtra("pointsTotal", lesson1.getPointsTotal())
                                .putExtra("lessonResults", lesson1Results)
                                .putExtra("highScore", lesson1HighScoreResults)

                        );
                    } finally {
                        getActivity().finish();
                    }
                }else{
                    Toast.makeText(getContext(), "Data lekce nebyly načteny. Připojte se k síti a opakujte pokus.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.startLesson2:
                /**
                 * Nastavení parametrů druhé lekce
                 */
                if(loadedL2) {
                    try {
                        startActivity(new Intent(this.getActivity(), Lesson2Activity.class)
                                .putExtra("level", lesson2.getLevel())
                                .putExtra("number", lesson2.getNumber())
                                .putExtra("name", lesson2.getName())
                                .putExtra("pointsTotal", lesson2.getPointsTotal())
                                .putExtra("lessonResults", lesson2Results)
                                .putExtra("highScore", lesson2HighScoreResults)

                        );
                    } finally {
                        getActivity().finish();
                    }
                }else{
                    Toast.makeText(getContext(), "Data lekce nebyly načteny. Připojte se k síti a opakujte pokus.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.startLesson3:
                /**
                 * Nastavení parametrů třetí lekce
                 */
                if(loadedL3) {
                    try {
                        startActivity(new Intent(this.getActivity(), Lesson3Activity.class)
                                .putExtra("level", lesson3.getLevel())
                                .putExtra("number", lesson3.getNumber())
                                .putExtra("name", lesson3.getName())
                                .putExtra("pointsTotal", lesson3.getPointsTotal())
                                .putExtra("lessonResults", lesson3Results)
                                .putExtra("highScore", lesson3HighScoreResults)

                        );
                    } finally {
                        getActivity().finish();
                    }
                }else {
                    Toast.makeText(getContext(), "Data lekce nebyly načteny. Připojte se k síti a opakujte pokus.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.startLesson4:
                Toast.makeText(getContext(), "Tato lekce není připravena", Toast.LENGTH_LONG).show();
                break;
            case R.id.startLesson5:
                Toast.makeText(getContext(), "Tato lekce není připravena", Toast.LENGTH_LONG).show();
                break;
            case R.id.startLesson6:
                Toast.makeText(getContext(), "Tato lekce není připravena", Toast.LENGTH_LONG).show();
                break;
            case R.id.startLesson7:
                Toast.makeText(getContext(), "Tato lekce není připravena", Toast.LENGTH_LONG).show();
                break;
        }
    }
}