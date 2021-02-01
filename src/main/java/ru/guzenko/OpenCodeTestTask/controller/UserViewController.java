package ru.guzenko.OpenCodeTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.guzenko.OpenCodeTestTask.entity.Answer;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;
import ru.guzenko.OpenCodeTestTask.service.SurveyService;
import ru.guzenko.OpenCodeTestTask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class UserViewController {

    private final SurveyService surveyService;
    private final UserService userService;

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return surveyService.findAll();
    }


    @GetMapping("/surveys/{id}")
    public Survey getSurveyById(@PathVariable("id") Long surveyId) {
        return surveyService.findById(surveyId);
    }

    @PostMapping("/surveys/{id}")
    public void saveUserAnswer(@PathVariable("id") Long surveyId,
                               @AuthenticationPrincipal User currentUser,
                               User user) {
        Survey surveyFromDb = surveyService.findById(surveyId);
        String username = userService.loadUserByUsername(currentUser.getUsername()).getUsername();
        List<Answer> newAnswers = user.getAnswers();

        userService.updateAnswers(username, newAnswers);
        userService.addCompletedSurvey(username, surveyFromDb);
    }
}
