package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import model.Task;

/**
 * Created by Aki on 15/06/14.
 */
public class AddTaskActivity2 extends BaseActivity {
    private static final String TAG = AddTaskActivity2.class.getSimpleName();
    private final AddTaskActivity2 self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button addTaskButton = (Button) findViewById(R.id.bt_register);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addTask();
            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public void addTask(){
        //追加するタスクのインスタンス作成
        Task task = new Task(getApplicationContext());

        //登録するタスクのコンテント取得、タスクインスタンスにセット
        EditText todoEt = (EditText) findViewById(R.id.et_todo);
        task.setContent(todoEt.getText().toString());

        //追加するタスクのインポータントレベル取得、タスクインスタンスにセット
        RadioGroup importantLvRg = (RadioGroup) findViewById(R.id.radio_group);
        switch (importantLvRg.getCheckedRadioButtonId()) {
            case R.id.radio_today:
                task.setImportant_level(0);
                break;
            case R.id.radio_tommorow:
                task.setImportant_level(-1);
                break;
        }
        try {
            task.addTask();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplication(), "タスクの追加に失敗しました", Toast.LENGTH_LONG);
        }
    }
}