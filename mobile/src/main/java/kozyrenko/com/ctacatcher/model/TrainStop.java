package kozyrenko.com.ctacatcher.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by dev on 8/17/14.
 */
public class TrainStop {
    public enum Direction {
        N, S, W, E
    }
    private String stopId;
    private Direction direction;
    private String stopName;
    private String stationName;
    private String stationDesc;
    private String parentStopId;
    private boolean ada;
    private double lat;
    private double lon;
    private Set<TrainLine> lines;

    public TrainStop(String stopId,
                     Direction direction,
                     String stopName,
                     double lon,
                     double lat,
                     String stationName,
                     String stationDesc,
                     String parentStopId,
                     boolean ada,
                     TrainLine ... trainLines) {
        this.stopId = stopId;
        this.direction = direction;
        this.stopName = stopName;
        this.stationName = stationName;
        this.stationDesc = stationDesc;
        this.parentStopId = parentStopId;
        this.ada = ada;
        this.lat = lat;
        this.lon = lon;
        lines = new HashSet<TrainLine>();
        for (TrainLine line : trainLines) {
            lines.add(line);
        }
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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

    public String getParentStopId() {
        return parentStopId;
    }

    public void setParentStopId(String parentStopId) {
        this.parentStopId = parentStopId;
    }

    public boolean isAda() {
        return ada;
    }

    public void setAda(boolean ada) {
        this.ada = ada;
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

    public void addLine(TrainLine line) {
        lines.add(line);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainStop{");
        sb.append("stopId='").append(stopId).append('\'');
        sb.append(", direction=").append(direction);
        sb.append(", stopName='").append(stopName).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", stationDesc='").append(stationDesc).append('\'');
        sb.append(", parentStopId='").append(parentStopId).append('\'');
        sb.append(", ada=").append(ada);
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }
}
