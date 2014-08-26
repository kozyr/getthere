package com.kozyrenko.ctacatcher.common.model;

/**
 * Created by dev on 8/25/14.
 */
public class TrainArrivalRequest {
    private TrainLine line;
    private String destination;

    public TrainArrivalRequest(TrainLine line) {
        this.line = line;
    }

    public TrainLine getLine() {
        return line;
    }

    public void setLine(TrainLine line) {
        this.line = line;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
