package com.threatfabric.dao;

import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;

import java.util.List;

public interface MessageDao {
    void saveMessage(Device device, List<Detection> detections);
}
