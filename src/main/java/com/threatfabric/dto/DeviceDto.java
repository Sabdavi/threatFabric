package com.threatfabric.dto;

//import javax.validation.constraints.NotNull;

import com.threatfabric.entity.DeviceType;

import javax.validation.constraints.NotNull;

public class DeviceDto {

    public DeviceDto() {
    }
    private Long id;
    private Long version;
    @NotNull
    private String deviceUid;
    @NotNull
    private String deviceModel;
    @NotNull
    private String osVersion;
    @NotNull
    private DeviceType deviceType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(String deviceUid) {
        this.deviceUid = deviceUid;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

}
