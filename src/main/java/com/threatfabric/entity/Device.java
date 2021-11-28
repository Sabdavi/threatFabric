package com.threatfabric.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Device")
public class Device {


    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String deviceUid;

    private String deviceModel;

    private String osVersion;

    @OneToMany(mappedBy = "device")
    private List<Detection> detections = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(String deviceUid) {
        this.deviceUid = deviceUid;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public List<Detection> getDetections() {
        return detections;
    }

    public void setDetections(List<Detection> detections) {
        this.detections = detections;
    }

    public void addDetection(Detection detection) {
        detections.add(detection);
        detection.setDevice(this);
    }

    public void removeDetection(Detection detection) {
        detections.remove(detection);
        detection.setDevice(null);
    }
}
