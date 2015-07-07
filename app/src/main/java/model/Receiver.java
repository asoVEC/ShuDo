package model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vegy.aso.ac.jp.shudo.AddTaskActivity;
import vegy.aso.ac.jp.shudo.MainActivity;
import vegy.aso.ac.jp.shudo.R;
import vegy.aso.ac.jp.shudo.TaskListActivity;

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
    private String TAG = "receiver";
    private Context taskContext;
    private int notificationProvisionalId;

    @Override
    public void onReceive(Context context, Intent receivedIntent) {
        Intent intentActivity = new Intent(context, TaskListActivity.class);
        intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intentActivity);
        taskContext = context;
//        MainActivity main = new MainActivity();
//        main.addAlarm();
        List pushList = new ArrayList();
            List<Task> taskList = Task.getAllTask(context);
            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).increaseImportantLv();
            }
            Log.d(TAG, taskList.size() + ":タスク数");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getImportant_level() >= 3) {
                    pushList.add(taskList.get(i).getContent());
                }

            }
            if (pushList.size() > 0) {
                notificationProvisionalId = receivedIntent.getIntExtra("notificationId", 0);
                NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = prepareNotification(pushList);
                myNotification.notify(notificationProvisionalId, notification);
            }

    }

    private Notification prepareNotification(List pushList) {
        String display = pushList.get(0).toString();

        if (pushList.size() > 1){
            display += "残り"+String.valueOf(pushList.size()-1)+"個のタスクが残っています";
        }

        Intent bootIntent =
                new Intent(taskContext, TaskListActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(taskContext, 0, bootIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                taskContext);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("ShuDo")
                .setContentTitle(display)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent);
        return builder.build();
    }
}