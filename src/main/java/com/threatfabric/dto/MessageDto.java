package com.threatfabric.model;


import java.util.List;

public class Message {
    private Device device;
    private List<Detection> detections;

    public Message() {
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Detection> getDetections() {
        return detections;
    }

    public void setDetections(List<Detection> detections) {
        this.detections = detections;
    }
}
