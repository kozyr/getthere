package kozyrenko.com.ctacatcher.model;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dev on 8/17/14.
 */
@Root(name="eta", strict = false)
public class TrainEta {
    @Element(name = "staId")
    private String stationId;
    @Element(name = "stpId")
    private String stopId;
    @Element(name = "staNm")
    private String stationName;
    @Element(name = "stpDe")
    private String stopDesc;
    @Element(name = "rn")
    private String runId;
    @Element(name = "rt")
    private String route;
    @Element(name = "destSt")
    private String destinationStationId;
    @Element(name = "destNm")
    private String destinationName;
    @Element(name = "prdt")
    private String predictedTimeStr;
    @Element(name = "arrT")
    private String arrivalTimeStr;
    @Element(name = "lat", required = false)
    private double lat;
    @Element(name = "lon", required = false)
    private double lon;
    @Element(name = "heading", required = false)
    private String heading;

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getArrivalTimeStr() {
        return arrivalTimeStr;
    }

    public void setArrivalTimeStr(String arrivalTimeStr) {
        this.arrivalTimeStr = arrivalTimeStr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getPredictedTimeStr() {
        return predictedTimeStr;
    }

    public void setPredictedTimeStr(String predictedTimeStr) {
        this.predictedTimeStr = predictedTimeStr;
    }

    public String getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(String destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }

    public DateTime getPredictedTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");
        return fmt.parseDateTime(getPredictedTimeStr());
    }

    public DateTime getArrivalTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");
        return fmt.parseDateTime(getArrivalTimeStr());
    }

    public int until() {
        DateTime now = new DateTime();

        return Minutes.minutesBetween(now, getArrivalTime()).getMinutes();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainEta{");
        sb.append("stationId='").append(stationId).append('\'');
        sb.append(", stopId='").append(stopId).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", stopDesc='").append(stopDesc).append('\'');
        sb.append(", runId='").append(runId).append('\'');
        sb.append(", route='").append(route).append('\'');
        sb.append(", destinationStationId='").append(destinationStationId).append('\'');
        sb.append(", destinationName='").append(destinationName).append('\'');
        sb.append(", predictedTimeStr='").append(predictedTimeStr).append('\'');
        sb.append(", arrivalTimeStr='").append(arrivalTimeStr).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", heading='").append(heading).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
