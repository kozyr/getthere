package com.kozyrenko.ctacatcher.common.util;

import android.location.Location;

import com.kozyrenko.ctacatcher.common.model.Arrival;
import com.kozyrenko.ctacatcher.common.model.TrainEta;
import com.kozyrenko.ctacatcher.common.model.TrainStation;
import com.kozyrenko.ctacatcher.common.model.TrainStop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 8/17/14.
 */
public class Util {
    public static float distance(TrainStation station, double lat, double lon) {
        float [] results = new float[3];
        Location.distanceBetween(station.getLat(), station.getLon(),
                lat, lon, results);

        return results[0];
    }

    public static String stringifyArrival(Arrival arrival) {
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

        return sb.toString();
    }
}
