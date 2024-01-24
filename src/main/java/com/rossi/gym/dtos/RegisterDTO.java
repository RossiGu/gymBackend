package com.rossi.gym.dtos;

import com.rossi.gym.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
