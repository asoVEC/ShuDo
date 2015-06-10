package model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aki on 15/06/02.
 */
public class Task {
    private final String TAG = "Task.class";
    private String content;
    private int important_level;
    private Context context;

    public int getImportant_level() {
        return important_level;
    }

    public String getContent() {
        return content;
    }

    public Task(Context context) {
        this.context = context;
    }

    //task登録　属性：（TODO名、今日or明日のフラグ）　
    public void addTask() {
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
            try {
                String sql = "INSERT INTO task(phrase) VALUES(?,?)";
                //INSERT,DELETE,UPDATE文の実行メソッド=execSQL
<<<<<<< Updated upstream
                sdb.execSQL(sql, new String[]{"task" + taskName, "important_lv" + 0});
=======
//                sdb.execSQL(sql,new String[]{taskName});
//                sdb.execSQL(sql,new String[]{when});
>>>>>>> Stashed changes
            }
            catch(Exception e) {
                e.getMessage();
            }
    }

    //DBからタスク一覧を取得するメソッドのひな形 引:SELECT文
    public List<Task> getTask(String sqlstr) {
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
        List<Task> taskList = new ArrayList<>();
        String[] param = {};
        if (sdb != null) {
            Cursor c = sdb.rawQuery(sqlstr,param);
            if(c.moveToFirst()){
                do {
                    Task task = new Task(context);
                    task.content = c.getString(1);
                    task.important_level = c.getInt(2);
                    taskList.add(task);
                }while(c.moveToNext());
            }
        }
        return taskList;
    }
    public List<Task> getAllTask(){
        String sqlstr = "select * from task;";
        return getTask(sqlstr);
    }
    public List<Task> getTaskByImportantLv(int lv){
        String sqlstr = "select * from task where important_lv = "+lv+";";
        return getTask(sqlstr);
    }

}