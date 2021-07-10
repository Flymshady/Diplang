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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import cz.uhk.fim.cellar.diplang.classes.Lesson;
import cz.uhk.fim.cellar.diplang.lessons.LessonViewModel;
import cz.uhk.fim.cellar.diplang.navigation.NavigationActivity;
import cz.uhk.fim.cellar.diplang.R;


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
        String dipsText = "dips";
        if(viewModel.getDipPoints().getValue()==1){
            dipsText = "dip";
        }
        pointsFinal.setText("Výsledek lekce je:\n" +
                viewModel.getDipPoints().getValue().toString()
                + " dips\nz celkových\n" +
                viewModel.getPointsTotal().getValue().toString() + " "+ dipsText);
        buttonFinishLesson = (Button) v.findViewById(R.id.buttonFinishLesson);
        buttonFinishLesson.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonFinishLesson:
                saveUserLesson();
                try {
                    startActivity(new Intent( getActivity(), NavigationActivity.class));
                } finally {
                    getActivity().finish();
                }
                break;
        }
    }

    private void saveUserLesson() {
        lesson = new Lesson();
        lesson.setDipsGained(viewModel.getDipPoints().getValue());
        lesson.setLevel(viewModel.getLevel().getValue());
        lesson.setNumber(viewModel.getLesson().getValue());
        lesson.setPointsTotal(viewModel.getPointsTotal().getValue());
        FirebaseDatabase.getInstance().getReference("UserTasks")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Lesson1").child("Results")
                .setValue(lesson);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())
                .child("Points").child("lesson1")
                .setValue(lesson.getDipsGained());
    }
}