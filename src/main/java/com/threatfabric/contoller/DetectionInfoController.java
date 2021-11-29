package com.threatfabric.contoller;

import com.threatfabric.dto.DetectionDto;
import com.threatfabric.dto.DetectionInfo;
import com.threatfabric.dto.DeviceDto;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;
import com.threatfabric.service.DetectionInfoService;
import com.threatfabric.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
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
    public ResponseEntity<DetectionInfo> saveDetectionInfo(@Valid @RequestBody DetectionInfo detectionInfo) {

        Device device = convertToDeviceEntity(detectionInfo.getDevice());
        List<Detection> detections = detectionInfo.getDetections()
                .stream()
                .map(this::convertToDetectionEntity)
                .collect(Collectors.toList());
        detectionInfoService.saveDetectionInfo(device, detections);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Device convertToDeviceEntity(DeviceDto deviceDto) {
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }

    private Detection convertToDetectionEntity(DetectionDto detectionDto) {
        Detection detection = modelMapper.map(detectionDto, Detection.class);
        return detection;
    }
}
