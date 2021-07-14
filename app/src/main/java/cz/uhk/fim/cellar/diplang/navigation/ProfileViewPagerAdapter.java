package cz.uhk.fim.cellar.diplang.navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page1Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page2Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page3Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page4Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page5Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page6Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragmentFinal;

public class ProfileViewPagerAdapter extends FragmentStateAdapter {
    public ProfileViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProfileChartFragment();
            case 1:
                return new ProfileFriendsFragment();
            default:
                return new ProfileChartFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
