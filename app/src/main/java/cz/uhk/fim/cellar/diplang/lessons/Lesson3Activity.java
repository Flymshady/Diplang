package cz.uhk.fim.cellar.diplang.lessons;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
 * Aktivita třetí lekce pro úroveň B2
 */
public class Lesson3Activity extends AppCompatActivity {

    private String level;
    private int numberOfLesson;
    private User user;
    private String userName;
    private TabLayout tabs;
    private ViewPager2 viewPager2;
    private LessonViewModel viewModel;
    private int pointsTotal, lessonResults, highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);

        /**
         * Předání parametrů o lekci do ViewModelu
         */
        viewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        viewModel.getDipPoints().observe(this, dipPoints -> {

        });
        viewModel.setDipPoints(0);
        level = getIntent().getStringExtra("level");
        viewModel.setLevel(level);
        numberOfLesson = getIntent().getIntExtra("number", 3);
        viewModel.setLesson(numberOfLesson);
        userName = getIntent().getStringExtra("name");
        viewModel.setUsername(userName);
        pointsTotal = getIntent().getIntExtra("pointsTotal", 1);
        viewModel.setPointsTotal(pointsTotal);
        lessonResults = getIntent().getIntExtra("lessonResults", -1);
        viewModel.setLessonResults(lessonResults);
        highScore = getIntent().getIntExtra("highScore", -1);
        viewModel.setHighScore(highScore);

        tabs = (TabLayout) findViewById(R.id.tabsL3);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPagerL3);
        /**
         * Zamezení používání "swipe" pro přechod
         */
        viewPager2.setUserInputEnabled(false);
        /**
         * Nastavení adapteru a tab layoutu
         */
        Lesson3ViewPagerAdapter adapter = new Lesson3ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(""+(position + 1));
                tab.view.setClickable(false);
            }
        }).attach();

    }

    /** Skrytí klávesnice při "kliknutí" mimo editovatelné textové pole **/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
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