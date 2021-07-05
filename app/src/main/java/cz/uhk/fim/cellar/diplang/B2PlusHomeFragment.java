package cz.uhk.fim.cellar.diplang;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class B2PlusHomeFragment extends Fragment {

    private TextView textLessonName1B2P,textLessonName2B2P, textLessonName3B2P, textLessonName4B2P, textLessonName5B2P, textLessonName6B2P, textLessonName7B2P;
    private TextView textLessonPoints1B2P, textLessonPoints2B2P, textLessonPoints3B2P, textLessonPoints4B2P, textLessonPoints5B2P, textLessonPoints6B2P, textLessonPoints7B2P;
    private SharedPreferences sp;
    private ImageView startLesson1B2P, startLesson2B2P, startLesson3B2P, startLesson4B2P, startLesson5B2P, startLesson6B2P, startLesson7B2P;
    private ImageView downloadLesson1B2P, downloadLesson2B2P, downloadLesson3B2P, downloadLesson4B2P, downloadLesson5B2P, downloadLesson6B2P, downloadLesson7B2P;

    public B2PlusHomeFragment() {
        // Required empty public constructor
    }

    public static B2PlusHomeFragment newInstance(String param1, String param2) {
        B2PlusHomeFragment fragment = new B2PlusHomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b2_plus_home, container, false);

        textLessonName1B2P = (TextView) v.findViewById(R.id.textLessonName1B2P);
        textLessonName2B2P = (TextView) v.findViewById(R.id.textLessonName2B2P);
        textLessonName3B2P = (TextView) v.findViewById(R.id.textLessonName3B2P);
        textLessonName4B2P = (TextView) v.findViewById(R.id.textLessonName4B2P);
        textLessonName5B2P = (TextView) v.findViewById(R.id.textLessonName5B2P);
        textLessonName6B2P = (TextView) v.findViewById(R.id.textLessonName6B2P);
        textLessonName7B2P = (TextView) v.findViewById(R.id.textLessonName7B2P);
        textLessonPoints1B2P = (TextView) v.findViewById(R.id.textLessonPoints1B2P);
        textLessonPoints2B2P = (TextView) v.findViewById(R.id.textLessonPoints2B2P);
        textLessonPoints3B2P = (TextView) v.findViewById(R.id.textLessonPoints3B2P);
        textLessonPoints4B2P = (TextView) v.findViewById(R.id.textLessonPoints4B2P);
        textLessonPoints5B2P = (TextView) v.findViewById(R.id.textLessonPoints5B2P);
        textLessonPoints6B2P = (TextView) v.findViewById(R.id.textLessonPoints6B2P);
        textLessonPoints7B2P = (TextView) v.findViewById(R.id.textLessonPoints7B2P);
        startLesson1B2P = (ImageView) v.findViewById(R.id.startLesson1B2P);
        startLesson2B2P = (ImageView) v.findViewById(R.id.startLesson2B2P);
        startLesson3B2P = (ImageView) v.findViewById(R.id.startLesson3B2P);
        startLesson4B2P = (ImageView) v.findViewById(R.id.startLesson4B2P);
        startLesson5B2P = (ImageView) v.findViewById(R.id.startLesson5B2P);
        startLesson6B2P = (ImageView) v.findViewById(R.id.startLesson6B2P);
        startLesson7B2P = (ImageView) v.findViewById(R.id.startLesson7B2P);
        downloadLesson1B2P = (ImageView) v.findViewById(R.id.downloadLesson1B2P);
        downloadLesson2B2P = (ImageView) v.findViewById(R.id.downloadLesson2B2P);
        downloadLesson3B2P = (ImageView) v.findViewById(R.id.downloadLesson3B2P);
        downloadLesson4B2P = (ImageView) v.findViewById(R.id.downloadLesson4B2P);
        downloadLesson5B2P = (ImageView) v.findViewById(R.id.downloadLesson5B2P);
        downloadLesson6B2P = (ImageView) v.findViewById(R.id.downloadLesson6B2P);
        downloadLesson7B2P = (ImageView) v.findViewById(R.id.downloadLesson7B2P);

        return v;
    }
}