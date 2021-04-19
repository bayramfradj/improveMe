package com.example.missionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeMission typeMission;
    private String entrepriseId;
    private String coachId;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private StateMission stateMission;
    @Lob
    private String content;
    private double price;
    private int nbrplace;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Demande> demandes;
}
