package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guzenko.OpenCodeTestTask.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
