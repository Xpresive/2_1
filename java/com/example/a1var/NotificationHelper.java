package com.example.a1var;


import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);
        int currentMinuteIn24Format = rightNow.get(Calendar.MINUTE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, RedActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentIntent(contentIntent)
                .setContentTitle("Notification!")
                .setContentText(
                        "Numatytas laikas " + currentHourIn24Format + ":"
                                + currentMinuteIn24Format + " atėjo"
                ).setSmallIcon(R.drawable.ic_launcher_foreground);
    }
}