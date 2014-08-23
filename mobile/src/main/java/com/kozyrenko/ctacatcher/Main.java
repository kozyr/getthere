package com.kozyrenko.ctacatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kozyrenko.ctacatcher.common.model.Arrival;
import com.kozyrenko.ctacatcher.common.model.TrainEta;
import com.kozyrenko.ctacatcher.service.CTAService;
import com.kozyrenko.ctacatcher.service.TrainLocator;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class Main extends Activity {

    private static final String TAG = "Main";

    private TextView arrivalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        arrivalView = (TextView) findViewById(R.id.arrival);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        // Intent intent = new Intent(this, CTAService.class);
        // startService(intent);

        TrainLocator locator = new TrainLocator(this);
        locator.getNextArrival()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Arrival>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Could not get arrivals", e);
                    }

                    @Override
                    public void onNext(Arrival arrival) {
                        showNextArrival(arrival);
                    }
                });

        super.onStart();
    }

    private void showNextArrival(Arrival arrival) {
        Log.i(TAG, "Arrival: " + arrival);
        Map<String, List<TrainEta>> grouped = new HashMap<String, List<TrainEta>>();

        StringBuilder sb = new StringBuilder();

        boolean addStation = false;

        for (TrainEta eta : arrival.getEtas()) {
            if (!addStation) {
                sb.append(eta.getStationName() + "\n");
                addStation = true;
            }
            List<TrainEta> stops = grouped.get(eta.getStopId());
            if (stops == null) {
                stops = new LinkedList<TrainEta>();
                grouped.put(eta.getStopId(), stops);
            }
            stops.add(eta);
        }

        for (String stopId: grouped.keySet()) {
            for (TrainEta eta: grouped.get(stopId)) {
                String message = eta.getLine().pretty() + " to " + eta.getDestinationName() + " in " + eta.until() + " minutes";
                sb.append(message + "\n");
            }
            sb.append("\n");
        }

        arrivalView.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, CTAService.class);
        stopService(intent);

        super.onDestroy();
    }
}
