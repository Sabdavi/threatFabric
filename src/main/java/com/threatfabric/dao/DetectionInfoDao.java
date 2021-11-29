package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;

import java.util.List;

public interface DetectionInfoDao {
    void saveDetectionInfo(Device device, List<Detection> detections);
    List<Object> getDetectionInfos(DetectionSearchCriteria detectionSearchCriteria);
}
