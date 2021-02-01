package ru.guzenko.OpenCodeTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;
import ru.guzenko.OpenCodeTestTask.repository.SurveyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    public Survey findById(Long id) {
        return surveyRepository.findById(id).get();
    }

    public void addNewSurvey(Survey survey) {
        surveyRepository.save(survey);
    }

    public void editSurvey(Long surveyId, Survey survey) {
        Survey surveyFromDb = surveyRepository.findById(surveyId).orElseGet(Survey::new);
        if (surveyFromDb != null){
            surveyFromDb.setTitle(survey.getTitle());
        }
        surveyRepository.save(surveyFromDb);
    }

    public void delete(Long surveyId) {
        surveyRepository.deleteById(surveyId);
    }

    public List<Survey> findAllCompletedOfSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        List<User> respondentsOfSurvey = survey.getRespondents();

        List<Survey> completedSurveys = new ArrayList<>();

        respondentsOfSurvey.forEach(user -> {
            completedSurveys.addAll(user.getCompletedSurveys());
        });

        return completedSurveys;
    }

    public List<Survey> findAllCompleted() {
        List<Survey> allSurveys = surveyRepository.findAll();
        List<Survey> completedSurveys = new ArrayList<>();

        allSurveys.forEach(survey -> {
            List<User> respondentsOfSurvey = survey.getRespondents();

            respondentsOfSurvey.forEach(user -> {
                completedSurveys.addAll(user.getCompletedSurveys());
            });

        });

        return completedSurveys;
    }
}
