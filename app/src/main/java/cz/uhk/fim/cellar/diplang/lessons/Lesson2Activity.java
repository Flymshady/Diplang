package cz.uhk.fim.cellar.diplang.lessons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.User;

public class Lesson2Activity extends AppCompatActivity {

    private String level;
    private int numberOfLesson;
    private User user;
    private String userName;
    private TabLayout tabs;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private int pointsTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);

        viewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        viewModel.getDipPoints().observe(this, dipPoints -> {

        });
        viewModel.setDipPoints(0);

        level = getIntent().getStringExtra("level");
        viewModel.setLevel(level);
        numberOfLesson = getIntent().getIntExtra("number", 2);
        viewModel.setLesson(numberOfLesson);
        userName = getIntent().getStringExtra("name");
        viewModel.setUsername(userName);
        pointsTotal = getIntent().getIntExtra("pointsTotal", 1);
        viewModel.setPointsTotal(pointsTotal);

        tabs = (TabLayout) findViewById(R.id.tabsL2);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerL2);
        //swipe disabled
        viewPager2.setUserInputEnabled(false);


        Lesson2ViewPagerAdapter adapter = new Lesson2ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText("Page "+(position + 1));
                tab.view.setClickable(false);
            }
        }).attach();





    }

    public void moveNext(View view) {
        //it doesn't matter if you're already in the last item
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
    }

    public void movePrevious(View view) {
        //it doesn't matter if you're already in the first item
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
    }
}