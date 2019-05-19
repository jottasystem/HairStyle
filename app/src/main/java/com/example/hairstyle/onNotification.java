package com.example.hairstyle;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class onNotification extends Application {

    public static final String CHANNEL_1_ID="HairStyle";



    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

    }

    private void createNotificationChannels(){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            NotificationChannel channel1 =  new NotificationChannel(
                    CHANNEL_1_ID,
                    "HairStyle",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("HairStyle");

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel1);
        }
    }
}
