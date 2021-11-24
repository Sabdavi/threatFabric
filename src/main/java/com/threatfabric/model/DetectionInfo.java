package com.threatfabric.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "DetectionInfo")
public class DetectionInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private UUID detectionId;

    private Date detectionTime;

    private String nameOfApp;

    private String typeOfApp;
}
