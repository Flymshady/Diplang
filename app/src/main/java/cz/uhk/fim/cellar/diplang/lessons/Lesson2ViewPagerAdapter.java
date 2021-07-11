package cz.uhk.fim.cellar.diplang.lessons;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page1Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page1Lesson2Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page2Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page2Lesson2Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page3Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page4Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page5Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page6Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragmentFinal;

public class Lesson2ViewPagerAdapter extends FragmentStateAdapter {
    public Lesson2ViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Page1Lesson2Fragment();
            case 1:
                return new Page2Lesson2Fragment();
            case 2:
                return new Page3Lesson2Fragment();
            case 3:
                return new Page4Lesson2Fragment();
            case 4:
                return new Page5Lesson2Fragment();
            case 5:
                return new Page6Lesson2Fragment();
            case 6:
                return new PageFragmentL2Final();
            default:
                return new Page1Lesson2Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
