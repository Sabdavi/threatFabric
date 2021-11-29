package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Device;

import java.util.List;

public interface DeviceDao {
    Device save(Device device);
    List<Device> getDevices(DetectionSearchCriteria detectionSearchCriteria);
}
