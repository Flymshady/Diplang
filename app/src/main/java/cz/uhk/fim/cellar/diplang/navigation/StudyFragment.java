package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.PhrasesActivity;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.SpeechActivity;
import cz.uhk.fim.cellar.diplang.TheoryLesson2Activity;
import cz.uhk.fim.cellar.diplang.TheoryLesson3Activity;
import cz.uhk.fim.cellar.diplang.TranslatorActivity;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment studijní sekce v NavigationActivity
 */
public class StudyFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user;
    private Button buttonSpeechActivity, buttonTranslatorActivity, buttonPhrasesActivity;
    private LinearLayout layoutStudy;

    public StudyFragment() {
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
        View v = inflater.inflate(R.layout.fragment_study, container, false);

        buttonSpeechActivity = (Button) v.findViewById(R.id.buttonSpeechActivity);
        buttonSpeechActivity.setOnClickListener(this);
        buttonTranslatorActivity = (Button) v.findViewById(R.id.buttonTranslatorActivity);
        buttonTranslatorActivity.setOnClickListener(this);
        buttonPhrasesActivity = (Button) v.findViewById(R.id.buttonPhrasesActivity);
        buttonPhrasesActivity.setOnClickListener(this);
        layoutStudy = (LinearLayout) v.findViewById(R.id.layoutStudy);

        loadData();

        return v;
    }

    /**
     * Načtebí dat z databáze
     */
    private void loadData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        /**
         * Načtení uložených teoretickýh materiálů
         */
        DatabaseReference myRefTask1 = database.getReference("UserTheory").child(user.getUid());
        myRefTask1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String lessonName = dataSnapshot.getKey().toString();
                    ImageButton btnTheory = new ImageButton(getActivity());
                    LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(200,
                            0, 1f);
                    lpBtn.setMargins(5,10,5,0);
                    if(lessonName.equals("Lesson2")){
                        btnTheory.setBackgroundResource(R.drawable.ic_baseline_looks_two_24);
                    }else if(lessonName.equals("Lesson3")){
                        btnTheory.setBackgroundResource(R.drawable.ic_baseline_looks_3_24);
                    }
                    btnTheory.setLayoutParams(lpBtn);
                    btnTheory.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(lessonName.equals("Lesson2")){
                            startActivity(new Intent(getActivity(), TheoryLesson2Activity.class));
                            }
                            else if(lessonName.equals("Lesson3")){
                                startActivity(new Intent(getActivity(), TheoryLesson3Activity.class));
                            }
                        }
                    });
                    layoutStudy.addView(btnTheory);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    /**
     * Nastavení buttonu
     * @param view View
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSpeechActivity:
                try {
                    startActivity(new Intent(getActivity(), SpeechActivity.class));
                } finally {
                    getActivity().finish();
                }
                break;
            case R.id.buttonTranslatorActivity:
                /**
                 * Přechod na aktivitu překladače
                 */
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.commit();
                startActivity(new Intent(getActivity(), TranslatorActivity.class));
                break;
            case R.id.buttonPhrasesActivity:
                /**
                 * Přechod na aktivitu uložených frází
                 */
                startActivity(new Intent(getActivity(), PhrasesActivity.class));
                break;

        }
    }
}