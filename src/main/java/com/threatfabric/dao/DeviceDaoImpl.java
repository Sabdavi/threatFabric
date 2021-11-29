package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.DetectionType;
import com.threatfabric.entity.Device;
import com.threatfabric.entity.DeviceType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl implements DeviceDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DeviceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Device save(Device device) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(device);
        tx.commit();
        session.close();
        return device;
    }

    @Override
    public List<Device> getDevices(DetectionSearchCriteria detectionSearchCriteria) {
        Session session = sessionFactory.openSession();
        return createQuery(detectionSearchCriteria, session).list();
    }

    private Query createQuery(DetectionSearchCriteria detectionSearchCriteria, Session  session){
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT device FROM Device device join device.detections detections where device.deviceType =:deviceType and detections.detectionType =:detectionType");

        return session.createQuery(hql.toString())
                .setParameter("deviceType", DeviceType.WEB)
                .setParameter("detectionType", DetectionType.NEW_DETECTION);

    }
}
