package com.dtos;

public record PasswordUpdateDto(Long userId, String oldPassword, String newPassword) {
}
