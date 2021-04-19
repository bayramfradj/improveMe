package com.improveme.administrationservice.entities;

import lombok.Data;

@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String role;
    private String img;
    private String entrepriseName;
    private Boolean enabled;
}
