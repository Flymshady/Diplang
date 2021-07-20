package cz.uhk.fim.cellar.diplang.lessons;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.R;
import cz.uhk.fim.cellar.diplang.classes.User;

/**
 * @author Štěpán Cellar - FIM UHK
 * Aktivita první lekce pro úroveň B1
 */
public class Lesson1B1Activity extends AppCompatActivity {

    private String level;
    private int numberOfLesson;
    private String userName;
    private TabLayout tabs;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private int pointsTotal, lessonResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1b1);

        /**
         * Předání parametrů o lekci do ViewModelu
         */
        viewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        viewModel.getDipPoints().observe(this, dipPoints -> {
        });
        viewModel.setDipPoints(0);
        level = getIntent().getStringExtra("level");
        viewModel.setLevel(level);
        numberOfLesson = getIntent().getIntExtra("number", 1);
        viewModel.setLesson(numberOfLesson);
        userName = getIntent().getStringExtra("name");
        viewModel.setUsername(userName);
        pointsTotal = getIntent().getIntExtra("pointsTotal", 1);
        viewModel.setPointsTotal(pointsTotal);
        lessonResults = getIntent().getIntExtra("lessonResults", -1);
        viewModel.setLessonResults(lessonResults);

        tabs = (TabLayout) findViewById(R.id.tabsL1B1);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerL1B1);
        /**
         * Zamezení používání "swipe" pro přesun
         */
        viewPager2.setUserInputEnabled(false);
        /**
         * Nastavení adapteru a tab layoutu
         */
        Lesson1B1ViewPagerAdapter adapter = new Lesson1B1ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(""+(position + 1));
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