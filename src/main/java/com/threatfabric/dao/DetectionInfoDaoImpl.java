package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Detection;
import com.threatfabric.entity.DetectionType;
import com.threatfabric.entity.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetectionInfoDaoImpl implements DetectionInfoDao {

    private DeviceDao deviceDao;
    private DetectionDao detectionDao;
    private SessionFactory sessionFactory;

    @Autowired
    public DetectionInfoDaoImpl(SessionFactory sessionFactory, DeviceDao deviceDao, DetectionDao detectionDao) {
        this.sessionFactory = sessionFactory;
        this.deviceDao = deviceDao;
        this.detectionDao = detectionDao;
    }

    @Override
    public void saveDetectionInfo(Device device, List<Detection> detections) {

        try (Session session = sessionFactory.openSession()) {
            Device savedDevice = saveDevice(device, session);
            saveDetections(savedDevice, detections, session);
        }

    }

    @Override
    public List<Object> getDetectionInfos(DetectionSearchCriteria detectionSearchCriteria) {
        return null;
    }

    private Device saveDevice(Device device, Session session) {
        Device returnedDevice = null;
        try {
            Device existedDevice = getExistedDevice(device, session);
            if (existedDevice != null) {
                existedDevice.setOsVersion(device.getOsVersion());
                existedDevice.setDeviceModel(device.getDeviceModel());
                session.update(existedDevice);
                returnedDevice = existedDevice;
            } else {
                session.save(device);
                returnedDevice = device;
            }
            Transaction trx = session.beginTransaction();
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return returnedDevice;
    }

    private Device getExistedDevice(Device device, Session session) {
        Device existedDevice = (Device) session.createQuery("FROM Device where deviceUid =:deviceUid")
                .setParameter("deviceUid", device.getDeviceUid())
                .uniqueResult();
        return existedDevice;
    }

    private void saveDetections(Device device, List<Detection> detections, Session session) {
        Transaction trx = session.beginTransaction();
        try {
            for (Detection detection : detections) {
                if (detection.getDetectionType() == DetectionType.NEW_DETECTION) {
                    Detection existedDetection = getExistedDetection(detection, session);
                    if (existedDetection != null) {
                        existedDetection.setNameOfApp(detection.getNameOfApp());
                        existedDetection.setTypeOfApp(detection.getTypeOfApp());
                        session.update(existedDetection);
                    } else {
                        session.save(detection);
                        device.getDetections().add(detection);
                        detection.setDevice(device);
                    }
                } else {
                    session.save(detection);
                    device.getDetections().add(detection);
                    detection.setDevice(device);
                }
            }
            session.update(device);
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Detection getExistedDetection(Detection detection, Session session) {
        Detection existedDetection = (Detection) session.createQuery("FROM Detection  where detectionId =:detectionId and detectionType =:detectionType")
                .setParameter("detectionId", detection.getDetectionId())
                .setParameter("detectionType", detection.getDetectionType())
                .uniqueResult();
        return existedDetection;
    }
}
