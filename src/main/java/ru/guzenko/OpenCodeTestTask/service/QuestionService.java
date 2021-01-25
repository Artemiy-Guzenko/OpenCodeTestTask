package ru.guzenko.OpenCodeTestTask.service;

import ru.guzenko.OpenCodeTestTask.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionsBySurveyId(Long id);

    void save(Question question);


}
