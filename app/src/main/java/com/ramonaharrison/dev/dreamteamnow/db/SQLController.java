package com.ramonaharrison.dev.dreamteamnow.db;

/**
 * Created by Ramona Harrison
 * on 6/27/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

    private TodoDBHelper TodoDBHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        context = c;
    }

    public SQLController open() throws SQLException {
        TodoDBHelper = new TodoDBHelper(context);
        database = TodoDBHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        TodoDBHelper.close();
    }

    public void insert(String name, String description, String location, int day, int month, int year, int hour, int min, boolean reminder, int minBefore) {
        int remind;
        if (reminder) {
            remind = 1;
        } else {
            remind = 0;
        }

        ContentValues contentValue = new ContentValues();
        contentValue.put(TodoDBHelper.TODO_NAME, name);
        contentValue.put(TodoDBHelper.TODO_DESC, description);
        contentValue.put(TodoDBHelper.TODO_LOCATION, location);
        contentValue.put(TodoDBHelper.TODO_DAY, day);
        contentValue.put(TodoDBHelper.TODO_MONTH, month);
        contentValue.put(TodoDBHelper.TODO_YEAR, year);
        contentValue.put(TodoDBHelper.TODO_HOUR, hour);
        contentValue.put(TodoDBHelper.TODO_MINUTE, min);
        contentValue.put(TodoDBHelper.TODO_REMINDER, remind);
        contentValue.put(TodoDBHelper.TODO_BEFORE, minBefore);
        database.insert(TodoDBHelper.TABLE, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { TodoDBHelper._ID, TodoDBHelper.TODO_NAME,
                TodoDBHelper.TODO_DESC, TodoDBHelper.TODO_LOCATION, TodoDBHelper.TODO_DAY, TodoDBHelper.TODO_MONTH, TodoDBHelper.TODO_YEAR, TodoDBHelper.TODO_HOUR, TodoDBHelper.TODO_MINUTE, TodoDBHelper.TODO_REMINDER, TodoDBHelper.TODO_BEFORE };
        Cursor cursor = database.query(TodoDBHelper.TABLE, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String description, String location, int day, int month, int year, int hour, int min, boolean reminder, int minBefore) {
        int remind;
        if (reminder) {
            remind = 1;
        } else {
            remind = 0;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoDBHelper.TODO_NAME, name);
        contentValues.put(TodoDBHelper.TODO_DESC, description);
        contentValues.put(TodoDBHelper.TODO_LOCATION, location);
        contentValues.put(TodoDBHelper.TODO_DAY, day);
        contentValues.put(TodoDBHelper.TODO_MONTH, month);
        contentValues.put(TodoDBHelper.TODO_YEAR, year);
        contentValues.put(TodoDBHelper.TODO_HOUR, hour);
        contentValues.put(TodoDBHelper.TODO_MINUTE, min);
        contentValues.put(TodoDBHelper.TODO_REMINDER, remind);
        contentValues.put(TodoDBHelper.TODO_BEFORE, minBefore);
        int i = database.update(TodoDBHelper.TABLE, contentValues,
                TodoDBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(TodoDBHelper.TABLE, TodoDBHelper._ID + "=" + _id, null);
    }
}