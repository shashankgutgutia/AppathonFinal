package com.adgvit.appathon2k18.appathon2k18;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Shashank Gutgutia on 14-02-2018.
 */

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG="Myfirebase,sgservice";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG,"FROM:"+remoteMessage);

        if(remoteMessage.getData().size()>0)
        {
            Log.d(TAG,"Message data"+remoteMessage.getData());
        }

        if(remoteMessage.getNotification()!=null)
        {
            Log.d(TAG,"Message body:"+remoteMessage.getNotification().getBody());

            Intent intent=new Intent(this,HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notifiBuilder=new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.adglogo)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher))
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setContentTitle("App-A-Thon 2k18")
                    .setContentIntent(pendingIntent);


            android.app.NotificationManager notificationManager=(android.app.NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0,notifiBuilder.build());

        }
    }
    private void sendNotification(String body)
    {
        Intent intent=new Intent(this,HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri notification= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder=new NotificationCompat.Builder(this)
                .setContentTitle("App-A-Thon 2k18")
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        android.app.NotificationManager notificationManager=(android.app.NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifiBuilder.build());


    }

}
