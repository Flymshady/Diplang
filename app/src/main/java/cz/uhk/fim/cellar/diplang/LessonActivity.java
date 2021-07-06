package cz.uhk.fim.cellar.diplang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    LinearLayout dotsLayout;
    SliderAdapter adapter;
    ViewPager2 pager2;
    int list[];
    TextView[] dots;

//https://www.youtube.com/watch?v=xd7SYulEWuc&ab_channel=Code2Develop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        level = getIntent().getStringExtra("level");
        numberOfLesson = getIntent().getIntExtra("lesson", 1);
        userName = getIntent().getStringExtra("name");

        dotsLayout=findViewById(R.id.dots_container);
        pager2 = findViewById(R.id.viewPager);
        list = new int[3];
        list[0]=getResources().getColor(R.color.blue);
        list[0]=getResources().getColor(R.color.black);
        list[0]=getResources().getColor(R.color.red);

        adapter = new SliderAdapter(list);
        pager2.setAdapter(adapter);

        dots = new TextView[3];
        dotsIndicator();

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedIndicator(position);
                super.onPageSelected(position);
            }
        });

    }

    private void selectedIndicator(int position) {
        for(int i=0; i<dots.length;i++){
            if(i==position){
                dots[i].setTextColor(list[position]);
            }else{
                dots[i].setTextColor(getResources().getColor(R.color.black));
            }
        }
    }

    private void dotsIndicator() {
        for(int i= 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            //circle shape
            dots[i].setText(Html.fromHtml("&#9679"));
            dots[i].setTextSize(18);
            dotsLayout.addView(dots[i]);
        }
    }
}