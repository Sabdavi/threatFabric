package com.threatfabric.contoller;

import com.threatfabric.dto.DetectionDto;
import com.threatfabric.dto.DeviceDto;
import com.threatfabric.dto.MessageDto;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;
import com.threatfabric.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DetectionController {

    private MessageService messageService;
    private ModelMapper modelMapper;

    @Autowired
    public DetectionController(MessageService messageService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/detection")
    public ResponseEntity<Detection> saveDetection(@RequestBody MessageDto messageDto) {

        Device device = convertToDeviceEntity(messageDto.getDevice());
        List<Detection> detections = messageDto.getDetections()
                .stream()
                .map(this::convertToDetectionEntity)
                .collect(Collectors.toList());
        messageService.saveMessage(device, detections);

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
