package cz.uhk.fim.cellar.diplang.lessons;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragment1;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragment2;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragment3;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragment4;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragmentFinal;

public class LessonViewPagerAdapter extends FragmentStateAdapter {
    public LessonViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PageFragment1();
            case 1:
                return new PageFragment2();
            case 2:
                return new PageFragment3();
            case 3:
                return new PageFragment4();
            case 4:
                return new PageFragmentFinal();
            default:
                return new PageFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
