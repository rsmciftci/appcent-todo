package com.appcent.todo.dataprovider;

import com.appcent.todo.dto.TaskSavingDto;
import com.appcent.todo.dto.TaskUpdatingDto;
import com.appcent.todo.entity.Task;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TaskDataProvider {
    public static TaskSavingDto getTaskSavingDto() {

        TaskSavingDto taskSavingDto = new TaskSavingDto();

        taskSavingDto.setTask("Test task 1");
        taskSavingDto.setDeadlineDate(LocalDate.of(2024,01,01));
        taskSavingDto.setDeadlineTime(LocalTime.of(12,30,30));
        taskSavingDto.setUserId(1L);

        return taskSavingDto;
    }

    public static Task getTask() {
        Task task = new Task();
        task.setTask("Test task 1");
        task.setDone(false);
        task.setCreationDateTime(LocalDateTime.now());
        task.setDeadlineDate(LocalDate.of(2023,01,01));
        task.setUser(UserDataProvider.getUser());
        task.setId(1L);

        return task;


    }

    public static TaskUpdatingDto getTaskUpdatingDto() {
        TaskUpdatingDto taskUpdatingDto = new TaskUpdatingDto();
        taskUpdatingDto.setTask("Test task 1");
        taskUpdatingDto.setDeadlineDate(LocalDate.of(2023,01,01));
        taskUpdatingDto.setDone(true);
        taskUpdatingDto.setId(1L);
        taskUpdatingDto.setCreationDateTime(LocalDateTime.of(2022,02,01,12,20));
        taskUpdatingDto.setUserId(1L);


        return  taskUpdatingDto;

    }
}
