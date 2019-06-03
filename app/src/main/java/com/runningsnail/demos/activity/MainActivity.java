package com.runningsnail.demos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.lv_content)
    ListView listView;

    private List<String> itemsData;
    private List<String> clickItemsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String name = this.getClass().getName();
        HiLogger.d(TAG,"class name:"+name);
        itemsData = getItemsData();
        clickItemsData = getClickItemsData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_main_list,
                itemsData);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String content = clickItemsData.get(position);
                HiLogger.d(TAG, "content %s", content);
                if (content.contains("activity")) {
                    startActivity(clickItemsData.get(position));
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
//        long test1 = 1556687346000L; //更早
//        long test2 = 1557724146000L; //本周
//        long test3 = 1557464946000L; //上周
//
//        int time1 = computeAreaTime(test1);
//        int time2 = computeAreaTime(test2);
//        int time3 = computeAreaTime(test3);
        long l = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        TimeZone timeZone = simpleDateFormat.getTimeZone();
        String displayName = timeZone.getDisplayName();
        String format = simpleDateFormat.format(l);
        HiLogger.d(TAG, "l" + l + ",timeFormat:" + format + ",displayName:" + displayName);

    }

    /**
     * 返回值有三个 0代表本周,1代表上周,2代表更早,-1代表未知
     *
     * @param time
     * @return
     */
    public int computeAreaTime(long time) {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTime().getTime();
        //当天零点时间
        long zoneTime = currentTime - currentTime % (24 * 60 * 60 * 1000) - 8 * 60 * 60 * 1000;
        calendar.setTime(new Date(zoneTime));
        //本周第一天时间点
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + 1);
        long thisWeekFirstDayTime = calendar.getTime().getTime();
        //上一周第一天时间点
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        long lastWeekFirstDayTime = calendar.getTime().getTime();
        if (time < lastWeekFirstDayTime) {
            return 2;
        }
        if (time >= lastWeekFirstDayTime && time < thisWeekFirstDayTime) {
            return 1;
        }
        return 0;
    }

    private void startActivity(String s) {
        try {
            Intent intent = new Intent(this, Class.forName(s));
            startActivity(intent);
            ToastUtil.showToast(this,"startActivity success");
        } catch (ClassNotFoundException e) {
            HiLogger.e(TAG, "class load error", e);
        }
    }

    public List<String> getItemsData() {
        String[] stringArray = getResources().getStringArray(R.array.item_data);
        return Arrays.asList(stringArray);
    }

    public List<String> getClickItemsData() {
        String[] stringArray = getResources().getStringArray(R.array.click_item_data);
        return Arrays.asList(stringArray);
    }
}
