package ru.guzenko.OpenCodeTestTask.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.guzenko.OpenCodeTestTask.entity.Question;
import ru.guzenko.OpenCodeTestTask.repository.QuestionRepository;
import ru.guzenko.OpenCodeTestTask.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestionsBySurveyId(Long id) {
        return questionRepository.getAllBySurvey_Id(id);
    }
}
