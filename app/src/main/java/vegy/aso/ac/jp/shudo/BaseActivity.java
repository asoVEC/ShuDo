package vegy.aso.ac.jp.shudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Aki on 15/06/02.
 */
public class BaseActivity extends AppCompatActivity {

    //画面遷移処理を簡単にするメソッド finishFlag:int 1= 遷移後に遷移元アクティビティを終了する
    protected void transit(Class classPlaceholder,int finishFlag) {
        Intent intent = new Intent(getApplicationContext(), classPlaceholder);
        startActivity(intent);
        if (finishFlag == 1) {
            finish();
        }
    }
    protected void transit(Class classPlaceholder,int finishFlag,String key, String value) {
        Intent intent = new Intent(getApplicationContext(), classPlaceholder);
        intent.putExtra(key, value);
        startActivity(intent);
        if (finishFlag == 1) {
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}