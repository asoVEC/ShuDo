package model;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * Created by shouhei on 15/06/23.
 */
public class PushService extends Service {
    private String TAG = "Service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "成功");
    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, "せいっこう");
//        List<Task> taskList = Task.getAllTask(getApplicationContext());
//        //できるけど冗長　
//        SharedPreferences preferencesTime = getSharedPreferences("PreferencesTime", Context.MODE_PRIVATE);
//        int time = preferencesTime.getInt("PreferencesTime", 10);
//        SharedPreferences preferencesFlag = getSharedPreferences("PreferencesTime", Context.MODE_PRIVATE);
//        String flg = preferencesFlag.getString("PreferencesFlag", "0");
//        if (flg.equals(today)) {
//            Log.d(TAG, "間違い");
//        }else {
//            //push通知する
//
//        }
//
//
//
//
//        for (int i = 0; i < taskList.size(); i++) {
//            if (taskList.get(i).getImportant_level()<=3){
//
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }
//
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
