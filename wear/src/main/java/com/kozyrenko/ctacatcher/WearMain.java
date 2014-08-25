package com.kozyrenko.ctacatcher;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.kozyrenko.ctacatcher.common.model.DataLayer;
import com.kozyrenko.ctacatcher.common.model.TrainStationManager;

import net.danlew.android.joda.JodaTimeAndroid;

public class WearMain extends Activity {

    private static final String TAG = "WearMain";

    private TextView mTextView;

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(DataLayer.ARRIVAL_INFO);
            mTextView.setText(text);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setContentView(R.layout.wear_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter(DataLayer.ARRIVAL_PATH));

        Intent findNear = new Intent(this, NearestTrainService.class);
        startService(findNear);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
        super.onDestroy();
    }
}
