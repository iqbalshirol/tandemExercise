package com.example.iqbal.tandemapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, LoadJSON.FlightListener {


    public static String URL = "http://api.spacexdata.com/v1/launches";
    private HashMap<Integer, Data> mDataMap;
    private List<HashMap<String, String>> mapList;
    private String FLIGHT_NUM = "flight_number";
    private ListView mListView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mDataMap = new HashMap<>();
        mapList = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);

        mProgressBar.setVisibility(View.VISIBLE);
        new LoadJSON(this).execute(URL);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, FlightDetailsActivity.class);
        intent.putExtra("key", i+1);
        intent.putExtra("map", mDataMap);
        startActivity(intent);
    }

    @Override
    public void onLoaded(List<Data> dataList) {
        int count = 1;
        for (Data data : dataList) {

            Log.i("Data", data.getFlight_number());
            HashMap<String, String> map = new HashMap<>();
            map.put(FLIGHT_NUM, data.getFlight_number());
            mapList.add(map);

            mDataMap.put(count, data);
            count++;
        }
        loadListView();
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void loadListView() {
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mapList, R.layout.list_item, new String[] {FLIGHT_NUM}, new int[] {R.id.flightNum});
        mListView.setAdapter(adapter);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error !!", Toast.LENGTH_LONG).show();
    }
}
