package ru.guzenko.OpenCodeTestTask.entity;

import lombok.Getter;
import lombok.Setter;
import ru.guzenko.OpenCodeTestTask.entity.secutiry.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "answer")
public class Answer extends AbstractEntity {

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_answers",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


}
