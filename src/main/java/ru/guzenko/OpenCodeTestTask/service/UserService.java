package ru.guzenko.OpenCodeTestTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.guzenko.OpenCodeTestTask.entity.Answer;
import ru.guzenko.OpenCodeTestTask.entity.Survey;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;
import ru.guzenko.OpenCodeTestTask.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    public static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION = 60 * 1000; //1 min

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user.setPassword(user.getPassword());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonLocked(true);
        userRepository.save(user);
        return true;
    }

    public void addCompletedSurvey(String username, Survey survey) {
        User user = userRepository.findByUsername(username);
        user.getCompletedSurveys().add(survey);
        userRepository.save(user);
    }

    public void updateAnswers(String username, List<Answer> answers) {
        User user = userRepository.findByUsername(username);

        answers.stream()
                .findFirst()
                .ifPresent(answer -> user.getAnswers().removeAll(answer.getQuestion().getAnswers()));
        user.getAnswers().addAll(answers);

        userRepository.save(user);
    }

    public List<Survey> findAllCompletedSurveys(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getCompletedSurveys();
    }


    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempts() + 1;
        userRepository.updateFailedAttempts(newFailAttempts, user.getUsername());
    }

    public void resetFailedAttempts(String username) {
        userRepository.updateFailedAttempts(0, username);
    }

    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        userRepository.save(user);
    }

    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempts(0);

            userRepository.save(user);

            return true;
        }
        return false;
    }
}
