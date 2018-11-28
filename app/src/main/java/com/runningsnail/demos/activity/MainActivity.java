package com.runningsnail.demos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.runningsnail.demos.HiLog;
import com.runningsnail.demos.R;
import java.util.Arrays;
import java.util.List;

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

        itemsData = getItemsData();
        clickItemsData = getClickItemsData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                itemsData);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(clickItemsData.get(position));
            }
        });


    }

    private void startActivity(String s) {
        try {
            Intent intent = new Intent(this, Class.forName(s));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            HiLog.e(TAG, "class load error", e);
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
