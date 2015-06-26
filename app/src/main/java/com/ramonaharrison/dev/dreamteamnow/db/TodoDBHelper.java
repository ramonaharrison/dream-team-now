package com.ramonaharrison.dev.dreamteamnow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ramona Harrison
 * on 6/25/15.
 */
public class TodoDBHelper extends SQLiteOpenHelper {

    public TodoDBHelper(Context context) {
        super(context, TodoContract.DB_NAME, null, TodoContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE %s (" +
                                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT)", TodoContract.TABLE,
                        TodoContract.Columns.TODO);

        Log.d("TaskDBHelper", "Query to form table: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+TodoContract.TABLE);
        onCreate(sqlDB);
    }
}