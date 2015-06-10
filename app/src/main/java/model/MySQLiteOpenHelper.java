package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aki on 15/06/02.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper  {
    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();
    private final MySQLiteOpenHelper self = this;
    private final String CREATE_STATEMENT = "create table task (_id integer primary key autoincrement," +
            "content text not null)";
    private final String INSERT_STATMENT = "insert into task (content) values('ますやま')";

    public MySQLiteOpenHelper(Context c){
        super(c,"vec.db",null,1);
    }


    //DB初回接続。ここでSQLテーブルの作成などを行う
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "DB初回接続です");
        db.execSQL(CREATE_STATEMENT);
        db.execSQL(INSERT_STATMENT);

    }

    //データベースのバージョンが上がったときに自動的に実行されるメソッド
    //不必要でも要実装
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }




}