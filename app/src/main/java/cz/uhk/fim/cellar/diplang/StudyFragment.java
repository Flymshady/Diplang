package cz.uhk.fim.cellar.diplang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StudyFragment extends Fragment implements View.OnClickListener {
    private Context mContext;

    private Button buttonSpeechActivity;

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

        }
    }
}