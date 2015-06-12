package vegy.aso.ac.jp.shudo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.net.ContentHandler;

import model.Task;
//todo
//タスクの登録
//日付情報の登録（今日or明日以降)

public class AddTaskActivity extends BaseActivity implements View.OnClickListener{
    //アクティビティに関する変数宣言
    public class activityAttribute{
        private RadioGroup group;
        private Button register;
        private taskAttribute taskAttribute;
        private RadioButton check;
        private EditText edit;
    }
    //taskに関するの変数宣言
    public class taskAttribute{
        private String flg;
        private String content;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //変数インスタンス化
        activityAttribute activityattribute = new activityAttribute();
        //変数に値をいれる
        activityattribute.group = (RadioGroup) findViewById(R.id.radio_group);
        activityattribute.register = (Button) findViewById(R.id.bt_register);
        //onClick呼び出し
        activityattribute.register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //変数をインスタンス化
        activityAttribute activityattribute = new activityAttribute();
        taskAttribute taskattribute = activityattribute.taskAttribute;
        //変数に値をいれる
        activityattribute.check = (RadioButton) findViewById(activityattribute.group.getCheckedRadioButtonId());
        activityattribute.edit = (EditText) findViewById(R.id.ed_todo);
        taskattribute.flg = (String)activityattribute.check.getText().toString();
        taskattribute.content = activityattribute.edit.getText().toString();
        //addTask呼び出し
        addTask(taskattribute.content, taskattribute.flg);
    }

    public void addTask(String content,String flg) {
        //Taskクラスインスタンス化
        Task task = new Task(getApplicationContext());
        task.setContent(content);
        if (flg =="今日"){
        task.setImportant_level(0);
        }else{
            task.setImportant_level(-1);
        }
        task.addTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

}
