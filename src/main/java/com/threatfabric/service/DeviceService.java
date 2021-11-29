package com.threatfabric.service;

import com.threatfabric.dao.DeviceDao;
import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private DeviceDao deviceDao;

    @Autowired
    public DeviceService(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public List<Device> getDevices(DetectionSearchCriteria detectionSearchCriteria){
        return deviceDao.getDevices(detectionSearchCriteria);
    }
}
