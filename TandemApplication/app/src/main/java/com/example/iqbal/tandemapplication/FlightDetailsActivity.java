package com.example.iqbal.tandemapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class FlightDetailsActivity extends AppCompatActivity {

    private TextView mTVFlightNum;
    private TextView mTVYear;
    private TextView mTVSuccess;
    private TextView mTVLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        mTVFlightNum = (TextView) findViewById(R.id.tvFlightNum);
        mTVYear = (TextView) findViewById(R.id.tvLaunchYear);
        mTVSuccess = (TextView) findViewById(R.id.tvSuccess);
        mTVLinks = (TextView) findViewById(R.id.tvLinks);

        Intent intent = getIntent();
        int key = intent.getIntExtra("key", 0);
        HashMap<Integer, Data> map = (HashMap<Integer, Data>) intent.getSerializableExtra("map");
        Data data = map.get(key);

        if (data != null) {
            mTVFlightNum.setText(data.getFlight_number());
            mTVYear.setText(data.getLaunch_year());
            mTVSuccess.setText(data.getLand_success());
            mTVLinks.setText(data.getLinks());
        }

    }
}
