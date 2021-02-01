package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guzenko.OpenCodeTestTask.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestion_Id(Long id);
}
