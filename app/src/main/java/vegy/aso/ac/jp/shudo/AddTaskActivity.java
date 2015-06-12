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

import model.Task;
//todo
//タスクの登録
//日付情報の登録（今日or明日以降)

public class AddTaskActivity extends BaseActivity implements View.OnClickListener{

    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    protected void onResume() {
        super.onResume();
        group = (RadioGroup) findViewById(R.id.radio_group);
        Button register = (Button) findViewById(R.id.bt_register);
        register.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_management_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        RadioButton check = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        EditText content = (EditText) findViewById(R.id.ed_todo);
        Toast.makeText(this, check.getText()+"：test"+content.getText(), Toast.LENGTH_LONG).show();
//        Task task = new Task();
//        task.getContent();
    }
}
