package com.threatfabric.model;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class Detection {

    public Detection() {
    }

    private long id;

    private UUID detectionId;

    @NotNull
    private Date detectionTime;

    @NotNull
    private String nameOfApp;

    @NotNull
    private String typeOfApp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(UUID detectionId) {
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
}
