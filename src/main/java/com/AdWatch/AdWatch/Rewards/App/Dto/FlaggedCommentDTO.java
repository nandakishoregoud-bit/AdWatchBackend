package com.AdWatch.AdWatch.Rewards.App.Dto;


import java.time.LocalDateTime;

public class FlaggedCommentDTO {
    private Long commentId;
    private String text;
    private LocalDateTime createdAt;
    private Long userId;
    private String userName;
    private String reason;

    public FlaggedCommentDTO(Long commentId, String text, LocalDateTime createdAt, Long userId, String userName, String reason) {
        this.commentId = commentId;
        this.text = text;
        this.createdAt = createdAt;
        this.userId = userId;
        this.userName = userName;
        this.reason = reason;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
