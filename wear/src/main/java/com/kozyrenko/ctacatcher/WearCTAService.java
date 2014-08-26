package com.kozyrenko.ctacatcher;

/**
 * Created by dev on 8/18/14.
 */

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.kozyrenko.ctacatcher.common.model.TrainArrival;
import com.kozyrenko.ctacatcher.common.model.DataLayer;
import com.kozyrenko.ctacatcher.common.util.Util;

/**
 * Created by dev on 8/14/14.
 */
public class WearCTAService extends WearableListenerService {

    private GoogleApiClient googleApiClient;

    private static final String TAG = "WearCTAService";

    @Override
    public void onCreate() {
        super.onCreate();

        googleApiClient = new GoogleApiClient.Builder(this.getApplicationContext())
                .addApi(Wearable.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onPeerConnected(Node peer) {
        Log.i(TAG, "onPeerConnected: " + peer);
    }

    @Override
    public void onPeerDisconnected(Node peer) {
        Log.i(TAG, "onPeerDisconnected: " + peer);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d(TAG, "onDataChanged: " + dataEvents);
        final List<DataEvent> events = FreezableUtils.freezeIterable(dataEvents);
        dataEvents.close();
        if(!googleApiClient.isConnected()) {
            ConnectionResult connectionResult = googleApiClient
                    .blockingConnect(30, TimeUnit.SECONDS);
            if (!connectionResult.isSuccess()) {
                Log.e(TAG, "DataLayerListenerService failed to connect to GoogleApiClient.");
                return;
            }
        }

        for (DataEvent event : events) {
            if (event.getType() == DataEvent.TYPE_DELETED) {
                Log.i(TAG, event + " deleted");
            } else if (event.getType() == DataEvent.TYPE_CHANGED) {
                String path = event.getDataItem().getUri().getPath();
                Log.i(TAG, "path " + path);
                if (DataLayer.ARRIVAL_PATH.equals(path)) {
                    DataMap dataMap = DataMap.fromByteArray(event.getDataItem().getData());
                    String arrivalJson = dataMap.getString(DataLayer.ARRIVAL_INFO);
                    sendArrivalInfoToUI(arrivalJson);
                }

            }
        }
    }

    private void sendArrivalInfoToUI(String arrivalJson) {
        Gson gson = new Gson();
        TrainArrival arrival = gson.fromJson(arrivalJson, TrainArrival.class);
        Intent intent = new Intent(DataLayer.ARRIVAL_PATH);
        intent.putExtra(DataLayer.ARRIVAL_INFO, Util.stringifyArrival(arrival));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        googleApiClient.disconnect();
        super.onDestroy();
    }
}

