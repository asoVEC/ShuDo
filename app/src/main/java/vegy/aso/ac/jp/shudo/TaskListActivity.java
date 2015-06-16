package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import model.Task;

//    true=今日,flase=明日

public class TaskListActivity extends BaseActivity implements View.OnClickListener{
    //flg宣言　デフォルトは今日
    private boolean flg = true;
    //メソッド分けたかったから諦めた
    private TextView title = (TextView) findViewById(R.id.title_tasklist);
    private Button todayButton = (Button) findViewById(R.id.todday_button_tasklist);
    private Button tommorowButton = (Button) findViewById(R.id.tommorow_button_tasklist);
    private List<Task> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }
    //基本処理
    @Override
    protected void onResume() {
        super.onResume();

        //ボタンクリック処理
        todayButton.setOnClickListener(this);
        tommorowButton.setOnClickListener(this);
        //タスクの取得
        if (flg = true) {
            title.setText("今日の予定一覧");
            taskList = Task.getTodayTask(getApplicationContext());
        }
        if (flg = false){
            title.setText("明日以降の予定一覧");
            taskList = Task.getTommorowTask(getApplicationContext());
        }
        //表示する
        taskDisplay(taskList);
    }

    public void taskDisplay(List<Task> task){


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.todday_button_tasklist:
                flg=true;
                break;
            case R.id.tommorow_button_tasklist:
                flg=false;
                break;

        }
    }
}
