package com.threatfabric.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Detection")
public class Detection {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private UUID detectionId;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date detectionTime;

    @NotNull
    private String nameOfApp;

    @NotNull
    private String typeOfApp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;
}
