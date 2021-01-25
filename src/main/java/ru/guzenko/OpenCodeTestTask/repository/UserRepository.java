package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
