package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Detection;

import java.util.List;

public interface DetectionDao {
    List<Detection> getDetections(DetectionSearchCriteria detectionSearchCriteria);
}
