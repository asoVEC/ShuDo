package vegy.aso.ac.jp.shudo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.PushService;
import model.Receiver;
import model.Task;

public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    Context c;


//    public MainActivity(Context c){
//        // 初期化
//        this.c = c;
//        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
//        Log.v(TAG,"初期化完了");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        List<Task> allTaskList = Task.getAllTask(getApplicationContext());
//        for (int i = 0; i < allTaskList.size(); i++) {
//            String content = String.valueOf(allTaskList.get(i).getTaskId() + allTaskList.get(i).getContent() + allTaskList.get(i).getImportant_level());
//            Log.d(TAG, content);
//        }
        //タスク更新テスト
//        task.getAllTask().get(0).updateTask(2);

        //タスク削除テスト
//        task.getAllTask().get(0).deleteTask();


        //タスク追加テスト
        Task task = new Task(getApplicationContext());
        task.setContent("きみやと焼肉とステーキとラーメンとチャーハンと餃子とカレーライスとパフェを食べる");
        task.setImportant_level(0);
        try {
            task.addTask();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //サービスの起動
//        startService(new Intent(this, PushService.class));
        //とりあえずコメントで残しとく　使わない予定
//        IntentFilter filter = new IntentFilter(KitchenTimerService.ACTION);
//        registerReceiver(receiver, filter);
        //初回起動かチェック
        if (checkInitState() == 1) {
            transit(TaskListActivity.class, 0);
            //    transit(TaskListActivity.class, 0);
        } else if (checkInitState() == 0) {
             updateInitState();
            transit(TaskListActivity.class, 0);
            //   transit(AddTaskActivity.class, 0);
//            transit(TaskListActivity.class, 0);
        }



    }


    //初回起動時かチェックする 戻:int 0=初回、1=初回ではない
    private int checkInitState() {
        int initState = 0;
        SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
        initState = data.getInt("initState", 0);
        return initState;
    }

    //初回起動情報を書き込む
    private void updateInitState() {
        try {
            SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("initState", 1);
            Log.d(TAG, "初回起動情報\"initState\"に1を代入しました");
            editor.apply();
        } catch (Exception e) {
            Log.d(TAG, "初回起動情報の書き込みに失敗しました");
        }
    }

    //【開発用】初回起動情報の初期化
    private void cleanInitState() {
        try {
            SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("initState", 0);
            Log.d(TAG, "初回起動情報\"initState\"に0を代入しました");
            editor.apply();
        } catch (Exception e) {
            Log.d(TAG, "初回起動情報の書き込みに失敗しました");
        }

    }

    //設定時間チェック
    //dateで比較　１日１回のみ
//    private void checkPreferencesTime() {
//        //設定時間の読み込み
//        SharedPreferences preferencesTime = getSharedPreferences("PreferencesTime", Context.MODE_PRIVATE);
//        int time = preferencesTime.getInt("PreferencesTime", 10);
//        //フラグの読み込み（今日更新したかどうか確認するため）
//        SharedPreferences preferencesFlag = getSharedPreferences("PreferencesTime", Context.MODE_PRIVATE);
//        String flg = preferencesFlag.getString("PreferencesFlag", "0");
//        //起動時間の取得
//        int nowTime = Integer.parseInt(getNowTime());
//        //起動した日付の取得
//        String today = getToday().toString();
//        //log
//        Log.d(TAG, "設定時刻は" + time);
//        Log.d(TAG, "flgは" + flg);
//        Log.d(TAG, "現在時刻は" + nowTime);
//        Log.d(TAG, "今日の日付は" + today);
//        //今日更新したかどうか　していない場合はelseで更新する
//        if (flg.equals(today)) {
//            Log.d(TAG, "間違い");
//        } else {
//            if (time <= nowTime) {
//                //更新する
//                updateImportantLv();
//                //flgに今日の日付を書き込む
//                SharedPreferences.Editor editor = preferencesFlag.edit();
//                editor.putString("PreferencesFlag", today);
//                editor.apply();
//                Log.d(TAG, preferencesFlag.getString("PreferencesFlag", "0"));
//            }
//        }
//    }
//
//    //データ更新
//    public void updateImportantLv() {
//        List<Task> taskList = Task.getAllTask(getApplicationContext());
//        Log.d(TAG, taskList.size() + ":タスク数");
//
//    }
}
