package cz.uhk.fim.cellar.diplang;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

/**
 * BroadcastReceiver class for managing the notifications
 * from the NotificationSettingsActitivy.class
 *
 */
public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        PendingIntent pendingIntent;

        /**
         * Devices with SDK version >= "Oreo" need the notification channel specification
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "DiplangReminderChallenge";
            String description = "Channel for Diplang Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyDiplang", name, importance);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);

        }
        /**
         * Customizable look of the notification
         */
        builder = new NotificationCompat.Builder(context, "notifyDiplang")
                .setSmallIcon(R.drawable.ic_black_school_24)
                .setContentTitle("Diplang upozornění")
                .setContentText("Hello there")
                .setAutoCancel(true);

        Intent repeatingIntent = new Intent(context, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 200, repeatingIntent,    PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(200, builder.build());
    }
}
