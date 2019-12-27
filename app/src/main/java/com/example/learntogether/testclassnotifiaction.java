package com.example.learntogether;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class testclassnotifiaction extends Application {

    public static final String From_CHANNEL_ID = "FROM_CHANNEL_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel fromchannel = new NotificationChannel(
                    From_CHANNEL_ID, "FCM_CHANNEL", NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(fromchannel);

        }
    }
}

