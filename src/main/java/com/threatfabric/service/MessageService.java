package com.threatfabric.service;

import com.threatfabric.dao.MessageDao;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void saveMessage(Device device, List<Detection> detections) {
        messageDao.saveMessage(device, detections);
    }
}
