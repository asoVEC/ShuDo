package vegy.aso.ac.jp.shudo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity{
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初回起動かチェック
        if (checkInitState() == 1) {
            transit(TaskListActivity.class, 0);
        }else if (checkInitState() == 0) {
            transit(TutorialActivity.class, 0);

        }
    }



    //初回起動時かチェックする 戻:int 0=初回、1=初回ではない
    private int checkInitState(){
        int initState = 0;
        SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
        initState = data.getInt("initState",0 );
        return initState;
    }

    //初回起動情報を書き込む
    private void updateInitState() {
        try {
            SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("initState", 1);
            Log.d(TAG, "初回起動情報\"initState\"に1を代入しました");
            editor.apply();
        } catch (Exception e) {
            Log.d(TAG, "初回起動情報の書き込みに失敗しました");
        }
    }
    //【開発用】初回起動情報の初期化
    private void cleanInitState() {
        try {
            SharedPreferences data = getSharedPreferences("initState", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("initState", 0);
            Log.d(TAG, "初回起動情報\"initState\"に0を代入しました");
            editor.apply();
        } catch (Exception e) {
            Log.d(TAG, "初回起動情報の書き込みに失敗しました");
        }

    }





}
