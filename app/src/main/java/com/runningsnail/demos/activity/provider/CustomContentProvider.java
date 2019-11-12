package com.runningsnail.demos.activity.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.runningsnail.demos.activity.datastore.CustomSQLiteHelper;

/**
 * @author yongjie created on 2019-05-07.
 */
public class CustomContentProvider extends ContentProvider {
    CustomSQLiteHelper customSQLiteHelper;
    @Override
    public boolean onCreate() {
        customSQLiteHelper = new CustomSQLiteHelper(getContext(), null);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase writableDatabase = customSQLiteHelper.getWritableDatabase();
        Cursor query = writableDatabase.query(CustomSQLiteHelper.TABLE_NAME_PERSON, null, null, null, null, null, null);
        return query;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
