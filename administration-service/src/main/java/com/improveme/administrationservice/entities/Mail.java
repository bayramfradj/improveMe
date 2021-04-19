package com.improveme.administrationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class Mail {
    private String sendTo;
    private String subject;
    private String message;
    private long idContact;
}
