package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.cellar.diplang.Classes.User;
import cz.uhk.fim.cellar.diplang.LessonFragments.PageFragment1;
import cz.uhk.fim.cellar.diplang.LessonFragments.PageFragment2;
import cz.uhk.fim.cellar.diplang.LessonFragments.PageFragment3;

public class LessonActivity extends AppCompatActivity {

    private String level;
    private int numberOfLesson;
    private User user;
    private String userName;
    private TabLayout tabs;
    private ViewPager2 viewPager2;


//https://www.youtube.com/watch?v=xd7SYulEWuc&ab_channel=Code2Develop
 //   https://www.youtube.com/watch?v=iJpB5ju3tN8&ab_channel=CodeDocuDeveloperC%23AspNetAngular

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        level = getIntent().getStringExtra("level");
        numberOfLesson = getIntent().getIntExtra("lesson", 1);
        userName = getIntent().getStringExtra("name");

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);



        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText("Tab "+ (position + 1));
            }
        }).attach();

    }
}