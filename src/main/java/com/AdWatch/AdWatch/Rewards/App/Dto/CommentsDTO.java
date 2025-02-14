package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;

public class CommentsDTO {
    private Long id;
    private String text;
    private Long userId;
    private String userName;
    private LocalDateTime updatedAt;

    // Constructor
    public CommentsDTO(Long id, String text, Long userId, String userName, LocalDateTime updatedAt) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
