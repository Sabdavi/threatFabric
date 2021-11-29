package com.threatfabric.contoller;

import com.threatfabric.dto.DetectionDto;
import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.AppType;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.DetectionType;
import com.threatfabric.entity.DeviceType;
import com.threatfabric.service.DetectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DetectionController {

    private DetectionService detectionService;
    private ModelMapper modelMapper;

    @Autowired
    public DetectionController(DetectionService detectionService, ModelMapper modelMapper) {
        this.detectionService = detectionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/detection")
    public ResponseEntity<List<DetectionDto>> getDetectionInfos(
            @RequestParam(required = false) DeviceType deviceType,
            @RequestParam(required = false) String osVersion,
            @RequestParam(required = false) DetectionType detectionType,
            @RequestParam(required = false)  @DateTimeFormat(pattern  ="yyyy-MM-dd") Date fromTime,
            @RequestParam(required = false) @DateTimeFormat(pattern  ="yyyy-MM-dd") Date toTime,
            @RequestParam(required = false) AppType appType){


        DetectionSearchCriteria searchCriteria = createSearchCriteria(deviceType, osVersion,
                detectionType, fromTime, toTime, appType);
        List<Detection> detections = detectionService.getDetections(searchCriteria);
        List<DetectionDto> detectionDtos = detections
                .stream()
                .map(this::convertToDetectionDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(detectionDtos, HttpStatus.ACCEPTED);
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

    private DetectionDto convertToDetectionDto(Detection detection) {
        DetectionDto detectionDto = modelMapper.map(detection, DetectionDto.class);
        return detectionDto;
    }
}
