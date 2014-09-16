package com.kozyrenko.ctacatcher;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;
import com.kozyrenko.ctacatcher.common.model.DataLayer;
import com.kozyrenko.ctacatcher.common.model.TrainArrivalRequest;
import com.kozyrenko.ctacatcher.common.model.TrainLine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 8/18/14.
 */
public class NearestTrainService extends IntentService
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    private static final String TAG = "NearestTrainService";

    public NearestTrainService() {
        super(NearestTrainService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mGoogleApiClient.blockingConnect(60000, TimeUnit.MILLISECONDS);

        if (mGoogleApiClient.isConnected()) {
            Set<String> nodeIds = getNodes();
            TrainArrivalRequest request = new TrainArrivalRequest(TrainLine.BROWN);
            Gson gson = new Gson();
            for (String nodeId : nodeIds) {
                Log.i(TAG, "Sending to " + nodeId);

                MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                        mGoogleApiClient,
                        nodeId,
                        DataLayer.ARRIVAL_REQUEST,
                        gson.toJson(request).getBytes()).await();
                if (!result.getStatus().isSuccess()) {
                    Log.e(TAG, "ERROR: failed to send Message: " + result.getStatus());
                }
            }
        } else {
            Log.e(TAG, "Google API not connected");
        }
        mGoogleApiClient.disconnect();
    }

    private Set<String> getNodes() {
        Set<String> results= new HashSet<String>();
        NodeApi.GetConnectedNodesResult nodes =
                Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
        for (Node node : nodes.getNodes()) {
            results.add(node.getId());
        }
        return results;
    }

    @Override
    public void onConnected(Bundle connectionHint) {

    }

    @Override
    public void onConnectionSuspended(int cause) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
    }

}
