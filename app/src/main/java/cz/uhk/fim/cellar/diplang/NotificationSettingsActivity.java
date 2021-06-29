package cz.uhk.fim.cellar.diplang;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * The NotificationSettingsActivity is class for customizable notifications
 *
 */
public class NotificationSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSaveNotification, buttonTimePicker;
    private Switch switchNotifications;
    private int hour, minute;
    private boolean timePicked;
    private SharedPreferences sp;

    /**
     * Load all components and set in/visibility of the components based on the switch state from
     * the sharedPreferences variable "switcher"
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        sp = getSharedPreferences("MyNotifications", Context.MODE_PRIVATE);

        buttonSaveNotification = (Button) findViewById(R.id.buttonSaveNotification);
        buttonSaveNotification.setOnClickListener(this);

        buttonTimePicker = (Button) findViewById(R.id.buttonTimePicker);

        switchNotifications = (Switch) findViewById(R.id.switchNotifications);

        switchNotifications.setChecked(sp.getBoolean("switcher", true));

        if(sp.getBoolean("switcher", true)) {
            buttonTimePicker.setVisibility(View.VISIBLE);
            buttonTimePicker.setText(String.format(Locale.getDefault(), "%02d:%02d", sp.getInt("hour", 0), sp.getInt("minute",0)));
            buttonSaveNotification.setVisibility(View.VISIBLE);
        }else{
            buttonTimePicker.setVisibility(View.INVISIBLE);
            buttonSaveNotification.setVisibility(View.INVISIBLE);
        }


        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("switcher", true);
                    editor.commit();
                    buttonTimePicker.setVisibility(View.VISIBLE);
                    buttonSaveNotification.setVisibility(View.VISIBLE);
                    saveNotification();
                }else{
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("switcher", false);
                    editor.commit();
                    buttonTimePicker.setVisibility(View.INVISIBLE);
                    buttonSaveNotification.setVisibility(View.INVISIBLE);
                    turnOffNotification();
                }
            }
        });
    }

    /**
     * Save the custom notification and repeat it at a specific time every day
     */
    private void saveNotification() {

        if(sp.getBoolean("timePicked",true)) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(NotificationSettingsActivity.this, ReminderBroadcast.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationSettingsActivity.this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);
            Calendar calendar = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            int hourSet = sp.getInt("hour", 0);
            int minuteSet = sp.getInt("minute", 0);
            calendar.set(Calendar.HOUR_OF_DAY, hourSet);
            calendar.set(Calendar.MINUTE, minuteSet);
            calendar.set(Calendar.SECOND, 00);
            if (now.after(calendar)) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Toast.makeText(this, "Upozornění je nastaveno v "+hourSet +":"+minuteSet, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Vyberte čas!", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Cancel all notifications
     */
    private void turnOffNotification(){
        Toast.makeText(this, "Upozornění je vypnuto!", Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(NotificationSettingsActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationSettingsActivity.this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSaveNotification:
                saveNotification();
                break;
        }
    }

    /**
     * Pop up the TimePickerDialog for specifying the time for repeated notifications and save it
     * to the shared preferences
     * @param view
     */
    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                buttonTimePicker.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                timePicked=true;
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.putBoolean("timePiecked",timePicked);
                editor.commit();

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Vyberte čas");
        timePickerDialog.show();

    }
}