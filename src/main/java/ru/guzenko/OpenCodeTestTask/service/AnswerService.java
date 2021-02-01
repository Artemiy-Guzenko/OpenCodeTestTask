package ru.guzenko.OpenCodeTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.guzenko.OpenCodeTestTask.entity.Answer;
import ru.guzenko.OpenCodeTestTask.repository.AnswerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<Answer> findAllByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestion_Id(questionId);
    }

    public Answer findByAnswerId(Long answerId) {
        return answerRepository.findById(answerId).get();
    }

    public void addNewAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void editAnswer(Long answerId, Answer answer) {
        Answer answerFromDb = answerRepository.findById(answerId).orElseGet(Answer::new);
        if (answerFromDb != null) {
            answerFromDb.setContent(answer.getContent());
        }
        answerRepository.save(answerFromDb);
    }

    public void deleteById(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
