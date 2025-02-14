package com.AdWatch.AdWatch.Rewards.App.Dto;


public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private boolean isBlocked;

    public UserResponseDTO(Long userId, String username, String email, boolean isBlocked) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.isBlocked = isBlocked;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}

