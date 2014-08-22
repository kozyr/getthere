package kozyrenko.com.ctacatcher.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by dev on 8/16/14.
 */
@Root(name = "train", strict = false)
public class TrainLocation {
    @Element(name = "destNm")
    private String destinationId;
    @Element(name = "destSt")
    private String destination;

    @Element(name = "nextStaNm")
    private String nextStation;
    @Element(name = "nextStaId")
    private String nextStationId;
    // private long predictedTime;
    // private long arrivalTime;
    @Element(name = "lat")
    private double lat;
    @Element(name = "lon")
    private double lon;

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNextStation() {
        return nextStation;
    }

    public void setNextStation(String nextStation) {
        this.nextStation = nextStation;
    }

    public String getNextStationId() {
        return nextStationId;
    }

    public void setNextStationId(String nextStationId) {
        this.nextStationId = nextStationId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainLocation{");
        sb.append("destinationId='").append(destinationId).append('\'');
        sb.append(", destination='").append(destination).append('\'');
        sb.append(", nextStation='").append(nextStation).append('\'');
        sb.append(", nextStationId='").append(nextStationId).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append('}');
        return sb.toString();
    }
}
