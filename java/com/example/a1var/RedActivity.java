package com.example.a1var;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_red);
    }

    public void stopSound(View v){
        Toast.makeText(this, "Stop sound",Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(RedActivity.this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 1, intent, 0
        );

        alarmManager.cancel(pendingIntent);

    }

    public void exitApp(View v){
        // finish the current activity and all parent activities
        this.finishAffinity();
        System.exit(0);
    }
}

