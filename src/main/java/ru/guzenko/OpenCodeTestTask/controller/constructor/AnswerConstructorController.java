package ru.guzenko.OpenCodeTestTask.controller.constructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.guzenko.OpenCodeTestTask.entity.Answer;
import ru.guzenko.OpenCodeTestTask.entity.Question;
import ru.guzenko.OpenCodeTestTask.service.AnswerService;
import ru.guzenko.OpenCodeTestTask.service.QuestionService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/constructor/answer")
public class AnswerConstructorController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping("/all/{id}")
    public List<Answer> getAllAnswersOfQuestion(@PathVariable("id") Long questionId) {
        return answerService.findAllByQuestionId(questionId);
    }

    @GetMapping("{id}")
    public Answer getAnswerById(@PathVariable("id") Long answerId) {
        return answerService.findByAnswerId(answerId);
    }

    @PostMapping("/new/{id}")
    public void addNewAnswer(@PathVariable("id") Long questionId, Answer answer) {
        Question currentQuestion = questionService.findByQuestionId(questionId);
        answer.setQuestion(currentQuestion);
        answerService.addNewAnswer(answer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long answerId) {
        answerService.deleteById(answerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("edit/{id}")
    public void editAnswer(@PathVariable("id") Long answerId, Answer answer) {
        answerService.editAnswer(answerId, answer);
    }
}
