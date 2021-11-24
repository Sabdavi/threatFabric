package com.threatfabric.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private UUID deviceUid;

    private int deviceType;

    private int deviceModel;

    private String osVersion;
}
