package com.threatfabric.contoller;

import com.threatfabric.dto.DetectionDto;
import com.threatfabric.dto.DetectionInfo;
import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.dto.DeviceDto;
import com.threatfabric.entity.*;
import com.threatfabric.service.DetectionInfoService;
import com.threatfabric.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DetectionInfoController {

    private DetectionInfoService detectionInfoService;
    private DeviceService deviceService;
    private ModelMapper modelMapper;

    @Autowired
    public DetectionInfoController(DetectionInfoService detectionInfoService, DeviceService deviceService,
                                   ModelMapper modelMapper) {
        this.detectionInfoService = detectionInfoService;
        this.deviceService = deviceService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/detectionInfo")
    public ResponseEntity<DetectionInfo> saveDetectionInfo(@RequestBody DetectionInfo detectionInfo) {

        Device device = convertToDeviceEntity(detectionInfo.getDevice());
        List<Detection> detections = detectionInfo.getDetections()
                .stream()
                .map(this::convertToDetectionEntity)
                .collect(Collectors.toList());
        detectionInfoService.saveDetectionInfo(device, detections);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

  /*  @GetMapping("/detectionInfo")
    public ResponseEntity<List<DeviceDto>> getDetectionInfos(
            @RequestParam(required = false) DeviceType deviceType,
            @RequestParam(required = false) String osVersion,
            @RequestParam(required = false) DetectionType detectionType,
            @RequestParam(required = false) Date fromTime,
            @RequestParam(required = false) Date toTime,
            @RequestParam(required = false) AppType appType){
        DetectionSearchCriteria searchCriteria = createSearchCriteria(deviceType, osVersion,
                detectionType, fromTime, toTime, appType);
        List<Device> devices = deviceService.getDevices(searchCriteria);
        List<DeviceDto> deviceDtos = devices
                .stream()
                .map(this::convertToDeviceDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(deviceDtos, HttpStatus.ACCEPTED);
    }*/

    private Device convertToDeviceEntity(DeviceDto deviceDto) {
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }

    private Detection convertToDetectionEntity(DetectionDto detectionDto) {
        Detection detection = modelMapper.map(detectionDto, Detection.class);
        return detection;
    }

    private DeviceDto convertToDeviceDto(Device device){
        DeviceDto deviceDto = modelMapper.map(device,DeviceDto.class);
        return deviceDto;
    }

    private DetectionSearchCriteria createSearchCriteria(DeviceType deviceType,
                                                         String osVersion,
                                                         DetectionType detectionType,
                                                         Date from,
                                                         Date to,
                                                         AppType appType){

        DetectionSearchCriteria detectionSearchCriteria = new DetectionSearchCriteria();
        Optional.ofNullable(deviceType).ifPresent(detectionSearchCriteria::setDeviceType);
        Optional.ofNullable(osVersion).ifPresent(detectionSearchCriteria::setOsVersion);
        Optional.ofNullable(detectionType).ifPresent(detectionSearchCriteria::setDetectionType);
        Optional.ofNullable(from).ifPresent(detectionSearchCriteria::setFromTime);
        Optional.ofNullable(to).ifPresent(detectionSearchCriteria::setToTime);
        Optional.ofNullable(appType).ifPresent(detectionSearchCriteria::setAppType);

        return detectionSearchCriteria;

    }
}
