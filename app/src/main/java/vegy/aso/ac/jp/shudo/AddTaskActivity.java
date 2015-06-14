package vegy.aso.ac.jp.shudo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import model.Task;
//todo
//タスクの登録
//日付情報の登録（今日or明日以降)

public class AddTaskActivity extends BaseActivity implements View.OnClickListener{
    //画面の変数宣言
    public class activityAttribute{
        private RadioGroup group;
        private Button register;
        private taskAttribute taskAttribute;
        private RadioButton check;
        private EditText edit;
    }
    //値を渡すための変数宣言
    public class taskAttribute{
        private String flg;
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
        //変数取得
        activityAttribute activityattribute = new activityAttribute();
        //変数に値
        activityattribute.group = (RadioGroup) findViewById(R.id.radio_group);
        activityattribute.register = (Button) findViewById(R.id.bt_register);
        activityattribute.register.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        //変数取得
        activityAttribute activityattribute = new activityAttribute();
        taskAttribute taskattribute = activityattribute.taskAttribute;
        activityattribute.check = (RadioButton) findViewById(activityattribute.group.getCheckedRadioButtonId());
        activityattribute.edit = (EditText) findViewById(R.id.et_todo);
        taskattribute.flg = activityattribute.check.getText().toString();
        taskattribute.content = activityattribute.edit.getText().toString();
        addTask(taskattribute.content, taskattribute.flg);
    }

    public void addTask(String content,String flg) {
        //Taskクラス取得
        Task task = new Task(getApplicationContext());
        task.setContent(content);
        if (flg =="今日"){
        task.setImportant_level(0);
        }else{
            task.setImportant_level(-1);
        }
        try {
            task.addTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
