package com.threatfabric.service;

import com.threatfabric.dao.DetectionDao;
import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Detection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionService {

    private DetectionDao detectionDao;

    public DetectionService(DetectionDao detectionDao) {
        this.detectionDao = detectionDao;
    }

    public List<Detection> getDetections(DetectionSearchCriteria detectionSearchCriteria){
        return detectionDao.getDetections(detectionSearchCriteria);
    }
}
