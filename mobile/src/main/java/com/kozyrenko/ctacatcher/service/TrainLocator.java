package com.kozyrenko.ctacatcher.service;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;

import java.util.concurrent.TimeUnit;

import com.kozyrenko.ctacatcher.common.model.TrainArrival;
import com.kozyrenko.ctacatcher.common.model.TrainArrivalRequest;
import com.kozyrenko.ctacatcher.common.model.TrainRoute;
import com.kozyrenko.ctacatcher.common.model.TrainStation;
import com.kozyrenko.ctacatcher.common.model.TrainStationManager;
import com.kozyrenko.ctacatcher.common.model.TrainStop;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dev on 8/16/14.
 */
public class TrainLocator {

    private Context context;
    private Subscription locator;

    private static final int INITIAL_LOCATION_TIMEOUT = 5000;
    private static final int LOCATION_UPDATE_INTERVAL = 1000;
    private static final int SUFFICIENT_INITIAL_ACCURACY = 200;

    private static final String TAG = TrainLocator.class.getSimpleName();

    public TrainLocator(Context context) {
        this.context = context;
    }


    public void showRoute(final String route) {
        getMyLocation().flatMap(new Func1<Location, Observable<TrainRoute>>() {
            @Override
            public Observable<TrainRoute> call(Location location) {
                return CTAClient.getRoute(route);
            }
        })
        .subscribe(new Subscriber<TrainRoute>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Could not get route", e);
            }

            @Override
            public void onNext(TrainRoute route) {
                Log.i(TAG, "Route: " + route);
            }
        });
    }

    public Observable<TrainArrival> getNextArrival(final String stopId) {
        return getMyLocation().flatMap(new Func1<Location, Observable<TrainArrival>>() {
            @Override
            public Observable<TrainArrival> call(Location location) {
                Observable<TrainArrival> result = Observable.empty();
                TrainStation nearest = getNearestStation(location);
                Log.i(TAG, "Nearest " + nearest);
                for (TrainStop stop : nearest.getStops()) {
                    if (stop.getStopId().equals(stopId)) {
                        result = CTAClient.getStopArrival(stop);
                        break;
                    }
                }
                return result;
            }
        });
    }

    public Observable<TrainArrival> getNextArrival() {
        return getMyLocation().flatMap(new Func1<Location, Observable<TrainArrival>>() {
            @Override
            public Observable<TrainArrival> call(Location location) {
                TrainStation nearest = getNearestStation(location);
                Log.i(TAG, "Nearest " + nearest);
                return CTAClient.getStationArrival(nearest);
            }
        });
    }

    public Observable<TrainArrival> getNextArrival(TrainArrivalRequest request) {
        return getMyLocation().flatMap(new Func1<Location, Observable<TrainArrival>>() {
            @Override
            public Observable<TrainArrival> call(Location location) {
                TrainStation nearest = getNearestStation(location);
                Log.i(TAG, "Nearest " + nearest);
                return CTAClient.getStationArrival(nearest);
            }
        });
    }

    private Observable<Location> getMyLocation() {
        LocationRequest req = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setExpirationDuration(INITIAL_LOCATION_TIMEOUT)
                .setInterval(LOCATION_UPDATE_INTERVAL);

        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(context);
        Observable<Location> goodEnoughQuicklyOrNothingObservable = locationProvider.getUpdatedLocation(req)
                .filter(new Func1<Location, Boolean>() {
                    @Override
                    public Boolean call(Location location) {
                        return location.getAccuracy() < SUFFICIENT_INITIAL_ACCURACY;
                    }
                })
                .timeout(INITIAL_LOCATION_TIMEOUT,
                        TimeUnit.MILLISECONDS,
                        locationProvider.getLastKnownLocation(),
                        Schedulers.io())
                .first()
                .observeOn(Schedulers.io());

        return goodEnoughQuicklyOrNothingObservable;
    }

    private TrainStation getNearestStation(Location location) {
        return TrainStationManager.getInstance().getNearestStation(
                location.getLatitude(), location.getLongitude());
    }


    public void stop() {
        if (locator != null && !locator.isUnsubscribed()) {
            locator.unsubscribe();
        }
    }
}
