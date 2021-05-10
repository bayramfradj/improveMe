package com.example.missionservice.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Enumerated(EnumType.STRING)
    private TypeDemende typeDemende;
    @Enumerated(EnumType.STRING)
    private StateCandidature stateCandidature;
    private boolean isPayed;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

}
