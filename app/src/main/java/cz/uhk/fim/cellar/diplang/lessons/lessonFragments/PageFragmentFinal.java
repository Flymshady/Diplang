package cz.uhk.fim.cellar.diplang.lessons.lessonFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.StringValue;

import cz.uhk.fim.cellar.diplang.classes.Lesson;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;
import cz.uhk.fim.cellar.diplang.R;

/**
 * @author Štěpán Cellar - FIM UHK
 * Fragment závěrečné stránky lekcí
 */
public class PageFragmentFinal extends Fragment implements View.OnClickListener {

    private TextView pointsFinal;
    private Button buttonFinishLesson;
    private ImageView finalStar;
    private LessonViewModel viewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Lesson lesson;
    public PageFragmentFinal() {
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
        View v = inflater.inflate(R.layout.fragment_page_final, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        finalStar = (ImageView) v.findViewById(R.id.finalStar);
        pointsFinal = (TextView) v.findViewById(R.id.pointsFinal);
        /**
         * Nastavení textu pro popis získaných bodů
         */


        String dipsText = "dips";
        if(viewModel.getDipPoints().getValue()==1){
            dipsText = "dip";
        }
        int highScore = viewModel.getHighScore().getValue();
        if(highScore<viewModel.getDipPoints().getValue()){
            highScore=viewModel.getDipPoints().getValue();
        }
        String highScoreText = "";
        if(highScore!=-1){
            highScoreText = "Nejvyšší skóre: "+ String.valueOf(highScore)+" dips";
        }
        String previousResults="";
        int lessonResults = viewModel.getLessonResults().getValue();
        if(lessonResults!=-1){
            previousResults="Předchozí pokus: "+String.valueOf(lessonResults)+" dips";
        }
        pointsFinal.setText("Výsledek lekce je:\n" +
                viewModel.getDipPoints().getValue().toString()
                + " dips\nz celkových\n" +
                viewModel.getPointsTotal().getValue().toString() + " "+ dipsText
                + "\n" + previousResults
                + "\n" + highScoreText
        );

        buttonFinishLesson = (Button) v.findViewById(R.id.buttonFinishLesson);
        buttonFinishLesson.setOnClickListener(this);

        /**
         * Nastavení vzhledu "hvězdy" pro znázornění úspěšnosti v lekci
         */
        if(viewModel.getDipPoints().getValue() == viewModel.getPointsTotal().getValue()){
            finalStar.setBackgroundResource(R.drawable.star_purple_full);
        }
        else if(viewModel.getDipPoints().getValue()==0){
            finalStar.setBackgroundResource(R.drawable.star_purple_border);
        }else{
            finalStar.setBackgroundResource(R.drawable.star_purple_half);
        }

        return v;
    }

    /**
     * Nastavení buttonu
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonFinishLesson:
                /**
                 * Zavolání metody pro uložení výsledků lekce a přesměrování na navigační stránku
                 */
                saveUserLesson();
                try {
                    startActivity(new Intent( getActivity(), NavigationActivity.class));
                } finally {
                    getActivity().finish();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        saveUserLesson();
        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        /**
         * Nastavení textu pro popis získaných bodů
         */

        String dipsText = "dips";
        if(viewModel.getDipPoints().getValue()==1){
            dipsText = "dip";
        }
        int highScore = viewModel.getHighScore().getValue();
        if(highScore<viewModel.getDipPoints().getValue()){
            highScore=viewModel.getDipPoints().getValue();
        }
        String highScoreText = "";
        if(highScore!=-1){
            highScoreText = "Nejvyšší skóre: "+ String.valueOf(highScore)+" dips";
        }
        String previousResults="";
        int lessonResults = viewModel.getLessonResults().getValue();
        if(lessonResults!=-1){
            previousResults="Předchozí pokus: "+String.valueOf(lessonResults)+" dips";
        }
        pointsFinal.setText("Výsledek lekce je:\n" +
                viewModel.getDipPoints().getValue().toString()
                + " dips\nz celkových\n" +
                viewModel.getPointsTotal().getValue().toString() + " "+ dipsText
                + "\n" + previousResults
                + "\n" + highScoreText
        );
        /**
         * Nastavení vzhledu "hvězdy" pro znázornění úspěšnosti v lekci
         */
        if(viewModel.getDipPoints().getValue() == viewModel.getPointsTotal().getValue()){
            finalStar.setBackgroundResource(R.drawable.star_purple_full);
        }
        else if(viewModel.getDipPoints().getValue()==0){
            finalStar.setBackgroundResource(R.drawable.star_purple_border);
        }else{
            finalStar.setBackgroundResource(R.drawable.star_purple_half);
        }
        finalStar.animate()
                .setDuration(2000).rotationYBy(360f).start();
    }

    /**
     * Uloží výsledky lekce na základě úrovně lekce a čísla lekce
     * Uloží získané body uživetele
     */
    private void saveUserLesson() {
        lesson = new Lesson();
        lesson.setDipsGained(viewModel.getDipPoints().getValue());
        lesson.setLevel(viewModel.getLevel().getValue());
        lesson.setNumber(viewModel.getLesson().getValue());
        lesson.setPointsTotal(viewModel.getPointsTotal().getValue());
        if(viewModel.getHighScore().getValue()<viewModel.getDipPoints().getValue()){
            lesson.setHighScore(viewModel.getDipPoints().getValue());
        }else {
            lesson.setHighScore(viewModel.getHighScore().getValue());
        }

        if(lesson.getNumber()==1 && lesson.getLevel().equals("B2")){
            FirebaseDatabase.getInstance().getReference("UserTasks")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Lesson1").child("Results")
                    .setValue(lesson);

            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Points").child("lesson1")
                    .setValue(lesson.getDipsGained());
        }
        else if(lesson.getNumber()==1 && lesson.getLevel().equals("B1")){
            FirebaseDatabase.getInstance().getReference("UserTasks")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Lesson1B1").child("Results")
                    .setValue(lesson);

            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Points").child("lesson1b1")
                    .setValue(lesson.getDipsGained());
        }
        else if(lesson.getNumber()==2 && lesson.getLevel().equals("B2")){
            FirebaseDatabase.getInstance().getReference("UserTasks")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Lesson2").child("Results")
                    .setValue(lesson);

            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Points").child("lesson2")
                    .setValue(lesson.getDipsGained());
        }
        else if(lesson.getNumber()==3 && lesson.getLevel().equals("B2")){
            FirebaseDatabase.getInstance().getReference("UserTasks")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Lesson3").child("Results")
                    .setValue(lesson);

            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                    .child("Points").child("lesson3")
                    .setValue(lesson.getDipsGained());
        }
    }
}