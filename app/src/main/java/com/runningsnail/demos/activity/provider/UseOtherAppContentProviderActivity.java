package com.runningsnail.demos.activity.provider;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

/**
 * 获取其他程序提供的ContentProvider
 */
public class UseOtherAppContentProviderActivity extends AppCompatActivity {


    public static final String TAG = "UseOtherAppContentProviderActivity";
    public static final int READ_CONTACT_REQUEST_CODE = 10;

    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_other_app_content_provider);
        contentResolver = this.getContentResolver();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_REQUEST_CODE);
        } else {
            getContactData();
        }

        initListener();

        PendingIntent activity = PendingIntent.getActivity(this, 100, null, 0);
    }

    private void initListener() {

    }

    private void getContactData() {
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  也是一个常量,为保存该数据的类名.下同
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                HiLogger.d(TAG, "name: %s phone: %s", name, phone);
            }
            cursor.close();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_CONTACT_REQUEST_CODE:
                if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContactData();
                }else{
                    ToastUtil.showToast(this, "没有权限!");
                }
                break;
            default:
                break;
        }
    }
}
