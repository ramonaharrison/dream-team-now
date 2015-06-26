package com.ramonaharrison.dev.dreamteamnow.db;

import android.provider.BaseColumns;

/**
 * Created by Ramona Harrison
 * on 6/25/15.
 */
public class TodoContract {
    public static final String DB_NAME = "com.example.TodoList.db.todo";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "todos";

    public class Columns {
        public static final String TODO = "todo";
        public static final String _ID = BaseColumns._ID;
    }
}
