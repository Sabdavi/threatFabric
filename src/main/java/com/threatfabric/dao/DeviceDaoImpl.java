package com.threatfabric.dao;

import com.threatfabric.entity.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
