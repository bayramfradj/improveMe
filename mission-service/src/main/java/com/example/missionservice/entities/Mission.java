package com.example.missionservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Mission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeMission typeMission;
    private String entrepriseId;
    private String entrepriseName;
    private String coachId;
    private String coachName;
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
    private String img;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Demande> demandes;
}
