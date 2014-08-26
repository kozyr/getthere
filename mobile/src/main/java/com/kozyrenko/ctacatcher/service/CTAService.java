package com.kozyrenko.ctacatcher.service;

import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.kozyrenko.ctacatcher.common.model.TrainArrival;
import com.kozyrenko.ctacatcher.common.model.DataLayer;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by dev on 8/14/14.
 */
public class CTAService extends WearableListenerService {

    private TrainLocator trainLocator;
    private GoogleApiClient googleApiClient;

    private static final String TAG = "CTAService";

    @Override
    public void onCreate() {
        super.onCreate();

        googleApiClient = new GoogleApiClient.Builder(this.getApplicationContext())
                .addApi(Wearable.API)
                .build();
        googleApiClient.connect();
        trainLocator = new TrainLocator(this);
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
        Log.i(TAG, "Received message!");
        trainLocator.getNextArrival()
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<TrainArrival>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Could not get arrivals", e);
                    }

                    @Override
                    public void onNext(TrainArrival arrival) {
                        Log.i(TAG, "Arrivals: " + arrival);
                        sendArrivalToWear(arrival);
                    }
                });

        super.onMessageReceived(messageEvent);

    }

    private void sendArrivalToWear(TrainArrival arrival) {
        if(!googleApiClient.isConnected()) {
            ConnectionResult connectionResult = googleApiClient
                    .blockingConnect(30, TimeUnit.SECONDS);
            if (!connectionResult.isSuccess()) {
                Log.e(TAG, "DataLayerListenerService failed to connect to GoogleApiClient.");
                return;
            }
        }
        PutDataMapRequest dataMap = PutDataMapRequest.create(DataLayer.ARRIVAL_PATH);

        Gson gson = new Gson();
        dataMap.getDataMap().putString(DataLayer.ARRIVAL_INFO, gson.toJson(arrival));

        PutDataRequest request = dataMap.asPutDataRequest();
        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
                .putDataItem(googleApiClient, request);
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
        // Loop through the events and send a message back to the node that created the data item.
        for (DataEvent event : events) {
            DataItem item = event.getDataItem();
        }
    }

    @Override
    public void onDestroy() {
        googleApiClient.disconnect();
        super.onDestroy();
    }
}
