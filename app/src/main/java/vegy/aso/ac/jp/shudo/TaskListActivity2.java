package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import model.TaskAdapter;

public class TaskListActivity2 extends AppCompatActivity {
    List<Task> taskListToday = new ArrayList<>();
    List<Task> taskListTomorrow = new ArrayList<>();
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        List<Task> allTaskList = Task.getAllTask(getApplicationContext());
        TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), R.layout.task_layout, allTaskList);
        taskAdapter.setNotifyOnChange(true);
        GridView gridView = (GridView) findViewById(R.id.gv_taskList);
        gridView.setAdapter(taskAdapter);

    }
}