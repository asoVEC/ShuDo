package vegy.aso.ac.jp.shudo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.Task;
import model.TaskAdapter;

//    true=今日,flase=明日

public class TaskListActivity extends BaseActivity implements View.OnClickListener {
    TextView title;
    Button todayButton;
    Button addButton;
    Button tommorowButton;
    ImageButton preferenceButton;
    TaskAdapter taskAdapter;
    private String TAG = "TaskListActivity";
    //flg宣言　デフォルトは今日
    private boolean flg;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        flg = true;

        title = (TextView) findViewById(R.id.title_tasklist);
        todayButton = (Button) findViewById(R.id.bt_today_tasklist);
        tommorowButton = (Button) findViewById(R.id.bt_tomorrow_tasklist);
        addButton = (Button) findViewById(R.id.bt_add_tasklist);
        preferenceButton = (ImageButton) findViewById(R.id.bt_preference_tasklist);
//        Log.d(TAG, flg + ":CREATE");
    }

    //基本処理
    @Override
    protected void onResume() {
        super.onResume();
        //宣言
//        Log.d(TAG, flg + ":RESUME");

        //ボタンクリック処理
        todayButton.setOnClickListener(this);
        tommorowButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        preferenceButton.setOnClickListener(this);
        preferenceButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RelativeLayout taskList = (RelativeLayout) findViewById(R.id.task_list);
                taskList.setBackgroundColor(Color.rgb(160, 30, 220));
                TextView titleString = (TextView) findViewById(R.id.title_tasklist);
                title.setTextColor(Color.parseColor("#ffffffff"));

                return true;
            }
        });
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
        //表示する
        taskAdapter = new TaskAdapter(getApplicationContext(), R.layout.task_layout, taskList);
        GridView gridView = (GridView) findViewById(R.id.gv_taskList);
        gridView.setAdapter(taskAdapter);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                task.deleteTask();
                taskAdapter.remove(task);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "タスクを削除しました", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "後回しにしました", Toast.LENGTH_LONG).show();
                Task task = (Task) parent.getItemAtPosition(position);
                task.updateTask(-1);
                taskAdapter.remove(task);
                taskAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_today_tasklist:
                flg = true;
                onResume();
                Log.d(TAG, flg + ":TODDAY CLICK");
                break;
            case R.id.bt_tomorrow_tasklist:
                flg = false;

                Log.d(TAG, flg + ":TOMMOROW CLICK");
                onResume();
                break;
            case R.id.bt_add_tasklist:
                transit(AddTaskActivity.class, 0);
                break;
            case R.id.bt_preference_tasklist:
                transit(PreferenceActivity.class, 0);
                break;
        }
    }
}
