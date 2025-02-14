package com.AdWatch.AdWatch.Rewards.App.Dto;

public class QuestionResponseDTO {
    private Long questionId;
    private String content;
    private Long userId;

    public QuestionResponseDTO(Long questionId, String content, Long userId) {
        this.questionId = questionId;
        this.content = content;
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
}