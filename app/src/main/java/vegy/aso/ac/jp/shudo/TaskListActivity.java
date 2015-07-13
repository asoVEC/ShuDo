package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import model.Task;
import model.TaskAdapter;

//    true=今日,flase=明日

public class TaskListActivity extends BaseActivity implements View.OnClickListener{
    TextView title;
    Button todayButton;
    Button tommorowButton;
    Button addTaskButton;
    TaskAdapter taskAdapter;
    GridView gridView;
    List<Task> allTaskList;
    private String TAG = "TaskListActivity";
    //flg宣言　デフォルトは今日
    private boolean  flg;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        flg = true;
        //宣言
        title = (TextView) findViewById(R.id.title_tasklist);
        todayButton = (Button) findViewById(R.id.todday_button_tasklist);
        tommorowButton = (Button) findViewById(R.id.tommorow_button_tasklist);
        addTaskButton = (Button) findViewById(R.id.button);
        todayButton.setOnClickListener(this);
        tommorowButton.setOnClickListener(this);
        addTaskButton.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gv_taskList);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                task.deleteTask();
                return false;
            }
        });
    }
    //基本処理
    @Override
    protected void onResume() {
        super.onResume();
        displayTask(0);

        }

    public void taskDisplay(List<Task> taskList) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.todday_button_tasklist:
                flg = true;
                Log.d(TAG, flg + ":TODDAY CLICK");
                break;
            case R.id.tommorow_button_tasklist:
                flg = false;
                Log.d(TAG, flg + ":TOMMOROW CLICK");
                break;
            case R.id. button :
                transit(AddTaskActivity.class, 0);
                break;
        }
    }

    //タスク取得 引:0=今日 1=明日以降
    public void displayTask(int flg) {
        allTaskList = Task.getAllTask(getApplicationContext());
        taskAdapter = new TaskAdapter(getApplicationContext(), R.layout.task_layout, allTaskList);
//        taskAdapter.setNotifyOnChange(true);
        gridView.setAdapter(taskAdapter);
    }

}
