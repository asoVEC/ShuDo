package model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Receiver extends BroadcastReceiver {
    public Receiver() {
    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//}

    private Context alarmReceiverContext;
    private int notificationProvisionalId;

    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        alarmReceiverContext = context;

        notificationProvisionalId = receivedIntent.getIntExtra("notificationId", 0);
        NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = prepareNotification();
        myNotification.notify(notificationProvisionalId, notification);

    }

    private Notification prepareNotification() {

        Intent bootIntent =
                new Intent(alarmReceiverContext, MainActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(alarmReceiverContext, 0, bootIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                alarmReceiverContext);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("ウェーイ!")
                .setContentTitle("ウェーイ!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent);

        NotificationCompat.BigPictureStyle pictureStyle =
                new NotificationCompat.BigPictureStyle(builder);
        pictureStyle.bigPicture(BitmapFactory.decodeResource(alarmReceiverContext.getResources(), R.drawable.cat));

        return pictureStyle.build();
    }
}