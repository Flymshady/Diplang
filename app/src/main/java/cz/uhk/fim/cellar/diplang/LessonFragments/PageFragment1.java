package cz.uhk.fim.cellar.diplang.LessonFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import cz.uhk.fim.cellar.diplang.LessonViewModel;
import cz.uhk.fim.cellar.diplang.R;

public class PageFragment1 extends Fragment implements View.OnClickListener {

    private Button btnNextToP2;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private TextView task1L1P1, task2L1P1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public PageFragment1() {
        // Required empty public constructor
    }


    public static PageFragment1 newInstance(String param1, String param2) {
        PageFragment1 fragment = new PageFragment1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View v = inflater.inflate(R.layout.fragment_page1, container, false);

        task1L1P1 = (TextView) v.findViewById(R.id.task1L1P1);
        task2L1P1 = (TextView) v.findViewById(R.id.task2L1P1);

        viewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        loadData();

        btnNextToP2 = (Button) v.findViewById(R.id.btnNextToP2);
        btnNextToP2.setOnClickListener(this);

        viewPager2 = (ViewPager2) getActivity().findViewById(R.id.viewPager);

        return v;
    }

    private void loadData() {
        DocumentReference dfTask1 = db.document("/lessons/lesson1/pages/page1/tasks/task1");
        dfTask1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                task1L1P1.setText(value.getString("Text1"));
                task2L1P1.setText(value.getString("Text2"));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNextToP2:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

        }
    }
}