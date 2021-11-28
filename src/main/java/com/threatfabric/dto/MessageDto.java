package com.threatfabric.dto;


import java.util.List;

public class MessageDto {
    private DeviceDto deviceDto;
    private List<DetectionDto> detectionDtos;

    public MessageDto() {
    }

    public DeviceDto getDevice() {
        return deviceDto;
    }

    public void setDevice(DeviceDto deviceDto) {
        this.deviceDto = deviceDto;
    }

    public List<DetectionDto> getDetections() {
        return detectionDtos;
    }

    public void setDetections(List<DetectionDto> detectionDtos) {
        this.detectionDtos = detectionDtos;
    }
}
