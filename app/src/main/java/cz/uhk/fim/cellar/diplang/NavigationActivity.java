package cz.uhk.fim.cellar.diplang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class NavigationActivity extends AppCompatActivity {

    private BottomNavigationView btm_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //nastaveni home fragmentu jako výchozího
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();

        btm_view = (BottomNavigationView) findViewById(R.id.bottom_view);
        btm_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               if(item.getItemId() == R.id.home){
                    getFragment(new HomeFragment());
               }else if(item.getItemId() == R.id.study){
                   getFragment(new StudyFragment());
               }else if(item.getItemId() == R.id.profile){
                   getFragment(new ProfileFragment());
               }

                return false;
            }
        });

    }
    //metoda pro volani fragmentů
    private void getFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment); //nahrazení fragmentu
        fragmentTransaction.commit();
    }
}