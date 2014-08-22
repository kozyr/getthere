package kozyrenko.com.ctacatcher.model;

import java.util.HashSet;
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
    private TrainStop there;
    private TrainStop back;
    private Set<TrainLine> lines;

    public TrainStation(TrainStop stop) {
        this.stationId = stop.getParentStopId();
        stationName = stop.getStationName();
        stationDesc = stop.getStationDesc();
        this.lat = stop.getLat();
        this.lon = stop.getLon();
        this.lines = new HashSet<TrainLine>();
        this.lines.addAll(stop.getLines());
        there = stop;
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

    public void setLines(Set<TrainLine> lines) {
        this.lines = lines;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public TrainStop getThere() {
        return there;
    }

    public void setThere(TrainStop there) {
        this.there = there;
    }

    public TrainStop getBack() {
        return back;
    }

    public void setBack(TrainStop back) {
        this.back = back;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainStation{");
        sb.append("stationId='").append(stationId).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", stationDesc='").append(stationDesc).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", there=").append(there);
        sb.append(", back=").append(back);
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }
}
