package cz.uhk.fim.cellar.diplang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class B2HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textLessonName1,textLessonName2, textLessonName3, textLessonName4, textLessonName5, textLessonName6, textLessonName7;
    private TextView textLessonPoints1, textLessonPoints2, textLessonPoints3, textLessonPoints4, textLessonPoints5, textLessonPoints6, textLessonPoints7;
    private SharedPreferences sp;
    private ImageView startLesson1, startLesson2, startLesson3, startLesson4, startLesson5, startLesson6, startLesson7;
    private ImageView downloadLesson1, downloadLesson2, downloadLesson3, downloadLesson4, downloadLesson5, downloadLesson6, downloadLesson7;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b2_home, container, false);
        sp = this.getActivity().getSharedPreferences("MyUser", Context.MODE_PRIVATE);

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

        startLesson1.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startLesson1:
                try {
                    startActivity(new Intent(this.getActivity(), LessonActivity.class)
                            .putExtra("level", "B2")
                            .putExtra("lesson", 1)
                            .putExtra("name", sp.getString("name",""))

                    );
                }finally {
                    getActivity().finish();
                }

                break;
        }
    }
}