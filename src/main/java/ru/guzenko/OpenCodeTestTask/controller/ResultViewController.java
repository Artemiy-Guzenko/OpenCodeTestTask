package ru.guzenko.OpenCodeTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;
import ru.guzenko.OpenCodeTestTask.service.SurveyService;
import ru.guzenko.OpenCodeTestTask.service.UserService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultViewController {

    private final UserService userService;
    private final SurveyService surveyService;

    @GetMapping("/surveys")
    public List<Survey> findAllCompletedSurveys() {
        return surveyService.findAllCompleted();
    }

    @GetMapping("/surveys/{userId}")
    public List<Survey> findAllCompletedSurveysOfUser(@PathVariable("userId") Long userId) {
        return userService.findAllCompletedSurveys(userId);
    }

    @GetMapping("/surveys/{id}")
    public List<Survey> findAllCompletedSurveysOfSurvey(@PathVariable("id") Long surveyId) {
        return surveyService.findAllCompletedOfSurvey(surveyId);
    }
}
