package vegy.aso.ac.jp.shudo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.Time;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Receiver;

public class PreferenceActivity extends Activity {


        private TextView textView;
        private Button button;
        private int hour;
        private int minute;

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

//            updateDisplay();
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
//                main.addAlarm();


//                // 現在の時刻を取得
//                Date date = new Date();
//                // 表示形式を設定
//                SimpleDateFormat sdf = new SimpleDateFormat("kkmm");
//                int now = Integer.parseInt(sdf.toString());


         }
       };
}




