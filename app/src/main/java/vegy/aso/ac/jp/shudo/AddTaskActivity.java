package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import model.Task;

public class AddTaskActivity extends BaseActivity implements View.OnClickListener {
    //画面の変数宣言
    public class addAttribute {
        private RadioGroup group;
        private Button register;
        private EditText edit;
        private String content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //変数インスタンス化
        addAttribute activityattribute = new addAttribute();
        //変数に値
        activityattribute.register = (Button) findViewById(R.id.bt_register);
        activityattribute.register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //変数インスタンス化
        addAttribute activityattribute = new addAttribute();
        //インスタンス変数に値を
        activityattribute.edit = (EditText) findViewById(R.id.et_todo);
        activityattribute.content = activityattribute.edit.getText().toString();
        activityattribute.group = (RadioGroup) findViewById(R.id.radio_group);

        //追加するタスクのインポータントレベル取得
        switch (activityattribute.group.getCheckedRadioButtonId()) {
            case R.id.radio_today:
                addTaskAttribute(activityattribute.content, 0);
                break;
            case R.id.radio_tommorow:
                addTaskAttribute(activityattribute.content, -1);
                break;
        }
    }

    public void addTaskAttribute(String content, int flg) {
        //Taskクラス取得
        Task task = new Task(getApplicationContext());
        task.setContent(content);
        task.setImportant_level(flg);
        try {
            task.addTask();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"登録エラー",Toast.LENGTH_LONG).show();
        }
    }
}
