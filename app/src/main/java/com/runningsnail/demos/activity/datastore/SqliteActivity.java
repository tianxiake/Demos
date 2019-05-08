package com.runningsnail.demos.activity.datastore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.CloseUtils;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqliteActivity extends AppCompatActivity {

    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.btn_upgrade)
    Button btnUpgrade;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_query)
    Button btnQuery;

    public static final String TAG = "SqliteActivity";
    CustomSQLiteHelper customSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);

        customSQLiteHelper = new CustomSQLiteHelper(this, null);
    }


    @OnClick({R.id.btn_insert, R.id.btn_upgrade,
            R.id.btn_delete, R.id.btn_query})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_insert:
                insertData();
                break;
            case R.id.btn_delete:
                deleteData();
                break;
            case R.id.btn_upgrade:
                upgradeData();
                break;
            case R.id.btn_query:
                queryData();
                break;
            default:
                break;

        }

    }

    private void queryData() {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = customSQLiteHelper.getWritableDatabase();
            Cursor query = writableDatabase.query(CustomSQLiteHelper.TABLE_NAME_PERSON, null, null, null, null, null, null);
            while (query.moveToNext()) {
                int columnIndex = query.getColumnIndex(CustomSQLiteHelper.TABLE_NAME_PERSON_COLUMN_NAME);
                String name = query.getString(columnIndex);

                int columnIndex2 = query.getColumnIndex(CustomSQLiteHelper.TABLE_NAME_PERSON_COLUMN_AGE);
                int age = query.getInt(columnIndex2);
                HiLogger.d(TAG, "name:%s age:%s", name, age);
            }
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        } finally {
            CloseUtils.closeIO(writableDatabase);
        }
    }

    private void upgradeData() {

    }

    private void deleteData() {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = customSQLiteHelper.getWritableDatabase();
            long deleteAll = writableDatabase.delete(CustomSQLiteHelper.TABLE_NAME_PERSON, null, null);
            ToastUtil.showToast(this, "删除成功 删除数量:" + deleteAll);
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        } finally {
            CloseUtils.closeIO(writableDatabase);
        }
    }

    private void insertData() {
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = customSQLiteHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(CustomSQLiteHelper.TABLE_NAME_PERSON_COLUMN_NAME, "lyj_" + System.currentTimeMillis());
            contentValues.put(CustomSQLiteHelper.TABLE_NAME_PERSON_COLUMN_AGE, 24);
            long id = writableDatabase.insert(CustomSQLiteHelper.TABLE_NAME_PERSON, null, contentValues);
            ToastUtil.showToast(this, "插入成功 id:" + id);
        } catch (Exception e) {
            HiLogger.e(TAG, "", e);
        } finally {
            CloseUtils.closeIO(writableDatabase);
        }
    }
}
