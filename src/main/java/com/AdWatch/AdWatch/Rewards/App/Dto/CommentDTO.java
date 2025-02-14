package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;

public class CommentDTO {
	private Long id;
	private String text;
	private UserDTO user; // Optional: Include user details if needed
	
	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	public CommentDTO(Long id, String text, UserDTO user, LocalDateTime updatedAt) {
		this.id = id;
		this.text = text;
		this.user = user;
		this.updatedAt = updatedAt;
	}

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

	public UserDTO getUser() {
		return user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	// Getters and setters

	
}
