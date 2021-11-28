package com.threatfabric.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private UUID deviceUid;

    @NotNull
    private int deviceType;

    @NotNull
    private int deviceModel;

    private String osVersion;

    @OneToMany(mappedBy = "device")
    private List<Detection> detections = new ArrayList<>();
}
