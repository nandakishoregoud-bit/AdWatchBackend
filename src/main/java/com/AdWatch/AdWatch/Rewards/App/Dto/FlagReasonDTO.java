package com.AdWatch.AdWatch.Rewards.App.Dto;


public class FlagReasonDTO {
    private Long id;
    private String reason;
    private Long userId;
    private String userName;

    public FlagReasonDTO(Long id, String reason, Long userId, String userName) {
        this.id = id;
        this.reason = reason;
        this.userId = userId;
        this.userName = userName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
}
