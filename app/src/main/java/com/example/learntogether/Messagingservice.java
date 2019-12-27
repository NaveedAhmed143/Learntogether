package com.example.learntogether;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class Messagingservice extends FirebaseMessagingService {

    //Start from here
    private NotificationManager mNM;

    public static String TAG = "THIS";

    @Override
    public void onCreate() {
        Log.d("CRE", "onCreate: ");
        super.onCreate();
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public void onNewToken( String s) {
        super.onNewToken(s);
        Log.d("NEW", "onNewToken: ");
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessages: ");
    }


    @Override
    public void onMessageReceived( RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("MSGREC", "onMessageReceived: From there... " + remoteMessage.getFrom());
        if (remoteMessage.getNotification().getBody() != null) {
            Notificationshow(remoteMessage.getNotification().getBody());
        } else {
            Log.d("INIF", "onMessageReceived: " + remoteMessage.getNotification().getBody());
        }

    }

    public void Notificationshow(String MESG) {
        Log.d("MSGS", "Notificationshow: ");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, Message.class), 0);

        Notification notification = new Notification.Builder(this)
                .setSubText("Hello")
                .setSmallIcon(R.drawable.stat_name)// the status icon
                .setTicker("Learntogether...")// name of application
                .setWhen(System.currentTimeMillis())// the time stamp
                .setContentTitle("Mesages")// the label of the entry
                .setAutoCancel(true)
                // the contents of the entry
                .build();
        //Runnable Code here....
        mNM.notify(Integer.parseInt("Edu"), notification);


        //       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
    /*          Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.drawable.stat_name);//设置通知的图标
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作
*/
        /*Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.stat_name)
                .setContentTitle("EducatoinalCenter")
                .setContentText(MESG)
                .setAutoCancel(true)
                .build();
        Log.d("IMIN", "Notifoca: ");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
*/




      /*  Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);*/








       /*  @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d("Deleted", "onDeletedMessages: ");
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("NEW", "onNewToken: ");
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification()!=null)
        {
            String title=remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            Notification notification = new NotificationCompat.Builder(this,"FA")
                    .setSmallIcon(R.drawable.stat_name)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setColor(Color.RED)
                    .build();
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.notify(11,notification);
        }
        if(remoteMessage.getData()!=null)
        {
            Log.d("AAA", "onMessageReceived: "+remoteMessage.getData().toString());
        }



        }*/






    }
}


