package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cz.uhk.fim.cellar.diplang.B2PlusHomeFragment;
import cz.uhk.fim.cellar.diplang.LessonViewModel;
import cz.uhk.fim.cellar.diplang.NavigationActivity;
import cz.uhk.fim.cellar.diplang.NotificationSettingsActivity;
import cz.uhk.fim.cellar.diplang.R;


public class PageFragmentFinal extends Fragment implements View.OnClickListener {

    private TextView pointsFinal;
    private Button buttonFinishLesson;
    private LessonViewModel viewModel;

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


        pointsFinal = (TextView) v.findViewById(R.id.pointsFinal);
        pointsFinal.setText(viewModel.getDipPoints().getValue().toString());
        buttonFinishLesson = (Button) v.findViewById(R.id.buttonFinishLesson);
        buttonFinishLesson.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonFinishLesson:
                try {
                    startActivity(new Intent( getActivity(), NavigationActivity.class));
                } finally {
                    getActivity().finish();
                }
                break;
        }
    }
}