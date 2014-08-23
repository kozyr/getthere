package com.kozyrenko.ctacatcher.common.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by dev on 8/16/14.
 */
@Root(name = "route", strict = false)
public class TrainList {
    @ElementList(inline = true)
    private List<TrainLocation> trains;

    public List<TrainLocation> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainLocation> trains) {
        this.trains = trains;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrainList{");
        sb.append("trains=").append(trains);
        sb.append('}');
        return sb.toString();
    }
}
