package kozyrenko.com.ctacatcher.service;

import android.util.Log;

import kozyrenko.com.ctacatcher.model.Arrival;
import kozyrenko.com.ctacatcher.model.Route;
import kozyrenko.com.ctacatcher.model.TrainStop;
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
        Route getRoute(@Query("key") String key, @Query("rt") String route);
    }

    private interface CTAArrivalService {
        @GET("/ttarrivals.aspx")
        Arrival getStopArrival(@Query("key") String key, @Query("stpid") String stopId);
    }

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setLog(new AndroidLog(TAG))
            .setConverter(new SimpleXMLConverter())
            .setEndpoint(CTA_URL)
            .build();
    private static final CTAPositionService POSITION_SERVICE = REST_ADAPTER.create(CTAPositionService.class);
    private static final CTAArrivalService ARRIVAL_SERVICE = REST_ADAPTER.create(CTAArrivalService.class);

    public static Observable<Route> getRoute(final String route) {
        return Observable.create(new Observable.OnSubscribe<Route>() {
            @Override
            public void call(Subscriber<? super Route> subscriber) {
                try {
                    subscriber.onNext(POSITION_SERVICE.getRoute(CTA_KEY, route));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<Arrival> getStopArrival(final TrainStop stop) {
        return Observable.create(new Observable.OnSubscribe<Arrival>() {
            @Override
            public void call(Subscriber<? super Arrival> subscriber) {
                try {
                    subscriber.onNext(ARRIVAL_SERVICE.getStopArrival(CTA_KEY, stop.getStopId()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
