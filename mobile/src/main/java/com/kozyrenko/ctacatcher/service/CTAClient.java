package com.kozyrenko.ctacatcher.service;

import com.kozyrenko.ctacatcher.common.model.TrainArrival;
import com.kozyrenko.ctacatcher.common.model.TrainRoute;
import com.kozyrenko.ctacatcher.common.model.TrainStation;
import com.kozyrenko.ctacatcher.common.model.TrainStop;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.SimpleXMLConverter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by dev on 8/15/14.
 */
public class CTAClient {
    private static final String CTA_KEY = "c1b69977503145a8a285af3ce1012c72";
    private static final String CTA_URL = "http://lapi.transitchicago.com/api/1.0";

    private static final String TAG = "CTAClient";

    private interface CTAPositionService {
        @GET("/ttpositions.aspx")
        TrainRoute getRoute(@Query("key") String key, @Query("rt") String route);
    }

    private interface CTAArrivalService {
        @GET("/ttarrivals.aspx")
        TrainArrival getStopArrival(@Query("key") String key, @Query("stpid") String stopId);

        @GET("/ttarrivals.aspx")
        TrainArrival getStationArrival(@Query("key") String key, @Query("mapid") String stationId);
    }

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setLog(new AndroidLog(TAG))
            .setConverter(new SimpleXMLConverter())
            .setEndpoint(CTA_URL)
            .build();
    private static final CTAPositionService POSITION_SERVICE = REST_ADAPTER.create(CTAPositionService.class);
    private static final CTAArrivalService ARRIVAL_SERVICE = REST_ADAPTER.create(CTAArrivalService.class);

    public static Observable<TrainRoute> getRoute(final String route) {
        return Observable.create(new Observable.OnSubscribe<TrainRoute>() {
            @Override
            public void call(Subscriber<? super TrainRoute> subscriber) {
                try {
                    subscriber.onNext(POSITION_SERVICE.getRoute(CTA_KEY, route));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<TrainArrival> getStopArrival(final TrainStop stop) {
        return Observable.create(new Observable.OnSubscribe<TrainArrival>() {
            @Override
            public void call(Subscriber<? super TrainArrival> subscriber) {
                try {
                    subscriber.onNext(ARRIVAL_SERVICE.getStopArrival(CTA_KEY, stop.getStopId()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<TrainArrival> getStationArrival(final TrainStation station) {
        return Observable.create(new Observable.OnSubscribe<TrainArrival>() {
            @Override
            public void call(Subscriber<? super TrainArrival> subscriber) {
                try {
                    subscriber.onNext(ARRIVAL_SERVICE.getStationArrival(CTA_KEY, station.getStationId()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
