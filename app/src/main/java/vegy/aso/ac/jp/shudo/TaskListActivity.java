package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.Task;

//    true=今日,flase=明日

public class TaskListActivity extends BaseActivity implements View.OnClickListener{
    private String TAG = "TaskListActivity";
    //flg宣言　デフォルトは今日
    private boolean  flg;
    private List<Task> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        flg = true;
        Log.d(TAG, flg + ":CREATE");
    }
    //基本処理
    @Override
    protected void onResume() {
        super.onResume();
        //宣言
        Log.d(TAG, flg + ":RESUME");
        TextView title = (TextView) findViewById(R.id.title_tasklist);
        Button todayButton = (Button) findViewById(R.id.todday_button_tasklist);
        Button tommorowButton = (Button) findViewById(R.id.tommorow_button_tasklist);
        Button bt = (Button) findViewById(R.id.button);

        //ボタンクリック処理
        todayButton.setOnClickListener(this);
        tommorowButton.setOnClickListener(this);
        bt.setOnClickListener(this);
        //タスクの取得
            if (flg == false) {
                title.setText("明日以降の予定一覧");
                taskList = Task.getTommorowTask(getApplicationContext());
                Log.d(TAG, "明日");
            }

            if (flg == true) {
                title.setText("今日の予定一覧");
                taskList = Task.getTodayTask(getApplicationContext());
                Log.d(TAG, "今日");
            }

            for (int i = 0; i < taskList.size(); i++) {
                String task = String.valueOf(taskList.get(i).getContent() + taskList.get(i).getImportant_level());
                Log.d(TAG, task);
            }
//        Toast.makeText(this, taskList.size(), Toast.LENGTH_LONG).show();
            //表示する
            taskDisplay(taskList);
        }

    public void taskDisplay(List<Task> taskList) {
//        TextView tv_ic_hello = (TextView) findViewById(R.id.title_tasklist);
//        tv_ic_hello.setBackgroundResource(R.drawable.husen);
//        tv_ic_hello.setText(taskList.get(0).getContent());

        for (int i = 0; i < taskList.size(); i++) {

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.todday_button_tasklist:
                flg = true;
                onResume();
//                finish();
//                startActivity(getIntent());
                Log.d(TAG, flg + ":TODDAY CLICK");
                break;
            case R.id.tommorow_button_tasklist:
                flg = false;

                Log.d(TAG, flg + ":TOMMOROW CLICK");
//                finish();
                onResume();
//                startActivity(getIntent());
                break;
            case R.id. button :
                transit(AddTaskActivity.class, 0);
                break;
        }
    }
}
