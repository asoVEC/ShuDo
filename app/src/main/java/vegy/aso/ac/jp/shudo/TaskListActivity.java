package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import model.Task;

public class TaskListActivity extends BaseActivity implements View.OnClickListener{

    private boolean flg = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flg = true) {
            List<Task> todayTask = Task.getTask(getApplicationContext(),"select * from task where important_lv = ");
        }
        if (flg = false){
            List<Task> tommorowTask = Task.getTask(getApplicationContext(),"select * from task where important_lv = -1");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
