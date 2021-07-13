package cz.uhk.fim.cellar.diplang.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import cz.uhk.fim.cellar.diplang.PhrasesActivity;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.SpeechActivity;
import cz.uhk.fim.cellar.diplang.TranslatorActivity;

public class StudyFragment extends Fragment implements View.OnClickListener {
    private Context mContext;

    private Button buttonSpeechActivity, buttonTranslatorActivity, buttonPhrasesActivity;

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


        return v;
    }

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

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.commit();
                startActivity(new Intent(getActivity(), TranslatorActivity.class));

                break;
            case R.id.buttonPhrasesActivity:
                startActivity(new Intent(getActivity(), PhrasesActivity.class));
                break;

        }
    }
}