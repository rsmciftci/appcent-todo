package com.appcent.todo.dto;


import lombok.Data;



import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TaskSavingDto {


    @NotNull
    private String task;

    @Future
    @NotNull
    private LocalDate deadlineDate;

    private LocalTime deadlineTime;
    private Long userId;

}
