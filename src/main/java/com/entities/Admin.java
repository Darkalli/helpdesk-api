package com.entities;

import com.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serial;
import java.time.LocalDate;

@Entity
public class Admin extends User{

    @Serial
    private static final long serialVersionUID = 1L;

    public Admin(String name, String email, String password, Profile role) {
        super(name, email, password, role);
        setRole(Profile.ADMIN);
    }


    public Admin() {
    }
}
