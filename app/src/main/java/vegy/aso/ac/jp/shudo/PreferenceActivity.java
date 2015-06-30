package vegy.aso.ac.jp.shudo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import java.util.Calendar;

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
                //receiver
                Intent bootIntent = new Intent(PreferenceActivity.this, Receiver.class);
                bootIntent.putExtra("notificationId", notificationId);
                alarmIntent = PendingIntent.getBroadcast(PreferenceActivity.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                int hour2 = 10;
                int minute2 = 12;

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarm.set(
                        AlarmManager.RTC_WAKEUP,
                        alarmStartTime,
                        alarmIntent
                );
                notificationId++;

         }
       };
}




