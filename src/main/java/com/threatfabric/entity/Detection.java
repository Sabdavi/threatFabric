package com.threatfabric.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Detection",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"detectionType", "detectionId"})})
public class Detection {


    @Id
    @GeneratedValue
    private Long id;

    private String detectionId;

    @Temporal(TemporalType.DATE)
    private Date detectionTime;

    private String nameOfApp;

    private String typeOfApp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    @Enumerated(EnumType.STRING)
    private DetectionType detectionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(String detectionId) {
        this.detectionId = detectionId;
    }

    public Date getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(Date detectionTime) {
        this.detectionTime = detectionTime;
    }

    public String getNameOfApp() {
        return nameOfApp;
    }

    public void setNameOfApp(String nameOfApp) {
        this.nameOfApp = nameOfApp;
    }

    public String getTypeOfApp() {
        return typeOfApp;
    }

    public void setTypeOfApp(String typeOfApp) {
        this.typeOfApp = typeOfApp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DetectionType getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(DetectionType detectionType) {
        this.detectionType = detectionType;
    }
}
