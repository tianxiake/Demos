package com.runningsnail.demos.activity.datastore;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-05-08.
 */
public class CustomSQLiteHelper extends SQLiteOpenHelper {

    public static final String TAG = "CustomSQLiteHelper";

    private static final int dbVersion = 2;

    private static final String dbName = "testDb";

    public static final String TABLE_NAME_PERSON = "person";
    public static final String TABLE_NAME_PERSON_COLUMN_NAME = "name";
    public static final String TABLE_NAME_PERSON_COLUMN_AGE = "age";


    public CustomSQLiteHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory, dbVersion);
    }

    public CustomSQLiteHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, dbName, factory, dbVersion, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        HiLogger.d(TAG, "onCreate");
        String sql = "create table " + TABLE_NAME_PERSON + "(id integer primary key autoincrement,"
                + TABLE_NAME_PERSON_COLUMN_NAME
                + " varchar(64)," + TABLE_NAME_PERSON_COLUMN_AGE + " integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        HiLogger.d(TAG, "onUpgrade oldVer: %s, newVer:%s", oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        HiLogger.d(TAG, "onOpen");
    }
}
