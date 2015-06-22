package vegy.aso.ac.jp.shudo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Task;

public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Task> allTaskList = Task.getAllTask(getApplicationContext());
        for (int i = 0; i < allTaskList.size(); i++) {
            String content = String.valueOf(allTaskList.get(i).getTaskId() + allTaskList.get(i).getContent() + allTaskList.get(i).getImportant_level());
            Log.d(TAG, content);
        }
        //タスク更新テスト
//        task.getAllTask().get(0).updateTask(2);

        //タスク削除テスト
//        task.getAllTask().get(0).deleteTask();


        //タスク追加テスト
//        Task task = new Task(getApplicationContext());
//        task.setContent("きみやと焼肉");
//        task.setImportant_level(0);
//        task.addTask();
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("HH");
        Log.d(TAG, time.toString());


        //初回起動かチェック
        if (checkInitState() == 1) {
            transit(TaskListActivity.class, 0);
        } else if (checkInitState() == 0) {
            transit(AddTaskActivity.class, 0);
//            transit(TaskListActivity.class, 0);

        }

        //設定時間だった場合はタスク更新
        checkPreferencesTime();

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
    private void checkPreferencesTime() {
        SharedPreferences preferencesTime = getSharedPreferences("PreferencesTime", Context.MODE_PRIVATE);
        String time = preferencesTime.getString("PreferencesTime", "12");
        Log.d(TAG, "設定時刻は" + time);
        //現在時刻
        String nowTime = getNowDate().toString();

        Log.d(TAG, "現在事項は" + nowTime);

//
        if (time.equals(nowTime)) {
            updateImportantLv(Task.getAllTask(getApplicationContext()));
        }
    }

    //現在時刻取得
    public static String getNowDate() {
        final SimpleDateFormat df = new SimpleDateFormat("HH");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    //データ更新
    public void updateImportantLv(List<Task> allTaskList){
        Task task = new Task(getApplicationContext());
        Log.d(TAG,allTaskList.size()+":タスク数");
        for (int i = 0; i<allTaskList.size();i++) {
            task.updateTask(allTaskList.get(i).getImportant_level()+1);
        }
}




}
