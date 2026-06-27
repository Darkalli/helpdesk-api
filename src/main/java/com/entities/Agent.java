package com.entities;

import com.enums.Profile;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.time.LocalDate;

@Entity
public class Agent extends User{

    @Serial
    private static final long serialVersionUID = 1L;

    public Agent(String name, String email, String password, Profile role) {
        super(name, email, password, role);
        setRole(Profile.AGENT);
    }

    public Agent() {

    }
}
