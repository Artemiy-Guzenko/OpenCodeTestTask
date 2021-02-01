package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    List<Survey> findAllByAuthor(User user);
}
