package cz.uhk.fim.cellar.diplang.lessons;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.jetbrains.annotations.NotNull;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page1Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page2Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page3Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page4Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page5Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page6Lesson1Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragmentFinal;

/**
 * @author Štěpán Cellar - FIM UHK
 * View Pager Adapter pro nastavení přechodů mezi fragmenty první lekce úrovně B2
 */
public class Lesson1ViewPagerAdapter extends FragmentStateAdapter {
    public Lesson1ViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Page1Lesson1Fragment();
            case 1:
                return new Page2Lesson1Fragment();
            case 2:
                return new Page3Lesson1Fragment();
            case 3:
                return new Page4Lesson1Fragment();
            case 4:
                return new Page5Lesson1Fragment();
            case 5:
                return new Page6Lesson1Fragment();
            case 6:
                return new PageFragmentFinal();
            default:
                return new Page1Lesson1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
