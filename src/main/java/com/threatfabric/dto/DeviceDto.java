package com.threatfabric.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Device {

    public Device() {
    }

    private long id;

    private UUID deviceUid;

    @NotNull
    private int deviceType;

    @NotNull
    private int deviceModel;

    private String osVersion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(UUID deviceUid) {
        this.deviceUid = deviceUid;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(int deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
