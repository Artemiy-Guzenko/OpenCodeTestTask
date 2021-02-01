package ru.guzenko.OpenCodeTestTask.controller.constructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.guzenko.OpenCodeTestTask.entity.Question;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;
import ru.guzenko.OpenCodeTestTask.service.QuestionService;
import ru.guzenko.OpenCodeTestTask.service.SurveyService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/constructor/question")
public class QuestionConstructorController {

    private final QuestionService questionService;
    private final SurveyService surveyService;



    @GetMapping("/all/{id}")
    public List<Question> getAllQuestionsOfSurvey(@RequestParam("id") Long surveyId) {
        return questionService.findAllBySurveyId(surveyId);
    }

    @GetMapping("/{id}")
    public Question getQuestion(@RequestParam("id") Long questionId) {
        return questionService.findByQuestionId(questionId);
    }

    @PostMapping("/new/{id}")
    public void addNewQuestion(@RequestParam("id") Long surveyId, Question question) {
        Survey currentSurvey = surveyService.findById(surveyId);
        question.setSurvey(currentSurvey);
        questionService.addNewQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long questionId) {
        questionService.deleteById(questionId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public void editSurvey(@PathVariable("id") Long questionId, Question question) {
        questionService.editQuestion(questionId, question);
    }
}
