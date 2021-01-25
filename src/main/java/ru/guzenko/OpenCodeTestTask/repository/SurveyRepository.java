package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guzenko.OpenCodeTestTask.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
