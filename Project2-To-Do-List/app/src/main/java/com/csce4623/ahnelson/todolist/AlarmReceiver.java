package com.csce4623.ahnelson.todolist;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel mChannel = new NotificationChannel("my_channel_01", "TEST", NotificationManager.IMPORTANCE_HIGH);
        mChannel.setDescription("This is a test.");
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mNotificationManager.createNotificationChannel(mChannel);

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent reminderIntent = new Intent(context, HomeActivity.class);
        int noteId = intent.getIntExtra("noteId", -1);
        String noteTitle = intent.getStringExtra("noteTitle");
        reminderIntent.putExtra("noteId", noteId);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, reminderIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(context, "my_channel_01")
                .setContentTitle("To-Do Reminder")
                .setContentText(noteTitle + " is due!")
                .setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();

        mNotificationManager.notify(0, notification);
    }

}