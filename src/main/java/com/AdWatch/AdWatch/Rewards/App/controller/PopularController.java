package com.AdWatch.AdWatch.Rewards.App.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AdWatch.AdWatch.Rewards.App.Dto.DTOConverter;

import com.AdWatch.AdWatch.Rewards.App.Dto.CommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.QuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;

@RestController
@RequestMapping("/api/popular")
public class PopularController {

    private final QuestionService questionService;

    public PopularController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Top 10 Questions of the Day
    @GetMapping("/top10ofDay")
    public List<QuestionDTO> getTop10OfDay() {
        List<Question> questions = questionService.getTopQuestionsOfTheDay();

        // Convert each Question entity to QuestionDTO
        return convertToDTO(questions);
    }

    // Top 10 Questions of the Week
    @GetMapping("/top10ofWeek")
    public List<QuestionDTO> getTop10OfWeek() {
        List<Question> questions = questionService.getTopQuestionsOfTheWeek();

        // Convert each Question entity to QuestionDTO
        return convertToDTO(questions);
    }

    // Top 10 Questions of the Month
    @GetMapping("/top10ofMonth")
    public List<QuestionDTO> getTop10OfMonth() {
        List<Question> questions = questionService.getTopQuestionsOfTheMonth();

        // Convert each Question entity to QuestionDTO
        return convertToDTO(questions);
    }

    private List<QuestionDTO> convertToDTO(List<Question> questions) {
        return questions.stream()
                .map(question -> {
                    List<CommentDTO> commentDTOs = new ArrayList<>();
                    return DTOConverter.convertToQuestionDTO(question, commentDTOs);
                })
                .collect(Collectors.toList());
    }
}
