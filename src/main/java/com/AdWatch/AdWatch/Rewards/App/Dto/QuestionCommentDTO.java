package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionCommentDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private UserDTO user;
   // private List<CommentDTO> comments; // Add comments list
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public QuestionCommentDTO(Long id, String title, String description, String category, UserDTO user,LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.updatedAt = updatedAt;
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	
	

    // Getters and setters
}
