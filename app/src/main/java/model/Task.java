package model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.Date;

/**
 * Created by Aki on 15/06/02.
 */
public class Task {
    private final String TAG = "Task.class";
    private String taskName;
    private String content;
    private Date createdDate;
    private Date excutedDate;
    private int importantLevel;
    private Context context;

    public Task(Context context) {
        this.context = context;
    }

    public void addTask() {

    }

    public String getContent () {
        SQLiteDatabase sdb = null;
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        try {
            sdb = helper.getWritableDatabase();
            //もしくは、
            //sdb = helper.getReadableDatabase();
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLiteDatabase接続に失敗しました");
            //異常終了
        }
        String content = null;
        String sqlstr = "select * from task;";
        String[] param = {};
        if (sdb != null) {
            Cursor c = sdb.rawQuery(sqlstr,param);
            if(c.moveToFirst()){
                do {
                    content = c.getString(1);
                }while(c.moveToNext());
            }
        }
        return content;
    }
}