package com.AdWatch.AdWatch.Rewards.App.Dto;


import java.time.LocalDateTime;

public class FlaggedQuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private int likes;
    private int dislikes;
    private boolean flagged;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;

    public FlaggedQuestionDTO(Long id, String title, String description, String category, int likes, int dislikes, boolean flagged, LocalDateTime createdAt, LocalDateTime updatedAt, UserDTO user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.likes = likes;
        this.dislikes = dislikes;
        this.flagged = flagged;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    // Getters and Setters
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}