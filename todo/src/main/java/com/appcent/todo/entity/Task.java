package com.appcent.todo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String task;

    private LocalDateTime updatedDateTime;
    private LocalDateTime creationDateTime;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private boolean isDone;

    @ManyToOne
    private User user;

}
