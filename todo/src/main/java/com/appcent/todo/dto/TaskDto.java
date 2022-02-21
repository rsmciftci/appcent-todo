package com.appcent.todo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TaskDto {


    private Long id;
    private String task;
    private LocalDateTime updatedDateTime;
    private LocalDateTime creationDateTime;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private Long userId;
    private boolean isDone;

}
