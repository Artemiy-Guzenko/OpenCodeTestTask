package ru.guzenko.OpenCodeTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.guzenko.OpenCodeTestTask.entity.Question;
import ru.guzenko.OpenCodeTestTask.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findAllBySurveyId(Long surveyId) {
        return questionRepository.findAllBySurveyId(surveyId);
    }

    public Question findByQuestionId(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    public void addNewQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteById(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public void editQuestion(Long questionId, Question question) {
        Question questionFromDb = questionRepository.findById(questionId).orElseGet(Question::new);
        if (questionFromDb != null) {
            questionFromDb.setCondition(question.getCondition());
            questionFromDb.setMultipleChoice(question.isMultipleChoice());
        }
        questionRepository.save(questionFromDb);
    }
}
