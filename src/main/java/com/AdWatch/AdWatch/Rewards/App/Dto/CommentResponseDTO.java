package com.AdWatch.AdWatch.Rewards.App.Dto;


public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private Long userId;

    public CommentResponseDTO(Long commentId, String content, Long userId) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
}
