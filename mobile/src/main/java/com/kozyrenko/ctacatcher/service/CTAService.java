package com.kozyrenko.ctacatcher.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.location.LocationRequest;
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

import com.kozyrenko.ctacatcher.common.model.Arrival;
import com.kozyrenko.ctacatcher.common.model.TrainEta;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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
                .subscribe(new Subscriber<Arrival>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Could not get arrivals", e);
                    }

                    @Override
                    public void onNext(Arrival arrival) {
                        Log.i(TAG, "Arrivals: " + arrival);
                        sendArrivalToWear(arrival);
                    }
                });

        super.onMessageReceived(messageEvent);

    }

    private void sendArrivalToWear(Arrival arrival) {
        if(!googleApiClient.isConnected()) {
            ConnectionResult connectionResult = googleApiClient
                    .blockingConnect(30, TimeUnit.SECONDS);
            if (!connectionResult.isSuccess()) {
                Log.e(TAG, "DataLayerListenerService failed to connect to GoogleApiClient.");
                return;
            }
        }
        PutDataMapRequest dataMap = PutDataMapRequest.create("/arrival");
        for (TrainEta eta : arrival.getEtas()) {
            dataMap.getDataMap().putString("train", eta.getLine().pretty());
        }

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
        super.onDestroy();
    }
}
