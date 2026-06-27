package com.dtos;

import com.enums.Profile;

public record UserRegisterDto(String name, String email, String password, Profile role) {
}
