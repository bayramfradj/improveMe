package com.improveme.evaluation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Response implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
    private String userId;
    private String UserName;
    private long groupeId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;
    private boolean isValidated;
    @Lob
    private String comment;
    private double note;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;


}
