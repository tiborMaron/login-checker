package com.codecool.loginchecker.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Userdata {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    @Singular
    @Column(name = "platforms")
    @ElementCollection(targetClass = Platform.class)
    @Enumerated
    private List<Platform> platforms;
}
