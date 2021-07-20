package cz.uhk.fim.cellar.diplang;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Štěpán Cellar - FIM UHK
 * Třída pro nastavení persistentního ukládání v zařízení
 */
public class FirebaseHandler extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
