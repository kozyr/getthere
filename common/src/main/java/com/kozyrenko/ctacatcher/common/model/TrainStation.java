package com.kozyrenko.ctacatcher.common.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by dev on 8/21/14.
 */
public class TrainStation {
    private String stationId;
    private String stationName;
    private String stationDesc;
    private double lat;
    private double lon;
    private final List<TrainStop> stops;
    private final Set<TrainLine> lines;

    public TrainStation(TrainStop stop) {
        this.stationId = stop.getParentStopId();
        stationName = stop.getStationName();
        stationDesc = stop.getStationDesc();
        this.lat = stop.getLat();
        this.lon = stop.getLon();
        this.lines = new HashSet<TrainLine>();
        this.lines.addAll(stop.getLines());
        stops = new LinkedList<TrainStop>();
        stops.add(stop);
    }

    public void addStop(TrainStop stop) {
        stops.add(stop);
        lines.addAll(stop.getLines());
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationDesc() {
        return stationDesc;
    }

    public void setStationDesc(String stationDesc) {
        this.stationDesc = stationDesc;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Set<TrainLine> getLines() {
        return lines;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }


    public List<TrainStop> getStops() {
        return stops;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainStation{");
        sb.append("stationId='").append(stationId).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", stationDesc='").append(stationDesc).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", stops=").append(stops);
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }
}
