package ru.guzenko.OpenCodeTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("UPDATE User u SET u.failedAttempts = ?1 WHERE u.username = ?2")
    @Modifying
    void updateFailedAttempts(int failAttempts, String username);
}
