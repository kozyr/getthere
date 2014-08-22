package kozyrenko.com.ctacatcher.util;

import android.location.Location;

import kozyrenko.com.ctacatcher.model.TrainStation;
import kozyrenko.com.ctacatcher.model.TrainStop;

/**
 * Created by dev on 8/17/14.
 */
public class GeoUtil {
    public static float distance(TrainStation station, double lat, double lon) {
        float [] results = new float[3];
        Location.distanceBetween(station.getLat(), station.getLon(),
                lat, lon, results);

        return results[0];
    }
}
