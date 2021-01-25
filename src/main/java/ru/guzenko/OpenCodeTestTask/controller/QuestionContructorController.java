package ru.guzenko.OpenCodeTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.guzenko.OpenCodeTestTask.entity.Question;
import ru.guzenko.OpenCodeTestTask.service.QuestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/constructor/question")
public class QuestionContructorController {

    //private final SurveyService surveyService;

    private final QuestionService questionService;

    @GetMapping("/all")
    public List<Question> getQuestionsBySurveyId(Long id) {
        return questionService.getQuestionsBySurveyId(id);
    }


}
