package vegy.aso.ac.jp.shudo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import model.PushService;

public class PreferenceActivity extends Activity {


        private TextView textView;
    private TextView textView2;
        private Button button;
        private int hour;
        private int minute;
    private PendingIntent mAlarmSender;
    private String TAG ="PreferenceActivity";

        private static final int TIME_DIALOG_ID = 0;
    int notificationId;
    private PendingIntent alarmIntent;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_preference);

            textView = (TextView) findViewById(R.id.textView);
            button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    showDialog(TIME_DIALOG_ID);
                }
            });

            Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            textView2 = (TextView) findViewById(R.id.set_time);

            SharedPreferences minuteTime = getSharedPreferences("MinuteTime", Context.MODE_PRIVATE);
            int minute = minuteTime.getInt("MinuteTime", 0);
            SharedPreferences hourTime = getSharedPreferences("HourTime", Context.MODE_PRIVATE);
            int hour = hourTime.getInt("HourTime", 8);

            textView2.setText(hour+"時"+minute+"分です");
        }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
        protected Dialog onCreateDialog(int id) {
            switch (id) {
                case TIME_DIALOG_ID:
                    return new TimePickerDialog(this, timeSetListener, hour, minute, true);
            }
            return null;
        }

    private OnTimeSetListener timeSetListener = new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int selectMinute) {

                hour = hourOfDay;
                minute = selectMinute;

                Log.d("hour", hourOfDay + ":" + selectMinute );

                SharedPreferences data = getSharedPreferences("MinuteTime", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("MinuteTime", selectMinute);
                editor.apply();
                SharedPreferences data2 = getSharedPreferences("HourTime", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = data2.edit();
                editor2.putInt("HourTime", hourOfDay);
                editor2.apply();
//                MainActivity main = new MainActivity();
                addAlarm();
                textView2.setText(hour+"時"+minute+"分です");
                
//                // 現在の時刻を取得
//                Date date = new Date();
//                // 表示形式を設定
//                SimpleDateFormat sdf = new SimpleDateFormat("kkmm");
//                int now = Integer.parseInt(sdf.toString());
         }
       };


    public void addAlarm(){
        // アラームを設定する
        mAlarmSender = this.getPendingIntent();
        //時間設定
        SharedPreferences minuteTime = getSharedPreferences("MinuteTime", Context.MODE_PRIVATE);
        int minute = minuteTime.getInt("MinuteTime", 0);
//        Log.d(TAG,minute+"設定時間minute");
        SharedPreferences hourTime = getSharedPreferences("HourTime", Context.MODE_PRIVATE);
        int hour = hourTime.getInt("HourTime", 8);
        Log.d(TAG,hour+"hour");

        // アラーム時間設定
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        // 設定した時刻をカレンダーに設定
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // 過去だったら明日にする
//        if(cal.getTimeInMillis() < System.currentTimeMillis()) {
//            cal.add(Calendar.DAY_OF_YEAR, 1);
//        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
// AlarmManager.RTC_WAKEUPで端末スリープ時に起動させるようにする
// 1回だけ通知の場合はalarmManager.set()を使う
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
// 一日毎にアラームを呼び出す
                AlarmManager.INTERVAL_DAY, mAlarmSender);

//        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mAlarmSender);
    }

    private PendingIntent getPendingIntent() {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(getApplicationContext(), PushService.class);
//        transit(PushService.class, 0);
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}




