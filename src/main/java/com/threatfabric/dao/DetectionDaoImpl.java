package com.threatfabric.dao;

import com.threatfabric.entity.Detection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetectionDaoImpl implements DetectionDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DetectionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Detection save(Detection detection) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(detection);
        tx.commit();
        session.close();
        return detection;
    }
}
