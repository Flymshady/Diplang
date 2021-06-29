package cz.uhk.fim.cellar.diplang;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSaveNotification;
    private int[] days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        buttonSaveNotification = (Button) findViewById(R.id.buttonSaveNotification);
        buttonSaveNotification.setOnClickListener(this);
        days = new int[]{1, 2, 4};
    }


    private void saveNotification() {


        Toast.makeText(this, "Upozornění je nastaveno!", Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(NotificationSettingsActivity.this, ReminderBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationSettingsActivity.this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 33);
        calendar.set(Calendar.SECOND, 00);
        if (now.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSaveNotification:
                saveNotification();
                break;
        }
    }
}