package com.threatfabric.dao;

import com.threatfabric.dto.DetectionSearchCriteria;
import com.threatfabric.entity.Detection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DetectionDaoImpl implements DetectionDao {

    private SessionFactory sessionFactory;

    @Autowired
    public DetectionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Detection> getDetections(DetectionSearchCriteria detectionSearchCriteria) {
        Session session = sessionFactory.openSession();
        Query query = createQuery(detectionSearchCriteria, session);
        return query.list();
    }

    private Query createQuery(DetectionSearchCriteria detectionSearchCriteria, Session  session){
        StringBuilder hql = new StringBuilder("FROM Detection detection where 1=1");
        Map<String,Object> params = new HashMap<>();
        if(detectionSearchCriteria.getOsVersion() != null){
            hql.append(" AND ");
            hql.append("detection.device.osVersion =:osVersion");
            params.put("osVersion",detectionSearchCriteria.getOsVersion());
        }
        if(detectionSearchCriteria.getDeviceType() != null){
            hql.append(" AND ");
            hql.append("detection.device.deviceType =:deviceType");
            params.put("deviceType",detectionSearchCriteria.getDeviceType());
        }
        if(detectionSearchCriteria.getAppType() != null){
            hql.append(" AND ");
            hql.append("detection.typeOfApp =:typeOfApp");
            params.put("typeOfApp",detectionSearchCriteria.getAppType());
        }
        if(detectionSearchCriteria.getDetectionType() != null){
            hql.append(" AND ");
            hql.append("detection.detectionType =:detectionType");
            params.put("detectionType",detectionSearchCriteria.getDetectionType());
        }
        if(detectionSearchCriteria.getFromTime() != null){
            hql.append(" AND ");
            hql.append("detection.detectionTime >=:fromTime");
            params.put("fromTime",detectionSearchCriteria.getFromTime());
        }
        if(detectionSearchCriteria.getToTime() != null){
            hql.append(" AND ");
            hql.append("detection.detectionTime <=:toTime");
            params.put("toTime",detectionSearchCriteria.getToTime());
        }
        hql.append(" order by detectionTime desc");

        return session.createQuery(hql.toString()).setProperties(params);
    }
}
