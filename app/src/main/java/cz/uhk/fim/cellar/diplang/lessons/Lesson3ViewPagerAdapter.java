package cz.uhk.fim.cellar.diplang.lessons;

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
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.Page7Lesson3Fragment;
import cz.uhk.fim.cellar.diplang.lessons.lessonFragments.PageFragmentFinal;

/**
 * @author Štěpán Cellar - FIM UHK
 * View Pager Adapter pro nastavení přechodů mezi fragmenty třetí lekce úrovně B2
 */
public class Lesson3ViewPagerAdapter extends FragmentStateAdapter {
    public Lesson3ViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Page1Lesson3Fragment();
            case 1:
                return new Page2Lesson3Fragment();
            case 2:
                return new Page3Lesson3Fragment();
            case 3:
                return new Page4Lesson3Fragment();
            case 4:
                return new Page5Lesson3Fragment();
            case 5:
                return new Page6Lesson3Fragment();
            case 6:
                return new Page7Lesson3Fragment();
            case 7:
                return new PageFragmentFinal();
            default:
                return new Page1Lesson3Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
