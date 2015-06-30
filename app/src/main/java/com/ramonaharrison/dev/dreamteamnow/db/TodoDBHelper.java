package com.ramonaharrison.dev.dreamteamnow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Ramona Harrison
 * on 6/25/15.
 */
public class TodoDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TODO";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "TODOS";

    public static final String TODO = "todo";
    public static final String _ID = BaseColumns._ID;
    public static final String TODO_NAME = "name";
    public static final String TODO_DESC = "description";
    public static final String TODO_LOCATION = "location";
    public static final String TODO_DAY = "day";
    public static final String TODO_MONTH = "month";
    public static final String TODO_YEAR = "year";
    public static final String TODO_HOUR = "hour";
    public static final String TODO_MINUTE = "minute";
    public static final String TODO_REMINDER = "reminder";
    public static final String TODO_BEFORE = "minbefore";

    //helper for types
    public static final String VARCHAR_TYPE = " VARCHAR(50)";
    public static final String INT_TYPE = " INTEGER";

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE +
            "("
            + _ID      + INT_TYPE +" PRIMARY KEY AUTOINCREMENT,"
            + TODO_NAME         + VARCHAR_TYPE  + ","
            + TODO_DESC        + VARCHAR_TYPE  + ","
            + TODO_LOCATION      + VARCHAR_TYPE  + ","
            + TODO_DAY          + INT_TYPE      + ","
            + TODO_MONTH          + INT_TYPE  + ","
            + TODO_YEAR       + INT_TYPE  + ","
            + TODO_HOUR        + INT_TYPE  + ","
            + TODO_MINUTE      + INT_TYPE  + ","
            + TODO_REMINDER     + INT_TYPE     + ","
            + TODO_BEFORE        + INT_TYPE +
            ")";

    public TodoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(sqlDB);
    }
}