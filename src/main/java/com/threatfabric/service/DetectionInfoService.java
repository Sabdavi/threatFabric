package com.threatfabric.service;

import com.threatfabric.dao.DetectionInfoDao;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionInfoService {

    private DetectionInfoDao detectionInfoDao;

    @Autowired
    public DetectionInfoService(DetectionInfoDao detectionInfoDao) {
        this.detectionInfoDao = detectionInfoDao;
    }

    public void saveDetectionInfo(Device device, List<Detection> detections) {
        detectionInfoDao.saveDetectionInfo(device, detections);
    }

}
