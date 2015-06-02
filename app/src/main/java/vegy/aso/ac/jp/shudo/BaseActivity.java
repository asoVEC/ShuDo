package vegy.aso.ac.jp.shudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

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
}