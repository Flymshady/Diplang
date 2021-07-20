package cz.uhk.fim.cellar.diplang.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import cz.uhk.fim.cellar.diplang.R;

/**
 * @author Štěpán Cellar - FIM UHK
 * Navigační aktivita obsahující fragment pro domovskou stránku, studijní sekci a profilovou sekci
 */
public class NavigationActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        /**
         * Nastavení pro volání fragemtnů a změna na navigačním baru
         */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

}