package com.appcent.todo.dto;


import lombok.Data;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TaskUpdatingDto {


    @NotNull
    private Long id;

    @NotNull
    private String task;
    private boolean isDone;
    private LocalDateTime creationDateTime;

    @NotNull
    private LocalDate deadlineDate;

    private LocalTime deadlineTime;

    @NotNull
    private Long userId;
}
